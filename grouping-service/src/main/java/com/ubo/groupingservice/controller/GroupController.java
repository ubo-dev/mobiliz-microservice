package com.ubo.groupingservice.controller;

import com.ubo.groupingservice.config.WebClientConfig;
import com.ubo.groupingservice.dto.CourierRequest;
import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.model.entity.CarRegistry;
import com.ubo.groupingservice.service.GroupService;
import com.ubo.groupingservice.util.ResponseHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private final GroupService groupService;

    private final WebClientConfig webClient;


    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllGroups() {
        try {
            List<GroupResponse> groups = groupService.getAllGroups();
            return ResponseHandler.generateResponse("Groups are listed successfully.", HttpStatus.OK,groups);
        } catch (GroupNotFoundException exception) {
            return ResponseHandler.generateResponse("No group found to list.",HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/getGroupById/{id}")
    public ResponseEntity<Object> getGroupById(@PathVariable Integer id) {
        try {
            GroupResponse group = groupService.getGroupById(id);
            return ResponseHandler.generateResponse("Group is listed successfully.", HttpStatus.OK,group);
        } catch (GroupNotFoundException exception) {
            return ResponseHandler.generateResponse("No group found to list.",HttpStatus.MULTI_STATUS,null);
        }
    }



    @GetMapping("/getRegistriesById/{id}")
    public ResponseEntity<Object> getRegistriesById(@PathVariable Integer id) {

        CarRegistry registryMono =  webClient.webClient()
                .get().uri(uriBuilder ->
                        uriBuilder.pathSegment("registries", "{id}").build(id))
                .retrieve()
                .bodyToMono(CarRegistry.class)
                .doOnError(error -> log.error("There is an error while sending request {}", error.getMessage()))
                .onErrorResume(error -> Mono.just(new CarRegistry()))
                .block();

        return ResponseHandler.generateResponse("Car registry",HttpStatus.OK,registryMono);
    }



    @PostMapping("/createGroup")
    public ResponseEntity<Object> createGroup(@RequestBody GroupRequest request) {
        try {
            groupService.createGroup(request);
            return ResponseHandler.generateResponse("Group created successfully.",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/createCourier/{id}")
    public ResponseEntity<Object> createCourier(@RequestBody CourierRequest request, @PathVariable Integer id) {
        try {
            groupService.createCourierForGroup(request,id);
            return ResponseHandler.generateResponse("Courier for group is created successfully",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
