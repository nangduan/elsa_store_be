package com.example.elsa_store.dto.request;

import com.example.elsa_store.constant.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String username;
    String password;
    String email;
    String phone;
    String fullName;
    Role role = Role.CUSTOMER;
    Boolean enabled;
}
