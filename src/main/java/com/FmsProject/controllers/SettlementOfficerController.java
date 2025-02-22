package com.FmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.AdvPaymentModel;
import com.FmsProject.services.SettlementOfficerService;

@RestController
@CrossOrigin
@RequestMapping("/SettlementOfficer")
public class SettlementOfficerController {
  @Autowired
  SettlementOfficerService settlementService;

  @GetMapping("/getPaymentRequests")
  public ResponseEntity<List<AdvPaymentModel>> getAdvancePaymentRequests() {

    List<AdvPaymentModel> advancePaymentRequests = settlementService.getAdvancePaymentRequests();
    return new ResponseEntity<>(advancePaymentRequests, HttpStatus.OK);
  }

  @PutMapping("/grantAdvance")
  public void grantAdvancePayment(@RequestBody AdvPaymentModel advance) {
    settlementService.grantAdvancePayment(advance);
  }

  @PutMapping("/rejectAdvance")
  public void rejectAdvancePayment(@RequestBody AdvPaymentModel advance) {
    settlementService.rejectAdvancePayment(advance);
  }

  @GetMapping("/printSettlement/{id}")
  public ResponseEntity<AdvPaymentModel> printSettlementDetails(@PathVariable String id) {
    Integer adId = Integer.parseInt(id);
    AdvPaymentModel advance = settlementService.printSettlementDetails(adId);
    return new ResponseEntity<>(advance, HttpStatus.OK);
  }

  @GetMapping("/numberOfRequests")
  public int getNoOfRequest() {
    return settlementService.getNoOfRequest();
  }
}
