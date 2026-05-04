package com.iflag.repository;

import com.iflag.entity.Attendance;
import com.iflag.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    long countByMemberIdAndStatus(Long memberId, AttendanceStatus status);

    List<Attendance> findByMemberId(Long memberId);
}
