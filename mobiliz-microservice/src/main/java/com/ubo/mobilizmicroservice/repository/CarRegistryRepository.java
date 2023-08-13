package com.ubo.mobilizmicroservice.repository;

import com.ubo.mobilizmicroservice.model.entity.CarRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRegistryRepository extends JpaRepository<CarRegistry, Integer> {

}
