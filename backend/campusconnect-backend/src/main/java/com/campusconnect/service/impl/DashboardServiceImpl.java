package com.campusconnect.service.impl;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.AdminDashboardResponse;
import com.campusconnect.repository.AssignmentRepository;
import com.campusconnect.repository.AttendanceRepository;
import com.campusconnect.repository.NoticeRepository;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.repository.SubjectRepository;
import com.campusconnect.repository.TeacherRepository;
import com.campusconnect.repository.UserRepository;
import com.campusconnect.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceRepository attendanceRepository;
    private final AssignmentRepository assignmentRepository;
    private final NoticeRepository noticeRepository;

    @Override
    public AdminDashboardResponse getAdminDashboard() {

        return AdminDashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalStudents(studentRepository.count())
                .totalTeachers(teacherRepository.count())
                .totalSubjects(subjectRepository.count())
                .totalAttendanceRecords(attendanceRepository.count())
                .totalAssignments(assignmentRepository.count())
                .totalNotices(noticeRepository.count())
                .build();
    }
}