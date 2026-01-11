
package com.example.elsa_store.service.impl;

import com.example.elsa_store.dto.request.PromotionRequest;
import com.example.elsa_store.dto.response.PromotionResponse;
import com.example.elsa_store.entity.Promotion;
import com.example.elsa_store.exception.ResourceNotFoundException;
import com.example.elsa_store.mapper.PromotionMapper;
import com.example.elsa_store.repository.PromotionRepository;
import com.example.elsa_store.service.PromotionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public PromotionResponse create(PromotionRequest req) {
        Promotion p = PromotionMapper.toEntity(req);
        p = promotionRepository.save(p);
        return PromotionMapper.toResponse(p);
    }

    @Override
    public PromotionResponse update(Long id, PromotionRequest req) {
        Promotion p = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        PromotionMapper.update(p, req);
        return PromotionMapper.toResponse(p);
    }

    @Override
    public void delete(Long id) {
        if (!promotionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Promotion not found");
        }
        promotionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PromotionResponse getById(Long id) {
        Promotion p = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        return PromotionMapper.toResponse(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionResponse> getAll() {
        return promotionRepository.findAll().stream()
                .map(PromotionMapper::toResponse)
                .toList();
    }
}
