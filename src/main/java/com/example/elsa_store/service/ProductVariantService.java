package com.example.elsa_store.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.elsa_store.dto.request.ProductVariantRequest;
import com.example.elsa_store.dto.response.ProductVariantDetailResponse;
import com.example.elsa_store.dto.response.ProductVariantResponse;

public interface ProductVariantService {
    ProductVariantResponse create(ProductVariantRequest req);

    ProductVariantResponse update(Long id, ProductVariantRequest req);

    void delete(Long id);

    ProductVariantDetailResponse getById(Long id);

    List<ProductVariantResponse> getAll(Long productId);

    List<String> uploadImages(Long roomId, List<MultipartFile> files);
}
