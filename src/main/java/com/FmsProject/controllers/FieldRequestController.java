package com.FmsProject.controllers;

import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.services.FieldRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.services.FieldRequestService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/fieldrequest")
public class FieldRequestController {

	@Autowired
	private FieldRequestService _fieldRequest;

	@GetMapping("/requests")
	public List<FieldRequestModel> getAll() {
		return _fieldRequest.getRequsition();
	}

	@GetMapping("/myrequest/{username}")
	public ResponseEntity<List<FieldRequestModel>> getMyRequest(@PathVariable("username") String username) {
		List<FieldRequestModel> response = _fieldRequest.getmyRequsition(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/approvedstatus")
	public List<FieldRequestModel> getAllApprovedRequest() {
		return _fieldRequest.getAllApprovedRequest();
	}

	@GetMapping("/dispatchedvehicle")
	public List<VehicleModel> getDispatchedVehicle() {
		return _fieldRequest.getDispatchedVehicle();
	}

	@GetMapping("/dispatchrequests")
	public List<FieldRequestModel> getAllDispatch() {
		return _fieldRequest.getRequsitionDispatch();
	}

	@PostMapping("/requests")
	public ResponseEntity<FieldRequestModel> createRequests(@RequestBody FieldRequestModel req) {
		FieldRequestModel f = _fieldRequest.sendRequisition(req);
		return new ResponseEntity(f, HttpStatus.OK);
	}

	@GetMapping("/requestCount")
	public Long departmentCount() {
		return _fieldRequest.requisitionCount();
	}

	@GetMapping("/requests/{id}")
	public Optional<FieldRequestModel> selectRequestById(@PathVariable Integer id) {
		return _fieldRequest.selectRequestById(id);
	}

	@DeleteMapping("/requests/{id}")
	public void deleteDepartments(@PathVariable("id") Long id) {
		_fieldRequest.deleteRequest(id);
	}

	@PutMapping("/updaterequest")
	public void updateRequest(@RequestBody FieldRequestModel req) {

		_fieldRequest.updateRequest(req);
	}

	@PutMapping("/approvestatus/{id}")
	public ResponseEntity<?> approveRequest(@RequestBody FieldRequestModel req, @PathVariable Integer id) {
		if (_fieldRequest.selectRequestById(id) != null) {
			_fieldRequest.approveRequest(req);
			return ResponseEntity.ok().body(req);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requesition With ID " + id + " Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

	@PutMapping("/rejectstatus/{id}")
	public void rejectRequest(@RequestBody FieldRequestModel req, @PathVariable Integer id) {
		_fieldRequest.rejectRequest(req);

	}

	@PutMapping("/addrejecter/{id}")
	public void addRejecter(@RequestBody FieldRequestModel rejecter, @PathVariable Integer id) {
		_fieldRequest.addRejecter(rejecter);

	}

}
