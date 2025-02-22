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

import com.FmsProject.models.GarageModel;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.models.ServiceType;
import com.FmsProject.services.MaintenanceRequestService;

@RestController
@CrossOrigin
@RequestMapping("MaintenanceRequest")
public class MaintenanceRequestController {

    @Autowired
    MaintenanceRequestService requestService;

    @PostMapping("/sendRequest")
    public ResponseEntity<MaintenanceRequestModel> sendServiceMaintenanceRequest(
            @RequestBody MaintenanceRequestModel request) {
        MaintenanceRequestModel response = requestService.sendServiceMaintenanceRequest(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//Get Service Types to populate on
@GetMapping("/getServiceType")
public ResponseEntity<List<ServiceType>> getServiceType() {
  List<ServiceType> serviceType=requestService.getServiceType();
return new ResponseEntity<>(serviceType,HttpStatus.OK);
}

       // Dynamically insertions of Maintenance Service Types
@PostMapping("/saveServiceType")
public ResponseEntity<?> insertServiceType(@RequestBody String serviceType) {
    System.out.println("Service Type:"+serviceType);
    ServiceType saveServiceType = requestService.insertServiceType(serviceType);
    if(saveServiceType!=null){
        System.out.println("Service Save Successfully:"+serviceType);
        return new ResponseEntity<>(saveServiceType,HttpStatus.OK);   
        
    }
    else{
        System.out.println("Service Save Failed:"+serviceType);
        return ResponseEntity.ok("Service Type Insertion Failed");
    }
//return ResponseEntity.ok("Service Type Inserted Successfully");
}




    @GetMapping("/getAllRequest")
    public ResponseEntity<List<MaintenanceRequestModel>> getAllOfMyMaintenanceReqcuest() {
        List<MaintenanceRequestModel> requests = requestService.getAllOfMyMaintenanceReqcuest();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("/updateServiceRequest")
    public ResponseEntity<MaintenanceRequestModel> UpdateMaintenanceRequest(
            @RequestBody MaintenanceRequestModel request) {
        MaintenanceRequestModel updated = requestService.UpdateMaintenanceRequest(request);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/whatType/{mainType}")
    public ResponseEntity<MaintenanceTypeModel> WhatType(@PathVariable String mainType) {
        String maintenanceType = "nothing";
        MaintenanceTypeModel requestType = requestService.WhatType(mainType);
        return new ResponseEntity<>(requestType, HttpStatus.OK);
    }

    @PostMapping("/sendCustodianRequest")
    public ResponseEntity<MaintenanceRequestModel> sendCustodianMaintenanceRequest(
            @RequestBody MaintenanceRequestModel request) {
        MaintenanceRequestModel response = requestService.sendCustodianMaintenanceRequest(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCustodianRequest/{director}")
    public ResponseEntity<List<MaintenanceRequestModel>> getCustodianMaintenanceReqcuest(
            @PathVariable String director) {
        List<MaintenanceRequestModel> requests = requestService.getCustodianMaintenanceReqcuest(director);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("/updateCustodianRequest")
    public ResponseEntity<MaintenanceRequestModel> UpdateCustodianRequest(
            @RequestBody MaintenanceRequestModel request) {
        MaintenanceRequestModel updated = requestService.UpdateCustodianRequest(request);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/getMaintenanceDetails/{id}")
    public ResponseEntity<MaintenanceCompletionModel> getMaintenanceDetails(@PathVariable int id) {
        MaintenanceCompletionModel response = requestService.getMaintenanceDetails(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/requestReport")
    public ResponseEntity<List<MaintenanceRequestModel>> getReportOfMaintenanceRequests(
            @RequestBody ReportModel request) {
        List<MaintenanceRequestModel> response = requestService.getReportOfMaintenanceRequests(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/vehiclegarage/{id}")

    public ResponseEntity<GarageModel> getVehicleGarage(@PathVariable("id") String id) {
        Integer reqid = Integer.parseInt(id);
        GarageModel garage = requestService.getVehicleGarage(reqid);
        return new ResponseEntity<GarageModel>(garage, HttpStatus.OK);
    }

}
