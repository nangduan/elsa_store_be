package com.example.elsa_store.dto.request;

import com.example.elsa_store.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String username;
    String email;
    String phone;
    String fullName;
    Role role = Role.CUSTOMER;
    Boolean enabled;
}
