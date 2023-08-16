package com.ubo.groupingservice.service;

import com.ubo.groupingservice.model.entity.CarRegistry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "registry-service")
public interface RegistryService {

    @GetMapping("/registries/getRegistryById/{id}")
    CarRegistry getRegistryById(@PathVariable Integer id);
}
