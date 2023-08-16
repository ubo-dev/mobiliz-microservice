package com.ubo.groupingservice.controller;

import com.ubo.groupingservice.dto.FleetRequest;
import com.ubo.groupingservice.dto.FleetResponse;
import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.FleetNotFoundException;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.service.FleetService;
import com.ubo.groupingservice.service.GroupService;
import com.ubo.groupingservice.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

@RestController
@RequestMapping("/fleets")
@RequiredArgsConstructor
public class FleetController {

    private final FleetService fleetService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllFleets() {
        try {
            List<FleetResponse> fleets = fleetService.getAllFleets();
            return ResponseHandler.generateResponse("Fleets are listed successfully.", HttpStatus.OK,fleets);
        } catch (FleetNotFoundException exception) {
            return ResponseHandler.generateResponse("No fleet found to list.",HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/getFleetById/{id}")
    public ResponseEntity<Object> getFleetById(@PathVariable Integer id) {
        try {
            FleetResponse fleet = fleetService.getFleetById(id);
            return ResponseHandler.generateResponse("Fleet is listed successfully.", HttpStatus.OK,fleet);
        } catch (FleetNotFoundException exception) {
            return ResponseHandler.generateResponse("No fleet found to list.",HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/createFleet")
    public ResponseEntity<Object> createFleet(@RequestBody FleetRequest request) {
        try {
            fleetService.createFleet(request);
            return ResponseHandler.generateResponse("Fleet created successfully.",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/createGroup/{id}")
    public ResponseEntity<Object> createGroup(@RequestBody GroupRequest request, @PathVariable Integer id) {
        try {
            fleetService.createGroupForFleet(request,id);
            return ResponseHandler.generateResponse("Group created for fleet successfully",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
