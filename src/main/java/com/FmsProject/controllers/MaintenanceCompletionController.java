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

import com.FmsProject.models.DriverModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceCostReportModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.services.MaintenanceCompletionService;

@RestController
@CrossOrigin
@RequestMapping("/maintenance")
public class MaintenanceCompletionController {
	@Autowired
	private MaintenanceCompletionService _service;

	@GetMapping("/getAll")
	public List<MaintenanceCompletionModel> getMaintenanceCompletion() {
		return _service.getMaintenanceCompletion();
	}

	@GetMapping("/costCount")
	public Float maintenanceConstCount() {
		return _service.maintenanceConstCount();
	}

	@PostMapping("/complete")
	public ResponseEntity<MaintenanceCompletionModel> completeMaintenance(
			@RequestBody MaintenanceCompletionModel comp) {
		MaintenanceCompletionModel d = _service.completeMaintenance(comp);
		return new ResponseEntity(d, HttpStatus.OK);
	}

	// maintenance cost by monthly
	@PostMapping("costreportbymonthrange")
	public ResponseEntity<List<MaintenanceCompletionModel>> costReport(@RequestBody MaintenanceCostReportModel report) {
		List<MaintenanceCompletionModel> response = _service.costReport(report);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @GetMapping("/costreportbyplateno/{req}")
	// public List<MaintenanceCompletionModel>
	// byplateNocostReport(@PathVariable("req") String req) {
	// System.out.println(req);
	// return _service.byplateNocostReport(req);
	// }

	@PostMapping("costreportbyplateno")
	public Float byplateNocostReport(@RequestBody MaintenanceCostReportModel report) {
		// System.out.println(report.getPlateNo());
		Float response = _service.byplateNocostReport(report.getPlateNo());
		return new Float(response);
	}

	// total maintenance cost by plate number
	@PostMapping("totalplatecost")
	public Float totalPlateNumberCost(@RequestBody MaintenanceCostReportModel report) {
		// System.out.println(report.getPlateNo());
		return _service.totalplatecost(report.getPlateNo());
	}

	// maintenance cost by yearly
	@PostMapping("costreportbyyearrange")
	public ResponseEntity<List<MaintenanceCompletionModel>> yearlyMaintenanceCostReport(
			@RequestBody MaintenanceCostReportModel report) {
		List<MaintenanceCompletionModel> response = _service.yearlyMaintenanceCostReport(report);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// total maintenance cost by month
	@PostMapping("totalmonthlycost")
	public double totalMonthlyCost(@RequestBody MaintenanceCostReportModel report) {
		return _service.totalMonthlyCost(report);
	}

	// total maintenance cost by yearly
	@PostMapping("totalyearlycost")
	public double totalYearlyCost(@RequestBody MaintenanceCostReportModel report) {
		return _service.totalYearlyCost(report);
	}

	@PutMapping("/updatecompletion")
	public void updateCompletion(@RequestBody MaintenanceCompletionModel ment) {
		_service.updateCompletion(ment);
	}

}
