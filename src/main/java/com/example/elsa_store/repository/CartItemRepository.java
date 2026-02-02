package com.example.elsa_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elsa_store.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndProductVariantId(Long cartId, Long productVariantId);
}
