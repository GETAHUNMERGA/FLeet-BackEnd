package com.FmsProject.services;

import com.FmsProject.mappers.GSAuthorizerMapper;
import com.FmsProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class GSAuthorizerService {

    @Autowired
    GSAuthorizerMapper mapper;

    public List<MaintenanceRequestModel> getVerifiedRequests() {
        return mapper.getVerifiedRequests();
    }

    public MaintenanceRequestModel authorizeVerifiedRequests(MaintenanceRequestModel model) {
        Date date = new Date();
        model.setAuthorizedDate(date);
        mapper.authorizeVerifiedRequests(model);
        return model;
    }

    @Transactional
    public MaintenanceRequestModel rejectVerifiedRequests(MaintenanceRequestModel model) {
        Date date = new Date();
        model.setAuthorizedDate(date);
        mapper.rejectVerifiedRequests(model);
        String status;
        if (Objects.equals(model.getReqDirectorate(), "General Technical and Security Service Directorate")) {
            status = "Pool";
        } else {
            status = "Assigned to Respective";
        }
        mapper.updateStatus(model.getPlateNo(), status);
        return model;
    }

    public List<MaintenanceCompletionModel> getVehiclesSubmitted() {
        return mapper.getVehiclesSubmitted();
    }

    @Transactional
    public MaintenanceCompletionModel authorizeSubmittedVehicles(MaintenanceCompletionModel model) {
        Date date = new Date();
        model.setAuthorizedDate(date);
        mapper.authorizeSubmittedVehicles(model);

        String directorate = mapper.getReqDirectorate(model.getRequestId());
        Double mainInterval = mapper.getMaintInterval(model.getRequestId());
        double currentMileage = model.getCurrentMilege();
        String dateFromGarage = model.getDateFromGarage();

        // update vehicle data
        String status;
        if (Objects.equals(directorate, "General Technical and Security Service Directorate")) {
            status = "Pool";
        } else {
            status = "Assigned to Respective";
        }

        if (mainInterval != null) {
            if (mainInterval == 50000.00) {
                mapper.updateVehicleTyres(model.getPlateNo(), status, currentMileage, dateFromGarage);
            } else if (mainInterval == 5000.00) {
                mapper.updateVehicleGsService(model.getPlateNo(), status, currentMileage, dateFromGarage);
            } else {
                mapper.updateVehicleBody(model.getPlateNo(), status, currentMileage, dateFromGarage);
            }
        } else {
            mapper.updateVehicleBody(model.getPlateNo(), status, currentMileage, dateFromGarage);
        }

        return model;
    }

    public List<MaintenanceRequestModel> getRequestsReport(AuthMaintReport reqReport) {

        List<MaintenanceRequestModel> response = null;
        if (reqReport.getFrom() == null || reqReport.getTo() == null) {
            if (reqReport.getListing().equals("Authorized")) {
                response = mapper.getAuthorizedRequests();
            } else if (reqReport.getListing().equals("Non-Authorized")) {
                response = mapper.getNonAuthorizedRequests();
            }

        } else {
            if (reqReport.getListing().equals("Authorized")) {

                response = mapper.getAuthorizedRequestsRange(reqReport);

            } else if (reqReport.getListing().equals("Non-Authorized")) {

                response = mapper.getNonAuthorizedRequestsRange(reqReport);

            }
        }
        return response;

    }

    public List<MaintenanceCompletionModel> getCompletedReport(AuthCompReport compReport) {

        List<MaintenanceCompletionModel> response = null;
        if (compReport.getFrom() == null || compReport.getTo() == null) {
            response = mapper.getCompletedRequests();
        } else {
            response = mapper.getCompletedRequestsRange(compReport);
        }
        return response;
    }
}
