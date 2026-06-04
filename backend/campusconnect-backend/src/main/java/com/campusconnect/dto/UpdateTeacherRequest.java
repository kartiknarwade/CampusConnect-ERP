package com.campusconnect.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateTeacherRequest {

    @NotBlank
    private String employeeId;

    @NotBlank
    private String department;

    @NotBlank
    private String designation;

    @NotBlank
    private String specialization;

    @NotNull
    private Integer experienceYears;
}
