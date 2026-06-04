package com.campusconnect.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.AttendanceResponse;
import com.campusconnect.dto.MarkAttendanceRequest;
import com.campusconnect.entity.Attendance;
import com.campusconnect.entity.Student;
import com.campusconnect.entity.Subject;
import com.campusconnect.entity.Teacher;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.AttendanceRepository;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.repository.SubjectRepository;
import com.campusconnect.repository.TeacherRepository;
import com.campusconnect.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AttendanceResponse markAttendance(MarkAttendanceRequest request) {

        if (attendanceRepository.existsByStudentIdAndSubjectIdAndAttendanceDate(
                request.getStudentId(),
                request.getSubjectId(),
                request.getAttendanceDate())) {
            throw new DuplicateResourceException("Attendance already marked for this student, subject and date");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + request.getStudentId()));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + request.getSubjectId()));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + request.getTeacherId()));

        Attendance attendance = Attendance.builder()
                .student(student)
                .subject(subject)
                .teacher(teacher)
                .attendanceDate(request.getAttendanceDate())
                .status(request.getStatus())
                .build();

        return mapToResponse(attendanceRepository.save(attendance));
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAttendanceBySubject(Long subjectId) {
        return attendanceRepository.findBySubjectId(subjectId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AttendanceResponse mapToResponse(Attendance attendance) {

        Student student = attendance.getStudent();
        Subject subject = attendance.getSubject();
        Teacher teacher = attendance.getTeacher();

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .studentId(student.getId())
                .studentName(student.getUser().getFirstName() + " " + student.getUser().getLastName())
                .rollNumber(student.getRollNumber())
                .subjectId(subject.getId())
                .subjectName(subject.getSubjectName())
                .subjectCode(subject.getSubjectCode())
                .teacherId(teacher.getId())
                .teacherName(teacher.getUser().getFirstName() + " " + teacher.getUser().getLastName())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus())
                .build();
    }
}