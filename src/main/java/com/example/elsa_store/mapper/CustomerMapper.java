
package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.CustomerRequest;
import com.example.elsa_store.dto.response.CustomerResponse;
import com.example.elsa_store.entity.Customer;
import com.example.elsa_store.entity.User;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest req, User user) {
        Customer c = new Customer();
        c.setUser(user);
        c.setGender(req.getGender());
        c.setDob(req.getDob());
        return c;
    }

    public static void update(Customer c, CustomerRequest req, User user) {
        c.setUser(user);
        c.setGender(req.getGender());
        c.setDob(req.getDob());
    }

    public static CustomerResponse toResponse(Customer c) {
        CustomerResponse res = new CustomerResponse();
        res.setId(c.getId());
        res.setUserId(c.getUser().getId());
        res.setGender(c.getGender());
        res.setDob(c.getDob());
        res.setFullName(c.getUser().getFullName());
        res.setEmail(c.getUser().getEmail());
        res.setPhone(c.getUser().getPhone());
        return res;
    }
}
