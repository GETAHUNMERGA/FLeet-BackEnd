package com.FmsProject.mappers;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.VehicleModel;

public interface FieldRequestMapper {

	@Select("select * from fieldRequest")
	List<FieldRequestModel> getRequisition();

	@Select("select * from fieldRequest where status = 'Checked' order by authorizedDate asc")
	List<FieldRequestModel> getRequisitionForDispatch();

	@Insert("insert into fieldRequest(directorate,date,passengers,requestedFor,purpose,departureDate,returnDate,noOfDaysRequested,materialloaded,destBranch,destCity,destKM,contingency,totalKM,expence,transportMode,requestedBy,requestDate,status,requestDateTime,passTelephone,requestedTime)"
			+ " values(#{directorate},#{date},#{passengers},#{requestedFor},#{purpose},#{departureDate},#{returnDate},#{noOfDaysRequested},#{materialloaded},#{destBranch},#{destCity},#{destKM},#{contingency},#{totalKM},#{expence},#{transportMode},#{requestedBy},#{requestDate},'Unauthorized',GETDATE(),#{passTelephone},CONVERT(TIME, GETDATE()))")

	void sendRequest(FieldRequestModel req);

	Long requestCount();

	@Select("select * from fieldRequest where id =#{id}")
	Optional<FieldRequestModel> selectRequestById(Integer id);

	@Delete("delete from fieldRequest where id =#{id}")
	void deleteRequest(Long id);

	@Update("update fieldRequest set passengers=#{passengers},requestedFor=#{requestedFor},"
			+ "purpose=#{purpose},departureDate=#{departureDate}, returnDate=#{returnDate},"
			+ "noOfDaysRequested=#{noOfDaysRequested},materialloaded=#{materialloaded},"
			+ "destBranch=#{destBranch},destCity=#{destCity},destKM=#{destKM},"
			+ "contingency=#{contingency},totalKM=#{totalKM},expence=#{expence},"
			+ "transportMode=#{transportMode},requestDate=#{requestDate},passTelephone=#{passTelephone} where id =#{id}")
	void updateRequest(FieldRequestModel req);

	@Update("update fieldRequest set approvedBy=#{approvedBy},approvedDate=#{approvedDate} where id =#{id} ")
	void approveRequest(FieldRequestModel req);

	@Update("update fieldRequest set status = 'Rejected',reasonOfRejection=#{reasonOfRejection},rejectedBy=#{rejectedBy} where id =#{id} ")
	void rejectRequest(FieldRequestModel req);

	@Update("update fieldRequest set approvedBy=#{approvedBy},approvedDate=#{approvedDate} where id = #{reqID}")
	void updateApprovedBy(String approvedBy, String approvedDate, Integer reqID);

	@Update("update fieldRequest set status = 'Approved' where id =#{reqID} ")
	void updateRequesterStatus(Integer reqID);

	@Update("update fieldRequest set approvedBy=#{approver},approvedDate=#{dateApproved} where id =#{reqID} ")
	void updateApprover(String approver, String dateApproved, Integer reqID);

	// @Update("update fieldRequest set rejectedBy=#{rejecter} where id =#{ids} ")
	// void updateRejecter(String rejecter, Integer ids);

	@Update("update fieldRequest set status = 'Rejected', rejectedBy=#{rejectedBy},reasonOfRejection=#{reasonOfRejection},rejectedDate=#{rejectedDate} where id=#{id}")
	void addRejecter(FieldRequestModel rejecter);

	@Select("select * from fieldRequest where status='Approved'")
	List<FieldRequestModel> getAllApprovedRequest();

	@Select("select * from tbVehicle where status ='dispatched'")
	List<VehicleModel> getDispatchedVehicle();

	@Select("select * from fieldRequest where requestedBy = #{username}")
	List<FieldRequestModel> getmyRequsition(String username);

}
