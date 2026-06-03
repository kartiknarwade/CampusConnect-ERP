package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateUserRequest;
import com.campusconnect.dto.UserResponse;
import com.campusconnect.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        UserResponse userResponse = userService.createUser(request);

        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(userResponse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        ApiResponse<List<UserResponse>> response =
                ApiResponse.<List<UserResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Users fetched successfully")
                        .data(users)
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable Long id) {

        UserResponse user = userService.getUserById(id);

        ApiResponse<UserResponse> response =
                ApiResponse.<UserResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("User fetched successfully")
                        .data(user)
                        .build();

        return ResponseEntity.ok(response);
    }
}
