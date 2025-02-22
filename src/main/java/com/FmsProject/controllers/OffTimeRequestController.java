package com.FmsProject.controllers;

import com.FmsProject.models.OffTimeRequestModel;
import com.FmsProject.services.OffTimeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/offtime")
public class OffTimeRequestController {
    @Autowired
    private OffTimeRequestService offTimeRequestService;

    @PostMapping("/offtimerequest")
    public void saveOffTimeRequest(@RequestBody OffTimeRequestModel offTimeRequestModel) throws ParseException {

        System.out.println("checking morning time data: " + offTimeRequestModel.getMorningTime());
        int dirId = offTimeRequestService.getDirId(offTimeRequestModel.getReqDir());
        offTimeRequestModel.setDirectorate(dirId);
        offTimeRequestService.saveofftimeRequestService(offTimeRequestModel);
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<OffTimeRequestModel>> getAllRequests(@PathVariable String username) {

        List<OffTimeRequestModel> requests = offTimeRequestService.getAllRequests(username);

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // getRequestById
    @RequestMapping(value = "getRequest/{id}", method = RequestMethod.GET)
    public ResponseEntity<OffTimeRequestModel> getRequestById(@PathVariable String id) {

        OffTimeRequestModel request = offTimeRequestService.getRequestById(id);

        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @PutMapping("/updaterequest/{id}")
    public void updateOffTimeRequest(@RequestBody OffTimeRequestModel offTimeRequestModel) throws ParseException {

        offTimeRequestService.updateofftimeRequestService(offTimeRequestModel);
    }

    @DeleteMapping("/requestcancel/{id}")
    public void cancelOffTimeRequest(@PathVariable("id") Long id) {
        offTimeRequestService.cancelOffTimeRequestService(id);
    }
}
