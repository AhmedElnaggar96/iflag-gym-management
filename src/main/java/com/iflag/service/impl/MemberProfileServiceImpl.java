package com.iflag.service.impl;

import com.iflag.entity.MemberProfile;
import com.iflag.entity.User;
import com.iflag.enums.AttendanceStatus;
import com.iflag.repository.AttendanceRepository;
import com.iflag.repository.MemberProfileRepository;
import com.iflag.service.MemberProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberProfileServiceImpl implements MemberProfileService {

    private final  MemberProfileRepository memberProfileRepository;
    private final AttendanceRepository attendanceRepository;

    public MemberProfileServiceImpl(MemberProfileRepository memberProfileRepository, AttendanceRepository attendanceRepository) {
        this.memberProfileRepository = memberProfileRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<MemberProfile> findAllMemberProfiles() {
        return memberProfileRepository.findAll();
    }

    @Override
    public MemberProfile saveMemberProfile(MemberProfile memberProfile) {
        return memberProfileRepository.save(memberProfile);
    }

    @Override
    public MemberProfile findMemberProfileById(Long id) {
        return memberProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member profile not found"));
    }

    @Override
    public long getUsedSessions(Long memberProfileId) {
        return attendanceRepository.countByMemberIdAndStatus(memberProfileId, AttendanceStatus.ATTENDED);
    }

    @Override
    public long getRemainingSessions(Long memberProfileId) {

        MemberProfile memberProfile = findMemberProfileById(memberProfileId);

        long availableSessions = memberProfile.getTotalSessions() + memberProfile.getExtraSessions();
        long usedSessions = getUsedSessions(memberProfileId);

        return availableSessions - usedSessions;
    }

    @Override
    public MemberProfile findByUser(User user) {
        return memberProfileRepository.findByUser(user);
    }

    @Override
    public long countMemberProfiles() {
        return memberProfileRepository.count();
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return memberProfileRepository.existsByUserId(userId);
    }

    @Override
    public List<MemberProfile> searchByNameOrId(String keyword) {

        if(keyword==null || keyword.isBlank()){
            return memberProfileRepository.findByUser_ActiveTrue();
        }

        String trimmedKeyword = keyword.trim();


        try {
            Long id = Long.parseLong(trimmedKeyword);

            return memberProfileRepository.findById(id)
                    .filter(memberProfile -> memberProfile.getUser().isActive())
                    .map(List::of)
                    .orElse(List.of());
        }

        catch (NumberFormatException e) {
           return memberProfileRepository.findByUser_ActiveTrueAndUser_FullNameContainingIgnoreCase(trimmedKeyword);
        }
    }
}
