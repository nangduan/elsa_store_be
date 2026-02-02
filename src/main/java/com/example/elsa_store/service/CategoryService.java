package com.example.elsa_store.service;

import java.util.List;

import com.example.elsa_store.dto.request.CategoryRequest;
import com.example.elsa_store.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse create(CategoryRequest req);

    CategoryResponse update(Long id, CategoryRequest req);

    void delete(Long id);

    CategoryResponse getById(Long id);

    List<CategoryResponse> getAll();
}
