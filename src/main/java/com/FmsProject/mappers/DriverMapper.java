package com.FmsProject.mappers;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.DriverModel;
import com.FmsProject.models.VehicleModel;

public interface DriverMapper {

	// @Select("select distinct(fullName) from tblDriver")
	@Select("select * from tblDriver")
	List<DriverModel> getDrivers();

	@Insert("insert into tblDriver(fullName,tellNo,residentPlace,Address,Age,licenseNo,Grade,expiryDate,plateNo,isAvailable,reasonToDisable)"
			+ "values(#{fullName},#{tellNo},#{residentPlace},#{Address},#{Age},#{licenseNo},#{Grade},#{expiryDate},#{plateNo},1,#{reasonToDisable})")
	void saveDrivers(DriverModel driv);

	@Select("select * from tblDriver where id =#{id}")
	Optional<DriverModel> getDriverById(Integer id);

	@Delete("delete from tblDriver where id =#{id}")
	void deleteDriver(Long id);

	@Update("update tblDriver set fullName=#{fullName},tellNo=#{tellNo},residentPlace=#{residentPlace},Address=#{Address},"
			+ "Age=#{Age},licenseNo=#{licenseNo},Grade=#{Grade},expiryDate=#{expiryDate},"
			+ "plateNo=#{plateNo} where id =#{id}")
	void updateDriver(DriverModel driv);

	@Select("select * from tbVehicle where plateNo = #{plateNo}")
	Optional<VehicleModel> getVehicleByPlateNo(String plateNo);

	@Update("update tblDriver set vehicleId=#{vehicleId} where plateNo='#{plateNo}'")
	void updateDriverVehicleId(DriverModel vid);

	@Select("select tbVehicle.plateNo from tbVehicle INNER JOIN tblDriver ON tbVehicle.plateNo=tblDriver.plateNo where tbVehicle.status='Pool' and tbVehicle.custodian='General Technical and Security Service Directorate' and tblDriver.isAvailable=1 ")
	List<VehicleModel> getVehicleByStatus();

	// @Select("select * from tbVehicle where status='Pool' and custodian='General
	// Technical and Security Service Directorate' ")
	// List<VehicleModel> getVehicleByStatus();

	@Select("select * from tblDriver where plateNo=#{plateNo}")
	Optional<DriverModel> getDriverByPlateNo(String plateNo);

	@Update("update tbVehicle set status ='dispatched' where plateNo=#{plate} ")
	void updateVehicleStatus(String plate);

	// @Update("update tblDriver set status ='dispatched' where plateNo=#{plate} ")
	// void updateDriverStatus(String plate);

	@Select("select * from tblDriver where status ='Pool' ")
	List<DriverModel> getDriverByStatus();

	@Select("select * from tbVehicle where isActive=1")
	List<VehicleModel> getActiveVehicle();

	@Update("update tbVehicle set isActive=0 where plateNo=#{plate}")
	void updateActiveVehicle(String plate);

	@Select("select * from tblDriver where tellNo=#{phone}")
	Optional<DriverModel> getDriverByPhone(String phone);

	List<DriverModel> getDriverByFullname();

	@Select("select * from tblDriver where fullName=#{fullname}")
	Optional<DriverModel> getDriverByFullname(String fullname);

	@Update("update tblDriver set isAvailable=0,reasonToDisable=#{reasonToDisable} where id=#{id}")
	void disableDriver(DriverModel driv);

	@Update("update tblDriver set isAvailable =1,reasonToDisable='' where id = #{id}")
	void enableDriver(DriverModel driv);

}
