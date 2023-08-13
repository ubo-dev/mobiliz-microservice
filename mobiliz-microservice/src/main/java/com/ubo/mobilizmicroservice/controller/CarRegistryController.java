package com.ubo.mobilizmicroservice.controller;

import com.ubo.mobilizmicroservice.dto.CarRegistryRequest;
import com.ubo.mobilizmicroservice.dto.CarRegistryResponse;
import com.ubo.mobilizmicroservice.exception.RegistryNotFoundException;
import com.ubo.mobilizmicroservice.model.entity.CarRegistry;
import com.ubo.mobilizmicroservice.service.CarRegistryService;
import com.ubo.mobilizmicroservice.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarRegistryController {

    private final CarRegistryService carRegistryService;

    @GetMapping("/registries")
    public ResponseEntity<Object> getAllRegistries() {
        try {
            List<CarRegistryResponse> registries = carRegistryService.getAllCarRegistry();
            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK,registries);

        }catch (RegistryNotFoundException exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/registries/{id}")
    public ResponseEntity<Object> getRegistryById(@PathVariable Integer id){
        try {
            CarRegistry registry = carRegistryService.getCarRegistryById(id);
            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK,registry);

        }catch (RegistryNotFoundException exception){
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/registries")
    public ResponseEntity<Object> createCarRegistry(@RequestBody CarRegistryRequest carRegistryRequest) {
        try {
            carRegistryService.createCarRegistry(carRegistryRequest);
            return ResponseHandler.generateResponse("Successfully added registry", HttpStatus.CREATED, carRegistryRequest);

        }catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/registries")
    public ResponseEntity<Object> updateCarRegistry(@RequestBody CarRegistryRequest carRegistryRequest) {
        try {
            carRegistryService.createCarRegistry(carRegistryRequest);
            return ResponseHandler.generateResponse("Successfully updated registry", HttpStatus.CREATED, carRegistryRequest);

        }catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }



}
