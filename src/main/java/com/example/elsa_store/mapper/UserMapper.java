package com.example.elsa_store.mapper;

import com.example.elsa_store.dto.request.UserRequest;
import com.example.elsa_store.dto.response.UserResponse;
import com.example.elsa_store.entity.User;

public class UserMapper {

    public static User toEntity(UserRequest req) {
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(req.getPassword());
        u.setEmail(req.getEmail());
        u.setPhone(req.getPhone());
        u.setFullName(req.getFullName());
        u.setRole(req.getRole());
        u.setEnabled(req.getEnabled() != null ? req.getEnabled() : Boolean.TRUE);
        return u;
    }

    public static void update(User u, UserRequest req) {
        u.setUsername(req.getUsername());
        if (req.getPassword() != null) {
            u.setPassword(req.getPassword());
        }
        u.setEmail(req.getEmail());
        u.setPhone(req.getPhone());
        u.setFullName(req.getFullName());
        if (req.getEnabled() != null) {
            u.setEnabled(req.getEnabled());
        }
    }

    public static UserResponse toResponse(User u) {
        UserResponse res = new UserResponse();
        res.setId(u.getId());
        res.setUsername(u.getUsername());
        res.setEmail(u.getEmail());
        res.setPhone(u.getPhone());
        res.setFullName(u.getFullName());
        res.setEnabled(u.isEnabled());
        return res;
    }
}
