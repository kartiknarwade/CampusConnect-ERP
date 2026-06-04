package com.campusconnect.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.AttendanceResponse;
import com.campusconnect.dto.MarkAttendanceRequest;
import com.campusconnect.service.AttendanceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/attendance")
@RequiredArgsConstructor
public class AdminAttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<ApiResponse<AttendanceResponse>> markAttendance(
            @Valid @RequestBody MarkAttendanceRequest request) {

        AttendanceResponse attendanceResponse =
                attendanceService.markAttendance(request);

        ApiResponse<AttendanceResponse> response =
                ApiResponse.<AttendanceResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Attendance marked successfully")
                        .data(attendanceResponse)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendance() {

        List<AttendanceResponse> attendanceList =
                attendanceService.getAllAttendance();

        ApiResponse<List<AttendanceResponse>> response =
                ApiResponse.<List<AttendanceResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Attendance records fetched successfully")
                        .data(attendanceList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByStudent(
            @PathVariable Long studentId) {

        List<AttendanceResponse> attendanceList =
                attendanceService.getAttendanceByStudent(studentId);

        ApiResponse<List<AttendanceResponse>> response =
                ApiResponse.<List<AttendanceResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Student attendance fetched successfully")
                        .data(attendanceList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceBySubject(
            @PathVariable Long subjectId) {

        List<AttendanceResponse> attendanceList =
                attendanceService.getAttendanceBySubject(subjectId);

        ApiResponse<List<AttendanceResponse>> response =
                ApiResponse.<List<AttendanceResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Subject attendance fetched successfully")
                        .data(attendanceList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByDate(
            @PathVariable LocalDate date) {

        List<AttendanceResponse> attendanceList =
                attendanceService.getAttendanceByDate(date);

        ApiResponse<List<AttendanceResponse>> response =
                ApiResponse.<List<AttendanceResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Date-wise attendance fetched successfully")
                        .data(attendanceList)
                        .build();

        return ResponseEntity.ok(response);
    }
}
