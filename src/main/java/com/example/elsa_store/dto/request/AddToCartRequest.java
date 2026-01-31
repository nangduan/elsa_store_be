package com.example.elsa_store.dto.request;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long productVariantId;
    private Integer quantity; // nếu null -> default 1
}
