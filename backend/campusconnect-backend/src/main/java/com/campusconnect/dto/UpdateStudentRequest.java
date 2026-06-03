package com.campusconnect.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateStudentRequest {

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
    @Min(1)
    @Max(8)
    private Integer semester;

    @NotNull(message = "Admission year is required")
    private Integer admissionYear;
}
