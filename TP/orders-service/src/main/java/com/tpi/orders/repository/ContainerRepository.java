package com.tpi.orders.repository;

import com.tpi.orders.model.ContainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<ContainerEntity, Long> {
}

