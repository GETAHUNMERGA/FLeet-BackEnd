package com.FmsProject.mappers;

import com.FmsProject.models.AdvPaymentModel;
import com.FmsProject.models.AdvTypeModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdvPaymentMapper {

        @Select("select * from tblAdvanceType")
        List<AdvTypeModel> getTypeOfSettlement();

        @Insert("insert into tblAdvancePayment(directorate,requestedBy,staffName,staffAcc,claimBranch,totalAmount,purpose,typeOfSettlment,departureDate,grade,noOfDays,crtDate,status)"
                        + " values("
                        + "#{directorate},#{requestedBy},#{staffName},#{staffAcc},#{claimBranch},#{totalAmount},#{purpose},#{typeOfSettlment},#{departureDate},#{grade},#{noOfDays},#{crtDate},'Unauthorized')")
        void createAdvance(AdvPaymentModel advPaymentModel);

        @Select("select * from tblAdvancePayment where id = #{id}")
        AdvPaymentModel getAdvanceById(String id);

        @Update("update tblAdvancePayment set staffName=#{staffName},staffAcc=#{staffAcc},claimBranch=#{claimBranch},totalAmount=#{totalAmount},purpose=#{purpose},"
                        + "typeOfSettlment=#{typeOfSettlment},departureDate=#{departureDate},grade=#{grade},noOfDays=#{noOfDays} where id=#{id}")
        void updateAdvancePayment(AdvPaymentModel advPaymentModel);

        @Select("SELECT id FROM tbDirectorate where directorate = #{reqDir}")
        int getDirId(String reqDir);

        @Select("SELECT id FROM users where username = #{reqBy}")
        int getReqId(String reqBy);

        @Select("select * from tblAdvancePayment where requestedBy = #{userId}")
        List<AdvPaymentModel> getAdvancePayments(int userId);

        @Select("SELECT username FROM users where id = #{id}")
        String getReqUserName(int id);

        @Select("select directorate from tbDirectorate where id = #{id}")
        String getReqDir(int id);

        @Select("select advanceType from tblAdvanceType where id = #{id}")
        String getAdvType(String id);

        @Delete("delete from tblAdvancePayment where id =#{id}")
        void cancelAdv(Long id);

        // Authorizer

        @Select("SELECT p.*, u.directorate as reqDir FROM tblAdvancePayment p, tblAdvRequesterApprover appreq, users u where"
                        + " p.status = 'Unauthorized' and appreq.approver=#{username} and p.requestedBy=u.id and appreq.requester=u.username")
        List<AdvPaymentModel> getAdvRequests(String username);

        @Update("update tblAdvancePayment set status = 'Authorized', appBy = #{appBy}, appDate = #{appDate} where id = #{id}")
        void authorizeAdvancePayment(AdvPaymentModel advPaymentModel);

        @Update("update tblAdvancePayment set status = 'Rejected',reasonForRejection = #{reasonForRejection}, appBy = #{appBy}, appDate = #{appDate} where id = #{id}")
        void rejectAdvancePayment(AdvPaymentModel advPaymentModel);
}
