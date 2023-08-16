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
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courierName;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<CarRegistry> carRegistries;

    @ManyToOne
    private Group group;

}
