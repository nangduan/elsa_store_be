
package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.ProductRequest;
import com.example.elsa_store.dto.response.ProductDetailResponse;
import com.example.elsa_store.dto.response.ProductResponse;
import com.example.elsa_store.entity.Category;
import com.example.elsa_store.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest request, Category category) {
        if (request == null) return null;
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .basePrice(request.getBasePrice())
                .category(category)
                .status(1)
                .build();
    }

    public static void updateEntity(Product product, ProductRequest request, Category category) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBasePrice(request.getBasePrice());
        product.setCategory(category);
    }

    public static ProductResponse toResponse(Product product) {
        if (product == null) return null;
        ProductResponse res = new ProductResponse();
        res.setId(product.getId());
        res.setImageUrl(product.getImageUrl());
        res.setName(product.getName());
        res.setDescription(product.getDescription());
        res.setBasePrice(product.getBasePrice());
        res.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : null);
        return res;
    }

    public static ProductDetailResponse toDetailResponse(Product product) {
        if (product == null) return null;
        ProductDetailResponse res = new ProductDetailResponse();
        res.setId(product.getId());
        res.setImageUrl(product.getImageUrl());
        res.setName(product.getName());
        res.setDescription(product.getDescription());
        res.setBasePrice(product.getBasePrice());
        res.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : null);
        return res;
    }
}
