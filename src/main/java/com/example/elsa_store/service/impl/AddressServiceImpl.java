
package com.example.elsa_store.service.impl;

import com.example.elsa_store.dto.request.AddressRequest;
import com.example.elsa_store.dto.response.AddressResponse;
import com.example.elsa_store.entity.Address;
import com.example.elsa_store.entity.Customer;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.AddressMapper;
import com.example.elsa_store.repository.AddressRepository;
import com.example.elsa_store.repository.CustomerRepository;
import com.example.elsa_store.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressServiceImpl(AddressRepository addressRepository,
                              CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public AddressResponse create(AddressRequest req) {
        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Address a = AddressMapper.toEntity(req, customer);
        a = addressRepository.save(a);
        return AddressMapper.toResponse(a);
    }

    @Override
    public AddressResponse update(Long id, AddressRequest req) {
        Address a = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        AddressMapper.update(a, req, customer);
        return AddressMapper.toResponse(a);
    }

    @Override
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Address not found");
        }
        addressRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponse getById(Long id) {
        Address a = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        return AddressMapper.toResponse(a);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponse> getAllByCustomer(Long customerId) {
        return addressRepository.findAll().stream()
                .filter(a -> a.getCustomer().getId().equals(customerId))
                .map(AddressMapper::toResponse)
                .toList();
    }
}
