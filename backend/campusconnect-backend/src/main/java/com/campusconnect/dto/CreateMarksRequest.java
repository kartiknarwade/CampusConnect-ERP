package com.campusconnect.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateMarksRequest {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Subject id is required")
    private Long subjectId;

    @NotNull(message = "Internal marks are required")
    @Min(0)
    @Max(30)
    private Integer internalMarks;

    @NotNull(message = "Practical marks are required")
    @Min(0)
    @Max(20)
    private Integer practicalMarks;

    @NotNull(message = "Theory marks are required")
    @Min(0)
    @Max(50)
    private Integer theoryMarks;
}