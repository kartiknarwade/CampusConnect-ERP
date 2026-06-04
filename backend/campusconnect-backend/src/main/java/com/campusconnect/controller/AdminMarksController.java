package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateMarksRequest;
import com.campusconnect.dto.MarksResponse;
import com.campusconnect.dto.UpdateMarksRequest;
import com.campusconnect.service.MarksService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/marks")
@RequiredArgsConstructor
public class AdminMarksController {

    private final MarksService marksService;

    @PostMapping
    public ResponseEntity<ApiResponse<MarksResponse>> createMarks(
            @Valid @RequestBody CreateMarksRequest request) {

        MarksResponse marksResponse = marksService.createMarks(request);

        ApiResponse<MarksResponse> response =
                ApiResponse.<MarksResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Marks created successfully")
                        .data(marksResponse)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MarksResponse>>> getAllMarks() {

        List<MarksResponse> marksList = marksService.getAllMarks();

        ApiResponse<List<MarksResponse>> response =
                ApiResponse.<List<MarksResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Marks fetched successfully")
                        .data(marksList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MarksResponse>> getMarksById(
            @PathVariable Long id) {

        MarksResponse marks = marksService.getMarksById(id);

        ApiResponse<MarksResponse> response =
                ApiResponse.<MarksResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Marks fetched successfully")
                        .data(marks)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<MarksResponse>>> getMarksByStudent(
            @PathVariable Long studentId) {

        List<MarksResponse> marksList = marksService.getMarksByStudent(studentId);

        ApiResponse<List<MarksResponse>> response =
                ApiResponse.<List<MarksResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Student marks fetched successfully")
                        .data(marksList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<ApiResponse<List<MarksResponse>>> getMarksBySubject(
            @PathVariable Long subjectId) {

        List<MarksResponse> marksList = marksService.getMarksBySubject(subjectId);

        ApiResponse<List<MarksResponse>> response =
                ApiResponse.<List<MarksResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subject marks fetched successfully")
                        .data(marksList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MarksResponse>> updateMarks(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMarksRequest request) {

        MarksResponse marksResponse = marksService.updateMarks(id, request);

        ApiResponse<MarksResponse> response =
                ApiResponse.<MarksResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Marks updated successfully")
                        .data(marksResponse)
                        .build();

        return ResponseEntity.ok(response);
    }
}
