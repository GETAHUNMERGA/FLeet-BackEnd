package com.FmsProject.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InCityRequestModel {
	private String requestBy;
	private String directorate;
	private String passenger;
	private String destination;
	private String requestTimeFrom;
	private String requestTimeTo;
	private String purpose;
	private Integer id;
	private String status;
	private String reasonForRejection;
	private String approvedBy;
	private Date approvedDate;
	private String authorizedBy;
	private Date authorizedDate;
	private String remark;
	private Date dateTo;
	private Integer numberOfDays;
	private String rejectedBy;
	private Date loginDate;
	private Date requestedDate;
	private Date dateFrom;
	private String passTelephone;
	private String requestedFor;
	private String requestedTime;
}
