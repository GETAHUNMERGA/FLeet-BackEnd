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

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.OffTimeRequestModel;
import com.FmsProject.services.InCityRequestService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/incity")
public class InCityRequestController {

	@Autowired
	private InCityRequestService incityrequestservice;

	@PostMapping("/incityrequest")
	public void saveInCityRequest(@RequestBody InCityRequestModel incityrequestmodel) {
		incityrequestservice.saveIncityRequestService(incityrequestmodel);
	}

	@GetMapping("/incityrequestview")
	public List<InCityRequestModel> viewIncityRequestController() {
		return incityrequestservice.viewIncityRequestService();
	}

	@GetMapping("/incityrequestviewbyusername/{name}")
	public List<InCityRequestModel> viewIncityRequestByUserName(@PathVariable("name") String name) {
		return incityrequestservice.viewIncityRequestByUserName(name);
	}

	@GetMapping("/incityrequestbyid/{id}")
	public Optional<InCityRequestModel> selectRequestById(@PathVariable("id") String id) {
		Integer reqid = Integer.parseInt(id);
		return incityrequestservice.selectRequestById(reqid);
	}

	@DeleteMapping("/incityrequestcancel/{id}")
	public void cancelInCityRequest(@PathVariable("id") String id) {
		Integer reqid = Integer.parseInt(id);
		incityrequestservice.cancelIncityRequestService(reqid);
	}

	@PutMapping("/incityrequestupdate/{id}")
	public void updatelInCityRequest(@RequestBody InCityRequestModel req, @PathVariable("id") String id) {
		Integer reqid = Integer.parseInt(id);
		if (incityrequestservice.selectRequestById(reqid) != null) {

			incityrequestservice.updateIncityRequestService(req);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requesition With id " + id + " Not Found");
		}

	}

	@PutMapping("/reject/{id}")
	public void rejectRequest(@RequestBody InCityRequestModel req, @PathVariable("id") String id,
			InCityDispatcherModel mod) {
		Integer reqid = Integer.parseInt(id);
		if (incityrequestservice.selectRequestById(reqid) != null) {
			incityrequestservice.rejectRequest(req, mod);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requesition With id " + id + " Not Found");
		}
	}

	@PutMapping("/approvestatus/{id}")
	public ResponseEntity<?> approveRequest(@PathVariable String id, @RequestBody InCityDispatcherModel req) {
		Integer reqid = Integer.parseInt(id);
		if (incityrequestservice.selectRequestById(reqid) != null) {
			incityrequestservice.approveRequest(req);
			return ResponseEntity.ok().body(req);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requesition With ID " + id + " Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

	@GetMapping("/incityapproved")
	public List<InCityRequestModel> incityRequestApproved() {
		return incityrequestservice.incityRequestApproved();
	}
}
