package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.AssignmentResponse;
import com.campusconnect.dto.CreateAssignmentRequest;
import com.campusconnect.dto.UpdateAssignmentRequest;
import com.campusconnect.service.AssignmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/assignments")
@RequiredArgsConstructor
public class AdminAssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<AssignmentResponse>> createAssignment(
            @Valid @RequestBody CreateAssignmentRequest request) {

        AssignmentResponse assignmentResponse =
                assignmentService.createAssignment(request);

        ApiResponse<AssignmentResponse> response =
                ApiResponse.<AssignmentResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Assignment created successfully")
                        .data(assignmentResponse)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getAllAssignments() {

        List<AssignmentResponse> assignments =
                assignmentService.getAllAssignments();

        ApiResponse<List<AssignmentResponse>> response =
                ApiResponse.<List<AssignmentResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Assignments fetched successfully")
                        .data(assignments)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponse>> getAssignmentById(
            @PathVariable Long id) {

        AssignmentResponse assignment =
                assignmentService.getAssignmentById(id);

        ApiResponse<AssignmentResponse> response =
                ApiResponse.<AssignmentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Assignment fetched successfully")
                        .data(assignment)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getAssignmentsBySubject(
            @PathVariable Long subjectId) {

        List<AssignmentResponse> assignments =
                assignmentService.getAssignmentsBySubject(subjectId);

        ApiResponse<List<AssignmentResponse>> response =
                ApiResponse.<List<AssignmentResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subject assignments fetched successfully")
                        .data(assignments)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getAssignmentsByTeacher(
            @PathVariable Long teacherId) {

        List<AssignmentResponse> assignments =
                assignmentService.getAssignmentsByTeacher(teacherId);

        ApiResponse<List<AssignmentResponse>> response =
                ApiResponse.<List<AssignmentResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher assignments fetched successfully")
                        .data(assignments)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponse>> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAssignmentRequest request) {

        AssignmentResponse assignment =
                assignmentService.updateAssignment(id, request);

        ApiResponse<AssignmentResponse> response =
                ApiResponse.<AssignmentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Assignment updated successfully")
                        .data(assignment)
                        .build();

        return ResponseEntity.ok(response);
    }
}