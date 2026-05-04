package com.iflag.service.impl;

import com.iflag.entity.ClashEvent;
import com.iflag.repository.ClashEventRepository;
import com.iflag.service.ClashEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClashEventServiceImpl implements ClashEventService {

    private final ClashEventRepository clashEventRepository;

    public ClashEventServiceImpl(ClashEventRepository clashEventRepository) {
        this.clashEventRepository = clashEventRepository;
    }

    @Override
    public List<ClashEvent> findAllClashEvents() {
        return clashEventRepository.findAll();
    }

    @Override
    public ClashEvent findClashEventById(Long id) {
        return clashEventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Clash event not found"));
    }

    @Override
    public ClashEvent saveClashEvent(ClashEvent clashEvent) {
        return clashEventRepository.save(clashEvent);
    }
}
