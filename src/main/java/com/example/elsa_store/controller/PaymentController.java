
package com.example.elsa_store.controller;

import com.example.elsa_store.dto.request.PaymentRequest;
import com.example.elsa_store.dto.response.PaymentResponse;
import com.example.elsa_store.service.PaymentService;
import jakarta.validation.Valid;
import com.example.elsa_store.dto.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ApiResponse<PaymentResponse> create(@Valid @RequestBody PaymentRequest req) {
        return ApiResponse.ok(paymentService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<PaymentResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody PaymentRequest req) {
        return ApiResponse.ok(paymentService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(paymentService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getAll() {
        return ApiResponse.ok(paymentService.getAll());
    }
}
