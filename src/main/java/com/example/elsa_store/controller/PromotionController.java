package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.PromotionRequest;
import com.example.elsa_store.dto.response.PromotionResponse;
import com.example.elsa_store.service.PromotionService;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public ApiResponse<PromotionResponse> create(@Valid @RequestBody PromotionRequest req) {
        return ApiResponse.ok(promotionService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<PromotionResponse> update(@PathVariable Long id, @Valid @RequestBody PromotionRequest req) {
        return ApiResponse.ok(promotionService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<PromotionResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(promotionService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<PromotionResponse>> getAll() {
        return ApiResponse.ok(promotionService.getAll());
    }
}
