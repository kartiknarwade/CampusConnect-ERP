package com.campusconnect.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.campusconnect.entity.Role;
import com.campusconnect.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeRoles(RoleRepository roleRepository) {
        return args -> {

            if (!roleRepository.existsByRoleName("ADMIN")) {
                roleRepository.save(
                        Role.builder()
                                .roleName("ADMIN")
                                .description("System Administrator")
                                .build());
            }

            if (!roleRepository.existsByRoleName("TEACHER")) {
                roleRepository.save(
                        Role.builder()
                                .roleName("TEACHER")
                                .description("Faculty Member")
                                .build());
            }

            if (!roleRepository.existsByRoleName("STUDENT")) {
                roleRepository.save(
                        Role.builder()
                                .roleName("STUDENT")
                                .description("Student User")
                                .build());
            }
        };
    }
}
