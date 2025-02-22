package com.FmsProject.mappers;

import com.FmsProject.models.FuelDetailDto;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VehicleMapper {

        @Insert("insert into tbVehicle(model,make,yearOfMake,engineNo,chassisNo,plateNo,cc,fuelType,disPerLit,carryingCapacity,lastMilege,insExpDate,insRenewalDate,policyNo,lmGenService,lmTyresChange,lmVehicleBody,description,purposeOfUsage,custodian,owner,status,isActive, createdBy, createdDate, vehicleType) values("
                        + "#{model},#{make},#{yearOfMake},#{engineNo},#{chassisNo},#{plateNo},#{cc},#{fuelType},#{disPerLit},#{carryingCapacity},#{lastMilege},#{insExpDate},#{insRenewalDate},#{policyNo},#{lastMilege},#{lastMilege},#{lastMilege},#{description},#{purposeOfUsage},#{custodian},#{owner},#{status},1, #{createdBy}, #{createdDate}, #{vehicleType})")
        void addVehicle(VehicleModel vehicleModel);

        @Select("select * from tbVehicle")
        List<VehicleModel> getAllVehicles();

        @Update("update tbVehicle set insExpDate=#{insExpDate},insRenewalDate=#{insRenewalDate},model=#{model}, make = #{make}"
                        + ",yearOfMake=#{yearOfMake},engineNo=#{engineNo}, chassisNo=#{chassisNo},cc=#{cc}, fuelType = #{fuelType}, disPerLit = #{disPerLit}"
                        + ", carryingCapacity=#{carryingCapacity}, lastMilege=#{lastMilege}, policyNo=#{policyNo} , lmGenService=#{lmGenService}, lmTyresChange =#{lmTyresChange}"
                        + ",lmVehicleBody = #{lmVehicleBody}, purposeOfUsage=#{purposeOfUsage},custodian=#{custodian},owner=#{owner}, status=#{status}"
                        + ", createdBy = #{createdBy}, createdDate = #{createdDate}, vehicleType=#{vehicleType} where id=#{id}")
        void updateVehicle(VehicleModel vehicleModel);

        @Update("update tbVehicle set status='Disposed' where id=#{id}")
        void disposeVehicle(int id);

        // @Select("SELECT T1.plateNo FROM tbVehicle T1 JOIN tblDriver T2 ON T1.plateNo
        // = T2.plateNo AND T2.isAvailable = 1 WHERE T1.status = 'Pool' AND
        // T1.custodian='General Technical and Security Service Directorate'")
        @Select("select plateNo from tbVehicle where status = 'Pool' and custodian = 'General Technical and Security Service Directorate' and vehicleType = #{vtype}")
        List<VehicleModel> getVehiclesOnPool(String vtype);

        @Select("select * from tbVehicle where plateNo = #{plateNo}")
        boolean vehicleAlreadyExists(String plateNo);

        @Update("update tbVehicle set status='Dispatched' where plateNo=#{plateNo}")
        void dispatchVehicle(String plateNo);

        @Select("select count(id) from tbVehicle where lastMilege - lmGenService >= 5000 AND custodian = 'General Technical and Security Service Directorate' AND status != 'Maintenance'")
        int getNoOfServiceMillageDue();

        @Select("select count(id) from tbVehicle where lastMilege - lmTyresChange >= 50000 AND custodian = 'General Technical and Security Service Directorate' AND status != 'Maintenance'")
        int getNoOfTyreMillageDue();

        @Select("select count(*) from tbVehicle where GETDATE() > insExpDate AND custodian = 'General Technical and Security Service Directorate'")
        int getNoOfInsuranceDue();

        @Select("select * from tbVehicle where lastMilege - lmGenService >= 5000 AND custodian = 'General Technical and Security Service Directorate' AND status != 'Maintenance'")
        List<VehicleModel> getServiceMillageDue();

        @Select("select * from tbVehicle where lastMilege - lmTyresChange >= 50000 AND custodian = 'General Technical and Security Service Directorate' AND status != 'Maintenance'")
        List<VehicleModel> getTyresMillageDue();

        @Select("select *  FROM [FleetManagementSystem].[dbo].[VehicleInsuranceDateDue]")
        List<VehicleModel> getInsuranceMillageDue();

        @Select("select * from tbMainType where MainInterval = 5000.00")
        List<MaintenanceTypeModel> getServiceMaintenanceTypes();

        @Select("select * from tbMainType where MainInterval = 50000.00")
        List<MaintenanceTypeModel> getTyresMaintenanceTypes();

        @Select("select * from tbVehicle where custodian = #{director} ")
        List<VehicleModel> getCustodianVehicle(String director);

        @Update("update tbVehicle set status='Pool',lastMilege=#{kmOnRet} where plateNo=#{plateNo}")
        void updateLastMileage(String plateNo, double kmOnRet);

        @Select("select * from tbVehicleType")
        List<VehicleTypesModel> getVehicleTypes();

        @Select("  select id from tbVehicleType where vehicleType = #{vehicleTypeString}")
        Integer getVehicleTypeId(String vehicleTypeString);

        @Select("select * from tbFuel")
        List<FuelDetailDto> getFuelTypes();

        @Select("select count(id) from tbOffTimeRequisition where status = 'Authorized'")
        int getNoOfftimeReq();

        @Select("select count(id) from fieldRequest where status = 'Authorized'")
        int getNoFieldReq();
}
