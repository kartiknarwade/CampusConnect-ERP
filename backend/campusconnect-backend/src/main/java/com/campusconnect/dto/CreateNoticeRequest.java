package com.campusconnect.dto;

import com.campusconnect.enums.NoticeTargetRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNoticeRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Target role is required")
    private NoticeTargetRole targetRole;

    @NotNull(message = "Created by user id is required")
    private Long createdByUserId;
}