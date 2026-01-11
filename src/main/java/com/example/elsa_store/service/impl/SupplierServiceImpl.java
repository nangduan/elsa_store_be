
package com.example.elsa_store.service.impl;

import com.example.elsa_store.dto.request.SupplierRequest;
import com.example.elsa_store.dto.response.SupplierResponse;
import com.example.elsa_store.entity.Supplier;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.SupplierMapper;
import com.example.elsa_store.repository.SupplierRepository;
import com.example.elsa_store.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierResponse create(SupplierRequest req) {
        Supplier s = SupplierMapper.toEntity(req);
        s = supplierRepository.save(s);
        return SupplierMapper.toResponse(s);
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest req) {
        Supplier s = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        SupplierMapper.update(s, req);
        return SupplierMapper.toResponse(s);
    }

    @Override
    public void delete(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found");
        }
        supplierRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponse getById(Long id) {
        Supplier s = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        return SupplierMapper.toResponse(s);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> getAll() {
        return supplierRepository.findAll().stream()
                .map(SupplierMapper::toResponse)
                .toList();
    }
}
