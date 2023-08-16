package com.ubo.groupingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRegistry implements Serializable {

    @Id
    private Integer id;

    private Integer vin;

    private String plate;

    private String brand;

    private String model;

    private Integer modelYear;

    @ManyToMany(mappedBy = "carRegistries", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Group> groupList;

    @ManyToMany(mappedBy = "carRegistries", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Courier> couriers;

    @ManyToMany(mappedBy = "carRegistries", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Fleet> fleets;

}
