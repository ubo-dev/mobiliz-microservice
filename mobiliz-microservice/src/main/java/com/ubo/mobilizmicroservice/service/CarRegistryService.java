package com.ubo.mobilizmicroservice.service;

import com.ubo.mobilizmicroservice.dto.CarRegistryRequest;
import com.ubo.mobilizmicroservice.dto.CarRegistryResponse;
import com.ubo.mobilizmicroservice.exception.RegistryNotFoundException;
import com.ubo.mobilizmicroservice.model.entity.CarRegistry;
import com.ubo.mobilizmicroservice.repository.CarRegistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarRegistryService {

    private final CarRegistryRepository carRegistryRepository;

    public void createCarRegistry(CarRegistryRequest request) {
        CarRegistry carRegistry = CarRegistry.builder()
                .vin(request.getVin())
                .plate(request.getPlate())
                .brand(request.getBrand())
                .model(request.getModel())
                .modelYear(request.getModelYear())
                .build();

       carRegistryRepository.save(carRegistry);
       log.info("Car with id: {} is registered", carRegistry.getId());
    }

    public List<CarRegistryResponse> getAllCarRegistry() {
        List<CarRegistry> carRegistries = carRegistryRepository.findAll();
        if (carRegistries.isEmpty())
            throw new RegistryNotFoundException("No registry has been found");

        return carRegistries.stream().map(this::mapToCarRegistryResponse).toList();
    }

    public CarRegistry getCarRegistryById(Integer id) {
        Optional<CarRegistry> optional = carRegistryRepository.findById(id);

        if (optional.isEmpty())
            throw new RegistryNotFoundException("Car Registry not found");

        return optional.get();
    }

    private CarRegistryResponse mapToCarRegistryResponse(CarRegistry carRegistry) {
        return CarRegistryResponse.builder()
                .id(carRegistry.getId())
                .vin(carRegistry.getVin())
                .plate(carRegistry.getPlate())
                .brand(carRegistry.getBrand())
                .model(carRegistry.getModel())
                .modelYear(carRegistry.getModelYear())
                .build();
    }
}
