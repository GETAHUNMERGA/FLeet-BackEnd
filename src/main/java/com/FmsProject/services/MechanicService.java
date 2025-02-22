package com.FmsProject.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.MechanicMapper;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceRequestModel;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.ReportModel;
import com.FmsProject.models.SubmissionReportModel;

@Service
public class MechanicService {
    @Autowired
    MechanicMapper mapper;

    public List<MaintenanceRequestModel> getCustodianRequest() {
        return mapper.getCustodianRequest();
    }

    public MaintenanceTypeModel getMaintenanceType(String maintType) {
        int ID = Integer.parseInt(maintType);
        return mapper.getMaintenanceType(ID);
    }

    public MaintenanceRequestModel verifyCustodianRequest(MaintenanceRequestModel request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        try {
            request.setModifiedDate(dateFormat.parse(d));
            int insured = Integer.parseInt(request.getIsInsured());
            request.setInsured(insured);
            mapper.verifyCustodianRequest(request);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return request;
    }

    public List<MaintenanceRequestModel> getMechanicRequests() {
        return mapper.getMechanicRequests();
    }

    public List<MaintenanceCompletionModel> getCompletedRequests() {
        return mapper.getCompletedRequests();
    }

    public MaintenanceRequestModel getRequestDetails(int request) {
        return mapper.getRequestDetails(request);
    }

    public MaintenanceCompletionModel modifyMaintenanceCompletion(MaintenanceCompletionModel request) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String d = dateFormat.format(date);
            request.setModifiedDate(dateFormat.parse(d));
            mapper.modifyMaintenanceCompletion(request);
            return request;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MaintenanceRequestModel> getUnverifiedRequestReport(ReportModel request) {
        List<MaintenanceRequestModel> response;
        if (request.getFrom() == null || request.getTo() == null) {
            response = mapper.getUnverifiedRequestReport(request);
        } else {
            response = mapper.getUnverifiedRequestReportRange(request);
        }

        return response;

    }

    public List<MaintenanceRequestModel> getMechanicRequestReport(ReportModel request) {
        List<MaintenanceRequestModel> response = null;
        if (request.getFrom() == null || request.getTo() == null) {
            if (request.getMechanicReport().equals("Unauthorized")) {
                response = mapper.getMechanicUnauthorizedRequestReport(request);
            } else if (request.getMechanicReport().equals("Authorized")) {
                response = mapper.getMechanicAuthorizedRequestReport(request);
            } else if (request.getMechanicReport().equals("Rejected")) {
                response = mapper.getMechanicRejectedRequestReport(request);
            }

        } else if (request.getFrom() != null || request.getTo() != null) {
            if (request.getMechanicReport().equals("Unauthorized")) {

                response = mapper.getMechanicUnauthorizedRequestReportRange(request);

            } else if (request.getMechanicReport().equals("Authorized")) {

                response = mapper.getMechanicAuthorizedRequestReportRange(request);

            } else if (request.getMechanicReport().equals("Rejected")) {
                response = mapper.getMechanicRejectedRequestReportRange(request);
            }
        }
        return response;

    }

    public List<SubmissionReportModel> getMechanicSubmittedRequestReport(ReportModel request) {
        List<SubmissionReportModel> response = null;
        if (request.getFrom() == null || request.getTo() == null) {
            if (request.getMechanicReport().equals("Completed")) {
                response = mapper.getMechanicSubmissionCompletedRequestReport(request);
            } else if (request.getMechanicReport().equals("Submitted")) {
                response = mapper.getMechanicSubmissionSubmittedRequestReport(request);
            } else if (request.getMechanicReport().equals("Authorized")) {
                response = mapper.getMechanicSubmissionAuthorizedRequestReport(request);
            }

        } else if (request.getFrom() != null || request.getTo() != null) {
            if (request.getMechanicReport().equals("Completed")) {

                response = mapper.getMechanicSubmissionCompletedRequestReportRange(request);

            } else if (request.getMechanicReport().equals("Submitted")) {

                response = mapper.getMechanicSubmissionSubmittedRequestReportRange(request);

            } else if (request.getMechanicReport().equals("Authorized")) {
                response = mapper.getMechanicSubmissionAuthorizedRequestReportRange(request);
            }
        }
        return response;
    }

    public int getUnverifiedNo() {
        return mapper.getUnverifiedNo();
    }

    public int getVerifiedNo() {
        return mapper.getVerifiedNo();
    }

    public int getAuthorizedRequestNo() {
        return mapper.getAuthorizedRequestNo();
    }

    public int getNoCompletionTechnical() {
        return mapper.getNoCompletionTechnical();
    }

    public int getNoSubmittedMechanic() {
        return mapper.getNoSubmittedMechanic();
    }
}
