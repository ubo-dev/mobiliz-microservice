package com.ubo.groupingservice.controller;

import com.ubo.groupingservice.dto.GroupRequest;
import com.ubo.groupingservice.dto.GroupResponse;
import com.ubo.groupingservice.exception.GroupNotFoundException;
import com.ubo.groupingservice.service.GroupService;
import com.ubo.groupingservice.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/grouping-service")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

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

    @PostMapping("/createGroup")
    public ResponseEntity<Object> createGroup(@RequestBody GroupRequest request) {
        try {
            groupService.createGroup(request);
            return ResponseHandler.generateResponse("Group created successfully.",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/createInnerGroup/{id}")
    public ResponseEntity<Object> createInnerGroup(@RequestBody GroupRequest request, @PathVariable Integer id) {
        try {
            groupService.createInnerGroup(request,id);
            return ResponseHandler.generateResponse("Inner group created successfully",HttpStatus.CREATED,request);
        } catch (Exception exception) {
            return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
