package com.campusconnect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateUserRequest;
import com.campusconnect.dto.UserResponse;
import com.campusconnect.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @RequestBody CreateUserRequest request) {

        UserResponse userResponse = userService.createUser(request);

        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(userResponse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
