package com.campusconnect.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateStudentRequest {

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Course is required")
    private String course;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester must be at most 8")
    private Integer semester;

    @NotNull(message = "Admission year is required")
    private Integer admissionYear;

    @NotNull(message = "User id is required")
    private Long userId;
}