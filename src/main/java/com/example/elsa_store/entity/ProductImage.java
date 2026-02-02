package com.example.elsa_store.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "image_url", length = 255, nullable = false)
    private String imageUrl;

    @Column(name = "is_main")
    private Boolean isMain = false;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
