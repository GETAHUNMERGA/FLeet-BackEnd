package com.FmsProject.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.MaintenanceRequestMapper;
import com.FmsProject.models.GarageModel;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.models.ServiceType;

@Service
public class MaintenanceRequestService {
    @Autowired
    MaintenanceRequestMapper mapper;

    public MaintenanceRequestModel sendServiceMaintenanceRequest(MaintenanceRequestModel request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        try {
            int mainId = mapper.getIdOfMaintenanceType(request.getMaintenanceType());
            request.setMainType(mainId);
            request.setRequestedDate(dateFormat.parse(d));
            if (mapper.sendServiceMaintenanceRequest(request)) {
                mapper.updateVehicle(request.getPlateNo());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return request;
    }
//get all Service Types
public List<ServiceType> getServiceType(){
    List<ServiceType> serviceType=mapper.getServiceType();
    return serviceType;
}
public ServiceType insertServiceType(String serviceType){
    ServiceType newServiceType = new ServiceType();
    newServiceType.setServiceType(serviceType); 
    mapper.saveServiceType(newServiceType); 
   return newServiceType;
}



    public List<MaintenanceRequestModel> getAllOfMyMaintenanceReqcuest() {

        return mapper.getAllOfMyMaintenanceReqcuest();
    }

    public MaintenanceRequestModel UpdateMaintenanceRequest(MaintenanceRequestModel request) {
        int mainId = mapper.getIdOfMaintenanceType(request.getMaintenanceType());
        request.setMainType(mainId);
        mapper.UpdateMaintenanceRequest(request);
        return request;
    }

    public MaintenanceTypeModel WhatType(String mainType) {
        try {
            return mapper.WhatType(mainType);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public MaintenanceRequestModel sendCustodianMaintenanceRequest(MaintenanceRequestModel request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        try {
            request.setRequestedDate(dateFormat.parse(d));
            if (mapper.sendCustodianMaintenanceRequest(request)) {
                mapper.updateVehicle(request.getPlateNo());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return request;
    }

    public List<MaintenanceRequestModel> getCustodianMaintenanceReqcuest(String director) {
        List<MaintenanceRequestModel> myrequest = null;
        if (director.equals("General Technical and Security Service Directorate")) {
            myrequest = mapper.getOfficerMaintenanceReqcuest(director);
        } else {
            myrequest = mapper.getCustodianMaintenanceReqcuest(director);
        }
        return myrequest;
    }

    public MaintenanceRequestModel UpdateCustodianRequest(MaintenanceRequestModel request) {
        mapper.UpdateCustodianRequest(request);
        return request;
    }

    public MaintenanceCompletionModel getMaintenanceDetails(int id) {

        return mapper.getMaintenanceDetails(id);
    }

    public List<MaintenanceRequestModel> getReportOfMaintenanceRequests(ReportModel request) {
        List<MaintenanceRequestModel> response;
        if (request.getFrom() == null || request.getTo() == null) {
            response = mapper.getReportOfMaintenanceRequests(request);
        } else {
            response = mapper.getReportOfMaintenanceRequestsRange(request);
        }

        return response;
    }

    public GarageModel getVehicleGarage(Integer id) {
        return mapper.getVehicleGarage(id);
    }

}
