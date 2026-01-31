
package com.example.elsa_store.service;

import com.example.elsa_store.dto.request.CategoryRequest;
import com.example.elsa_store.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest req);
    CategoryResponse update(Long id, CategoryRequest req);
    void delete(Long id);
    CategoryResponse getById(Long id);
    List<CategoryResponse> getAll();
}
