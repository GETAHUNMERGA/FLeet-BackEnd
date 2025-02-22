package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRequestModel {
    private Long ID;
    private String plateNo;
    private float previousServiceMilage;
    private String previousServiceDate;
    private float currentMilage;
    private float milageDifference;
    private float fuelGaugeInAmount;
    private String requestedBy;
    private Date requestedDate;
    private String reqDirectorate;
    private String modifiedBy;
    private Date modifiedDate;
    private String authorizedBy;
    private Date authorizedDate;
    private String workRepairRequested;
    private float kmInsp;
    private Long garage;
    private String technicalBy;
    private Date technicalRespondDate;
    private String maintenanceType;
    private int mainType;
    private int serviceType;
    private String model;
    private String chassisNo;
    private String engineNo;
    private String status;
    private String reasonForRejection;
    private String isInsured;
    private int insured;
    private float kmTechnical;
    private float fuelTechnical;
    private String garageName;
}