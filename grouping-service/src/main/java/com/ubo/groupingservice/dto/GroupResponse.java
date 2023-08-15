package com.ubo.groupingservice.dto;

import com.ubo.groupingservice.model.entity.CarRegistry;
import com.ubo.groupingservice.model.entity.Group;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupResponse {

    private Integer id;
    private String groupName;
    private List<CarRegistry> carRegistries;
    private List<Group> innerGroups;
}
