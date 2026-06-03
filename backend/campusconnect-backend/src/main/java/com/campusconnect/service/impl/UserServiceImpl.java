package com.campusconnect.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.CreateUserRequest;
import com.campusconnect.dto.UpdateUserRequest;
import com.campusconnect.dto.UserResponse;
import com.campusconnect.entity.Role;
import com.campusconnect.entity.User;
import com.campusconnect.repository.RoleRepository;
import com.campusconnect.repository.UserRepository;
import com.campusconnect.service.UserService;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

    	if (userRepository.existsByEmail(request.getEmail())) {
    	    throw new DuplicateResourceException(
    	            "User already exists with email: " + request.getEmail());
    	}

        Role role = roleRepository.findByRoleName(request.getRoleName())
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Role not found: " + request.getRoleName()));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .enabled(true)
                .accountLocked(false)
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .roleName(savedUser.getRole().getRoleName())
                .build();
    }
    
    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().getRoleName())
                .build();
    }
    
    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "User already exists with email: " + request.getEmail());
        }

        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found: " + request.getRoleName()));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(role);

        if (request.getEnabled() != null) {
            user.setEnabled(request.getEnabled());
        }

        if (request.getAccountLocked() != null) {
            user.setAccountLocked(request.getAccountLocked());
        }

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }
    
    @Override
    public UserResponse disableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        user.setEnabled(false);

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }

    @Override
    public UserResponse enableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        user.setEnabled(true);
        user.setAccountLocked(false);

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }
}
