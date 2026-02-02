package com.example.elsa_store.service;

import com.example.elsa_store.dto.request.PromotionProductRequest;
import com.example.elsa_store.dto.response.PromotionProductResponse;

public interface PromotionProductService {
    PromotionProductResponse create(PromotionProductRequest req);
}
