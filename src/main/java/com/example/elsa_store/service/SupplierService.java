package com.example.elsa_store.service;

import java.util.List;

import com.example.elsa_store.dto.request.SupplierRequest;
import com.example.elsa_store.dto.response.SupplierResponse;

public interface SupplierService {
    SupplierResponse create(SupplierRequest req);

    SupplierResponse update(Long id, SupplierRequest req);

    void delete(Long id);

    SupplierResponse getById(Long id);

    List<SupplierResponse> getAll();
}
