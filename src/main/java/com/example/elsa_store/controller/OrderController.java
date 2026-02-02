package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.OrderRequest;
import com.example.elsa_store.dto.response.OrderResponse;
import com.example.elsa_store.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
        return ApiResponse.ok(orderService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(orderService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getAll() {
        return ApiResponse.ok(orderService.getAll());
    }

    @GetMapping("/by-user/{userId}")
    public ApiResponse<List<OrderResponse>> getAllByUserId(@PathVariable Long userId) {
        return ApiResponse.ok(orderService.getAllByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ApiResponse.ok(null);
    }
}
