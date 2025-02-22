package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceCostReportModel;

public interface MaintenanceCompletionMapper {

	@Insert("insert into tbMaintenanceSubmission(requestId,maintenanceCost,dateTaken,dateFromGarage,dateFromTechnical,garage,plateNo,modifiedBy,modifiedDate,authorizedBy,authorizedDate,technicalBy,technicalRespondDate,status)"
			+ "values(#{requestId},#{maintenanceCost},#{dateTaken},#{dateFromGarage},#{dateFromTechnical},#{garage},#{plateNo},#{modifiedBy},#{modifiedDate},#{authorizedBy},#{authorizedDate},#{technicalBy},#{technicalRespondDate},'Completed')")
	void completeMaintenance(MaintenanceCompletionModel comp);

	@Update("update tbMaintenaceReq set status='Completed' where ID=#{Id}")
	void updateMaintenanceReqStatus(Integer Id);

	@Select("select * from tbMaintenanceSubmission")
	List<MaintenanceCompletionModel> getMaintenanceCompletion();

	@Update("update tbMaintenanceSubmission set maintenanceCost=#{maintenanceCost},dateTaken=#{dateTaken},dateFromGarage=#{dateFromGarage},dateFromTechnical=#{dateFromTechnical} where id =#{id}")
	void updateCompletion(MaintenanceCompletionModel ment);

	@Select(" select sum(maintenanceCost) FROM tbMaintenanceSubmission")
	Float maintenanceConstCount();

	@Select("SELECT * FROM tbMaintenanceSubmission WHERE  dateFromGarage >=#{fromMonth} AND dateFromGarage <= #{toMonth}")
	List<MaintenanceCompletionModel> costReport(MaintenanceCostReportModel report);

	@Select("SELECT * FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-4,2) =#{fromMonth}")
	List<MaintenanceCompletionModel> monthlyCostReport(String fromMonth);

	// @Select("SELECT * FROM tbMaintenanceSubmission WHERE dateFromGarage BETWEEN
	// #{fromYear} AND #{toYear}")
	// List<MaintenanceCompletionModel>
	// yearlyMaintenanceCostReport(MaintenanceCostReportModel report);

	@Select("SELECT * FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-9,4)= #{fromMonth}")
	List<MaintenanceCompletionModel> yearlyCostReport(String fromMonth);

	@Select(" select sum(maintenanceCost) FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-4,2)= #{fromMonth}")
	double totalMonthlyCost(String fromMonth);

	@Select(" select sum(maintenanceCost) FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-9,4)= #{fromYear}")
	double totalYearlyCost(String fromYear);

	@Select(" select * FROM tbMaintenanceSubmission where plateNo= #{string}")
	Float byplateNocostReport(String string);

	@Select(" select sum(maintenanceCost) FROM tbMaintenanceSubmission where plateNo= #{string}")
	Float totalplatecost(String string);

	@Update("update tbMaintenanceSubmission set maintenanceCost=#{maintenanceCost} where id =#{id}")
	void updateCompletionCost(MaintenanceCompletionModel ment);

	@Select("SELECT * FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-9,4)= #{fromMonth} and plateNo = #{plateNo}")
	List<MaintenanceCompletionModel> yearlyCostReportPlateNo(String fromMonth, String plateNo);

	@Select(" select sum(maintenanceCost) FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-9,4)= #{fromYear} and plateNo = #{plateNo}")
	double totalYearlyCostPlateNo(String fromYear, String plateNo);

	@Select(" select sum(maintenanceCost) FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-4,2)= #{fromMonth} and plateNo = #{plateNo}")
	double totalMonthlyCostPlateNo(String fromMonth, String plateNo);

	@Select("SELECT * FROM tbMaintenanceSubmission where SUBSTRING(dateFromGarage, LEN(dateFromGarage)-4,2) =#{fromMonth} and plateNo = #{plateNo}")
	List<MaintenanceCompletionModel> monthlyCostReportPlateNo(String fromMonth, String plateNo);
}
