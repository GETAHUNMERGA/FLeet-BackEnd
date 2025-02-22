package com.FmsProject.controllers;

import java.util.HashMap;
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

import com.FmsProject.models.DriverModel;
import com.FmsProject.models.GarageModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.services.TechnicalserviceService;

@RestController
@CrossOrigin
@RequestMapping("/technical")
public class TechnicalServiceController {
	@Autowired
	private TechnicalserviceService _technical;

	@GetMapping("/service")
	public List<MaintenanceRequestModel> getAllMaintenenceRequest() {
		return _technical.getAllMaintenenceRequest();
	}

	@GetMapping("/complete")
	public List<MaintenanceRequestModel> getMaintenanceCompletedVehicle() {
		return _technical.getMaintenanceCompletedVehicle();
	}

	@GetMapping("/garage")
	public List<GarageModel> getGarage() {
		return _technical.getGarage();
	}

	@PutMapping("/assigngarage/{id}")
	public void assignGarage(@PathVariable Long id, @RequestBody MaintenanceRequestModel req) {
		_technical.assignGarage(req);
		// return new ResponseEntity<>("Driver Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/numOfAauthorizedRequests")
	public int getNoOfAuthorizedrequest() {
		return _technical.getNoOfAuthorizedrequest();
	}

	@GetMapping("/numOfUnderMaintenance")
	public int getNoOfUnderMaintenance() {
		return _technical.getNoOfUnderMaintenance();
	}

	@GetMapping("/numOfGarage")
	public int getNoOfGarage() {
		return _technical.getNoOfGarage();
	}

	@GetMapping("/getMaintReq/{id}")
	public ResponseEntity<MaintenanceRequestModel> getMaintenanceRequest(@PathVariable String id) {
		Integer mainteId = Integer.parseInt(id);
		MaintenanceRequestModel response = _technical.getMaintenanceRequest(mainteId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
