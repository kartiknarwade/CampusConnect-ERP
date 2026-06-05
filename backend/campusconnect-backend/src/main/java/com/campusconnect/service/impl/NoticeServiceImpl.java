package com.campusconnect.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.CreateNoticeRequest;
import com.campusconnect.dto.NoticeResponse;
import com.campusconnect.dto.UpdateNoticeRequest;
import com.campusconnect.entity.Notice;
import com.campusconnect.entity.User;
import com.campusconnect.enums.NoticeTargetRole;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repository.NoticeRepository;
import com.campusconnect.repository.UserRepository;
import com.campusconnect.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public NoticeResponse createNotice(CreateNoticeRequest request) {

        User createdBy = userRepository.findById(request.getCreatedByUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + request.getCreatedByUserId()));

        Notice notice = Notice.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .targetRole(request.getTargetRole())
                .createdBy(createdBy)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(noticeRepository.save(notice));
    }

    @Override
    public List<NoticeResponse> getAllNotices() {
        return noticeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoticeResponse getNoticeById(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notice not found with id: " + id));

        return mapToResponse(notice);
    }

    @Override
    public List<NoticeResponse> getNoticesByTargetRole(NoticeTargetRole targetRole) {
        return noticeRepository.findByTargetRole(targetRole)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoticeResponse updateNotice(Long id, UpdateNoticeRequest request) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notice not found with id: " + id));

        notice.setTitle(request.getTitle());
        notice.setDescription(request.getDescription());
        notice.setTargetRole(request.getTargetRole());
        notice.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(noticeRepository.save(notice));
    }

    private NoticeResponse mapToResponse(Notice notice) {

        User createdBy = notice.getCreatedBy();

        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .description(notice.getDescription())
                .targetRole(notice.getTargetRole())
                .createdByUserId(createdBy.getId())
                .createdByName(createdBy.getFirstName() + " " + createdBy.getLastName())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}
