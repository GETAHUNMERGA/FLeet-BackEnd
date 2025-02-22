package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.OffTimeRequestModel;

public interface DispatcherPrintMapper {

    @Select("select  * from incityrequest where status='Approved' and requestedDate>= DATEADD(M, -3, GETDATE())  order by id desc")
    List<InCityRequestModel> getApprovedIncityRequest();

    @Select("select * from tbOffTimeRequisition  where status='Approved' order by id desc")
    List<OffTimeRequestModel> getApprovedOfftimeRequest();

    @Select("select * from fieldRequest where status = 'Approved' order by id desc")
    List<FieldRequestModel> getApprovedFieldRequest();

}
