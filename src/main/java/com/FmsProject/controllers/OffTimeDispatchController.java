package com.FmsProject.controllers;

import com.FmsProject.models.*;
import com.FmsProject.response.MessageResponse;
import com.FmsProject.services.OffTimeDispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/offtime/dispatch")
public class OffTimeDispatchController {

    @Autowired
    private OffTimeDispatcherService dispatcherService;

    @PostMapping("/approve")
    public ResponseEntity<MessageResponse> dispatcherApprove(@RequestBody OffTimeDispatcherModel offTimeDispatcherModel)
            throws ParseException {

        String message;

        try {
            dispatcherService.dispatcherApprove(offTimeDispatcherModel);

            message = "Request approved Successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not Approve request: " + ". Error: " + e.getMessage();
            System.out.println(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    @PostMapping("/reject")
    public void dispatcherReject(@RequestParam("reqId") Integer reqId,
            @RequestParam("reasonForRejection") String reason, @RequestParam("approvedBy") String approvedBy,
            @RequestParam("approvedDate") String approvedDate) throws ParseException {

        dispatcherService.dispatcherReject(reqId, reason, approvedBy, approvedDate);
    }

    @GetMapping("/authorized")
    public ResponseEntity<List<OffTimeRequestModel>> getAuthorizedRequests() {

        List<OffTimeRequestModel> requests = dispatcherService.getAuthorizedRequests();

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/dispatched")
    public ResponseEntity<List<OffTimeRequestModel>> getDispatchedRequests() {

        List<OffTimeRequestModel> requests = dispatcherService.getDispatchedRequests();

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/get/drivername/{plateNo}")
    public ResponseEntity<VehicleDetailDto> getDriverName(@PathVariable String plateNo) {

        String driverName = dispatcherService.getDriverName(plateNo);
        double departureKm = dispatcherService.getDepartureKm(plateNo);
        VehicleDetailDto vdto = new VehicleDetailDto();
        vdto.setDriverName(driverName);
        vdto.setKmOnDep(departureKm);

        return ResponseEntity.status(HttpStatus.OK).body(vdto);
    }

    @GetMapping("/getDrivers")
    public ResponseEntity<List<DriverDetailDto>> getDrivers() {

        List<DriverDetailDto> driverdto = dispatcherService.getDriverDetail();

        return ResponseEntity.status(HttpStatus.OK).body(driverdto);
    }

    @RequestMapping(value = "getDispatched/{id}", method = RequestMethod.GET)
    public ResponseEntity<OffTimeDispatcherModel> getDispatchedById(@PathVariable String id) {

        OffTimeDispatcherModel dispatched = dispatcherService.getDispatchedById(id);

        return ResponseEntity.status(HttpStatus.OK).body(dispatched);
    }

    @RequestMapping(value = "approved/printout/{id}", method = RequestMethod.GET)
    public ResponseEntity<OfftimePrintDto> getDispatchedPrintOut(@PathVariable String id) {

        OfftimePrintDto dispatched = dispatcherService.getDispatchedPrintOut(id);

        return ResponseEntity.status(HttpStatus.OK).body(dispatched);
    }

    @PutMapping("/updateDispatched/{reqId}")
    public void updateDispatched(@PathVariable int reqId, @RequestBody OffTimeDispatcherModel offTimeDispatcherModel)
            throws ParseException {

        offTimeDispatcherModel.setReqId(reqId);
        double kmDifference = offTimeDispatcherModel.getKmOnRet() - offTimeDispatcherModel.getKmOnDep();
        offTimeDispatcherModel.setKmDifference(kmDifference);
        dispatcherService.updateDispatched(offTimeDispatcherModel);
    }

    @GetMapping("/driveravailable/{driverName}")
    public ResponseEntity<Integer> driverAvailable(@PathVariable String driverName) {

        Integer isAvailable = dispatcherService.driverAvailable(driverName);

        return ResponseEntity.status(HttpStatus.OK).body(isAvailable);
    }

    @GetMapping("getRequestById/{id}")
    public ResponseEntity<OffTimeRequestModel> getRequestById(@PathVariable String id) {

        OffTimeRequestModel request = dispatcherService.getRequestById(id);

        return ResponseEntity.status(HttpStatus.OK).body(request);
    }


    @GetMapping("/fueldetail")
    public ResponseEntity<List<FuelDetailDto>> getFuelDetail() {

        List<FuelDetailDto> fuel = dispatcherService.getFuelDetail();

        return new ResponseEntity<>(fuel, HttpStatus.OK);
    }

    @PutMapping("/updatePrice")
    public void updateFuelPrice( @RequestBody FuelDetailDto fuelDetail) throws ParseException {

        dispatcherService.updateFuelPrice(fuelDetail);
    }
}
