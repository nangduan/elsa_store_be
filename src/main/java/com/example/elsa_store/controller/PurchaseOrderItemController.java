
package com.example.elsa_store.controller;

import com.example.elsa_store.entity.PurchaseOrderItem;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.repository.PurchaseOrderItemRepository;
import jakarta.validation.Valid;
import com.example.elsa_store.dto.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-order-items")
public class PurchaseOrderItemController {

    private final PurchaseOrderItemRepository purchaseOrderItemRepository;

    public PurchaseOrderItemController(PurchaseOrderItemRepository purchaseOrderItemRepository) {
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
    }

    @GetMapping
    public ApiResponse<List<PurchaseOrderItem>> getAll() {
        return ApiResponse.ok(purchaseOrderItemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PurchaseOrderItem> getById(@PathVariable Long id) {
        PurchaseOrderItem item = purchaseOrderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrderItem not found"));
        return ApiResponse.ok(item);
    }

    @PostMapping
    public ApiResponse<PurchaseOrderItem> create(@Valid @RequestBody PurchaseOrderItem body) {
        body.setId(null);
        return ApiResponse.ok(purchaseOrderItemRepository.save(body));
    }

    @PutMapping("/{id}")
    public ApiResponse<PurchaseOrderItem> update(@PathVariable Long id,
                                           @Valid @RequestBody PurchaseOrderItem body) {
        PurchaseOrderItem existing = purchaseOrderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrderItem not found"));
        body.setId(existing.getId());
        return ApiResponse.ok(purchaseOrderItemRepository.save(body));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        if (!purchaseOrderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("PurchaseOrderItem not found");
        }
        purchaseOrderItemRepository.deleteById(id);
        return ApiResponse.ok(null);
    }
}
