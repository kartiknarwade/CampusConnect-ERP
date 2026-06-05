package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.CreateNoticeRequest;
import com.campusconnect.dto.NoticeResponse;
import com.campusconnect.dto.UpdateNoticeRequest;
import com.campusconnect.enums.NoticeTargetRole;

public interface NoticeService {

    NoticeResponse createNotice(CreateNoticeRequest request);

    List<NoticeResponse> getAllNotices();

    NoticeResponse getNoticeById(Long id);

    List<NoticeResponse> getNoticesByTargetRole(NoticeTargetRole targetRole);

    NoticeResponse updateNotice(Long id, UpdateNoticeRequest request);
}
