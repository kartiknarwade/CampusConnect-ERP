package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.CreateTeacherRequest;
import com.campusconnect.dto.TeacherResponse;
import com.campusconnect.dto.UpdateTeacherRequest;

public interface TeacherService {

    TeacherResponse createTeacher(CreateTeacherRequest request);

    List<TeacherResponse> getAllTeachers();

    TeacherResponse getTeacherById(Long id);

    TeacherResponse getTeacherByEmployeeId(String employeeId);

    TeacherResponse getTeacherByUserId(Long userId);

    TeacherResponse updateTeacher(Long id, UpdateTeacherRequest request);
}
