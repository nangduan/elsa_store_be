package com.example.elsa_store.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "transaction_code", length = 100)
    private String transactionCode;
}
