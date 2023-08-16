package com.ubo.groupingservice.service;

import com.ubo.groupingservice.dto.CourierRequest;
import com.ubo.groupingservice.dto.CourierResponse;
import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.CourierNotFoundException;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.model.entity.Courier;
import com.ubo.groupingservice.model.entity.Group;
import com.ubo.groupingservice.repository.CourierRepository;
import com.ubo.groupingservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;

    public void createCourier(CourierRequest request) {
        Courier courier = Courier.builder()
                .courierName(request.getCourierName())
                .build();

        courierRepository.save(courier);
        log.info("Group with name: {} is created", courier.getCourierName());
    }


    public List<CourierResponse> getAllCouriers() {
        List<Courier> couriers = courierRepository.findAll();
        if (couriers.isEmpty())
            throw new GroupNotFoundException("No group has been found");

        return couriers.stream().map(this::mapToCourierResponse).toList();
    }

    public CourierResponse getCourierById(Integer id) {
        Optional<Courier> optional = courierRepository.findById(id);

        if (optional.isEmpty())
            throw new CourierNotFoundException("Courier not found");

        return mapToCourierResponse(optional.get());
    }

    private CourierResponse mapToCourierResponse(Courier courier) {
        return CourierResponse.builder()
                .id(courier.getId())
                .courierName(courier.getCourierName())
                .carRegistries(courier.getCarRegistries())
                .build();
    }

    private Courier mapToCourier(CourierResponse response) {
        return Courier.builder()
                .id(response.getId())
                .courierName(response.getCourierName())
                .carRegistries(response.getCarRegistries())
                .build();
    }
}
