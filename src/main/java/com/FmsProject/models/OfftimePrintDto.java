package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfftimePrintDto {

    int id;
    int reqId;
    String reqDate;
    String reqBy;
    String appBy;
    String appDate;
    String authorizedBy;
    String authorizedDate;
    String vehicleNeededFrom;
    String vehicleNeededTo;
    String departureDate;
    String morningTime;
    String nightTime;
    String returnDate;
    String returnTime;
    double km;
    // this is requester's phone number
    String telephone;
    String nameOfPassengers;
    String residentialArea;
    double kmOnDep;
    String plateNo;
    String driverName;
    //this is driver's phone number
    String tellNo;
    String directorate;
    String requestedTime;
    String requestedFor;
}
