package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.SupplierRequest;
import com.example.elsa_store.dto.response.SupplierResponse;
import com.example.elsa_store.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ApiResponse<SupplierResponse> create(@Valid @RequestBody SupplierRequest req) {
        return ApiResponse.ok(supplierService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<SupplierResponse> update(@PathVariable Long id, @Valid @RequestBody SupplierRequest req) {
        return ApiResponse.ok(supplierService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<SupplierResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(supplierService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<SupplierResponse>> getAll() {
        return ApiResponse.ok(supplierService.getAll());
    }
}
