package com.example.elsa_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.elsa_store.entity.PromotionProduct;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Long> {}
