
package com.example.elsa_store.controller;

import com.example.elsa_store.entity.PurchaseOrder;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.repository.PurchaseOrderRepository;
import jakarta.validation.Valid;
import com.example.elsa_store.dto.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @GetMapping
    public ApiResponse<List<PurchaseOrder>> getAll() {
        return ApiResponse.ok(purchaseOrderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PurchaseOrder> getById(@PathVariable Long id) {
        PurchaseOrder item = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found"));
        return ApiResponse.ok(item);
    }

    @PostMapping
    public ApiResponse<PurchaseOrder> create(@Valid @RequestBody PurchaseOrder body) {
        body.setId(null);
        return ApiResponse.ok(purchaseOrderRepository.save(body));
    }

    @PutMapping("/{id}")
    public ApiResponse<PurchaseOrder> update(@PathVariable Long id,
                                           @Valid @RequestBody PurchaseOrder body) {
        PurchaseOrder existing = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found"));
        body.setId(existing.getId());
        return ApiResponse.ok(purchaseOrderRepository.save(body));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        if (!purchaseOrderRepository.existsById(id)) {
            throw new ResourceNotFoundException("PurchaseOrder not found");
        }
        purchaseOrderRepository.deleteById(id);
        return ApiResponse.ok(null);
    }
}
