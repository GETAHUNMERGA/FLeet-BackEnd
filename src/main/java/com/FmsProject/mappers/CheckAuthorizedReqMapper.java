package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.OffTimeRequestModel;

public interface CheckAuthorizedReqMapper {
    @Select("select * from fieldRequest where status = 'Authorized' order by requestDateTime asc")
    List<FieldRequestModel> getFieldRequests();

    @Update("update fieldRequest set status = 'Checked', approvedBy = #{approvedBy}, approvedDate = #{authorizedDate} where id = #{id}")
    void authorizeFieldRequests(FieldRequestModel field);

    @Update("update fieldRequest set status = 'Rejected',reasonOfRejection = #{reasonOfRejection}, approvedBy = #{approvedBy}, approvedDate = #{authorizedDate} where id = #{id}")
    void rejectFieldRequests(FieldRequestModel field);

    @Select("SELECT * FROM tbOffTimeRequisition where status = 'Authorized'")
    List<OffTimeRequestModel> getOfftimeRequests();

    @Update("update tbOffTimeRequisition set status = 'Rejected',reasonForRejection = #{reasonForRejection}, approvedBy = #{approvedBy}, approvedDate = #{authorizedDate} where id = #{id}")

    void rejectOfftimeRequests(OffTimeRequestModel offtime);

    @Update("update tbOffTimeRequisition set status = 'Checked', approvedBy = #{approvedBy}, approvedDate = #{authorizedDate} where id = #{id}")
    void authorizeOfftimeRequests(OffTimeRequestModel offtime);

}
