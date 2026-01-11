
package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.AddressRequest;
import com.example.elsa_store.dto.response.AddressResponse;
import com.example.elsa_store.entity.Address;
import com.example.elsa_store.entity.Customer;

public class AddressMapper {

    public static Address toEntity(AddressRequest req, Customer customer) {
        Address a = new Address();
        a.setCustomer(customer);
        a.setFullName(req.getFullName());
        a.setPhone(req.getPhone());
        a.setProvince(req.getProvince());
        a.setDistrict(req.getDistrict());
        a.setWard(req.getWard());
        a.setDetail(req.getDetail());
        a.setIsDefault(req.getIsDefault());
        return a;
    }

    public static void update(Address a, AddressRequest req, Customer customer) {
        a.setCustomer(customer);
        a.setFullName(req.getFullName());
        a.setPhone(req.getPhone());
        a.setProvince(req.getProvince());
        a.setDistrict(req.getDistrict());
        a.setWard(req.getWard());
        a.setDetail(req.getDetail());
        a.setIsDefault(req.getIsDefault());
    }

    public static AddressResponse toResponse(Address a) {
        AddressResponse res = new AddressResponse();
        res.setId(a.getId());
        res.setCustomerId(a.getCustomer().getId());
        res.setFullName(a.getFullName());
        res.setPhone(a.getPhone());
        res.setProvince(a.getProvince());
        res.setDistrict(a.getDistrict());
        res.setWard(a.getWard());
        res.setDetail(a.getDetail());
        res.setIsDefault(a.getIsDefault());
        return res;
    }
}
