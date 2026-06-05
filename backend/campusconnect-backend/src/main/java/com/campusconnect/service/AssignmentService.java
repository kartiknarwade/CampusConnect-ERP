package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.AssignmentResponse;
import com.campusconnect.dto.CreateAssignmentRequest;
import com.campusconnect.dto.UpdateAssignmentRequest;

public interface AssignmentService {

    AssignmentResponse createAssignment(CreateAssignmentRequest request);

    List<AssignmentResponse> getAllAssignments();

    AssignmentResponse getAssignmentById(Long id);

    List<AssignmentResponse> getAssignmentsBySubject(Long subjectId);

    List<AssignmentResponse> getAssignmentsByTeacher(Long teacherId);

    AssignmentResponse updateAssignment(Long id, UpdateAssignmentRequest request);
}