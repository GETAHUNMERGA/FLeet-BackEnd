package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverModel {
	private Integer id;
	private String fullName;
	private String tellNo;
	private String residentPlace;
	private String Address;
	private Integer Age;
	private String licenseNo;
	private String Grade;
	private String expiryDate;
	private String plateNo;
	private String status;
	private Integer isAvailable;
	private String reasonToDisable;
	

}
