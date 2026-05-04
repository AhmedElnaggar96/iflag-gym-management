package com.iflag.service;

import com.iflag.entity.ClashResult;

import java.util.List;

public interface ClashResultService {

    List<ClashResult> findAllClashResults();

    ClashResult findClashResultById(Long id);

    ClashResult saveClashResult(ClashResult clashResult);

    List<ClashResult> findClashResultsByMemberId(Long memberId);

    void deleteClashResultById(Long id);

    long countClashResults();

    boolean existsByClashEventIdAndMemberId(Long clashEventId, Long memberId);
}
