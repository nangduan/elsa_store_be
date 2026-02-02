package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.PromotionRequest;
import com.example.elsa_store.dto.response.PromotionResponse;
import com.example.elsa_store.entity.Promotion;

public class PromotionMapper {

    public static Promotion toEntity(PromotionRequest req) {
        Promotion p = new Promotion();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setType(req.getType());
        p.setValue(req.getValue());
        p.setStartDate(req.getStartDate());
        p.setEndDate(req.getEndDate());
        p.setStatus(req.getStatus());
        p.setCouponCode(req.getCouponCode());
        p.setMinOrderValue(req.getMinOrderValue());
        return p;
    }

    public static void update(Promotion p, PromotionRequest req) {
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setType(req.getType());
        p.setValue(req.getValue());
        p.setStartDate(req.getStartDate());
        p.setEndDate(req.getEndDate());
        p.setStatus(req.getStatus());
        p.setCouponCode(req.getCouponCode());
        p.setMinOrderValue(req.getMinOrderValue());
    }

    public static PromotionResponse toResponse(Promotion p) {
        PromotionResponse res = new PromotionResponse();
        res.setId(p.getId());
        res.setName(p.getName());
        res.setDescription(p.getDescription());
        res.setType(p.getType());
        res.setValue(p.getValue());
        res.setStartDate(p.getStartDate());
        res.setEndDate(p.getEndDate());
        res.setStatus(p.getStatus());
        res.setCouponCode(p.getCouponCode());
        res.setMinOrderValue(p.getMinOrderValue());
        return res;
    }
}
