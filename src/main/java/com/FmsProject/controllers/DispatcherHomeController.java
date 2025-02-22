package com.FmsProject.controllers;

import com.FmsProject.services.DispatcherHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/Dispatcher/")
public class DispatcherHomeController {

    @Autowired
    private DispatcherHomeService dispatcherService;

    @GetMapping("noOfIncity")
    public int getNumOfIncity() {
        return dispatcherService.getNumOfIncity();
    }

    @GetMapping("noOfOfftime")
    public int getNumOfOfftime() {
        return dispatcherService.getNumOfOfftime();
    }

    @GetMapping("noOfField")
    public int getNumOfField() {
        return dispatcherService.getNumOfField();
    }

    @GetMapping("noOfPooled")
    public int getNumOfPooledVehicle() {
        return dispatcherService.getNumOfPooledVehicle();
    }

    @GetMapping("noOfMaintenance")
    public int getNumOfMaintenanceVehicle() {
        return dispatcherService.getNumOfMaintenanceVehicle();
    }

    @GetMapping("noOfDisposed")
    public int getNumOfDisposedVehicle() {
        return dispatcherService.getNumOfDisposedVehicle();
    }

    @GetMapping("noOfDispatched")
    public int getNumOfDispatchedVehicle() {
        return dispatcherService.getNumOfDispatchedVehicle();
    }
}
