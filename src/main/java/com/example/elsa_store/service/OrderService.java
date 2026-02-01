
package com.example.elsa_store.service;

import com.example.elsa_store.dto.request.OrderRequest;
import com.example.elsa_store.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest request);
    OrderResponse getById(Long id);
    List<OrderResponse> getAll();
    List<OrderResponse> getAllByUser(Long userId);
    void delete(Long id);
}
