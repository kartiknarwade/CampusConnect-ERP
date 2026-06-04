package com.campusconnect.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarksResponse {

    private Long id;

    private Long studentId;
    private String studentName;
    private String rollNumber;

    private Long subjectId;
    private String subjectName;
    private String subjectCode;

    private Integer internalMarks;
    private Integer practicalMarks;
    private Integer theoryMarks;
    private Integer totalMarks;
    private String grade;
}