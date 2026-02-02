package com.example.elsa_store.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(
        name = "cart_items",
        uniqueConstraints =
                @UniqueConstraint(
                        name = "uk_cart_variant",
                        columnNames = {"cart_id", "product_variant_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // snapshot giá tại thời điểm add (tuỳ bạn; nếu muốn luôn lấy giá mới nhất thì bỏ cột này)
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "line_total", nullable = false)
    private Double lineTotal;
}
