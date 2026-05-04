package com.iflag.service.impl;

import com.iflag.entity.ClashResult;
import com.iflag.repository.ClashResultRepository;
import com.iflag.service.ClashResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClashResultServiceImpl implements ClashResultService {


    private final ClashResultRepository clashResultRepository;

    public ClashResultServiceImpl(ClashResultRepository clashResultRepository) {
        this.clashResultRepository = clashResultRepository;
    }

    @Override
    public List<ClashResult> findAllClashResults() {
        return clashResultRepository.findAll();
    }

    @Override
    public ClashResult findClashResultById(Long id) {
        return clashResultRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Clash Result Not Found"));
    }

    @Override
    public ClashResult saveClashResult(ClashResult clashResult) {
        return clashResultRepository.save(clashResult);
    }

    @Override
    public List<ClashResult> findClashResultsByMemberId(Long memberId) {
        return clashResultRepository.findByMemberId(memberId);
    }

    @Override
    public void deleteClashResultById(Long id) {
        clashResultRepository.deleteById(id);
    }

    @Override
    public long countClashResults() {
        return clashResultRepository.count();
    }

    @Override
    public boolean existsByClashEventIdAndMemberId(Long clashEventId, Long memberId) {
        return clashResultRepository.existsByClashEventIdAndMemberId(clashEventId, memberId);
    }
}
