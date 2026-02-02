package com.example.elsa_store.dto.response;

<<<<<<< HEAD
import com.example.elsa_store.entity.enums.Role;
=======
import com.example.elsa_store.constant.Role;
>>>>>>> upstream/develop
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    boolean authenticated;
    String accessToken;
    String refreshToken;
    Role role;
    UserResponse user;
}
