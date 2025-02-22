package com.FmsProject.controllers;

import com.FmsProject.models.FuelDetailDto;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;
import com.FmsProject.response.MessageResponse;
import com.FmsProject.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addVehicle(@RequestBody VehicleModel vehicleModel) throws ParseException {

        String message;

        try {
            vehicleService.addVehicle(vehicleModel);

            message = "Vehicle Added Successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not Add Vehicle: " + ". Error: " + e.getMessage();
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleModel>> getAllVehicles() {

        List<VehicleModel> vehicles = vehicleService.getAllVehicles();

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/all/onpool/{vtype}")
    public ResponseEntity<List<VehicleModel>> getVehiclesOnPool(@PathVariable String vtype) {

        List<VehicleModel> vehicles = vehicleService.getVehiclesOnPool(vtype);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PutMapping("/update")
    public void updateVehicle(@RequestBody VehicleModel vehicleModel) throws ParseException {

        vehicleService.updateVehicle(vehicleModel);
    }

    @PutMapping("/dispose/{id}")
    public void disposeVehicle(@PathVariable int id) throws ParseException {

        vehicleService.disposeVehicle(id);
    }

    @GetMapping("/exists/{plateNo}")
    public boolean vehicleAlreadyExists(@PathVariable String plateNo) {

        try {
            return vehicleService.vehicleAlreadyExists(plateNo);
        } catch (Exception ex) {
            return false;
        }

    }

    @GetMapping("/serviceMillageDueNo")
    public int getNoOfServiceMillageDue() {
        return vehicleService.getNoOfServiceMillageDue();
    }

    @GetMapping("/tyreMillageDueNo")
    public int getNoOfTyreMillageDue() {
        return vehicleService.getNoOfTyreMillageDue();
    }

    @GetMapping("/insuranceDueNo")
    public int getNoOfInsuranceDue() {
        return vehicleService.getNoOfInsuranceDue();
    }

    @GetMapping("/offtimeReq")
    public int getNoOfftimeReq() {
        return vehicleService.getNoOfftimeReq();
    }

    @GetMapping("/fieldReq")
    public int getNoFieldReq() {
        return vehicleService.getNoFieldReq();
    }

    @GetMapping("/serviceMillageDue")
    public ResponseEntity<List<VehicleModel>> getServiceMillageDue() {

        List<VehicleModel> vehicles = vehicleService.getServiceMillageDue();

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/tyreMillageDue")
    public ResponseEntity<List<VehicleModel>> getTyresMillageDue() {

        List<VehicleModel> vehicles = vehicleService.getTyresMillageDue();

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/insuranceDue")
    public ResponseEntity<List<VehicleModel>> getInsuranceMillageDue() {

        List<VehicleModel> vehicles = vehicleService.getInsuranceMillageDue();

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/serviceMaintenanceType")
    public ResponseEntity<List<MaintenanceTypeModel>> getServiceMaintenanceTypes() {

        List<MaintenanceTypeModel> types = vehicleService.getServiceMaintenanceTypes();

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/tyresMaintenanceType")
    public ResponseEntity<List<MaintenanceTypeModel>> getTyresMaintenanceTypes() {

        List<MaintenanceTypeModel> types = vehicleService.getTyresMaintenanceTypes();

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/custodianVehicles/{director}")
    public ResponseEntity<List<VehicleModel>> getCustodianVehicle(@PathVariable String director) {

        List<VehicleModel> vehicles = vehicleService.getCustodianVehicle(director);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/vehicleTypes")
    public ResponseEntity<List<VehicleTypesModel>> getVehicleTypes() {
        List<VehicleTypesModel> vehicleTypes = vehicleService.getVehicleTypes();
        return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);
    }

    @GetMapping("/fuelTypes")
    public ResponseEntity<List<FuelDetailDto>> getFuelTypes() {
        List<FuelDetailDto> fuelTypes = vehicleService.getFuelTypes();
        return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
    }

}
