package com.FmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.OffTimeRequestModel;
import com.FmsProject.services.DispatcherPrintService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/dispatcherPrint")
public class DispatcherPrintController {
    @Autowired
    DispatcherPrintService printService;

    @GetMapping("/incityrequest")

    public ResponseEntity<List<InCityRequestModel>> getApprovedIncityRequest() {
        List<InCityRequestModel> incityrequests = printService.getApprovedIncityRequest();
        return new ResponseEntity<>(incityrequests, HttpStatus.OK);
    }

    @GetMapping("/offtimerequest")
    public ResponseEntity<List<OffTimeRequestModel>> getApprovedOfftimeRequest() {
        List<OffTimeRequestModel> offtimerequests = printService.getApprovedOfftimeRequest();
        return new ResponseEntity<>(offtimerequests, HttpStatus.OK);
    }

    @GetMapping("/fieldrequest")
    public ResponseEntity<List<FieldRequestModel>> getApprovedFieldRequest() {
        List<FieldRequestModel> fieldrequests = printService.getApprovedFieldRequest();
        return new ResponseEntity<>(fieldrequests, HttpStatus.OK);
    }

}
