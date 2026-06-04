package com.campusconnect.service;

import java.time.LocalDate;
import java.util.List;

import com.campusconnect.dto.AttendanceResponse;
import com.campusconnect.dto.MarkAttendanceRequest;

public interface AttendanceService {

    AttendanceResponse markAttendance(MarkAttendanceRequest request);

    List<AttendanceResponse> getAllAttendance();

    List<AttendanceResponse> getAttendanceByStudent(Long studentId);

    List<AttendanceResponse> getAttendanceBySubject(Long subjectId);

    List<AttendanceResponse> getAttendanceByDate(LocalDate date);
}
