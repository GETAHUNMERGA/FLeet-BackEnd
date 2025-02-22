package com.FmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.models.SubmissionReportModel;
import com.FmsProject.services.MechanicService;

@RestController
@CrossOrigin
@RequestMapping("/mechanic/")
public class MechanicController {
    @Autowired
    MechanicService mechanicService;

    @GetMapping("getCustodianRequest")
    public ResponseEntity<List<MaintenanceRequestModel>> getCustodianRequest() {
        List<MaintenanceRequestModel> requests = mechanicService.getCustodianRequest();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("getMaintenanceType/{maintType}")
    public ResponseEntity<MaintenanceTypeModel> getMaintenanceType(@PathVariable String maintType) {
        MaintenanceTypeModel maintenanceType = mechanicService.getMaintenanceType(maintType);
        return new ResponseEntity<>(maintenanceType, HttpStatus.OK);
    }

    @PutMapping("verifyRequest")
    public ResponseEntity<MaintenanceRequestModel> verifyCustodianRequest(
            @RequestBody MaintenanceRequestModel request) {
        MaintenanceRequestModel response = mechanicService.verifyCustodianRequest(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getMechanicRequests")
    public ResponseEntity<List<MaintenanceRequestModel>> getMechanicRequests() {
        List<MaintenanceRequestModel> requests = mechanicService.getMechanicRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("getCompletedRequests")
    public ResponseEntity<List<MaintenanceCompletionModel>> getCompletedRequests() {
        List<MaintenanceCompletionModel> requests = mechanicService.getCompletedRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("getRequestDetails/{request}")
    public ResponseEntity<MaintenanceRequestModel> getRequestDetails(@PathVariable int request) {
        MaintenanceRequestModel res = mechanicService.getRequestDetails(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("modifyMaintenanceCompletion")
    public ResponseEntity<MaintenanceCompletionModel> modifyMaintenanceCompletion(
            @RequestBody MaintenanceCompletionModel request) {
        MaintenanceCompletionModel response = mechanicService.modifyMaintenanceCompletion(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Unverified custodian requests
    @PostMapping("unverifiedRequestReport")
    public ResponseEntity<List<MaintenanceRequestModel>> getUnverifiedRequestReport(@RequestBody ReportModel request) {
        List<MaintenanceRequestModel> response = mechanicService.getUnverifiedRequestReport(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // mechanic requests report
    @PostMapping("mechanicRequestReport")
    public ResponseEntity<List<MaintenanceRequestModel>> getMechanicRequestReport(@RequestBody ReportModel request) {
        // System.out.println(request);
        List<MaintenanceRequestModel> response = mechanicService.getMechanicRequestReport(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mechanic submitted request
    @PostMapping("submittedRequestReport")
    public ResponseEntity<List<SubmissionReportModel>> getMechanicSubmittedRequestReport(
            @RequestBody ReportModel request) {
        // System.out.println(request);
        List<SubmissionReportModel> response = mechanicService.getMechanicSubmittedRequestReport(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("unverifiedNo")
    public int getUnverifiedNo() {
        return mechanicService.getUnverifiedNo();
    }

    @GetMapping("verifiedNo")
    public int getVerifiedNo() {
        return mechanicService.getVerifiedNo();
    }

    @GetMapping("authorizedNo")
    public int getAuthorizedRequestNo() {
        return mechanicService.getAuthorizedRequestNo();
    }

    @GetMapping("completionTechnicalNo")
    public int getNoCompletionTechnical() {
        return mechanicService.getNoCompletionTechnical();
    }

    @GetMapping("mechanicSubmittedNo")
    public int getNoSubmittedMechanic() {
        return mechanicService.getNoSubmittedMechanic();
    }

}
