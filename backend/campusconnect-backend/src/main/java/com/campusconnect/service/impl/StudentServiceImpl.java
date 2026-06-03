package com.campusconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.CreateStudentRequest;
import com.campusconnect.dto.StudentResponse;
import com.campusconnect.dto.UpdateStudentRequest;
import com.campusconnect.entity.Student;
import com.campusconnect.entity.User;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.InvalidOperationException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.repository.UserRepository;
import com.campusconnect.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public StudentResponse createStudent(CreateStudentRequest request) {

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new DuplicateResourceException(
                    "Student already exists with roll number: " + request.getRollNumber());
        }

        if (studentRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateResourceException(
                    "Student profile already exists for user id: " + request.getUserId());
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        if (!"STUDENT".equals(user.getRole().getRoleName())) {
        	throw new InvalidOperationException(
        	        "Selected user is not a STUDENT role user");
        }

        Student student = Student.builder()
                .rollNumber(request.getRollNumber())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .address(request.getAddress())
                .department(request.getDepartment())
                .course(request.getCourse())
                .semester(request.getSemester())
                .admissionYear(request.getAdmissionYear())
                .user(user)
                .build();

        Student savedStudent = studentRepository.save(student);

        return mapToStudentResponse(savedStudent);
    }

    private StudentResponse mapToStudentResponse(Student student) {

        User user = student.getUser();

        return StudentResponse.builder()
                .id(student.getId())
                .rollNumber(student.getRollNumber())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .address(student.getAddress())
                .department(student.getDepartment())
                .course(student.getCourse())
                .semester(student.getSemester())
                .admissionYear(student.getAdmissionYear())
                .userId(user.getId())
                .studentName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .build();
    }
    
    @Override
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        return mapToStudentResponse(student);
    }

    @Override
    public StudentResponse getStudentByRollNumber(String rollNumber) {

        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with roll number: " + rollNumber));

        return mapToStudentResponse(student);
    }
    
    @Override
    public StudentResponse updateStudent(Long id, UpdateStudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        if (!student.getRollNumber().equals(request.getRollNumber())
                && studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new DuplicateResourceException(
                    "Student already exists with roll number: " + request.getRollNumber());
        }

        student.setRollNumber(request.getRollNumber());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setAddress(request.getAddress());
        student.setDepartment(request.getDepartment());
        student.setCourse(request.getCourse());
        student.setSemester(request.getSemester());
        student.setAdmissionYear(request.getAdmissionYear());

        Student updatedStudent = studentRepository.save(student);

        return mapToStudentResponse(updatedStudent);
    }
    
    @Override
    public StudentResponse getStudentByUserId(Long userId) {

        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found for user id: " + userId));

        return mapToStudentResponse(student);
    }
}
