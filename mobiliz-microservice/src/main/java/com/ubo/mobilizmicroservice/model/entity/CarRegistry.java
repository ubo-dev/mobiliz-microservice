package com.ubo.mobilizmicroservice.model.entity;

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

    @Column(name = "sase_no")
    private Integer vin;

    @Column(name = "plaka")
    private String plate;

    @Column(name = "marka")
    private String brand;

    private String model;

    @Column(name = "modelYili")
    private Integer modelYear;

}
