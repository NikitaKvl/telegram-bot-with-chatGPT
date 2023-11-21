package com.example.telegrambotwithchatgpt.config;

import com.example.telegrambotwithchatgpt.dto.request.UserRequestDto;
import com.example.telegrambotwithchatgpt.service.AuthenticationService;
import com.example.telegrambotwithchatgpt.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers(
                    "/admin/**", "/register/**").hasAnyAuthority("ADMIN");
            authorizationManagerRequestMatcherRegistry.anyRequest().permitAll();
        });

        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @PostConstruct
    public void createDefaultUser() {
        try {
            roleService.createDefaultRoles();
            UserRequestDto defaultUser = new UserRequestDto();
            defaultUser.setUsername("admin");
            defaultUser.setPassword("password");
            defaultUser.setRole("ADMIN");
            authenticationService.register(defaultUser);
        } catch (Exception e) {
            throw new RuntimeException("Can't create default user", e);
        }
    }

}
