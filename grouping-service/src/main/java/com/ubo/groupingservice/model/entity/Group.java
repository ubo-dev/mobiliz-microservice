package com.ubo.groupingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "group_table")
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

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<CarRegistry> carRegistries;

    @ManyToOne
    private Fleet fleet;

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Courier> couriers;


}
