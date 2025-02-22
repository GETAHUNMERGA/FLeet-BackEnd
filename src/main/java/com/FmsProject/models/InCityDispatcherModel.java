package com.FmsProject.models;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InCityDispatcherModel {
	
	private int id;
	private String plateNumber;
	private String driverName;
	private  Date departureDate;
	private String departureTime;
	private Float departureKm;
	private Date returnDate;
	private String returnTime;
	private Float returnKm;
	private String approvedBy;
	private Date approvedDate;
	private String remark;
	private String reasonForRejection;
	private String status;
	private Integer requesterId;
	private String requestBy;
	private float kmDifference;
}
