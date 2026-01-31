
package com.example.elsa_store.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponse {

    private Long id;
    private String imageUrl;
    private String name;
    private String description;
    private Double basePrice;
    private String categoryName;
    private List<String> images;
    private List<ProductVariantResponse> productVariants;
}
