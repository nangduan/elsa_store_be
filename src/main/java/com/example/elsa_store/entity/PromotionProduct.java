package com.example.elsa_store.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "promotion_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionProduct extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
