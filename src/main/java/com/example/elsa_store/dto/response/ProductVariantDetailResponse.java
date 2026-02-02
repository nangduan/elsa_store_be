package com.example.elsa_store.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantDetailResponse {
    private Long id;
    private String imageUrl;
    private Long productId;
    private String productName;
    private String color;
    private String size;
    private String sku;
    private Double price;
    private Integer status;
    private List<String> images = new ArrayList<>();
}
