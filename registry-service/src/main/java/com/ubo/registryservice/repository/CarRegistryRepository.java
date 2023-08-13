package com.ubo.registryservice.repository;

import com.ubo.registryservice.model.entity.CarRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRegistryRepository extends JpaRepository<CarRegistry, Integer> {

}
