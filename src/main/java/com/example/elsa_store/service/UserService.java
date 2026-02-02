package com.example.elsa_store.service;

import java.util.List;

import com.example.elsa_store.dto.request.UserRequest;
import com.example.elsa_store.dto.response.UserResponse;

public interface UserService {
    UserResponse create(UserRequest req);

    UserResponse update(Long id, UserRequest req);

    void delete(Long id);

    UserResponse getById(Long id);

    List<UserResponse> getAll();
}
