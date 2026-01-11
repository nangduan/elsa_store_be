package com.example.elsa_store.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private Long id;
    private Long productVariantId;

    private String productName;
    private String color;
    private String size;
    private String sku;
    private String imageUrl;

    private Double unitPrice;
    private Integer quantity;
    private Double lineTotal;
}