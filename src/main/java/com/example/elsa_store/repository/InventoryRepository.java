package com.example.elsa_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.elsa_store.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
