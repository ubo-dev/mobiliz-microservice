package com.ubo.groupingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fleet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fleetName;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<CarRegistry> carRegistries;

    @OneToMany(mappedBy = "fleet",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Group> groups;
}
