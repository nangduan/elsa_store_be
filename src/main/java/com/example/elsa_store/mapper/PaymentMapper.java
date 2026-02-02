package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.PaymentRequest;
import com.example.elsa_store.dto.response.PaymentResponse;
import com.example.elsa_store.entity.Order;
import com.example.elsa_store.entity.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequest req, Order order) {
        Payment p = new Payment();
        p.setOrder(order);
        p.setPaymentMethod(req.getPaymentMethod());
        p.setAmount(req.getAmount());
        p.setStatus(req.getStatus());
        p.setPaidAt(req.getPaidAt());
        p.setTransactionCode(req.getTransactionCode());
        return p;
    }

    public static void update(Payment p, PaymentRequest req, Order order) {
        p.setOrder(order);
        p.setPaymentMethod(req.getPaymentMethod());
        p.setAmount(req.getAmount());
        p.setStatus(req.getStatus());
        p.setPaidAt(req.getPaidAt());
        p.setTransactionCode(req.getTransactionCode());
    }

    public static PaymentResponse toResponse(Payment p) {
        PaymentResponse res = new PaymentResponse();
        res.setId(p.getId());
        res.setOrderId(p.getOrder().getId());
        res.setPaymentMethod(p.getPaymentMethod());
        res.setAmount(p.getAmount());
        res.setStatus(p.getStatus());
        res.setPaidAt(p.getPaidAt());
        res.setTransactionCode(p.getTransactionCode());
        return res;
    }
}
