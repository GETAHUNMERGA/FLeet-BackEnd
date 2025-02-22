package com.FmsProject.controllers;

import com.FmsProject.models.*;
import com.FmsProject.services.GSAuthorizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/maintAuthorize/")
public class GSAuthorizerController {

    @Autowired
    GSAuthorizerService authorizerService;

    @GetMapping("getVerifiedRequests")
    public ResponseEntity<List<MaintenanceRequestModel>> getVerifiedRequests() {
        List<MaintenanceRequestModel> requests = authorizerService.getVerifiedRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("authorize")
    public ResponseEntity<MaintenanceRequestModel> authorizeVerifiedRequests(
            @RequestBody MaintenanceRequestModel model) {
        MaintenanceRequestModel request = authorizerService.authorizeVerifiedRequests(model);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PutMapping("reject")
    public ResponseEntity<MaintenanceRequestModel> rejectVerifiedRequests(@RequestBody MaintenanceRequestModel model) {
        MaintenanceRequestModel request = authorizerService.rejectVerifiedRequests(model);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping("getVehiclesSubmitted")
    public ResponseEntity<List<MaintenanceCompletionModel>> getVehiclesSubmitted() {
        List<MaintenanceCompletionModel> requests = authorizerService.getVehiclesSubmitted();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("authorizeSubmittedVehicles")
    public ResponseEntity<MaintenanceCompletionModel> authorizeSubmittedVehicles(
            @RequestBody MaintenanceCompletionModel model) {
        MaintenanceCompletionModel request = authorizerService.authorizeSubmittedVehicles(model);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    // For report

    @PostMapping("getRequestsReport")
    public ResponseEntity<List<MaintenanceRequestModel>> getRequestsReport(@RequestBody AuthMaintReport reqReport) {
        // System.out.println(request);
        List<MaintenanceRequestModel> response = authorizerService.getRequestsReport(reqReport);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("getCompletedReport")
    public ResponseEntity<List<MaintenanceCompletionModel>> getCompletedReport(@RequestBody AuthCompReport compReport) {
        // System.out.println(request);
        List<MaintenanceCompletionModel> response = authorizerService.getCompletedReport(compReport);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
