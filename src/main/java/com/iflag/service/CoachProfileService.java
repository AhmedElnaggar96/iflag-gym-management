package com.iflag.service;

import com.iflag.entity.CoachProfile;

import java.util.List;

public interface CoachProfileService {

    List<CoachProfile> findAllCoachProfiles();

    CoachProfile saveCoachProfile(CoachProfile coachProfile);

    CoachProfile findCoachProfileById(Long id);

    long countCoachProfiles();

    boolean existsByUserId(Long userId);

    List<CoachProfile> findActiveCoachProfiles();
}