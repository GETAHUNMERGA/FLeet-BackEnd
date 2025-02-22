package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvPaymentModel {
    private int id;
    private String staffName;
    private String reqDir;
    private String reqBy;
    private int directorate;
    private int requestedBy;
    private String staffAcc;
    private String claimBranch;
    private double totalAmount;
    private String purpose;
    private String typeOfSettlment;
    private String departureDate;
    private String grade;
    private int noOfDays;
    private String status;
    private String crtDate;
    private String settledBy;
    private Date dateSettled;
    private String approvedBy;
    private int appBy;
    private Date appDate;
    private String reasonForRejection;
}
