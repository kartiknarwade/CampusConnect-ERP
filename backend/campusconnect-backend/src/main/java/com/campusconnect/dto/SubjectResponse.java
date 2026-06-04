package com.campusconnect.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectResponse {

    private Long id;
    private String subjectCode;
    private String subjectName;
    private String department;
    private Integer semester;

    private Long teacherId;
    private String teacherName;
}
