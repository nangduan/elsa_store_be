package com.example.elsa_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.elsa_store.entity.ProductVariantImage;

@Repository
public interface ProductVariantImageRepository extends JpaRepository<ProductVariantImage, Long> {
    List<ProductVariantImage> findAllByProductVariant_Id(Long productVariantId);
}
