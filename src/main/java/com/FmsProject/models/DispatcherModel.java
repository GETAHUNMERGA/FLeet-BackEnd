package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DispatcherModel {
	private String plateNo;
	private Float distancePerLitter;
	private Float givenKM;
	private String driverName;
	private Float salary;
	private Float totalKMAllowedOnSingleTrip;
	private Float doubleTrip;
	private Float estimatedfuelRequired;
	private Float fuelInBirr;
	private Integer noOfDaysRequired;
	private Float perdiemPerDay;
	private Float accomodation;
	private Float totalPerdiemAndAccomodation;
	private Float grandTotal;
	private Float cashAdvancedTo;
	private String dispdate;
	private Float kmReadingOnDeparture;
	private Float kmReadingOnReturn;
	private Float kmDifference;
	private String reasonOfRejection;
	private String approvedBy;
	private String approvedDate;
	private String requestedBy;
	private Integer requesterID;
	private Integer isReturned;
	private Integer vehicleType;
	private Float littresInBirr;
}
