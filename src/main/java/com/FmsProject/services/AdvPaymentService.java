package com.FmsProject.services;

import com.FmsProject.mappers.AdvPaymentMapper;
import com.FmsProject.models.AdvPaymentModel;
import com.FmsProject.models.AdvTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdvPaymentService {

    @Autowired
    private AdvPaymentMapper mapper;

    public List<AdvTypeModel> getTypeOfSettlement() {
        return mapper.getTypeOfSettlement();
    }

    public void createAdvance(AdvPaymentModel advPaymentModel) {
        mapper.createAdvance(advPaymentModel);
    }

    public AdvPaymentModel getAdvanceById(String id) {
        return mapper.getAdvanceById(id);
    }

    public void updateAdvancePayment(AdvPaymentModel advPaymentModel) {
        mapper.updateAdvancePayment(advPaymentModel);
    }

    public int getDirId(String reqDir) {
        return mapper.getDirId(reqDir);
    }

    public int getReqId(String reqBy) {
        return mapper.getReqId(reqBy);
    }

    public List<AdvPaymentModel> getAdvancePayments(String username) {
        int userId = mapper.getReqId(username);
        return mapper.getAdvancePayments(userId);
    }

    public AdvPaymentModel getAdvDetail(String id) {

        AdvPaymentModel adv = mapper.getAdvanceById(id);
        adv.setReqBy(mapper.getReqUserName(adv.getRequestedBy()));
        adv.setReqDir(mapper.getReqDir(adv.getDirectorate()));
        adv.setTypeOfSettlment(mapper.getAdvType(adv.getTypeOfSettlment()));
        adv.setApprovedBy(mapper.getReqUserName(adv.getAppBy()));
        return adv;
    }

    public void cancelAdv(Long id) {
        mapper.cancelAdv(id);
    }

    public List<AdvPaymentModel> getAdvRequests(String username) {
        // int dirId = mapper.getDirId(directorate);
        return mapper.getAdvRequests(username);
    }

    public AdvPaymentModel authorizeAdvancePayment(AdvPaymentModel advPaymentModel) {
        Date date = new Date();
        advPaymentModel.setAppDate(date);
        int authId = mapper.getReqId(advPaymentModel.getApprovedBy());
        advPaymentModel.setAppBy(authId);
        mapper.authorizeAdvancePayment(advPaymentModel);
        return advPaymentModel;
    }

    public AdvPaymentModel rejectAdvancePayment(AdvPaymentModel advPaymentModel) {
        Date date = new Date();
        advPaymentModel.setAppDate(date);
        int authId = mapper.getReqId(advPaymentModel.getApprovedBy());
        advPaymentModel.setAppBy(authId);
        mapper.rejectAdvancePayment(advPaymentModel);
        return advPaymentModel;
    }
}
