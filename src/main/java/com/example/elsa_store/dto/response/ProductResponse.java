package com.example.elsa_store.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String imageUrl;
    private String name;
    private String description;
    private Double basePrice;
    private String categoryName;
}
