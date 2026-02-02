package com.example.elsa_store.service;

import java.util.List;

import com.example.elsa_store.dto.request.CustomerRequest;
import com.example.elsa_store.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse create(CustomerRequest req);

    CustomerResponse update(Long id, CustomerRequest req);

    void delete(Long id);

    CustomerResponse getById(Long id);

    List<CustomerResponse> getAll();
}
