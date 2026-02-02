package com.example.elsa_store.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.elsa_store.dto.request.ProductRequest;
import com.example.elsa_store.dto.response.ProductDetailResponse;
import com.example.elsa_store.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

    ProductDetailResponse getById(Long id);

    List<String> uploadImages(Long roomId, List<MultipartFile> files);

    List<ProductResponse> getAll();
}
