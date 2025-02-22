package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {
    Long id;
    String model;
    String make;
    int yearOfMake;
    String engineNo;
    String chassisNo;
    String plateNo;
    String cc;
    int fuelType;
    double disPerLit;
    String carryingCapacity;

    double lastMilege;
    String insExpDate;
    String insRenewalDate;
    String policyNo;
    double lmGenService;
    double lmTyresChange;
    double lmVehicleBody;
    String description;
    String purposeOfUsage;
    String status;
    Integer isActive;
    String custodian;
    String owner;
    Date previousServiceDate;
    String vehicleTypeString;
    Integer vehicleType;
    String createdBy;
    Date createdDate;
    String isStandBy;
}
