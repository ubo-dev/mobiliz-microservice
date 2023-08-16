package com.ubo.groupingservice.controller;

import com.ubo.groupingservice.dto.CourierRequest;
import com.ubo.groupingservice.dto.CourierResponse;
import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.CourierNotFoundException;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.service.CourierService;
import com.ubo.groupingservice.service.GroupService;
import com.ubo.groupingservice.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllCouriers() {
        try {
            List<CourierResponse> couriers = courierService.getAllCouriers();
            return ResponseHandler.generateResponse("Courier are listed successfully.", HttpStatus.OK,couriers);
        } catch (CourierNotFoundException exception) {
            return ResponseHandler.generateResponse("No Courier found to list.",HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/getCourierById/{id}")
    public ResponseEntity<Object> getGroupById(@PathVariable Integer id) {
        try {
            CourierResponse courier = courierService.getCourierById(id);
            return ResponseHandler.generateResponse("Courier is listed successfully.", HttpStatus.OK,courier);
        } catch (CourierNotFoundException exception) {
            return ResponseHandler.generateResponse("No Courier found to list.",HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/createCourier")
    public ResponseEntity<Object> createGroup(@RequestBody CourierRequest request) {
        try {
            courierService.createCourier(request);
            return ResponseHandler.generateResponse("Courier created successfully.",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

}
