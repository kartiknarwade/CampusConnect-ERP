package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateStudentRequest;
import com.campusconnect.dto.StudentResponse;
import com.campusconnect.dto.UpdateStudentRequest;
import com.campusconnect.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/students")
@RequiredArgsConstructor
public class AdminStudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @Valid @RequestBody CreateStudentRequest request) {

        StudentResponse studentResponse = studentService.createStudent(request);

        ApiResponse<StudentResponse> response = ApiResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Student profile created successfully")
                .data(studentResponse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {

        List<StudentResponse> students = studentService.getAllStudents();

        ApiResponse<List<StudentResponse>> response =
                ApiResponse.<List<StudentResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Students fetched successfully")
                        .data(students)
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(
            @PathVariable Long id) {

        StudentResponse student = studentService.getStudentById(id);

        ApiResponse<StudentResponse> response =
                ApiResponse.<StudentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Student fetched successfully")
                        .data(student)
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/roll/{rollNumber}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentByRollNumber(
            @PathVariable String rollNumber) {

        StudentResponse student = studentService.getStudentByRollNumber(rollNumber);

        ApiResponse<StudentResponse> response =
                ApiResponse.<StudentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Student fetched successfully")
                        .data(student)
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStudentRequest request) {

        StudentResponse studentResponse = studentService.updateStudent(id, request);

        ApiResponse<StudentResponse> response =
                ApiResponse.<StudentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Student updated successfully")
                        .data(studentResponse)
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentByUserId(
            @PathVariable Long userId) {

        StudentResponse student =
                studentService.getStudentByUserId(userId);

        ApiResponse<StudentResponse> response =
                ApiResponse.<StudentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Student fetched successfully")
                        .data(student)
                        .build();

        return ResponseEntity.ok(response);
    }
}
