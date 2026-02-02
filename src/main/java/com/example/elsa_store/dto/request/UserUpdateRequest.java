package com.example.elsa_store.dto.request;

<<<<<<< HEAD
import com.example.elsa_store.entity.enums.Role;
=======
import com.example.elsa_store.constant.Role;
>>>>>>> upstream/develop
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
