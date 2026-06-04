package com.campusconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.CreateSubjectRequest;
import com.campusconnect.dto.SubjectResponse;
import com.campusconnect.dto.UpdateSubjectRequest;
import com.campusconnect.entity.Subject;
import com.campusconnect.entity.Teacher;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.SubjectRepository;
import com.campusconnect.repository.TeacherRepository;
import com.campusconnect.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public SubjectResponse createSubject(CreateSubjectRequest request) {

        if (subjectRepository.existsBySubjectCode(request.getSubjectCode())) {
            throw new DuplicateResourceException(
                    "Subject already exists with code: " + request.getSubjectCode());
        }

        Teacher teacher = null;

        if (request.getTeacherId() != null) {
            teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Teacher not found with id: " + request.getTeacherId()));
        }

        Subject subject = Subject.builder()
                .subjectCode(request.getSubjectCode())
                .subjectName(request.getSubjectName())
                .department(request.getDepartment())
                .semester(request.getSemester())
                .teacher(teacher)
                .build();

        return mapToResponse(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found with id: " + id));

        return mapToResponse(subject);
    }

    @Override
    public SubjectResponse getSubjectByCode(String subjectCode) {

        Subject subject = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found with code: " + subjectCode));

        return mapToResponse(subject);
    }

    @Override
    public SubjectResponse updateSubject(Long id, UpdateSubjectRequest request) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found with id: " + id));

        if (!subject.getSubjectCode().equals(request.getSubjectCode())
                && subjectRepository.existsBySubjectCode(request.getSubjectCode())) {
            throw new DuplicateResourceException(
                    "Subject already exists with code: " + request.getSubjectCode());
        }

        Teacher teacher = null;

        if (request.getTeacherId() != null) {
            teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Teacher not found with id: " + request.getTeacherId()));
        }

        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectName(request.getSubjectName());
        subject.setDepartment(request.getDepartment());
        subject.setSemester(request.getSemester());
        subject.setTeacher(teacher);

        return mapToResponse(subjectRepository.save(subject));
    }

    private SubjectResponse mapToResponse(Subject subject) {

        Teacher teacher = subject.getTeacher();

        return SubjectResponse.builder()
                .id(subject.getId())
                .subjectCode(subject.getSubjectCode())
                .subjectName(subject.getSubjectName())
                .department(subject.getDepartment())
                .semester(subject.getSemester())
                .teacherId(teacher != null ? teacher.getId() : null)
                .teacherName(teacher != null
                        ? teacher.getUser().getFirstName() + " " + teacher.getUser().getLastName()
                        : null)
                .build();
    }
}
