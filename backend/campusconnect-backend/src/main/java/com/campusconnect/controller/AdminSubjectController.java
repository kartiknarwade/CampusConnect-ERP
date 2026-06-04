package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateSubjectRequest;
import com.campusconnect.dto.SubjectResponse;
import com.campusconnect.dto.UpdateSubjectRequest;
import com.campusconnect.service.SubjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/subjects")
@RequiredArgsConstructor
public class AdminSubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<ApiResponse<SubjectResponse>> createSubject(
            @Valid @RequestBody CreateSubjectRequest request) {

        SubjectResponse subjectResponse = subjectService.createSubject(request);

        ApiResponse<SubjectResponse> response =
                ApiResponse.<SubjectResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Subject created successfully")
                        .data(subjectResponse)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getAllSubjects() {

        List<SubjectResponse> subjects = subjectService.getAllSubjects();

        ApiResponse<List<SubjectResponse>> response =
                ApiResponse.<List<SubjectResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subjects fetched successfully")
                        .data(subjects)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubjectResponse>> getSubjectById(
            @PathVariable Long id) {

        SubjectResponse subject = subjectService.getSubjectById(id);

        ApiResponse<SubjectResponse> response =
                ApiResponse.<SubjectResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subject fetched successfully")
                        .data(subject)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{subjectCode}")
    public ResponseEntity<ApiResponse<SubjectResponse>> getSubjectByCode(
            @PathVariable String subjectCode) {

        SubjectResponse subject = subjectService.getSubjectByCode(subjectCode);

        ApiResponse<SubjectResponse> response =
                ApiResponse.<SubjectResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subject fetched successfully")
                        .data(subject)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SubjectResponse>> updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSubjectRequest request) {

        SubjectResponse subjectResponse = subjectService.updateSubject(id, request);

        ApiResponse<SubjectResponse> response =
                ApiResponse.<SubjectResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subject updated successfully")
                        .data(subjectResponse)
                        .build();

        return ResponseEntity.ok(response);
    }
}
