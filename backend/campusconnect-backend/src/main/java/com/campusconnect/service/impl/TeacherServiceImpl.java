package com.campusconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.CreateTeacherRequest;
import com.campusconnect.dto.TeacherResponse;
import com.campusconnect.dto.UpdateTeacherRequest;
import com.campusconnect.entity.Teacher;
import com.campusconnect.entity.User;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.InvalidOperationException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.TeacherRepository;
import com.campusconnect.repository.UserRepository;
import com.campusconnect.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public TeacherResponse createTeacher(CreateTeacherRequest request) {

        if (teacherRepository.existsByEmployeeId(request.getEmployeeId())) {
            throw new DuplicateResourceException(
                    "Teacher already exists with employee id: " + request.getEmployeeId());
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + request.getUserId()));

        if (!"TEACHER".equals(user.getRole().getRoleName())) {
            throw new InvalidOperationException(
                    "Selected user is not a TEACHER role user");
        }

        Teacher teacher = Teacher.builder()
                .employeeId(request.getEmployeeId())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .specialization(request.getSpecialization())
                .experienceYears(request.getExperienceYears())
                .user(user)
                .build();

        return mapToResponse(teacherRepository.save(teacher));
    }

    @Override
    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TeacherResponse getTeacherById(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found with id: " + id));

        return mapToResponse(teacher);
    }

    @Override
    public TeacherResponse getTeacherByEmployeeId(String employeeId) {

        Teacher teacher = teacherRepository.findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found with employee id: " + employeeId));

        return mapToResponse(teacher);
    }

    @Override
    public TeacherResponse getTeacherByUserId(Long userId) {

        Teacher teacher = teacherRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found for user id: " + userId));

        return mapToResponse(teacher);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, UpdateTeacherRequest request) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found with id: " + id));

        teacher.setEmployeeId(request.getEmployeeId());
        teacher.setDepartment(request.getDepartment());
        teacher.setDesignation(request.getDesignation());
        teacher.setSpecialization(request.getSpecialization());
        teacher.setExperienceYears(request.getExperienceYears());

        return mapToResponse(teacherRepository.save(teacher));
    }

    private TeacherResponse mapToResponse(Teacher teacher) {

        User user = teacher.getUser();

        return TeacherResponse.builder()
                .id(teacher.getId())
                .employeeId(teacher.getEmployeeId())
                .department(teacher.getDepartment())
                .designation(teacher.getDesignation())
                .specialization(teacher.getSpecialization())
                .experienceYears(teacher.getExperienceYears())
                .userId(user.getId())
                .teacherName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
