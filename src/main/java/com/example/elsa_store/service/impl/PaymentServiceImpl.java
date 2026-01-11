
package com.example.elsa_store.service.impl;

import com.example.elsa_store.dto.request.PaymentRequest;
import com.example.elsa_store.dto.response.PaymentResponse;
import com.example.elsa_store.entity.Order;
import com.example.elsa_store.entity.Payment;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.PaymentMapper;
import com.example.elsa_store.repository.OrderRepository;
import com.example.elsa_store.repository.PaymentRepository;
import com.example.elsa_store.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public PaymentResponse create(PaymentRequest req) {
        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Payment p = PaymentMapper.toEntity(req, order);
        p = paymentRepository.save(p);
        return PaymentMapper.toResponse(p);
    }

    @Override
    public PaymentResponse update(Long id, PaymentRequest req) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        PaymentMapper.update(p, req, order);
        return PaymentMapper.toResponse(p);
    }

    @Override
    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getById(Long id) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return PaymentMapper.toResponse(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getAll() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }
}
