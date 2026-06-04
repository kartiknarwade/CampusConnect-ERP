package com.campusconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.CreateMarksRequest;
import com.campusconnect.dto.MarksResponse;
import com.campusconnect.dto.UpdateMarksRequest;
import com.campusconnect.entity.Marks;
import com.campusconnect.entity.Student;
import com.campusconnect.entity.Subject;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.MarksRepository;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.repository.SubjectRepository;
import com.campusconnect.service.MarksService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public MarksResponse createMarks(CreateMarksRequest request) {

        if (marksRepository.existsByStudentIdAndSubjectId(
                request.getStudentId(), request.getSubjectId())) {
            throw new DuplicateResourceException(
                    "Marks already exist for this student and subject");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + request.getStudentId()));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found with id: " + request.getSubjectId()));

        int total = calculateTotal(
                request.getInternalMarks(),
                request.getPracticalMarks(),
                request.getTheoryMarks());

        Marks marks = Marks.builder()
                .student(student)
                .subject(subject)
                .internalMarks(request.getInternalMarks())
                .practicalMarks(request.getPracticalMarks())
                .theoryMarks(request.getTheoryMarks())
                .totalMarks(total)
                .grade(calculateGrade(total))
                .build();

        return mapToResponse(marksRepository.save(marks));
    }

    @Override
    public List<MarksResponse> getAllMarks() {
        return marksRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MarksResponse getMarksById(Long id) {

        Marks marks = marksRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Marks not found with id: " + id));

        return mapToResponse(marks);
    }

    @Override
    public List<MarksResponse> getMarksByStudent(Long studentId) {
        return marksRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MarksResponse> getMarksBySubject(Long subjectId) {
        return marksRepository.findBySubjectId(subjectId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MarksResponse updateMarks(Long id, UpdateMarksRequest request) {

        Marks marks = marksRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Marks not found with id: " + id));

        int total = calculateTotal(
                request.getInternalMarks(),
                request.getPracticalMarks(),
                request.getTheoryMarks());

        marks.setInternalMarks(request.getInternalMarks());
        marks.setPracticalMarks(request.getPracticalMarks());
        marks.setTheoryMarks(request.getTheoryMarks());
        marks.setTotalMarks(total);
        marks.setGrade(calculateGrade(total));

        return mapToResponse(marksRepository.save(marks));
    }

    private int calculateTotal(Integer internal, Integer practical, Integer theory) {
        return internal + practical + theory;
    }

    private String calculateGrade(Integer total) {
        if (total >= 90) return "A+";
        if (total >= 80) return "A";
        if (total >= 70) return "B+";
        if (total >= 60) return "B";
        if (total >= 50) return "C";
        if (total >= 40) return "D";
        return "F";
    }

    private MarksResponse mapToResponse(Marks marks) {

        Student student = marks.getStudent();
        Subject subject = marks.getSubject();

        return MarksResponse.builder()
                .id(marks.getId())
                .studentId(student.getId())
                .studentName(student.getUser().getFirstName() + " " + student.getUser().getLastName())
                .rollNumber(student.getRollNumber())
                .subjectId(subject.getId())
                .subjectName(subject.getSubjectName())
                .subjectCode(subject.getSubjectCode())
                .internalMarks(marks.getInternalMarks())
                .practicalMarks(marks.getPracticalMarks())
                .theoryMarks(marks.getTheoryMarks())
                .totalMarks(marks.getTotalMarks())
                .grade(marks.getGrade())
                .build();
    }
}
