package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffTimeDispatcherModel {
     int id;
     int reqId;
     String appBy;
    String appDate;
    double kmOnDep;
    double kmOnRet;
    double kmDifference;
    String date;
    String status;
    String reasonForRejection;
    String plateNo;
    String driverName;
}
