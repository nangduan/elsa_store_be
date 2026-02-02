package com.example.elsa_store.service;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.example.elsa_store.dto.request.PaymentRequest;
import com.example.elsa_store.dto.response.PaymentResponse;
import com.example.elsa_store.dto.response.PaymentVnPayResponse;

public interface PaymentService {
    PaymentResponse create(PaymentRequest req);

    PaymentResponse update(Long id, PaymentRequest req);

    void delete(Long id);

    PaymentResponse getById(Long id);

    List<PaymentResponse> getAll();

    PaymentVnPayResponse createVnPayPayment(HttpServletRequest request);
}
