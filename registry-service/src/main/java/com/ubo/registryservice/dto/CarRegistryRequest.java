package com.ubo.registryservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRegistryRequest {
    private Integer vin;
    private String plate;
    private String brand;
    private String model;
    private Integer modelYear;
}
