package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.CustomerRequest;
import com.example.elsa_store.dto.response.CustomerResponse;
import com.example.elsa_store.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ApiResponse<CustomerResponse> create(@Valid @RequestBody CustomerRequest req) {
        return ApiResponse.ok(customerService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<CustomerResponse> update(@PathVariable Long id, @Valid @RequestBody CustomerRequest req) {
        return ApiResponse.ok(customerService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(customerService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<CustomerResponse>> getAll() {
        return ApiResponse.ok(customerService.getAll());
    }
}
