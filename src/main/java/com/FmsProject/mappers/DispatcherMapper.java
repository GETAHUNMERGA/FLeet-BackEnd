package com.FmsProject.mappers;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.FuelDetailDto;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.OffTimeDispatcherModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;

public interface DispatcherMapper {

	@Select("select * from tblDispatcher")
	List<DispatcherModel> getRequisition();

	//@Update("update fieldRequest set status ='Approved'")
	@Insert("insert into tblDispatcher(plateNo,distancePerLitter,givenKM,driverName,salary,totalKMAllowedOnSingleTrip,"
			+ "doubleTrip,estimatedfuelRequired,fuelInBirr,noOfDaysRequired,perdiemPerDay,accomodation,totalPerdiemAndAccomodation,"
			+ "grandTotal,cashAdvancedTo,dispdate,kmReadingOnDeparture,kmReadingOnReturn,kmDifference,"
			+ "reasonOfRejection,approvedBy,approvedDate,requestedBy,requesterID,isReturned,vehicleType,littresInBirr) "
			+ "values(#{plateNo},#{distancePerLitter},#{givenKM},#{driverName},#{salary},#{totalKMAllowedOnSingleTrip},"
			+ "#{doubleTrip},#{estimatedfuelRequired},#{fuelInBirr},#{noOfDaysRequired},#{perdiemPerDay},#{accomodation},#{totalPerdiemAndAccomodation},"
			+ "#{grandTotal},#{cashAdvancedTo},#{dispdate},#{kmReadingOnDeparture},#{kmReadingOnReturn},#{kmDifference},"
			+ "#{reasonOfRejection},#{approvedBy},#{approvedDate},#{requestedBy},#{requesterID},0, #{vehicleType},#{littresInBirr})")
	void approveRequests(DispatcherModel req);

	@Select("select * from tblDispatcher where id=#{id}")
	Optional<DispatcherModel> getRequestById(Integer id);

	@Select("select * from tblDispatcher where requesterID=#{reqid}")
	Optional<DispatcherModel> getDispatcherByRequesterID(Integer reqid);

	@Select("  select * from tblDispatcher inner join tbVehicle on tblDispatcher.plateNo = tbVehicle.plateNo inner join fieldRequest on tblDispatcher.requesterID = fieldRequest.id where tbVehicle.status='Dispatched' and tblDispatcher.isReturned=0 and fieldRequest.status = 'Approved'")
	List<DispatcherModel> getAllAprovedRequest();

	@Update("update tblDispatcher set kmReadingOnReturn=#{kmReadingOnReturn},kmDifference=#{kmDifference},isReturned=1 where requesterID = #{requesterID}")
	void updateApprovedRequest(DispatcherModel driv);

	@Update("update tbVehicle set status = 'Pool',lastMilege=#{returnkm} where plateNo=#{plate} ")
	void updateVehicleStatus(Float returnkm,String plate);

	@Select("select * from tbVehicleType")
	List<VehicleTypesModel> getAllVehicleTypes();

	@Select("select * from tbVehicle where vehicleType=#{vtypes} and status ='Pool'")
	List<VehicleModel> getVehicleByType(Integer vtypes);

	@Select("select * from tbVehicleType where id =#{id}")
	Optional<VehicleTypesModel> getVehicleTypeById(Integer id);

	@Select("select * from tblDispatcher where driverName =#{driverName}")
	Optional<DispatcherModel> getDispatchedByDriverName(String driverName);

	@Select("select * from incityDispatch where driverName =#{driverName}")
	Optional<InCityDispatcherModel> getIncityDispatchedByDriverName(String driverName);

	@Select("select * from tbOffTimeDispatch where driverName =#{driverName}")
	Optional<OffTimeDispatcherModel> getOfftimeDispatchedByDriverName(String driverName);

	@Select("select * from tbFuel where id = #{id}")
	Optional<FuelDetailDto> getFuelTypeById(Integer id);

}
