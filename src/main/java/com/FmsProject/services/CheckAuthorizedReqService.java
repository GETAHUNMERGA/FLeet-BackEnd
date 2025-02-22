package com.FmsProject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.CheckAuthorizedReqMapper;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.OffTimeRequestModel;

@Service
public class CheckAuthorizedReqService {
    @Autowired
    CheckAuthorizedReqMapper mapper;

    public List<FieldRequestModel> getFieldRequests() {
        return mapper.getFieldRequests();
    }

    public FieldRequestModel authorizeFieldRequests(FieldRequestModel field) {
        Date date = new Date();
        field.setAuthorizedDate(date);
        mapper.authorizeFieldRequests(field);
        return field;
    }

    public FieldRequestModel rejectFieldRequests(FieldRequestModel field) {
        Date date = new Date();
        field.setAuthorizedDate(date);
        mapper.rejectFieldRequests(field);
        return field;
    }

    public OffTimeRequestModel rejectOfftimeRequests(OffTimeRequestModel offtime) {
        Date date = new Date();
        offtime.setAuthorizedDate(date);
        mapper.rejectOfftimeRequests(offtime);
        return offtime;
    }

    public OffTimeRequestModel authorizeOfftimeRequests(OffTimeRequestModel offtime) {
        Date date = new Date();
        offtime.setAuthorizedDate(date);
        mapper.authorizeOfftimeRequests(offtime);
        return offtime;
    }

    public List<OffTimeRequestModel> getOfftimeRequests() {
        return mapper.getOfftimeRequests();
    }

}
