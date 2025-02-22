package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldRequestModel {

	private Integer id;
	private String directorate;
	private String date;
	private String passengers;
	private String requestedFor;
	private String purpose;
	private String departureDate;
	private String returnDate;
	private Integer noOfDaysRequested;
	private Float materialloaded;
	private String destBranch;
	private String destCity;
	private Float destKM;
	private Float contingency;
	private Float totalKM;
	private String expence;
	private String transportMode;
	private String requestedBy;
	private String requestDate;
	private String status;//private Status status; STATUS ENUM{PENDING,APPROVED,REJECTED,AUTHORIZED}
	private String reasonOfRejection;
	private String approvedBy;
	private String approvedDate;
	private String authorizedBy;
	private Date authorizedDate;
	private String rejectedBy;
	private String rejectedDate;
	private Date requestDateTime;
	private String passTelephone;
    private String requestedTime;
}
