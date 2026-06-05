package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.dto.ApiResponse;
import com.campusconnect.dto.CreateNoticeRequest;
import com.campusconnect.dto.NoticeResponse;
import com.campusconnect.dto.UpdateNoticeRequest;
import com.campusconnect.enums.NoticeTargetRole;
import com.campusconnect.service.NoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/notices")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<ApiResponse<NoticeResponse>> createNotice(
            @Valid @RequestBody CreateNoticeRequest request) {

        NoticeResponse notice = noticeService.createNotice(request);

        ApiResponse<NoticeResponse> response =
                ApiResponse.<NoticeResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Notice created successfully")
                        .data(notice)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<NoticeResponse>>> getAllNotices() {

        List<NoticeResponse> notices = noticeService.getAllNotices();

        ApiResponse<List<NoticeResponse>> response =
                ApiResponse.<List<NoticeResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Notices fetched successfully")
                        .data(notices)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoticeResponse>> getNoticeById(
            @PathVariable Long id) {

        NoticeResponse notice = noticeService.getNoticeById(id);

        ApiResponse<NoticeResponse> response =
                ApiResponse.<NoticeResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Notice fetched successfully")
                        .data(notice)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/target/{targetRole}")
    public ResponseEntity<ApiResponse<List<NoticeResponse>>> getNoticesByTargetRole(
            @PathVariable NoticeTargetRole targetRole) {

        List<NoticeResponse> notices =
                noticeService.getNoticesByTargetRole(targetRole);

        ApiResponse<List<NoticeResponse>> response =
                ApiResponse.<List<NoticeResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Notices fetched successfully")
                        .data(notices)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NoticeResponse>> updateNotice(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNoticeRequest request) {

        NoticeResponse notice =
                noticeService.updateNotice(id, request);

        ApiResponse<NoticeResponse> response =
                ApiResponse.<NoticeResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Notice updated successfully")
                        .data(notice)
                        .build();

        return ResponseEntity.ok(response);
    }
}
