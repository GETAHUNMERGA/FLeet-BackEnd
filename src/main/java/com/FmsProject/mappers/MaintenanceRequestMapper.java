package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.GarageModel;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.models.ServiceType;

public interface MaintenanceRequestMapper {

        @Select("select ID from tbMainType where MainType = #{maintenanceType}")
        int getIdOfMaintenanceType(String maintenanceType);

        @Insert("insert into tbMaintenaceReq(plateNo, currentMilage, previousServiceMilage,workRepairRequested,"
                        + " milageDifference, fuelGaugeInAmount,requestedBy, requestedDate, reqDirectorate, "
                        + "maintenanceType, status, previousServiceDate)"
                        + "values(#{plateNo},#{currentMilage}, #{previousServiceMilage}, #{workRepairRequested},"
                        + " #{milageDifference}, #{fuelGaugeInAmount}, #{requestedBy}, #{requestedDate}, #{reqDirectorate},"
                        + " #{mainType}, 'Unverified', CAST(#{previousServiceDate} as date))")





        
        // @Insert("insert into tbMaintenaceReq(plateNo, currentMilage, previousServiceMilage,workRepairRequested,"
        //                 + " milageDifference, fuelGaugeInAmount,requestedBy, requestedDate, reqDirectorate, "
        //                 + "maintenanceType, status, previousServiceDate)"
        //                 + "values(#{plateNo},#{currentMilage}, #{previousServiceMilage}, #{workRepairRequested},"
        //                 + " #{milageDifference}, #{fuelGaugeInAmount}, #{requestedBy}, #{requestedDate}, #{reqDirectorate},"
        //                 + " #{serviceType}, 'Unverified', CAST(#{previousServiceDate} as date))")

        boolean sendServiceMaintenanceRequest(MaintenanceRequestModel request);
     //get Service Types from DB
     @Select("select * from tblServiceType")
     List<ServiceType>getServiceType();
      @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
       @Insert("insert into tblServiceType (serviceType) values (#{serviceType})")
        void  saveServiceType(ServiceType serviceType);

        @Update("update tbVehicle set status ='Maintenance' where plateNo = #{plateNo}")
        void updateVehicle(String plateNo);

        @Select("SELECT r.ID ,r.plateNo ,r.currentMilage,r.previousServiceMilage,r.previousServiceDate,r.milageDifference,r.fuelGaugeInAmount,r.requestedBy,r.requestedDate ,r.reqDirectorate,r.modifiedBy,r.modifiedDate,r.authorizedBy ,r.authorizedDate,r.workRepairRequested,r.KMInsp, r.technicalBy ,r.technicalRespondDate ,t.MainType as maintenanceType,r.status FROM tbMaintenaceReq r, tbMainType t where r.maintenanceType = t.ID AND r.reqDirectorate = 'General Technical and Security Service Directorate'")
        List<MaintenanceRequestModel> getAllOfMyMaintenanceReqcuest();

        @Update("update tbMaintenaceReq set workRepairRequested = #{workRepairRequested},fuelGaugeInAmount=#{fuelGaugeInAmount}"
                        + ", maintenanceType = #{mainType} where ID = #{ID}")
        void UpdateMaintenanceRequest(MaintenanceRequestModel request);

        @Select("select * from tbMainType where MainType = #{mainType}")
        MaintenanceTypeModel WhatType(String mainType);

        @Insert("insert into tbMaintenaceReq(plateNo, currentMilage, previousServiceMilage,workRepairRequested,"
                        + " milageDifference, fuelGaugeInAmount,requestedBy, requestedDate, reqDirectorate "
                        + ", status, previousServiceDate)"
                        + "values(#{plateNo},#{currentMilage}, #{previousServiceMilage}, #{workRepairRequested},"
                        + " #{milageDifference}, #{fuelGaugeInAmount}, #{requestedBy}, #{requestedDate}, #{reqDirectorate}"
                        + ", 'Unverified', CAST(#{previousServiceDate} as date))")
        boolean sendCustodianMaintenanceRequest(MaintenanceRequestModel request);

        @Select("SELECT * FROM tbMaintenaceReq where reqDirectorate = #{director}")
        List<MaintenanceRequestModel> getCustodianMaintenanceReqcuest(String director);

        @Update("update tbMaintenaceReq set workRepairRequested = #{workRepairRequested},fuelGaugeInAmount=#{fuelGaugeInAmount}"
                        + " where ID = #{ID}")
        void UpdateCustodianRequest(MaintenanceRequestModel request);

        @Select("SELECT * FROM tbMaintenaceReq where reqDirectorate = #{director} AND maintenanceType is null")
        List<MaintenanceRequestModel> getOfficerMaintenanceReqcuest(String director);

        @Select("select * from tbMaintenanceSubmission where requestId = #{id}")
        MaintenanceCompletionModel getMaintenanceDetails(int id);

        @Select("select * from tbMaintenaceReq where reqDirectorate = #{directorate}")
        List<MaintenanceRequestModel> getReportOfMaintenanceRequests(ReportModel request);

        @Select("select * from tbMaintenaceReq where reqDirectorate = #{directorate} and requestedDate>=#{from} AND requestedDate<=#{to}")
        List<MaintenanceRequestModel> getReportOfMaintenanceRequestsRange(ReportModel request);

        @Select("select id, name, location, phone  from tbGarage g where g.id = (select garage from tbMaintenaceReq where id = #{id})")
        GarageModel getVehicleGarage(Integer id);

}
