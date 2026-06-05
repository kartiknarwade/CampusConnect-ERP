package com.campusconnect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.AdminDashboardResponse;
import com.campusconnect.dto.ApiResponse;
import com.campusconnect.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<AdminDashboardResponse>> getDashboard() {

        AdminDashboardResponse dashboard =
                dashboardService.getAdminDashboard();

        ApiResponse<AdminDashboardResponse> response =
                ApiResponse.<AdminDashboardResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Dashboard fetched successfully")
                        .data(dashboard)
                        .build();

        return ResponseEntity.ok(response);
    }
}