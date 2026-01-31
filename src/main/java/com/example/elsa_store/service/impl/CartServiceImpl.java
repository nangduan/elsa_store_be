package com.example.elsa_store.service.impl;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.AddToCartRequest;
import com.example.elsa_store.dto.request.UpdateCartItemRequest;
import com.example.elsa_store.dto.response.CartItemResponse;
import com.example.elsa_store.dto.response.CartResponse;
import com.example.elsa_store.entity.*;
import com.example.elsa_store.repository.*;
import com.example.elsa_store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<CartResponse> getMyCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        recalculateCart(cart);
        return toResponse(cart);
    }

    @Override
    public ApiResponse<CartResponse> addItem(Long customerId, AddToCartRequest req) {
        int addQty = (req.getQuantity() == null || req.getQuantity() <= 0) ? 1 : req.getQuantity();

        Cart cart = getOrCreateCart(customerId);

        ProductVariant variant = productVariantRepository.findById(req.getProductVariantId())
                .orElseThrow(() -> new IllegalArgumentException("ProductVariant không tồn tại"));

        CartItem item = cartItemRepository
                .findByCartIdAndProductVariantId(cart.getId(), variant.getId())
                .orElse(null);

        double unitPrice = (variant.getPrice() != null) ? variant.getPrice() : 0.0;

        if (item == null) {
            item = CartItem.builder()
                    .cart(cart)
                    .productVariant(variant)
                    .quantity(addQty)
                    .unitPrice(unitPrice)
                    .lineTotal(unitPrice * addQty)
                    .build();
            cart.getItems().add(item);
        } else {
            int newQty = item.getQuantity() + addQty;
            item.setQuantity(newQty);

            // Nếu muốn snapshot giá tại lần đầu add, giữ nguyên unitPrice
            // Nếu muốn lấy giá mới nhất mỗi lần add, uncomment:
            // item.setUnitPrice(unitPrice);

            item.setLineTotal(item.getUnitPrice() * newQty);
        }

        cartRepository.save(cart);
        recalculateCart(cart);
        return toResponse(cart);
    }

    @Override
    public ApiResponse<CartResponse> updateItemQuantity(Long customerId, Long cartItemId, UpdateCartItemRequest req) {
        if (req.getQuantity() == null) {
            throw new IllegalArgumentException("quantity không được null");
        }

        Cart cart = getOrCreateCart(customerId);

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem không tồn tại"));

        // bảo vệ: item có thuộc cart của user không?
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new IllegalArgumentException("Không có quyền sửa item này");
        }

        if (req.getQuantity() <= 0) {
            // quantity <= 0 => remove
            cart.getItems().removeIf(i -> i.getId().equals(item.getId()));
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(req.getQuantity());
            item.setLineTotal(item.getUnitPrice() * req.getQuantity());
        }

        recalculateCart(cart);
        return toResponse(cart);
    }

    @Override
    public ApiResponse<CartResponse> removeItem(Long customerId, Long cartItemId) {
        Cart cart = getOrCreateCart(customerId);

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem không tồn tại"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new IllegalArgumentException("Không có quyền xoá item này");
        }

        cart.getItems().removeIf(i -> i.getId().equals(item.getId()));
        cartItemRepository.delete(item);

        recalculateCart(cart);
        return toResponse(cart);
    }

    @Override
    public ApiResponse<CartResponse> clearCart(Long customerId) {
        Cart cart = getOrCreateCart(customerId);

        // orphanRemoval=true => chỉ cần clear list là đủ nếu JPA đang manage
        cart.getItems().clear();
        cart.setTotalAmount(0.0);

        cartRepository.save(cart);
        return toResponse(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId).orElseThrow();

                    Cart cart = Cart.builder()
                            .user(user)
                            .totalAmount(0.0)
                            .build();

                    return cartRepository.save(cart);
                });
    }

    private void recalculateCart(Cart cart) {
        double total = 0.0;
        if (cart.getItems() != null) {
            for (CartItem i : cart.getItems()) {
                if (i.getUnitPrice() == null) i.setUnitPrice(0.0);
                if (i.getQuantity() == null) i.setQuantity(0);
                i.setLineTotal(i.getUnitPrice() * i.getQuantity());
                total += i.getLineTotal();
            }
        }
        cart.setTotalAmount(total);
    }

    private ApiResponse<CartResponse> toResponse(Cart cart) {
        CartResponse cartResponse = CartResponse.builder()
                .cartId(cart.getId())
                .userId(cart.getUser().getId())
                .items(cart.getItems().stream().map(this::toItemResponse).toList())
                .totalAmount(cart.getTotalAmount() == null ? 0.0 : cart.getTotalAmount())
                .build();
        return ApiResponse.<CartResponse>builder()
                .data(cartResponse)
                .build();
    }

    private CartItemResponse toItemResponse(CartItem item) {
        ProductVariant v = item.getProductVariant();
        String productName = (v != null && v.getProduct() != null) ? v.getProduct().getName() : null;

        return CartItemResponse.builder()
                .id(item.getId())
                .productVariantId(v != null ? v.getId() : null)
                .productName(productName)
                .color(v != null ? v.getColor() : null)
                .size(v != null ? v.getSize() : null)
                .sku(v != null ? v.getSku() : null)
                .imageUrl(v != null ? v.getImageUrl() : null)
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .lineTotal(item.getLineTotal())
                .build();
    }
}
