package com.iflag.service.impl;

import com.iflag.entity.Attendance;
import com.iflag.repository.AttendanceRepository;
import com.iflag.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> findAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance findAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Attendance not found"));
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> findAttendanceByMemberId(Long memberId) {
        return attendanceRepository.findByMemberId(memberId);
    }

    @Override
    public void deleteAttendanceById(Long id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public long countAttendance() {
        return attendanceRepository.count();
    }
}
