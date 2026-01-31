
package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.SupplierRequest;
import com.example.elsa_store.dto.response.SupplierResponse;
import com.example.elsa_store.entity.Supplier;

public class SupplierMapper {

    public static Supplier toEntity(SupplierRequest req) {
        Supplier s = new Supplier();
        s.setName(req.getName());
        s.setPhone(req.getPhone());
        s.setAddress(req.getAddress());
        s.setEmail(req.getEmail());
        return s;
    }

    public static void update(Supplier s, SupplierRequest req) {
        s.setName(req.getName());
        s.setPhone(req.getPhone());
        s.setAddress(req.getAddress());
        s.setEmail(req.getEmail());
    }

    public static SupplierResponse toResponse(Supplier s) {
        SupplierResponse res = new SupplierResponse();
        res.setId(s.getId());
        res.setName(s.getName());
        res.setPhone(s.getPhone());
        res.setAddress(s.getAddress());
        res.setEmail(s.getEmail());
        return res;
    }
}
