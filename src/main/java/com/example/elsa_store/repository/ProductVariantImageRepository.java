
package com.example.elsa_store.repository;

import com.example.elsa_store.entity.ProductImage;
import com.example.elsa_store.entity.ProductVariantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantImageRepository extends JpaRepository<ProductVariantImage, Long> {
    List<ProductVariantImage> findAllByProductVariant_Id(Long productVariantId);
}
