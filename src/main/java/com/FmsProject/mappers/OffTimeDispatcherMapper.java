package com.FmsProject.mappers;

import com.FmsProject.models.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OffTimeDispatcherMapper {

        @Insert("insert into tbOffTimeDispatch(reqId,kmOnDep,kmOnRet,kmDifference,date,plateNo,driverName,appBy,appDate ) values("
                        + "#{reqId},#{kmOnDep},#{kmOnRet},#{kmDifference},#{date},#{plateNo},#{driverName},#{appBy},#{appDate})")
        void dispatcherApprove(OffTimeDispatcherModel offTimeDispatcherModel);

        @Update("update tbOffTimeRequisition set status='Approved' where id = #{reqId}")
        void approveInRequest(OffTimeDispatcherModel offTimeDispatcherModel);

        @Select("select fullName from tblDriver where plateNo = #{plateNo}")
        String getDriverName(String plateNo);

        @Update("update tbOffTimeRequisition set reasonForRejection=#{reason},status='Rejected',approvedBy=#{approvedBy},approvedDate=#{approvedDate} where id= #{reqId}")
        void dispatcherReject(Integer reqId, String reason, String approvedBy, String approvedDate);

        @Select("select lastMilege from tbVehicle where plateNo = #{plateNo}")
        double getDepartureKm(String plateNo);

        @Select("select * from tbOffTimeRequisition where status = 'Checked'")
        List<OffTimeRequestModel> getAuthorizedRequests();

        // @Select("select * from tbOffTimeRequisition tr,tbOffTimeDispatch td,tbVehicle
        // v where tr.status = 'Approved' and tr.id = td.reqId and td.plateNo =
        // v.plateNo and "
        // + "v.status = 'Dispatched'")
        @Select("select * from tbOffTimeRequisition tr,tbOffTimeDispatch td,tbVehicle v where tr.status = 'Approved' and tr.id = td.reqId and td.kmOnRet = '0.00' and td.plateNo = v.plateNo ORDER BY td.reqId")
        List<OffTimeRequestModel> getDispatchedRequests();

        @Select("select td.kmOnDep, td.kmOnRet, td.date,td.plateNo, tr.approvedBy as appBy, tr.approvedDate as appDate,td.driverName from tbOffTimeDispatch td,"
                        + "tbOffTimeRequisition tr where td.reqId = #{id} and td.reqId = tr.id")
        OffTimeDispatcherModel getDispatchedById(String id);

        @Update("update tbOffTimeDispatch set kmOnRet=#{kmOnRet},kmDifference=#{kmDifference},date=#{date} where reqId=#{reqId}")
        void updateDispatched(OffTimeDispatcherModel model);

        @Select("select dir.directorate,tr.reqBy, tr.date as reqDate, tr.approvedBy as appBy,tr.approvedDate as appDate,tr.authorizedBy,tr.authorizedDate,tr.vehicleNeededFrom,tr.vehicleNeededTo,"
                        + "tr.departureDate,tr.morningTime,tr.nightTime,tr.returnDate,tr.returnTime,tr.km,tr.telephone,tr.nameOfPassengers,tr.residentialArea,td.kmOnDep,"
                        + "td.plateNo,dr.fullName as driverName,dr.tellNo, tr.requestedTime, tr.requestedFor from tbOffTimeRequisition tr,tbOffTimeDispatch td,tblDriver dr,tbDirectorate dir where td.reqId = #{id} and td.reqId = tr.id "
                        + "and td.driverName = dr.fullName and tr.directorate = dir.id")
        OfftimePrintDto getDispatchedPrintOut(String id);

        @Select("select distinct(fullName), id from tblDriver")
        List<DriverDetailDto> getDriverDetail();

        @Select("update tblDriver set isAvailable = '0' where fullName = #{driverName}")
        void changeDriverAvailability(String driverName);

        @Select("update tblDriver set isAvailable = '1' where fullName = #{driverName}")
        void updateDriver(String driverName);

        @Select("select isAvailable from tblDriver where fullName = #{driverName}")
        Integer driverAvailable(String driverName);

        @Select("select r.*, d.directorate as reqDir from tbOffTimeRequisition r,tbDirectorate d  where r.id = #{id} and r.directorate = d.id")
        OffTimeRequestModel getRequestById(String id);

        @Select("select * from tbFuel")
        List<FuelDetailDto> getFuelDetail();

        @Update("update tbFuel set price = #{price} where id = #{id}")
        void updateFuelPrice(FuelDetailDto fuelDetail);

        // @Update("update tbOffTimeRequisition set status='Returned' where
        // id=#{reqId}")
        // void updateRequest(int reqId);
}
