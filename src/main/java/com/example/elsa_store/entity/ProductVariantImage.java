package com.example.elsa_store.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "product_variant_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantImage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    @Column(name = "image_url", length = 255, nullable = false)
    private String imageUrl;

    @Column(name = "is_main")
    private Boolean isMain = false;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
