package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.DispatcherReportModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.OffTimeDispatcherModel;

public interface DispatcherReportMapper {
   @Select("SELECT * FROM tblDispatchedListView WHERE dispdate BETWEEN #{fieldfromDate} AND #{fieldtoDate}")
	List<DispatcherModel> dispatcherFieldReport(DispatcherReportModel report);
   
   @Select("SELECT * FROM tblIncityDispatchedListView WHERE departureDate BETWEEN #{incityFromDate} AND #{incityToDate}")
	List<InCityDispatcherModel> dispatcherIncityReport(DispatcherReportModel report);
   
   @Select("SELECT * FROM tblOfftimeDispatchedListView WHERE CONVERT(NVARCHAR(MAX), date) >=#{offtimeFromDate} AND CONVERT(NVARCHAR(MAX), date) <= #{offtimeToDate}")
	List<OffTimeDispatcherModel> dispatcherOfftimeReport(DispatcherReportModel report);

   @Select("select * from tblDispatchedListView")
    List<DispatcherModel> getAllFieldDispatch();

   @Select("select * from tblIncityDispatchedListView")
   List<InCityDispatcherModel> getAllIncityDispatch();

   @Select("select * from tblOfftimeDispatchedListView")
   List<OffTimeDispatcherModel> getAllOfftimeDispatch();




}
