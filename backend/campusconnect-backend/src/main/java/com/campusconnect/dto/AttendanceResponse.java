package com.campusconnect.dto;

import java.time.LocalDate;

import com.campusconnect.enums.AttendanceStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceResponse {

    private Long id;

    private Long studentId;
    private String studentName;
    private String rollNumber;

    private Long subjectId;
    private String subjectName;
    private String subjectCode;

    private Long teacherId;
    private String teacherName;

    private LocalDate attendanceDate;
    private AttendanceStatus status;
}
