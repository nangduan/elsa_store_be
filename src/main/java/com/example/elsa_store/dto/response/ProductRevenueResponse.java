package com.example.elsa_store.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRevenueResponse {
    private Long productVariantId;
    private String productName;
    private String imageUrl;
    private Long quantitySold;
    private Double revenue;
}
