package com.ubo.groupingservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRegistry {

    @Id
    private Integer id;

    private Integer vin;

    private String plate;

    private String brand;

    private String model;

    private Integer modelYear;

}
