package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffTimeRequestModel {
    Long id;
    String reqDir;
    String reqBy;
    int directorate;
    int requestedBy;
    String date;
    String vehicleNeededFrom;
    String vehicleNeededTo;
    String nameOfPassengers;
    String residentialArea;
    String departureDate;
    String morningTime;
    String nightTime;
    String returnDate;
    String returnTime;
    double km;
    String reason;
    String telephone;
    String status;
    String reasonForRejection;
    String approvedBy;
    Date approvedDate;
    private String authorizedBy;
    private Date authorizedDate;
    private String requestedFor;
    private String requestedTime;
}
