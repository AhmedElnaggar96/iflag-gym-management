package com.iflag.repository;

import com.iflag.entity.MemberProfile;
import com.iflag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberProfileRepository extends JpaRepository<MemberProfile,Long> {

    MemberProfile findByUser(User user);

    boolean existsByUserId(Long userId);

    List<MemberProfile> findByUser_ActiveTrue();

    List<MemberProfile> findByUser_ActiveTrueAndUser_FullNameContainingIgnoreCase(String keyword);

}
