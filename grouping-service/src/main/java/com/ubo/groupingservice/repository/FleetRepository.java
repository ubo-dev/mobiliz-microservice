package com.ubo.groupingservice.repository;

import com.ubo.groupingservice.model.entity.Fleet;
import com.ubo.groupingservice.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Integer> {
}
