package com.ubo.groupingservice.service;

import com.ubo.groupingservice.dto.FleetRequest;
import com.ubo.groupingservice.dto.FleetResponse;
import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.FleetNotFoundException;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.model.entity.Fleet;
import com.ubo.groupingservice.model.entity.Group;
import com.ubo.groupingservice.repository.FleetRepository;
import com.ubo.groupingservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FleetService {

    private final FleetRepository fleetRepository;


    public void createFleet(FleetRequest request) {
        Fleet fleet = Fleet.builder()
                .fleetName(request.getFleetName())
                .build();

        fleetRepository.save(fleet);
        log.info("Fleet with name: {} is created", fleet.getFleetName());
    }

    public void createGroupForFleet(GroupRequest request, Integer id) {
        Group group = Group.builder()
                .groupName(request.getGroupName())
                .build();

        FleetResponse fleetToAdd = getFleetById(id);

        fleetToAdd.setGroups(List.of(group));


        fleetRepository.save(mapToFleet(fleetToAdd));
        log.info("Fleet with name: {} is updated with groups of: {}", fleetToAdd.getFleetName(),fleetToAdd.getGroups());

    }

    public List<FleetResponse> getAllFleets() {
        List<Fleet> fleets = fleetRepository.findAll();
        if (fleets.isEmpty())
            throw new FleetNotFoundException("No fleet has been found");

        return fleets.stream().map(this::mapToFleetResponse).toList();
    }

    public FleetResponse getFleetById(Integer id) {
        Optional<Fleet> optional = fleetRepository.findById(id);

        if (optional.isEmpty())
            throw new FleetNotFoundException("Fleet not found");

        return mapToFleetResponse(optional.get());
    }

    private FleetResponse mapToFleetResponse(Fleet fleet) {
        return FleetResponse.builder()
                .id(fleet.getId())
                .fleetName(fleet.getFleetName())
                .carRegistries(fleet.getCarRegistries())
                .groups(fleet.getGroups())
                .build();
    }

    private Fleet mapToFleet(FleetResponse response) {
        return Fleet.builder()
                .id(response.getId())
                .fleetName(response.getFleetName())
                .carRegistries(response.getCarRegistries())
                .groups(response.getGroups())
                .build();
    }
}
