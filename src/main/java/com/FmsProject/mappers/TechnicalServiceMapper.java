package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.GarageModel;
import com.FmsProject.models.MaintenanceRequestModel;

public interface TechnicalServiceMapper {

	@Select("select * from tbMaintenaceReq where status ='Authorized' and garage IS NULL")
	List<MaintenanceRequestModel> getAllMaintenenceRequest();

	@Update("update tbMaintenaceReq set technicalBy=#{technicalBy},technicalRespondDate=#{technicalRespondDate},garage=#{garage}, kmTechnical=#{kmTechnical},fuelTechnical=#{fuelTechnical} where ID =#{ID}")
	void assignGarage(MaintenanceRequestModel req);

	@Select("select * from tbGarage")
	List<GarageModel> getGarage();

	@Select("select * from tbMaintenaceReq where status ='Authorized' and garage IS NOT NULL")
	List<MaintenanceRequestModel> getMaintenanceCompletedVehicle();

	@Select("select count(*) FROM tbMaintenaceReq where status = 'Authorized' and garage is null")
	int getNoOfAuthorizedrequest();

	@Select("select count(*) FROM tbMaintenaceReq where status = 'Authorized' and garage IS NOT NULL")
	int getNoOfUnderMaintenance();

	@Select("select count(*) FROM tbGarage ")
	int getNoOfGarage();

	@Select("select r.fuelTechnical,r.fuelGaugeInAmount,r.kmTechnical,r.kmInsp,r.plateNo,r.isInsured as insured,r.maintenanceType"
			+ ",r.requestedBy,r.reqDirectorate,r.requestedDate,r.workRepairRequested"
			+ ",r.previousServiceMilage,g.name as garageName,v.chassisNo,v.engineNo"
			+ " from tbMaintenaceReq r"
			+ " inner join  tbGarage g on  r.garage=g.id"
			+ " inner join tbVehicle v on r.plateNo = v.plateNo"
			+ " where r.ID=#{maintId}")
	MaintenanceRequestModel getMaintenanceRequest(Integer maintId);

	@Select(" select t.MainType as maintenanceType from tbMaintenaceReq r, tbMainType t where r.ID=#{maintId} AND r.maintenanceType=t.ID")
	String getMaintenanceType(Integer maintId);

}
