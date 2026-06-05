package com.campusconnect.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.AssignmentResponse;
import com.campusconnect.dto.CreateAssignmentRequest;
import com.campusconnect.dto.UpdateAssignmentRequest;
import com.campusconnect.entity.Assignment;
import com.campusconnect.entity.Subject;
import com.campusconnect.entity.Teacher;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.AssignmentRepository;
import com.campusconnect.repository.SubjectRepository;
import com.campusconnect.repository.TeacherRepository;
import com.campusconnect.service.AssignmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AssignmentResponse createAssignment(CreateAssignmentRequest request) {

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found with id: " + request.getSubjectId()));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found with id: " + request.getTeacherId()));

        Assignment assignment = Assignment.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .subject(subject)
                .teacher(teacher)
                .createdAt(LocalDateTime.now())
                .build();

        return mapToResponse(assignmentRepository.save(assignment));
    }

    @Override
    public List<AssignmentResponse> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AssignmentResponse getAssignmentById(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assignment not found with id: " + id));

        return mapToResponse(assignment);
    }

    @Override
    public List<AssignmentResponse> getAssignmentsBySubject(Long subjectId) {
        return assignmentRepository.findBySubjectId(subjectId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AssignmentResponse> getAssignmentsByTeacher(Long teacherId) {
        return assignmentRepository.findByTeacherId(teacherId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AssignmentResponse updateAssignment(Long id, UpdateAssignmentRequest request) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assignment not found with id: " + id));

        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());

        return mapToResponse(assignmentRepository.save(assignment));
    }

    private AssignmentResponse mapToResponse(Assignment assignment) {

        Subject subject = assignment.getSubject();
        Teacher teacher = assignment.getTeacher();

        return AssignmentResponse.builder()
                .id(assignment.getId())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .subjectId(subject.getId())
                .subjectName(subject.getSubjectName())
                .subjectCode(subject.getSubjectCode())
                .teacherId(teacher.getId())
                .teacherName(
                        teacher.getUser().getFirstName()
                                + " "
                                + teacher.getUser().getLastName())
                .createdAt(assignment.getCreatedAt())
                .build();
    }
}
