package com.FmsProject.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.DriverModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.FuelDetailDto;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.OffTimeDispatcherModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;
import com.FmsProject.services.DispatcherService;
import com.FmsProject.services.FieldRequestService;

@RestController
@CrossOrigin
@RequestMapping("/fieldapproval")
public class DispatcherController {

	@Autowired
	private DispatcherService _dispatcherService;

	@Autowired
	private FieldRequestService _fieldService;

	@GetMapping("/approve")
	public List<DispatcherModel> getAllRequest() {
		return _dispatcherService.getRequsition();
	}

	@GetMapping("/vehicletypes")
	public List<VehicleTypesModel> getAllVehicleTypes() {
		return _dispatcherService.getAllVehicleTypes();
	}

	@GetMapping("/vehiclebytype/{vtypes}")
	public List<VehicleModel> getVehicleByType(@PathVariable Integer vtypes) {
		return _dispatcherService.getVehicleByType(vtypes);
	}

	@GetMapping("/vtypebyid/{id}")
	public Optional<VehicleTypesModel> getVehicleTypeById(@PathVariable Integer id) {
		return _dispatcherService.getVehicleTypeById(id);
	}
	@GetMapping("/fueltype/{id}")
	public Optional<FuelDetailDto> getFuelTypeById(@PathVariable Integer id) {
		return _dispatcherService.getFuelTypeById(id);
	}

	@GetMapping("/checkdriver/{driverName}")
	public Optional<DispatcherModel> getDispatchedByDriverName(@PathVariable String driverName) {
		return _dispatcherService.getDispatchedByDriverName(driverName);
	}

	@GetMapping("/checkdriverincity/{driverName}")
	public Optional<InCityDispatcherModel> getIncityDispatchedByDriverName(@PathVariable String driverName) {
		return _dispatcherService.getIncityDispatchedByDriverName(driverName);
	}

	@GetMapping("/checkdriverofftime/{driverName}")
	public Optional<OffTimeDispatcherModel> getOfftimeDispatchedByDriverName(@PathVariable String driverName) {
		return _dispatcherService.getOfftimeDispatchedByDriverName(driverName);
	}

	@GetMapping("/approvedrequest")
	public List<DispatcherModel> getAllAprovedRequest() {
		return _dispatcherService.getAllAprovedRequest();
	}

	@PostMapping("/approve")
	public ResponseEntity<DispatcherModel> approveRequests(@RequestBody DispatcherModel req) {
		DispatcherModel dis = _dispatcherService.approveRequests(req);
		return new ResponseEntity(dis, HttpStatus.OK);

	}

	@GetMapping("/approve/{id}")
	public Optional<DispatcherModel> getRequestById(@PathVariable("id") String id) {
		Integer ReqID = Integer.parseInt(id);
		return _dispatcherService.getRequestById(ReqID);
	}

	@GetMapping("/getapproved/{reqid}")
	public Optional<DispatcherModel> getDispatcherByRequesterID(@PathVariable("reqid") String reqid) {
		Integer ReqID = Integer.parseInt(reqid);
		return _dispatcherService.getDispatcherByRequesterID(ReqID);
	}

	@PutMapping("/updateapprovedrequest/{reqId}")
	public void updateApprovedRequest(@PathVariable("reqId") String reqId, @RequestBody DispatcherModel driv) {
		_dispatcherService.updateApprovedRequest(driv);
	}

}
