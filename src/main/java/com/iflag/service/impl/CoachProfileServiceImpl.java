package com.iflag.service.impl;

import com.iflag.entity.CoachProfile;
import com.iflag.repository.CoachProfileRepository;
import com.iflag.service.CoachProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachProfileServiceImpl implements CoachProfileService {

    private final CoachProfileRepository coachProfileRepository;

    public CoachProfileServiceImpl(CoachProfileRepository coachProfileRepository) {
        this.coachProfileRepository = coachProfileRepository;
    }

    @Override
    public List<CoachProfile> findAllCoachProfiles() {
        return coachProfileRepository.findAll();
    }

    @Override
    public CoachProfile saveCoachProfile(CoachProfile coachProfile) {
        return coachProfileRepository.save(coachProfile);
    }

    @Override
    public CoachProfile findCoachProfileById(Long id) {
        return coachProfileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Coach profile Not Found"));
    }

    @Override
    public long countCoachProfiles() {
        return coachProfileRepository.count();
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return coachProfileRepository.existsByUserId(userId);
    }

    @Override
    public List<CoachProfile> findActiveCoachProfiles() {
        return coachProfileRepository.findByUser_ActiveTrue();
    }
}
