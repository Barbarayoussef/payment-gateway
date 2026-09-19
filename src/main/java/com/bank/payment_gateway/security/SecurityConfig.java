package com.bank.payment_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/payment/pay").hasAnyRole("MERCHANT", "ADMIN")

                        // Sensitive operations are restricted to Admins only
                        .requestMatchers("/api/payment/add-card", "/api/payment/transactions", "/api/payment/card/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.securityMatcher("/api/**").build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("bank_admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails merchant = User.withDefaultPasswordEncoder()
                .username("merchant_user")
                .password("merchant123")
                .roles("MERCHANT")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

}
