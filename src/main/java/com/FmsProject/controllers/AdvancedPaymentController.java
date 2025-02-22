package com.FmsProject.controllers;

import com.FmsProject.models.AdvPaymentModel;
import com.FmsProject.models.AdvTypeModel;
import com.FmsProject.models.OffTimeRequestModel;
import com.FmsProject.services.AdvPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/advPayment")
public class AdvancedPaymentController {

    @Autowired
    private AdvPaymentService advPaymentService;

    @GetMapping("/getTypeOfSettlement")
    public ResponseEntity<List<AdvTypeModel>> getTypeOfSettlement() {
        List<AdvTypeModel> type = advPaymentService.getTypeOfSettlement();
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @PostMapping("/createAdvance")
    public void createAdvance(@RequestBody AdvPaymentModel advPaymentModel) throws ParseException {

        int dirId = advPaymentService.getDirId(advPaymentModel.getReqDir());
        advPaymentModel.setDirectorate(dirId);
        int reqId = advPaymentService.getReqId(advPaymentModel.getReqBy());
        advPaymentModel.setRequestedBy(reqId);
        advPaymentService.createAdvance(advPaymentModel);
    }

    @RequestMapping(value = "getAdvPayment/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdvPaymentModel> getAdvanceById(@PathVariable String id) {

        AdvPaymentModel adv = advPaymentService.getAdvanceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(adv);
    }

    @PutMapping("/updateAdvance/{id}")
    public void updateAdvancePayment(@RequestBody AdvPaymentModel advPaymentModel) throws ParseException {

        advPaymentService.updateAdvancePayment(advPaymentModel);
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<AdvPaymentModel>> getAdvancePayments(@PathVariable String username) {

        List<AdvPaymentModel> adv = advPaymentService.getAdvancePayments(username);

        return new ResponseEntity<>(adv, HttpStatus.OK);
    }

    @RequestMapping(value = "getAdvDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdvPaymentModel> getAdvDetail(@PathVariable String id) {

        AdvPaymentModel adv = advPaymentService.getAdvDetail(id);

        return ResponseEntity.status(HttpStatus.OK).body(adv);
    }

    @DeleteMapping("/cancelAdv/{id}")
    public void cancelAdv(@PathVariable("id") Long id) {
        advPaymentService.cancelAdv(id);
    }

    //Authorizer
    @GetMapping("/getAdvRequests/{username}")
    public ResponseEntity<List<AdvPaymentModel>> getAdvRequests(
            @PathVariable("username") String username) {
        List<AdvPaymentModel> requests = advPaymentService.getAdvRequests(username);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("authorize")
    public ResponseEntity<AdvPaymentModel> authorizeAdvancePayment(@RequestBody AdvPaymentModel advPaymentModel) {
        AdvPaymentModel adv = advPaymentService.authorizeAdvancePayment(advPaymentModel);
        return new ResponseEntity<>(adv, HttpStatus.OK);
    }

    @PutMapping("reject")
    public ResponseEntity<AdvPaymentModel> rejectAdvancePayment(@RequestBody AdvPaymentModel advPaymentModel) {
        AdvPaymentModel adv = advPaymentService.rejectAdvancePayment(advPaymentModel);
        return new ResponseEntity<>(adv, HttpStatus.OK);
    }
}
