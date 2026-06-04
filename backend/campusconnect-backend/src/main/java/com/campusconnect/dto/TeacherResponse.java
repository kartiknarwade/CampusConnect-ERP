package com.campusconnect.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherResponse {

    private Long id;
    private String employeeId;
    private String department;
    private String designation;
    private String specialization;
    private Integer experienceYears;

    private Long userId;
    private String teacherName;
    private String email;
}
