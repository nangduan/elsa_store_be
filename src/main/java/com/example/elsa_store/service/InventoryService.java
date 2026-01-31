
package com.example.elsa_store.service;

import com.example.elsa_store.dto.request.InventoryRequest;
import com.example.elsa_store.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {
    InventoryResponse create(InventoryRequest req);
    InventoryResponse update(Long id, InventoryRequest req);
    void delete(Long id);
    InventoryResponse getById(Long id);
    List<InventoryResponse> getAll();
}
