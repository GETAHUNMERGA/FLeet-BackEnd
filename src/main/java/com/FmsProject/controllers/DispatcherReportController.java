package com.FmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.DispatcherReportModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.OffTimeDispatcherModel;
import com.FmsProject.services.DispatcherReportService;

@RestController
@CrossOrigin
@RequestMapping("/dispatcherReport")
public class DispatcherReportController {

    @Autowired
    private DispatcherReportService _report;

    @GetMapping("/getAllField")
    public List<DispatcherModel> getAllFieldDispatch() {
        return _report.getAllFieldDispatch();
    }

    @GetMapping("/getAllIncity")
    public List<InCityDispatcherModel> getAllIncityDispatch() {
        return _report.getAllIncityDispatch();
    }

    @GetMapping("/getAllOfftime")
    public List<OffTimeDispatcherModel> getAllOfftimeDispatch() {
        return _report.getAllOfftimeDispatch();
    }

    @PostMapping("field")
    public ResponseEntity<List<DispatcherModel>> fieldReport(@RequestBody DispatcherReportModel report) {
        List<DispatcherModel> response = _report.dispatcherFieldReport(report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("incity")
    public ResponseEntity<List<InCityDispatcherModel>> incityReport(@RequestBody DispatcherReportModel report) {
        List<InCityDispatcherModel> response = _report.dispatcherIncityReport(report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("offtime")
    public ResponseEntity<List<OffTimeDispatcherModel>> offtimeReport(@RequestBody DispatcherReportModel report) {
        List<OffTimeDispatcherModel> response = _report.dispatcherOfftimeReport(report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
