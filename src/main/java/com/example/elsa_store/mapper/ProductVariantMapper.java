package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.ProductVariantRequest;
import com.example.elsa_store.dto.response.ProductVariantDetailResponse;
import com.example.elsa_store.dto.response.ProductVariantResponse;
import com.example.elsa_store.entity.Product;
import com.example.elsa_store.entity.ProductVariant;

public class ProductVariantMapper {

    public static ProductVariant toEntity(ProductVariantRequest req, Product product) {
        ProductVariant v = new ProductVariant();
        v.setProduct(product);
        v.setColor(req.getColor());
        v.setSize(req.getSize());
        v.setSku(req.getSku());
        v.setPrice(req.getPrice());
        v.setStatus(req.getStatus());
        return v;
    }

    public static void update(ProductVariant v, ProductVariantRequest req, Product product) {
        v.setProduct(product);
        v.setColor(req.getColor());
        v.setSize(req.getSize());
        v.setSku(req.getSku());
        v.setPrice(req.getPrice());
        v.setStatus(req.getStatus());
    }

    public static ProductVariantResponse toResponse(ProductVariant v) {
        ProductVariantResponse res = new ProductVariantResponse();
        res.setId(v.getId());
        res.setImageUrl(v.getImageUrl());
        res.setProductId(v.getProduct().getId());
        res.setProductName(v.getProduct().getName());
        res.setColor(v.getColor());
        res.setSize(v.getSize());
        res.setSku(v.getSku());
        res.setPrice(v.getPrice());
        res.setStatus(v.getStatus());
        return res;
    }

    public static ProductVariantDetailResponse toDetailResponse(ProductVariant v) {
        ProductVariantDetailResponse res = new ProductVariantDetailResponse();
        res.setId(v.getId());
        res.setImageUrl(v.getImageUrl());
        res.setProductId(v.getProduct().getId());
        res.setProductName(v.getProduct().getName());
        res.setColor(v.getColor());
        res.setSize(v.getSize());
        res.setSku(v.getSku());
        res.setPrice(v.getPrice());
        res.setStatus(v.getStatus());
        return res;
    }
}
