package com.example.elsa_store.service;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.AddToCartRequest;
import com.example.elsa_store.dto.request.UpdateCartItemRequest;
import com.example.elsa_store.dto.response.CartResponse;

public interface CartService {
    ApiResponse<CartResponse> getMyCart(Long userId);
    ApiResponse<CartResponse> addItem(Long userId, AddToCartRequest req);
    ApiResponse<CartResponse> updateItemQuantity(Long userId, Long cartItemId, UpdateCartItemRequest req);
    ApiResponse<CartResponse> removeItem(Long userId, Long cartItemId);
    ApiResponse<CartResponse> clearCart(Long userId);
}
