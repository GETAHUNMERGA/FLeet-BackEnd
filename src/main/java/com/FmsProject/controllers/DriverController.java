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

import com.FmsProject.models.DriverModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.services.DriverService;

@RestController
@CrossOrigin
@RequestMapping("/fieldapproval")
public class DriverController {
	@Autowired
	private DriverService _driverService;

	@GetMapping("/drivers")
	public List<DriverModel> getAll() {
		return _driverService.getDrivers();
	}

	@PostMapping("/drivers")
	public ResponseEntity<DriverModel> createRequests(@RequestBody DriverModel driv) {
		DriverModel d = _driverService.saveDrivers(driv);
		return new ResponseEntity(d, HttpStatus.OK);
	}

	@GetMapping("/drivers/{id}")
	public Optional<DriverModel> getDriverById(@PathVariable Integer id) {
		return _driverService.getDriverById(id);
	}

	@GetMapping("/drivertell/{phone}")
	public Optional<DriverModel> getDriverByPhone(@PathVariable String phone) {
		return _driverService.getDriverByPhone(phone);
	}

	@GetMapping("/vehicle/{plateNo}")
	public Optional<VehicleModel> getVehicleByPlateNo(@PathVariable String plateNo) {
		return _driverService.getVehicleByPlateNo(plateNo);
	}

	@GetMapping("/driversPlate/{plateNo}")
	public Optional<DriverModel> getDriverByPlateNo(@PathVariable String plateNo) {
		return _driverService.getDriverByPlateNo(plateNo);
	}

	@GetMapping("/vehicle")
	public List<VehicleModel> getVehicleByStatus() {
		return _driverService.getVehicleByStatus();
	}

	@GetMapping("/isActive")
	public List<VehicleModel> getActiveVehicle() {
		return _driverService.getActiveVehicle();
	}

	@GetMapping("/driverstatus")
	public List<DriverModel> getDriverByStatus() {
		return _driverService.getDriverByStatus();
	}

	@GetMapping("/plate/{fullname}")
	public Optional<DriverModel> getDriverByFullname(@PathVariable String fullname) {
		return _driverService.getDriverByFullname(fullname);
	}

	@DeleteMapping("/drivers/{id}")
	public ResponseEntity<?> deleteDriver(@PathVariable("id") Long id) {
		_driverService.deleteDriver(id);
		return new ResponseEntity<>("Driver Deleted Successfully", HttpStatus.OK);
	}

	@PutMapping("/drivers")
	public void updateDriver(@RequestBody DriverModel driv) {
		_driverService.updateDriver(driv);
	}

	@PutMapping("/disabledrivers")
	public void disableDriver(@RequestBody DriverModel driv) {
		_driverService.disableDriver(driv);
	}

	@PutMapping("/enabledrivers")
	public void enableDriver(@RequestBody DriverModel driv) {
		_driverService.enableDriver(driv);
		// return new ResponseEntity<>("Driver Deleted Successfully", HttpStatus.OK);
	}

	@PutMapping("/updatedrivers/{plateNo}")
	public ResponseEntity<?> updateDriverVehicleId(@PathVariable String plateNo, @RequestBody DriverModel vid) {
		if (_driverService.getVehicleByPlateNo(plateNo) != null) {

			_driverService.updateDriverVehicleId(vid);

			return ResponseEntity.ok().body(vid);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Vehicle With ID " + plateNo + " Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}

	}

}
