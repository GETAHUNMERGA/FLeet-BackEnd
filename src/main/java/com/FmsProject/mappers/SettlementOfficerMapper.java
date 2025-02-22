package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.AdvPaymentModel;

public interface SettlementOfficerMapper {

        @Select("select a.id, a.staffName, a.staffAcc, a.status, a.totalAmount, a.crtDate, a.noOfDays, t.advanceType as typeOfSettlment,"
                        + " d.directorate as reqDir, u.username as reqBy, a.purpose, a.grade, a.claimBranch, "
                        + "a.departureDate from tblAdvancePayment a, tblAdvanceType t, tbDirectorate d, users u"
                        + " where a.directorate = d.id and a.requestedBy = u.id and a.typeOfSettlment = t.id and a.status = 'Authorized'")
        List<AdvPaymentModel> getAdvancePaymentRequests();

        @Update("update tblAdvancePayment set status = 'Granted', settledBy = #{settledBy}, dateSettled = #{dateSettled} where id = #{id} ")
        void grantAdvancePayment(AdvPaymentModel advance);

        @Update("update tblAdvancePayment set status = 'Rejected', settledBy = #{settledBy},reasonForRejection=#{reasonForRejection},dateSettled = #{dateSettled}  where id = #{id} ")
        void rejectAdvancePayment(AdvPaymentModel advance);

        @Select("select a.id, a.staffName, a.staffAcc, a.status, a.totalAmount, a.crtDate, a.noOfDays, t.advanceType as typeOfSettlment,"
                        + " d.directorate as reqDir, u.username as reqBy, aprov.username as approvedBy, a.purpose, a.grade, a.claimBranch, "
                        + "a.departureDate from tblAdvancePayment a, tblAdvanceType t, tbDirectorate d, users u, users as aprov"
                        + " where a.directorate = d.id and a.requestedBy = u.id and a.appBy = aprov.id and a.typeOfSettlment = t.id and a.id = #{id}")
        AdvPaymentModel printSettlementDetails(Integer adId);

        @Select("select count(*) FROM tblAdvancePayment where status = 'Authorized' ")
        int getNoOfRequest();

}
