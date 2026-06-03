package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.CreateUserRequest;
import com.campusconnect.dto.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
}
