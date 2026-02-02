package com.example.elsa_store.service.impl;

import org.springframework.stereotype.Service;

import com.example.elsa_store.dto.request.PromotionProductRequest;
import com.example.elsa_store.dto.response.PromotionProductResponse;
import com.example.elsa_store.entity.Product;
import com.example.elsa_store.entity.Promotion;
import com.example.elsa_store.entity.PromotionProduct;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.ProductMapper;
import com.example.elsa_store.mapper.PromotionMapper;
import com.example.elsa_store.repository.ProductRepository;
import com.example.elsa_store.repository.PromotionProductRepository;
import com.example.elsa_store.repository.PromotionRepository;
import com.example.elsa_store.service.PromotionProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionProductServiceImpl implements PromotionProductService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;
    private final PromotionProductRepository promotionProductRepository;

    @Override
    public PromotionProductResponse create(PromotionProductRequest req) {
        Product product = productRepository
                .findById(req.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Promotion promotion = promotionRepository
                .findById(req.getPromotionId())
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

        PromotionProduct promotionProduct = new PromotionProduct();
        promotionProduct.setProduct(product);
        promotionProduct.setPromotion(promotion);

        promotionProductRepository.save(promotionProduct);

        PromotionProductResponse promotionProductResponse = new PromotionProductResponse();
        promotionProductResponse.setProduct(ProductMapper.toResponse(product));
        promotionProductResponse.setPromotion(PromotionMapper.toResponse(promotion));

        return promotionProductResponse;
    }
}
