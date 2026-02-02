package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.InventoryRequest;
import com.example.elsa_store.dto.response.InventoryResponse;
import com.example.elsa_store.service.InventoryService;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ApiResponse<InventoryResponse> create(@Valid @RequestBody InventoryRequest req) {
        return ApiResponse.ok(inventoryService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<InventoryResponse> update(@PathVariable Long id, @Valid @RequestBody InventoryRequest req) {
        return ApiResponse.ok(inventoryService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<InventoryResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(inventoryService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<InventoryResponse>> getAll() {
        return ApiResponse.ok(inventoryService.getAll());
    }
}
