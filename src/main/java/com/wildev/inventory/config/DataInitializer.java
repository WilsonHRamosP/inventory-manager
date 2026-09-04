package com.wildev.inventory.config;

import com.wildev.inventory.entity.User;
import com.wildev.inventory.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            // =========================
            // USUARIO ADMIN
            // =========================

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();

                admin.setUsername("admin");

                admin.setPassword(
                        passwordEncoder.encode("admin123")
                );

                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println(
                        "Usuario ADMIN creado correctamente."
                );
            }


            // =========================
            // USUARIO USER
            // =========================

            if (userRepository.findByUsername("user").isEmpty()) {

                User user = new User();

                user.setUsername("user");

                user.setPassword(
                        passwordEncoder.encode("user123")
                );

                user.setRole("USER");

                userRepository.save(user);

                System.out.println(
                        "Usuario USER creado correctamente."
                );
            }

        };
    }
}
