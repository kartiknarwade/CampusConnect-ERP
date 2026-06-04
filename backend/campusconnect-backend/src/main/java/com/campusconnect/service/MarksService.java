package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.CreateMarksRequest;
import com.campusconnect.dto.MarksResponse;
import com.campusconnect.dto.UpdateMarksRequest;

public interface MarksService {

    MarksResponse createMarks(CreateMarksRequest request);

    List<MarksResponse> getAllMarks();

    MarksResponse getMarksById(Long id);

    List<MarksResponse> getMarksByStudent(Long studentId);

    List<MarksResponse> getMarksBySubject(Long subjectId);

    MarksResponse updateMarks(Long id, UpdateMarksRequest request);
}
