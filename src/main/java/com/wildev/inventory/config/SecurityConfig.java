package com.wildev.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // RECURSOS PÚBLICOS
                        // =========================
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // =========================
                        // LOGIN
                        // =========================
                        .requestMatchers("/login").permitAll()

                        // =========================
                        // DASHBOARD PÚBLICO
                        // =========================
                        .requestMatchers("/dashboard").permitAll()

                        // =========================
                        // PRODUCTOS
                        // =========================

                        // Consultar productos: público
                        .requestMatchers("/products").permitAll()

                        // Crear/editar/eliminar: ADMIN
                        .requestMatchers("/products/new")
                        .hasRole("ADMIN")

                        .requestMatchers("/products/edit/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/products/delete/**")
                        .hasRole("ADMIN")

                        // =========================
                        // CATEGORÍAS
                        // =========================

                        // Consultar categorías: público
                        .requestMatchers("/categories").permitAll()

                        // Crear/editar/eliminar: ADMIN
                        .requestMatchers("/categories/new")
                        .hasRole("ADMIN")

                        .requestMatchers("/categories/edit/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/categories/delete/**")
                        .hasRole("ADMIN")

                        // =========================
                        // VENTAS
                        // =========================

                        // Consultar ventas: público
                        .requestMatchers("/sales").permitAll()

                        // Ver detalle de venta: público
                        .requestMatchers("/sales/view/**")
                        .permitAll()

                        // Crear venta: USER y ADMIN
                        .requestMatchers("/sales/new")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/sales/save")
                        .hasAnyRole("USER", "ADMIN")

                        // Editar/eliminar: ADMIN
                        .requestMatchers("/sales/edit/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/sales/delete/**")
                        .hasRole("ADMIN")

                        // =========================
                        // ACCESO DENEGADO
                        // =========================
                        .requestMatchers("/access-denied")
                        .permitAll()

                        // =========================
                        // TODO LO DEMÁS
                        // =========================
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}