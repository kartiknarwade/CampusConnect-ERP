package com.campusconnect.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignmentResponse {

    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;

    private Long subjectId;
    private String subjectName;
    private String subjectCode;

    private Long teacherId;
    private String teacherName;

    private LocalDateTime createdAt;
}
