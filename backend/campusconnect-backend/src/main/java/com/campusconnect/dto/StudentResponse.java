package com.campusconnect.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {

    private Long id;
    private String rollNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String department;
    private String course;
    private Integer semester;
    private Integer admissionYear;

    private Long userId;
    private String studentName;
    private String email;
}
