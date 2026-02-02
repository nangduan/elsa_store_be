package com.example.elsa_store.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.elsa_store.dto.request.CategoryRequest;
import com.example.elsa_store.dto.response.CategoryResponse;
import com.example.elsa_store.entity.Category;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.CategoryMapper;
import com.example.elsa_store.repository.CategoryRepository;
import com.example.elsa_store.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CategoryRequest req) {
        Category parent = null;
        if (req.getParentId() != null) {
            parent = categoryRepository
                    .findById(req.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
        }
        Category c = CategoryMapper.toEntity(req, parent);
        c = categoryRepository.save(c);
        return CategoryMapper.toResponse(c);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest req) {
        Category c =
                categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Category parent = null;
        if (req.getParentId() != null) {
            parent = categoryRepository
                    .findById(req.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
        }
        CategoryMapper.update(c, req, parent);
        return CategoryMapper.toResponse(c);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getById(Long id) {
        Category c =
                categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return CategoryMapper.toResponse(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }
}
