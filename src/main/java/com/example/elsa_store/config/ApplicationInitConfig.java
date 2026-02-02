package com.example.elsa_store.config;


import com.example.elsa_store.entity.User;
<<<<<<< HEAD
import com.example.elsa_store.entity.enums.Role;
=======
import com.example.elsa_store.constant.Role;
>>>>>>> upstream/develop
import com.example.elsa_store.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static String ADMIN_USER_NAME = "admin123";

    @NonFinal
    static String ADMIN_PASSWORD = "admin123";

    /**
     * Be run every time the application start
     * @return
     */
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {

                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);
                log.warn(
                        "{ADMIN_USER_NAME} user has been create with default password: {ADMIN_PASSWORD}, please change it");
            }
        };
    }
}
