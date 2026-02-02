package com.example.elsa_store.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "purchase_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status")
    private Integer status; // 0: draft, 1: done, 2: canceled

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> items;
}
