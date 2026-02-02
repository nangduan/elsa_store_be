package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.CategoryRequest;
import com.example.elsa_store.dto.response.CategoryResponse;
import com.example.elsa_store.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest req, Category parent) {
        Category c = new Category();
        c.setName(req.getName());
        c.setParent(parent);
        return c;
    }

    public static void update(Category c, CategoryRequest req, Category parent) {
        c.setName(req.getName());
        c.setParent(parent);
    }

    public static CategoryResponse toResponse(Category c) {
        CategoryResponse res = new CategoryResponse();
        res.setId(c.getId());
        res.setName(c.getName());
        if (c.getParent() != null) {
            res.setParentId(c.getParent().getId());
            res.setParentName(c.getParent().getName());
        }
        return res;
    }
}
