package com.FmsProject.mappers;

import com.FmsProject.models.*;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GSAuthorizerMapper {

    @Select("select * FROM tbMaintenaceReq where status = 'Verified'")
    List<MaintenanceRequestModel> getVerifiedRequests();

    @Update("update tbMaintenaceReq set status = 'Authorized', authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{ID}")
    void authorizeVerifiedRequests(MaintenanceRequestModel model);

    @Update("update tbMaintenaceReq set status = 'Rejected',reasonForRejection = #{reasonForRejection}, authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{ID}")
    void rejectVerifiedRequests(MaintenanceRequestModel model);

    @Select("select * from tbMaintenanceSubmission where status = 'Submitted'")
    List<MaintenanceCompletionModel> getVehiclesSubmitted();

    @Update("update tbMaintenanceSubmission set status = 'Authorized', authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
    void authorizeSubmittedVehicles(MaintenanceCompletionModel model);

    @Select("select reqDirectorate from tbMaintenaceReq where ID= #{requestId}")
    String getReqDirectorate(Integer requestId);

    @Select("select mt.MainInterval from tbMaintenaceReq mr,tbMaintenanceSubmission ms,tbMainType mt " +
            "where ms.requestId= #{requestId} and ms.requestId = mr.ID and mr.maintenanceType = mt.ID")
    Double getMaintInterval(Integer requestId);

    @Update("update tbVehicle set status = #{status},lastMilege = #{currentMileage},lmTyresChange = #{currentMileage},previousServiceDate = #{dateFromGarage} where plateNo = #{plateNo}")
    void updateVehicleTyres(String plateNo, String status, double currentMileage, String dateFromGarage);

    @Update("update tbVehicle set status = #{status},lastMilege = #{currentMileage},lmGenService = #{currentMileage},previousServiceDate = #{dateFromGarage} where plateNo = #{plateNo}")
    void updateVehicleGsService(String plateNo, String status, double currentMileage, String dateFromGarage);

    @Update("update tbVehicle set status = #{status},lastMilege = #{currentMileage},lmVehicleBody = #{currentMileage},previousServiceDate = #{dateFromGarage} where plateNo = #{plateNo}")
    void updateVehicleBody(String plateNo, String status, double currentMileage, String dateFromGarage);

    @Select("select *  FROM [FleetManagementSystem].[dbo].[AuthorizedMaintRequests] where requestedDate>=#{from} AND requestedDate<=#{to}")
    List<MaintenanceRequestModel> getAuthorizedRequestsRange(AuthMaintReport reqReport);

    @Select("select *  FROM [FleetManagementSystem].[dbo].[NonAuthorizedMaintRequests] where requestedDate>=#{from} AND requestedDate<=#{to}")
    List<MaintenanceRequestModel> getNonAuthorizedRequestsRange(AuthMaintReport reqReport);

    @Select("select * FROM [FleetManagementSystem].[dbo].[AuthorizedMaintRequests]")
    List<MaintenanceRequestModel> getAuthorizedRequests();

    @Select("select * FROM [FleetManagementSystem].[dbo].[NonAuthorizedMaintRequests]")
    List<MaintenanceRequestModel> getNonAuthorizedRequests();

    @Select("select * FROM [FleetManagementSystem].[dbo].[CompletedMaintSubmissions]")
    List<MaintenanceCompletionModel> getCompletedRequests();

    @Select("select *  FROM [FleetManagementSystem].[dbo].[CompletedMaintSubmissions] where dateTaken>=#{from} AND dateTaken<=#{to}")
    List<MaintenanceCompletionModel> getCompletedRequestsRange(AuthCompReport compReport);

    @Update("update tbVehicle set status=#{status} where plateNo=#{plateNo}")
    void updateStatus(String plateNo, String status);
}
