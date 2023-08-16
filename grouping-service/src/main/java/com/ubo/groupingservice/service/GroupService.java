package com.ubo.groupingservice.service;

import com.ubo.groupingservice.dto.CourierRequest;
import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.model.entity.CarRegistry;
import com.ubo.groupingservice.model.entity.Courier;
import com.ubo.groupingservice.model.entity.Group;
import com.ubo.groupingservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;




    public void createGroup(GroupRequest request) {
        Group group = Group.builder()
                .groupName(request.getGroupName())
                .build();

        groupRepository.save(group);
        log.info("Group with name: {} is created", group.getGroupName());
    }

    public void createCourierForGroup(CourierRequest request, Integer id) {
        Courier courier = Courier.builder()
                .courierName(request.getCourierName())
                .build();

        GroupResponse groupToAdd = getGroupById(id);

        List<Courier> courierList = groupToAdd.getCouriers();


        if (courierList.isEmpty()) {
            courierList.add(courier);
            groupToAdd.setCouriers(List.of(courier));
        } else {
            courierList.add(courier);
            groupToAdd.setCouriers(courierList);
        }

        Group group = mapToGroup(groupToAdd);

        groupRepository.save(group);
        log.info("Group with name: {} is updated with inner groups of: {}", groupToAdd.getGroupName(),groupToAdd.getCouriers());

    }

    public List<GroupResponse> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        if (groups.isEmpty())
            throw new GroupNotFoundException("No group has been found");

        return groups.stream().map(this::mapToGroupResponse).toList();
    }

    public GroupResponse getGroupById(Integer id) {
        Optional<Group> optional = groupRepository.findById(id);

        if (optional.isEmpty())
            throw new GroupNotFoundException("Group not found");

        return mapToGroupResponse(optional.get());
    }

    private GroupResponse mapToGroupResponse(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .carRegistries(group.getCarRegistries())
                .couriers(group.getCouriers())
                .build();
    }

    private Group mapToGroup(GroupResponse response) {
        return Group.builder()
                .id(response.getId())
                .groupName(response.getGroupName())
                .carRegistries(response.getCarRegistries())
                .couriers(response.getCouriers())
                .build();
    }
}
