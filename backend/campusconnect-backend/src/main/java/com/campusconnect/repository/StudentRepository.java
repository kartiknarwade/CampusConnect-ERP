package com.campusconnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByRollNumber(String rollNumber);

    boolean existsByUserId(Long userId);

    Optional<Student> findByRollNumber(String rollNumber);
    
    Optional<Student> findByUserId(Long userId);
}
