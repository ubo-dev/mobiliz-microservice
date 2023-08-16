package com.ubo.registryservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer vin;

    private String plate;

    private String brand;

    private String model;

    private Integer modelYear;

}
