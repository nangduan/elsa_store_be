package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.PromotionProductRequest;
import com.example.elsa_store.dto.response.PromotionProductResponse;
import com.example.elsa_store.entity.PromotionProduct;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.repository.PromotionProductRepository;
import com.example.elsa_store.service.PromotionProductService;

@RestController
@RequestMapping("/promotion-products")
public class PromotionProductController {

    private final PromotionProductRepository promotionProductRepository;
    private final PromotionProductService promotionProductService;

    public PromotionProductController(
            PromotionProductRepository promotionProductRepository, PromotionProductService promotionProductService) {
        this.promotionProductRepository = promotionProductRepository;
        this.promotionProductService = promotionProductService;
    }

    @GetMapping
    public ApiResponse<List<PromotionProduct>> getAll() {
        return ApiResponse.ok(promotionProductRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PromotionProduct> getById(@PathVariable Long id) {
        PromotionProduct item = promotionProductRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PromotionProduct not found"));
        return ApiResponse.ok(item);
    }

    @PostMapping
    public ApiResponse<PromotionProductResponse> create(@Valid @RequestBody PromotionProductRequest body) {
        return ApiResponse.ok(promotionProductService.create(body));
    }

    @PutMapping("/{id}")
    public ApiResponse<PromotionProduct> update(@PathVariable Long id, @Valid @RequestBody PromotionProduct body) {
        PromotionProduct existing = promotionProductRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PromotionProduct not found"));
        body.setId(existing.getId());
        return ApiResponse.ok(promotionProductRepository.save(body));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        if (!promotionProductRepository.existsById(id)) {
            throw new ResourceNotFoundException("PromotionProduct not found");
        }
        promotionProductRepository.deleteById(id);
        return ApiResponse.ok(null);
    }
}
