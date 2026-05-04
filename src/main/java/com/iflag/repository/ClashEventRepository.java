package com.iflag.repository;

import com.iflag.entity.ClashEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClashEventRepository extends JpaRepository<ClashEvent,Long> {
}
