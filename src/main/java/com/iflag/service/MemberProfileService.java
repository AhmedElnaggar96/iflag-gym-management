package com.iflag.service;

import com.iflag.entity.MemberProfile;
import com.iflag.entity.User;

import java.util.List;

public interface MemberProfileService {

    List<MemberProfile> findAllMemberProfiles();

    MemberProfile saveMemberProfile(MemberProfile memberProfile);

    MemberProfile findMemberProfileById(Long id);

    long getUsedSessions(Long memberProfileId);

    long getRemainingSessions(Long memberProfileId);

    MemberProfile findByUser(User user);

    long countMemberProfiles();

    boolean existsByUserId(Long userId);

    List<MemberProfile> searchByNameOrId(String keyword);
}
