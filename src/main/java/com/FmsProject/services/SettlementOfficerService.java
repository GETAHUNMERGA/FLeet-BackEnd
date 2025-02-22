package com.FmsProject.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.SettlementOfficerMapper;
import com.FmsProject.models.AdvPaymentModel;

@Service
public class SettlementOfficerService {
    @Autowired
    SettlementOfficerMapper mapper;

    public List<AdvPaymentModel> getAdvancePaymentRequests() {
        return mapper.getAdvancePaymentRequests();
    }

    public void grantAdvancePayment(AdvPaymentModel advance) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);

        try {
            advance.setDateSettled(dateFormat.parse(d));
            mapper.grantAdvancePayment(advance);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void rejectAdvancePayment(AdvPaymentModel advance) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);

        try {
            advance.setDateSettled(dateFormat.parse(d));
            mapper.rejectAdvancePayment(advance);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public AdvPaymentModel printSettlementDetails(Integer adId) {
        return mapper.printSettlementDetails(adId);
    }

    public int getNoOfRequest() {
        return mapper.getNoOfRequest();
    }

}
