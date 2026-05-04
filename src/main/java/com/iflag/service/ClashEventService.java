package com.iflag.service;

import com.iflag.entity.ClashEvent;

import java.util.List;

public interface ClashEventService {

    List<ClashEvent> findAllClashEvents();

    ClashEvent findClashEventById(Long id);

    ClashEvent saveClashEvent(ClashEvent clashEvent);
}
