package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.InventoryRequest;
import com.example.elsa_store.dto.response.InventoryResponse;
import com.example.elsa_store.entity.Inventory;
import com.example.elsa_store.entity.ProductVariant;

public class InventoryMapper {

    public static Inventory toEntity(InventoryRequest req, ProductVariant variant) {
        Inventory i = new Inventory();
        i.setProductVariant(variant);
        i.setQuantity(req.getQuantity());
        i.setMinQuantity(req.getMinQuantity());
        return i;
    }

    public static void update(Inventory i, InventoryRequest req, ProductVariant variant) {
        i.setProductVariant(variant);
        i.setQuantity(req.getQuantity());
        i.setMinQuantity(req.getMinQuantity());
    }

    public static InventoryResponse toResponse(Inventory i) {
        InventoryResponse res = new InventoryResponse();
        res.setId(i.getId());
        res.setProductVariantId(i.getProductVariant().getId());
        res.setProductName(i.getProductVariant().getProduct().getName());
        res.setQuantity(i.getQuantity());
        res.setMinQuantity(i.getMinQuantity());
        return res;
    }
}
