package com.campusconnect.service;

import com.campusconnect.dto.CreateUserRequest;
import com.campusconnect.dto.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);
}
