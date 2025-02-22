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
import com.FmsProject.models.GarageModel;
import com.FmsProject.services.GarageService;

@RestController
@CrossOrigin
@RequestMapping("/garage")
public class GarageController {

  @Autowired
  private GarageService service;

  @PostMapping("/registergarage")
  public ResponseEntity<GarageModel> registerGarage(@RequestBody GarageModel mod) {
    service.registerGarage(mod);
    return new ResponseEntity<>(mod, HttpStatus.OK);
  }

  @GetMapping("/gragephone/{phone}")
  public Optional<GarageModel> checkGaragePhone(@PathVariable("phone") String phone) {
    return service.checkGaragePhone(phone);
  }

  @GetMapping("/getgarage")
  public ResponseEntity<List<GarageModel>> getGarage() {
    List<GarageModel> resp = service.getGarage();
    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  @PutMapping("/editgarage/{id}")
  public void editGarage(@PathVariable("id") String id, @RequestBody GarageModel req) {
    Integer garageId = Integer.parseInt(id);
    if (service.selectGarageById(garageId) != null) {

      service.editGarage(req);
    } else {
      HashMap<String, String> message = new HashMap<>();
      message.put("message", "Requesition With id " + id + " Not Found");
    }
  }

  @GetMapping("/getgaragebyid/{id}")
  public Optional<GarageModel> selectGarageById(@PathVariable("id") Integer id) {
    return service.selectGarageById(id);
  }

  @DeleteMapping("/disposegarage/{id}")
  public void disposeGarage(@PathVariable("id") Long id) {
    service.disposeGarage(id);
  }
}