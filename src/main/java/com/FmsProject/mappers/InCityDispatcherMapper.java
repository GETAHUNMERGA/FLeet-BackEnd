package com.FmsProject.mappers;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.FmsProject.models.DriverModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;

public interface InCityDispatcherMapper {

	@Insert("insert into incityDispatch(plateNumber,driverName,departureDate,departureTime,departureKm,returnDate,returnTime,approvedBy,approvedDate,remark,requesterId,requestBy) values("
			+ "#{plateNumber},#{driverName},#{departureDate},#{departureTime},#{departureKm},#{returnDate},#{returnTime},#{approvedBy},#{approvedDate},#{remark},#{requesterId},#{requestBy})")
	public void saveIncityDispatcherMapper(InCityDispatcherModel model);

	@Select("SELECT * FROM ((incityrequest INNER JOIN incityDispatch ON incityrequest.id = incityDispatch.requesterId) INNER JOIN tbVehicle ON incityDispatch.plateNumber = tbVehicle.plateNo) \r\n"
			+
			" where incityrequest.status='Approved' And tbVehicle.status='Dispatched' And incityDispatch.returnKm is NULL;")
	List<InCityDispatcherModel> viewIncityDispatchMapper();

	@Select("select * from incityDispatch where id =#{id}")
	Optional<InCityDispatcherModel> selectRequestById(Integer id);

	@Insert("insert into incityDispatch(reasonOfRejection,approvedDate,approvedBy) values("
			+ "#{reasonOfRejection},#{approvedDate},#{approvedBy}")
	public void rejectReuest(InCityDispatcherModel req);

	@Select("select * from incityDispatch where id=#{id}")
	public Optional<InCityDispatcherModel> selectDispatcherById(Integer id);

	@Update("update tbVehicle set status='Dispatched' where plateNo=#{plateNo}")
	public void vehiclestatus(String plateNo);

	@Select("SELECT T1.plateNo FROM  tbVehicle T1 WHERE T1.vehicleType=#{id} AND (T1.status = 'Pool' AND T1.custodian='General Technical and Security Service Directorate')")
	public List<VehicleModel> getvehiclebystatus(Integer id);

	@Select("select distinct(fullName) from tblDriver")
	List<DriverModel> getDriverName();

	@Select("select * from tbVehicle where plateNo=#{vehiclePlateNo}")
	public Optional<VehicleModel> getDepartureKm(String vehiclePlateNo);

	@Select("select * from incityDispatch where requesterId=#{id}")
	public Optional<InCityDispatcherModel> selectDispatchedPlateNo(Integer id);

	@Select("select tellNo from tblDriver where fullName=#{plateNo}")
	public Optional<DriverModel> getDriverPhoneNumber(String plateNo);

	@Update("update incityDispatch set returnKm=#{returnKm},kmDifference=#{kmDifference} where requesterId = #{requesterId}")
	public void modifyDispatchedReturnKm(InCityDispatcherModel model);

	@Update("update tbVehicle set status='Pool',lastMilege=#{returnKm} where plateNo=#{plateNumber}")
	public void modifyVehicleStatus(String plateNumber, Float returnKm);

	@Select("select * from tbVehicleType")
	public List<VehicleTypesModel> getVehicleTypes();
	
   @Select("select id from tbVehicleType where vehicleType = #{type}")
	public Integer getVehicleTypeId(String type);
   
   @Update("update tblDriver set isAvailable=0 where fullName=#{name}")
   public void setDriveOff(String name);
   
   @Update("update tblDriver set isAvailable=1 where fullName=#{driverName}")
   public void returnDriverName(String driverName);

}
