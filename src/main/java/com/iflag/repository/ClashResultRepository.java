package com.iflag.repository;

import com.iflag.entity.ClashResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClashResultRepository extends JpaRepository<ClashResult,Long> {


    List<ClashResult> findByMemberId(Long memberId);

    boolean existsByClashEventIdAndMemberId(Long ClashEventId, Long memberId);
}
