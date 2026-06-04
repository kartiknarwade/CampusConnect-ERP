package com.campusconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findByStudentId(Long studentId);

    List<Marks> findBySubjectId(Long subjectId);

    Optional<Marks> findByStudentIdAndSubjectId(
            Long studentId,
            Long subjectId);

    boolean existsByStudentIdAndSubjectId(
            Long studentId,
            Long subjectId);
}
