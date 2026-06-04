package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateTeacherRequest;
import com.campusconnect.dto.TeacherResponse;
import com.campusconnect.dto.UpdateTeacherRequest;
import com.campusconnect.service.TeacherService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/teachers")
@RequiredArgsConstructor
public class AdminTeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<ApiResponse<TeacherResponse>> createTeacher(
            @Valid @RequestBody CreateTeacherRequest request) {

        TeacherResponse teacherResponse =
                teacherService.createTeacher(request);

        ApiResponse<TeacherResponse> response =
                ApiResponse.<TeacherResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Teacher created successfully")
                        .data(teacherResponse)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TeacherResponse>>> getAllTeachers() {

        List<TeacherResponse> teachers =
                teacherService.getAllTeachers();

        ApiResponse<List<TeacherResponse>> response =
                ApiResponse.<List<TeacherResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Teachers fetched successfully")
                        .data(teachers)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeacherResponse>> getTeacherById(
            @PathVariable Long id) {

        TeacherResponse teacher =
                teacherService.getTeacherById(id);

        ApiResponse<TeacherResponse> response =
                ApiResponse.<TeacherResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher fetched successfully")
                        .data(teacher)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<TeacherResponse>> getTeacherByEmployeeId(
            @PathVariable String employeeId) {

        TeacherResponse teacher =
                teacherService.getTeacherByEmployeeId(employeeId);

        ApiResponse<TeacherResponse> response =
                ApiResponse.<TeacherResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher fetched successfully")
                        .data(teacher)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<TeacherResponse>> getTeacherByUserId(
            @PathVariable Long userId) {

        TeacherResponse teacher =
                teacherService.getTeacherByUserId(userId);

        ApiResponse<TeacherResponse> response =
                ApiResponse.<TeacherResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher fetched successfully")
                        .data(teacher)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeacherResponse>> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTeacherRequest request) {

        TeacherResponse teacher =
                teacherService.updateTeacher(id, request);

        ApiResponse<TeacherResponse> response =
                ApiResponse.<TeacherResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher updated successfully")
                        .data(teacher)
                        .build();

        return ResponseEntity.ok(response);
    }
}
