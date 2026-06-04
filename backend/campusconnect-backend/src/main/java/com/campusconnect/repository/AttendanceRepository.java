package com.campusconnect.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findBySubjectId(Long subjectId);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    boolean existsByStudentIdAndSubjectIdAndAttendanceDate(
            Long studentId,
            Long subjectId,
            LocalDate attendanceDate);
}
