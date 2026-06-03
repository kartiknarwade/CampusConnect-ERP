package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.CreateStudentRequest;
import com.campusconnect.dto.StudentResponse;
import com.campusconnect.dto.UpdateStudentRequest;

public interface StudentService {

    StudentResponse createStudent(CreateStudentRequest request);
    
    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    StudentResponse getStudentByRollNumber(String rollNumber);
    
    StudentResponse updateStudent(Long id, UpdateStudentRequest request);
    
    StudentResponse getStudentByUserId(Long userId);
}
