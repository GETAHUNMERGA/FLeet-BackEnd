package com.FmsProject.mappers;

import java.util.List;

import com.FmsProject.models.AuthReqReport;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.OffTimeRequestModel;
import com.FmsProject.models.UserModel;

public interface RequestAuthorizerMapper {

	// @Select("SELECT * FROM tbOffTimeRequisition o WHERE "
	// + "o.directorate = (SELECT d.id FROM tbDirectorate d WHERE d.directorate =
	// '${director}')")
	@Select("SELECT * FROM tbOffTimeRequisition where directorate = ${director} AND status = 'Unauthorized'")
	List<OffTimeRequestModel> getOfftimeRequests(int director);

	@Select("select id from tbDirectorate where directorate = #{directorate}")
	int getDirectorId(String directorate);

	@Update("update tbOffTimeRequisition set status = 'Authorized', authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
	void authorizeOfftimeRequests(OffTimeRequestModel offtime);

	@Update("update tbOffTimeRequisition set status = 'Rejected',reasonForRejection = #{reasonForRejection}, authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
	void rejectOfftimeRequests(OffTimeRequestModel offtime);

	@Select("select * from incityrequest where directorate = #{directorate} AND status = 'Unauthorized'")
	List<InCityRequestModel> getInCityRequests(String directorate);

	@Update("update incityrequest set status = 'Authorized', authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
	void authorizeInCityRequests(InCityRequestModel incity);

	@Update("update incityrequest set status = 'Rejected',reasonForRejection = #{reasonForRejection}, authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
	void rejectIncityRequests(InCityRequestModel incity);

	@Select("select * from fieldRequest where directorate = #{directorate} AND status = 'Unauthorized' order by requestDateTime asc")
	List<FieldRequestModel> getFieldRequests(String directorate);

	@Update("update fieldRequest set status = 'Authorized', authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
	void authorizeFieldRequests(FieldRequestModel field);

	@Update("update fieldRequest set status = 'Rejected',reasonOfRejection = #{reasonOfRejection}, authorizedBy = #{authorizedBy}, authorizedDate = #{authorizedDate} where id = #{id}")
	void rejectFieldRequests(FieldRequestModel field);

	@Select("select * FROM [FleetManagementSystem].[dbo].[AuthorizedOfftimeRequests] where directorate = #{directorate}")
	List<OffTimeRequestModel> getAuthorizedOfftimeRequests(AuthReqReport reqReport);

	@Select("select * FROM [FleetManagementSystem].[dbo].[UnauthorizedOfftimeRequests] where directorate = #{directorate}")
	List<OffTimeRequestModel> getNonAuthorizedOfftimeRequests(AuthReqReport reqReport);

	@Select("select *  FROM [FleetManagementSystem].[dbo].[AuthorizedOfftimeRequests] where date>=#{from} AND date<=#{to} and directorate = #{directorate}")
	List<OffTimeRequestModel> getAuthorizedOfftimeRequestsRange(AuthReqReport reqReport);

	@Select("select *  FROM [FleetManagementSystem].[dbo].[UnauthorizedOfftimeRequests] where date>=#{from} AND date<=#{to} and directorate = #{directorate}")
	List<OffTimeRequestModel> getNonAuthorizedOfftimeRequestsRange(AuthReqReport reqReport);

	@Select("select * FROM [FleetManagementSystem].[dbo].[AuthorizedIncityRequests] where directorate=#{directorate}")
	List<InCityRequestModel> getAuthorizedIncityRequests(AuthReqReport reqReport);

	@Select("select * FROM [FleetManagementSystem].[dbo].[UnauthorizedIncityRequests] where directorate=#{directorate}")
	List<InCityRequestModel> getNonAuthorizedIncityRequests(AuthReqReport reqReport);

	@Select("select *  FROM [FleetManagementSystem].[dbo].[AuthorizedIncityRequests] where requestedDate>=#{from} AND requestedDate<=#{to} and directorate=#{directorate}")
	List<InCityRequestModel> getAuthorizedIncityRequestsRange(AuthReqReport reqReport);

	@Select("select *  FROM [FleetManagementSystem].[dbo].[UnauthorizedIncityRequests] where requestedDate>=#{from} AND requestedDate<=#{to} and directorate=#{directorate}")
	List<InCityRequestModel> getNonAuthorizedIncityRequestsRange(AuthReqReport reqReport);

	@Select("select * FROM [FleetManagementSystem].[dbo].[AuthorizedFieldRequests] where directorate=#{directorate}")
	List<FieldRequestModel> getAuthorizedFieldRequests(AuthReqReport reqReport);

	@Select("select * FROM [FleetManagementSystem].[dbo].[UnauthorizedFieldRequests] where directorate=#{directorate}")
	List<FieldRequestModel> getNonAuthorizedFieldRequests(AuthReqReport reqReport);

	@Select("select *  FROM [FleetManagementSystem].[dbo].[AuthorizedFieldRequests] where date>=#{from} AND date<=#{to} and directorate=#{directorate}")
	List<FieldRequestModel> getAuthorizedFieldRequestsRange(AuthReqReport reqReport);

	@Select("select *  FROM [FleetManagementSystem].[dbo].[UnauthorizedFieldRequests] where requestDate>=#{from} AND requestDate<=#{to} and directorate=#{directorate}")
	List<FieldRequestModel> getNonAuthorizedFieldRequestsRange(AuthReqReport reqReport);

	@Select("select u.email,u.firstName, u.middleName, u.lastName, u.city, u.phoneNumber, u.address,u.enabled,u.isDelegated, u.username,u.directorate,"
			+ " u.isLoggedin,r.roleId from users u, user_role r where u.username = r.username and u.directorate = #{directorate} and r.roleId = 'Requester'")
	List<UserModel> getAllUsers(String directorate);

	@Update("update users set isDelegated = '1' where username = #{username} ")
	void delegateAuthorizer(UserModel user);

	@Update("update users set isDelegated = '0' where username = #{username} ")
	void deactivateDelegation(UserModel user);

	@Select("insert into delegationHistory(username, createdBy, createdDate, delegatedRole, status) values(#{username},"
			+ " #{createdBy}, #{createdDate}, #{roleId}, #{status})")
	void recordDelegationHistory(UserModel user);

	@Select("select u.enabled,u.isDelegated, u.username,u.directorate,"
			+ " u.isLoggedin,r.roleId from users u, user_role r where u.username = r.username and r.roleId = 'GS Mechanic'")
	List<UserModel> getMechanicUsers();

	@Select("select u.enabled,u.isDelegated, u.username,u.directorate,"
			+ " u.isLoggedin,r.roleId from users u, user_role r where u.username = r.username and r.roleId = 'Dispatcher'")
	List<UserModel> getDispatcherUsers();
}
