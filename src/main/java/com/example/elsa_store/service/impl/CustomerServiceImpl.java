
package com.example.elsa_store.service.impl;

import com.example.elsa_store.dto.request.CustomerRequest;
import com.example.elsa_store.dto.response.CustomerResponse;
import com.example.elsa_store.entity.Customer;
import com.example.elsa_store.entity.User;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.CustomerMapper;
import com.example.elsa_store.repository.CustomerRepository;
import com.example.elsa_store.repository.UserRepository;
import com.example.elsa_store.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CustomerResponse create(CustomerRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Customer c = CustomerMapper.toEntity(req, user);
        c = customerRepository.save(c);
        return CustomerMapper.toResponse(c);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest req) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        CustomerMapper.update(c, req, user);
        return CustomerMapper.toResponse(c);
    }

    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return CustomerMapper.toResponse(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }
}
