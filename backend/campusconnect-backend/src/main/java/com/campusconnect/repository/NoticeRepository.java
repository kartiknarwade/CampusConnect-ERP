package com.campusconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Notice;
import com.campusconnect.enums.NoticeTargetRole;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByTargetRole(NoticeTargetRole targetRole);
}
