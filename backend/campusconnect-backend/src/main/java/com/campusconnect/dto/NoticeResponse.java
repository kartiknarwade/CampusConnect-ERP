package com.campusconnect.dto;

import java.time.LocalDateTime;

import com.campusconnect.enums.NoticeTargetRole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeResponse {

    private Long id;
    private String title;
    private String description;
    private NoticeTargetRole targetRole;

    private Long createdByUserId;
    private String createdByName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}