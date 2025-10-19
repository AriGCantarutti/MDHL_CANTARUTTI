package com.tpi.orders.repository;

import com.tpi.orders.model.Leg;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LegRepository extends JpaRepository<Leg, Long> {
    List<Leg> findByRequestId(Long requestId);
}

