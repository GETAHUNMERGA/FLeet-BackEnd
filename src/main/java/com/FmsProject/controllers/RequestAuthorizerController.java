package com.FmsProject.controllers;

import java.util.List;

import com.FmsProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FmsProject.services.RequestAuthorizerService;

@RestController
@CrossOrigin
@RequestMapping("/requestAuthorize/")
public class RequestAuthorizerController {
	@Autowired
	RequestAuthorizerService authorizerService;

	@GetMapping("/getOfftimeRequests/{directorate}")
	public ResponseEntity<List<OffTimeRequestModel>> getOfftimeRequests(
			@PathVariable("directorate") String directorate) {
		List<OffTimeRequestModel> requests = authorizerService.getOfftimeRequests(directorate);
		return new ResponseEntity<>(requests, HttpStatus.OK);
	}

	@PutMapping("authorize")
	public ResponseEntity<OffTimeRequestModel> authorizeOfftimeRequests(@RequestBody OffTimeRequestModel offtime) {
		OffTimeRequestModel request = authorizerService.authorizeOfftimeRequests(offtime);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	@PutMapping("reject")
	public ResponseEntity<OffTimeRequestModel> rejectOfftimeRequests(@RequestBody OffTimeRequestModel offtime) {
		OffTimeRequestModel request = authorizerService.rejectOfftimeRequests(offtime);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	@GetMapping("/getInCityRequests/{directorate}")
	public ResponseEntity<List<InCityRequestModel>> getInCityRequests(@PathVariable("directorate") String directorate) {
		List<InCityRequestModel> requests = authorizerService.getInCityRequests(directorate);
		return new ResponseEntity<>(requests, HttpStatus.OK);
	}

	@PutMapping("authorizeInCity")
	public ResponseEntity<InCityRequestModel> authorizeInCityRequests(@RequestBody InCityRequestModel incity) {
		InCityRequestModel request = authorizerService.authorizeInCityRequests(incity);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	@PutMapping("rejectInCity")
	public ResponseEntity<InCityRequestModel> rejectInCityRequests(@RequestBody InCityRequestModel incity) {
		InCityRequestModel request = authorizerService.rejectIncityRequests(incity);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	@GetMapping("/getFieldRequests/{directorate}")
	public ResponseEntity<List<FieldRequestModel>> getFieldRequests(@PathVariable("directorate") String directorate) {
		List<FieldRequestModel> requests = authorizerService.getFieldRequests(directorate);
		return new ResponseEntity<>(requests, HttpStatus.OK);
	}

	@PutMapping("authorizeField")
	public ResponseEntity<FieldRequestModel> authorizeFieldRequests(@RequestBody FieldRequestModel field) {
		FieldRequestModel request = authorizerService.authorizeFieldRequests(field);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	@PutMapping("rejectField")
	public ResponseEntity<FieldRequestModel> rejectFieldRequests(@RequestBody FieldRequestModel field) {
		FieldRequestModel request = authorizerService.rejectFieldRequests(field);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	// For Report
	@PostMapping("getOfftimeReport")
	public ResponseEntity<List<OffTimeRequestModel>> getOfftimeReport(@RequestBody AuthReqReport reqReport) {

		List<OffTimeRequestModel> response = authorizerService.getOfftimeReport(reqReport);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("getIncityReport")
	public ResponseEntity<List<InCityRequestModel>> getIncityReport(@RequestBody AuthReqReport reqReport) {

		List<InCityRequestModel> response = authorizerService.getIncityReport(reqReport);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("getFieldReport")
	public ResponseEntity<List<FieldRequestModel>> getFieldReport(@RequestBody AuthReqReport reqReport) {

		List<FieldRequestModel> response = authorizerService.getFieldReport(reqReport);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("alluser/{directorate}")
	public ResponseEntity<List<UserModel>> getAllUsers(@PathVariable("directorate") String directorate) {
		List<UserModel> users = authorizerService.getAllUsers(directorate);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("delegateUser")
	public ResponseEntity<UserModel> delegateAuthorizer(@RequestBody UserModel user) {

		UserModel response = authorizerService.delegateAuthorizer(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("deactivateDelegation")
	public ResponseEntity<UserModel> deactivateDelegation(@RequestBody UserModel user) {

		UserModel response = authorizerService.deactivateDelegation(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("mechanicUsers")
	public ResponseEntity<List<UserModel>> getMechanicUsers() {
		List<UserModel> users = authorizerService.getMechanicUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("dispatcherUsers")
	public ResponseEntity<List<UserModel>> getDispatcherUsers() {
		List<UserModel> users = authorizerService.getDispatcherUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
