package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.models.SubmissionReportModel;

public interface MechanicMapper {

        @Select("select * from tbMaintenaceReq where status = 'Unverified'")
        List<MaintenanceRequestModel> getCustodianRequest();

        @Select("select * from tbMainType where ID = #{maintType}")
        MaintenanceTypeModel getMaintenanceType(int maintType);

        @Update("update tbMaintenaceReq set modifiedBy = #{modifiedBy}, modifiedDate=#{modifiedDate}, kmInsp=#{kmInsp}"
                        + ",isInsured=#{insured}, status='Verified', workRepairRequested = #{workRepairRequested}, fuelGaugeInAmount = #{fuelGaugeInAmount} where ID = #{ID} ")
        void verifyCustodianRequest(MaintenanceRequestModel request);

        @Select("select * from tbMaintenaceReq where status != 'Unverified'")
        List<MaintenanceRequestModel> getMechanicRequests();

        @Select("select * FROM [FleetManagementSystem].[dbo].[tbMaintenanceSubmission]")
        List<MaintenanceCompletionModel> getCompletedRequests();

        @Select("select r.*, v.model from tbMaintenaceReq r, tbVehicle v where r.ID = #{request} AND r.plateNo = v.plateNo")
        MaintenanceRequestModel getRequestDetails(int request);

        @Update("update tbMaintenanceSubmission set currentMilege = #{currentMilege}, petrolAmount=#{petrolAmount}, vehicleReceivedBy=#{vehicleReceivedBy},"
                        + "vehicleGivenBy = #{vehicleGivenBy}, vehicleReceivedDate = #{vehicleReceivedDate}, modifiedBy = #{modifiedBy}, modifiedDate=#{modifiedDate}"
                        + ", status = 'Submitted' where requestId = #{requestId}")
        void modifyMaintenanceCompletion(MaintenanceCompletionModel request);

        @Select("select * from UnverifiedCustodianRequests where requestedDate>=#{from} AND requestedDate<=#{to}")
        List<MaintenanceRequestModel> getUnverifiedRequestReportRange(ReportModel request);

        @Select("select * from UnverifiedCustodianRequests")
        List<MaintenanceRequestModel> getUnverifiedRequestReport(ReportModel request);

        @Select("select * from UnauthorizedMechanicRequests where requestedDate>=#{from} AND requestedDate<=#{to}  ")
        List<MaintenanceRequestModel> getMechanicUnauthorizedRequestReportRange(ReportModel request);

        @Select("select * from AuthorizedMechanicRequests where requestedDate>=#{from} AND requestedDate<=#{to} ")
        List<MaintenanceRequestModel> getMechanicAuthorizedRequestReportRange(ReportModel request);

        @Select("select * from RejectedMechanicRequests where requestedDate>=#{from} AND requestedDate<=#{to}  ")
        List<MaintenanceRequestModel> getMechanicRejectedRequestReportRange(ReportModel request);

        @Select("select * from UnauthorizedMechanicRequests")
        List<MaintenanceRequestModel> getMechanicUnauthorizedRequestReport(ReportModel request);

        @Select("select * from AuthorizedMechanicRequests")
        List<MaintenanceRequestModel> getMechanicAuthorizedRequestReport(ReportModel request);

        @Select("select * from RejectedMechanicRequests  ")
        List<MaintenanceRequestModel> getMechanicRejectedRequestReport(ReportModel request);

        @Select("select * from RequestSubmissionFromTechnical")
        List<SubmissionReportModel> getMechanicSubmissionCompletedRequestReport(ReportModel request);

        @Select("select * from RequestSubmissionFromTechnical where requestedDate>=#{from} AND requestedDate<=#{to}")
        List<SubmissionReportModel> getMechanicSubmissionCompletedRequestReportRange(ReportModel request);

        @Select("select * from UnauthorizedMechanicSubmittedRequests")
        List<SubmissionReportModel> getMechanicSubmissionSubmittedRequestReport(ReportModel request);

        @Select("select * from UnauthorizedMechanicSubmittedRequests where requestedDate>=#{from} AND requestedDate<=#{to}")
        List<SubmissionReportModel> getMechanicSubmissionSubmittedRequestReportRange(ReportModel request);

        @Select("select * from AuthorizedMechanicSubmittedRequests")
        List<SubmissionReportModel> getMechanicSubmissionAuthorizedRequestReport(ReportModel request);

        @Select("select * from AuthorizedMechanicSubmittedRequests where requestedDate>=#{from} AND requestedDate<=#{to}")
        List<SubmissionReportModel> getMechanicSubmissionAuthorizedRequestReportRange(ReportModel request);

        @Select("select count(*)  FROM UnverifiedCustodianRequests")
        int getUnverifiedNo();

        @Select("select count(*)  FROM UnauthorizedMechanicRequests")
        int getVerifiedNo();

        @Select("select count(*)  FROM AuthorizedMechanicRequests")
        int getAuthorizedRequestNo();

        @Select("select count(*)  FROM RequestSubmissionFromTechnical")
        int getNoCompletionTechnical();

        @Select("select count(*)  FROM UnauthorizedMechanicSubmittedRequests")
        int getNoSubmittedMechanic();

}
