package com.FmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.OffTimeRequestModel;
import com.FmsProject.services.CheckAuthorizedReqService;

@RestController
@CrossOrigin
@RequestMapping("/checkAuthorizedReq/")
public class CheckAuthorizedReqController {
    @Autowired
    CheckAuthorizedReqService authorizerService;

    @GetMapping("/getFieldRequests")
    public ResponseEntity<List<FieldRequestModel>> getFieldRequests() {
        List<FieldRequestModel> requests = authorizerService.getFieldRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("authorizeField")
    public ResponseEntity<FieldRequestModel> authorizeFieldRequests(@RequestBody FieldRequestModel field) {
        FieldRequestModel request = authorizerService.authorizeFieldRequests(field);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PutMapping("rejectField")
    public ResponseEntity<FieldRequestModel> rejectFieldRequests(@RequestBody FieldRequestModel field) {
        FieldRequestModel request = authorizerService.rejectFieldRequests(field);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping("/getOfftimeRequests")
    public ResponseEntity<List<OffTimeRequestModel>> getOfftimeRequests() {
        List<OffTimeRequestModel> requests = authorizerService.getOfftimeRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("authorize")
    public ResponseEntity<OffTimeRequestModel> authorizeOfftimeRequests(@RequestBody OffTimeRequestModel offtime) {
        OffTimeRequestModel request = authorizerService.authorizeOfftimeRequests(offtime);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PutMapping("reject")
    public ResponseEntity<OffTimeRequestModel> rejectOfftimeRequests(@RequestBody OffTimeRequestModel offtime) {
        OffTimeRequestModel request = authorizerService.rejectOfftimeRequests(offtime);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
