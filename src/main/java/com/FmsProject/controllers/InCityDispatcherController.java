package com.FmsProject.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;
import com.FmsProject.services.InCityDispatcherService;

@RestController
@CrossOrigin
@RequestMapping("/incitydispatch")
public class InCityDispatcherController {

	@Autowired
	private InCityDispatcherService service;

	@PostMapping("/save")
	public ResponseEntity<InCityDispatcherModel> saveInCityDispatch(@RequestBody InCityDispatcherModel model) {
		InCityDispatcherModel response = service.saveIncityRequestService(model);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/incitydispatchview")
	public List<InCityDispatcherModel> viewIncityDispatcherController() {
		return service.viewIncityDispatchService();
	}

	@PutMapping("/reject/{id}")
	public void rejectRequest(@RequestBody InCityDispatcherModel req, @PathVariable("id") String id) {
		Integer reqId = Integer.parseInt(id);
		if (service.selectRequestById(reqId) != null) {

			service.rejectRequest(req);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requesition With id " + id + " Not Found");
		}

	}

	@GetMapping("/incitydispatchbyid/{id}")
	public Optional<InCityDispatcherModel> selectDispatcherById(@PathVariable("id") String id) {
		Integer reqid = Integer.parseInt(id);
		return service.selectDispatcherById(reqid);
	}

	@GetMapping("/vehicle/{type}")
	public List<VehicleModel> getvehiclebystatus(@PathVariable String type) {
		return service.getvehiclebystatus(type);
	}

	@GetMapping("/drivername")
	public List<DriverModel> getDriverName() {
		return service.getDriverName();
	}

	@GetMapping("/departurekm/{vehiclePlateNo}")
	public Optional<VehicleModel> getDepartureKm(@PathVariable String vehiclePlateNo) {
		return service.getDepartureKm(vehiclePlateNo);
	}

	@GetMapping("/plateNo/{id}")
	public Optional<InCityDispatcherModel> selectDispatchedPlateNo(@PathVariable("id") String id) {
		Integer reqid = Integer.parseInt(id);
		return service.selectDispatchedPlateNo(reqid);
	}

	@GetMapping("/driverphonenumber/{plateNo}")
	public Optional<DriverModel> getDriverPhoneNumber(@PathVariable String plateNo) {
		return service.getDriverPhoneNumber(plateNo);
	}

	@PutMapping("/modifyrkm/{id}")
	public void modifyDispatchedReturnKm(@PathVariable Integer id, @RequestBody InCityDispatcherModel model) {
		service.modifyDispatchedReturnKm(model);
	}

	@GetMapping("/vehicletype")
	public List<VehicleTypesModel> getVehicleTypes() {
		return service.getVehicleTypes();
	}

	@PutMapping("/setoffdriver/{name}")
	public void setDriveOff(@PathVariable String name) {
		service.setDriveOff(name);
	}

}
