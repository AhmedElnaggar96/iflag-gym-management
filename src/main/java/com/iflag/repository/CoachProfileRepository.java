package com.iflag.repository;

import com.iflag.entity.CoachProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachProfileRepository extends JpaRepository<CoachProfile,Long> {

    boolean existsByUserId(Long userId);

    List<CoachProfile> findByUser_ActiveTrue();
}
