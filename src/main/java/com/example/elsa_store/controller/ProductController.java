package com.example.elsa_store.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.elsa_store.dto.common.ApiResponse;
import com.example.elsa_store.dto.request.ProductRequest;
import com.example.elsa_store.dto.response.ProductDetailResponse;
import com.example.elsa_store.dto.response.ProductResponse;
import com.example.elsa_store.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ApiResponse.ok(productService.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ApiResponse.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(productService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll() {
        return ApiResponse.ok(productService.getAll());
    }

    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<List<String>> uploadImages(
            @PathVariable Long id, @RequestPart("files") List<MultipartFile> files) {
        return ApiResponse.ok(productService.uploadImages(id, files));
    }
}
