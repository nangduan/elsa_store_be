
package com.example.elsa_store.service;

import com.example.elsa_store.dto.request.PromotionProductRequest;
import com.example.elsa_store.dto.request.PromotionRequest;
import com.example.elsa_store.dto.response.PromotionProductResponse;
import com.example.elsa_store.dto.response.PromotionResponse;

import java.util.List;

public interface PromotionProductService {
    PromotionProductResponse create(PromotionProductRequest req);
}
