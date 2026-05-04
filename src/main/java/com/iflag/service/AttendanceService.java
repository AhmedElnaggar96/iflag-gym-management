package com.iflag.service;

import com.iflag.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> findAllAttendance();

    Attendance findAttendanceById(Long id);

    Attendance saveAttendance(Attendance attendance);

    List<Attendance> findAttendanceByMemberId(Long memberId);

    void deleteAttendanceById(Long id);

    long countAttendance();
}
