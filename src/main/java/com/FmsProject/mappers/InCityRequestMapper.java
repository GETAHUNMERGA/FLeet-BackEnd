package com.FmsProject.mappers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.InCityRequestModel;

public interface InCityRequestMapper {

	@Insert("insert into incityrequest(requestBy,directorate,passenger,destination,dateFrom,requestTimeFrom,requestTimeTo,purpose,status,DateTo,numberOfDays,requestedDate,passTelephone,requestedFor,requestedTime)"
			+ "values("
			+ "#{requestBy},#{directorate},#{passenger},#{destination},#{dateFrom},#{requestTimeFrom},#{requestTimeTo},#{purpose},'Unauthorized',#{dateTo},#{numberOfDays},#{requestedDate},#{passTelephone}, #{requestedFor},CONVERT(TIME, GETDATE()))")
	void saveIncityRequestMapper(InCityRequestModel model);

	@Select("select * from incityrequest where status='Authorized' ORDER BY id ASC")
	List<InCityRequestModel> viewIncityRequestMapper();

	@Select("select * from incityrequest where id =#{id}")
	Optional<InCityRequestModel> selectRequestById(Integer id);

	@Delete("delete from incityrequest where id =#{id}")
	void cancelIncityRequestMapper(Integer id);

	@Update("update incityrequest set requestBy=#{requestBy},directorate=#{directorate},passenger=#{passenger},destination=#{destination},dateFrom=#{dateFrom},requestTimeFrom=#{requestTimeFrom},DateTo=#{dateTo},requestTimeTo=#{requestTimeTo},numberOfDays=#{numberOfDays},requestedDate=#{requestedDate},purpose=#{purpose}, passTelephone=#{passTelephone},requestedFor=#{requestedFor} where id=#{id}")
	void updateIncityRequestMapper(InCityRequestModel model);

	@Update("update incityrequest set reasonForRejection = #{reasonForRejection},status = 'Rejected',approvedDate=#{approvedDate},rejectedBy=#{rejectedBy} where id =#{id}")
	void rejectRequest(InCityRequestModel req);

	@Select("select * from incityrequest where requestBy=#{name}")
	List<InCityRequestModel> incityrequestviewbyusername(String name);

	@Select("select * from incityrequest where status='Approved' ")
	List<InCityRequestModel> incityRequestApproved();

	@Update("update incityrequest set status='Approved',approvedDate=#{approvedDate},approvedBy=#{approvedBy},remark=#{remark} where id=#{requesterId}")
	void approveRequest(String approvedBy, Date approvedDate, Integer requesterId, String remark);
}