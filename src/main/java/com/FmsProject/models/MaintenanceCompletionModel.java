package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceCompletionModel {
	private Integer id;
	private Integer requestId;
	private Float maintenanceCost;
	private String dateTaken;
	private String dateFromGarage;
	private String dateFromTechnical;
	private Float currentMilege;
	private Float petrolAmount;
	private Date dateSentToGarage;
	private String vehicleReceivedBy;
	private String vehicleGivenBy;
	private Date vehicleReceivedDate;
	private Integer garage;
	private String plateNo;
	private String modifiedBy;
    private Date modifiedDate;
    private String authorizedBy;
    private Date authorizedDate;
    private String technicalBy;
    private Date technicalRespondDate;
    private String status;

}
