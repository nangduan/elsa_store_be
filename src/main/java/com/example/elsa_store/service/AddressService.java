package com.example.elsa_store.service;

import java.util.List;

import com.example.elsa_store.dto.request.AddressRequest;
import com.example.elsa_store.dto.response.AddressResponse;

public interface AddressService {
    AddressResponse create(AddressRequest req);

    AddressResponse update(Long id, AddressRequest req);

    void delete(Long id);

    AddressResponse getById(Long id);

    List<AddressResponse> getAllByCustomer(Long customerId);
}
