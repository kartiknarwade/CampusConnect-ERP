package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.CreateSubjectRequest;
import com.campusconnect.dto.SubjectResponse;
import com.campusconnect.dto.UpdateSubjectRequest;

public interface SubjectService {

    SubjectResponse createSubject(CreateSubjectRequest request);

    List<SubjectResponse> getAllSubjects();

    SubjectResponse getSubjectById(Long id);

    SubjectResponse getSubjectByCode(String subjectCode);

    SubjectResponse updateSubject(Long id, UpdateSubjectRequest request);
}
