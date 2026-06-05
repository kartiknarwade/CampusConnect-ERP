package com.campusconnect.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDashboardResponse {

    private Long totalUsers;
    private Long totalStudents;
    private Long totalTeachers;
    private Long totalSubjects;
    private Long totalAttendanceRecords;
    private Long totalAssignments;
    private Long totalNotices;
}
