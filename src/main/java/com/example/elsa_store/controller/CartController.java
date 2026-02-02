package com.example.elsa_store.controller;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.AddToCartRequest;
import com.example.elsa_store.dto.request.UpdateCartItemRequest;
import com.example.elsa_store.dto.response.CartResponse;
import com.example.elsa_store.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ApiResponse<CartResponse> getCart(@RequestParam Long userId) {
        return cartService.getMyCart(userId);
    }

    @PostMapping("/items")
    public ApiResponse<CartResponse> addItem(@RequestParam Long userId, @RequestBody AddToCartRequest req) {
        return cartService.addItem(userId, req);
    }

    @PutMapping("/items/{cartItemId}")
    public ApiResponse<CartResponse> updateQty(
            @RequestParam Long userId, @PathVariable Long cartItemId, @RequestBody UpdateCartItemRequest req) {
        return cartService.updateItemQuantity(userId, cartItemId, req);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ApiResponse<CartResponse> removeItem(@RequestParam Long userId, @PathVariable Long cartItemId) {
        return cartService.removeItem(userId, cartItemId);
    }

    @DeleteMapping
    public ApiResponse<CartResponse> clear(@RequestParam Long userId) {
        return cartService.clearCart(userId);
    }
}
