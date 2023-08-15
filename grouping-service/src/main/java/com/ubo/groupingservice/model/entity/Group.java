package com.ubo.groupingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<CarRegistry> carRegistries;

    @OneToMany(mappedBy = "group")
    private List<Group> innerGroups;


}
