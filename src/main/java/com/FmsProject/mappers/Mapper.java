package com.FmsProject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.FmsProject.models.AuthenticationRequest;
import com.FmsProject.models.ChangePasswordModel;
import com.FmsProject.models.DirectorateModel;
import com.FmsProject.models.LoginHistory;
import com.FmsProject.models.ReqAppModel;
import com.FmsProject.models.RoleModel;
import com.FmsProject.models.UserModel;

public interface Mapper {
        @Select("select username, password from users where username = #{username} AND enabled = '1'")
        AuthenticationRequest getUserDetails(String userName);

        @Select("SELECT roleId FROM user_role where username = #{username}")
        String getRole(String username);

        @Select("SELECT directorate FROM users where username = #{username}")
        String getDirectorate(String username);

        @Select("select * from roles")
        List<RoleModel> getRoles();

        @Insert("insert into users(email,firstName, middleName, lastName, city, phoneNumber, address,enabled, username,password,directorate, createdBy, createdDate, isLoggedin, isDelegated) values("
                        + "#{email}, #{firstName}, #{middleName}, #{lastName}, #{city}, #{phoneNumber}, #{address},'1', #{username}, #{password}, #{directorate}, #{createdBy}, #{createdDate}, '0', '0')")
        void addUser(UserModel user);

        @Insert("insert into user_role(username, roleId) values(#{username}, #{roleId})")
        void addUserRole(UserModel user);

        @Select("select TOP 1 * from login_history where username = #{username}")
        LoginHistory isLogged(String username);

        @Select("select * from users where username = #{username}")
        UserModel authenticateUser(ChangePasswordModel change);

        @Update("update users set password = #{newPassword} where username = #{username}")
        void changePassword(ChangePasswordModel change);

        @Insert("insert into login_history(username, loginDate) values(#{username}, #{loginDate})")
        void saveHistory(ChangePasswordModel change);

        @Select("select * from tbDirectorate")
        List<DirectorateModel> getDirectorates();

        @Select("select u.email,u.firstName, u.middleName, u.lastName, u.city, u.phoneNumber, u.address,u.enabled, u.username,u.password,u.directorate,"
                        + " u.createdBy,u.isLoggedin, u.createdDate, r.roleId from users u, user_role r where u.username = r.username")
        List<UserModel> getAllUsers();

        @Update("update users set enabled = '0', createdBy = #{createdBy}, createdDate = #{createdDate} where username = #{username}")
        void deactivateUser(UserModel user);

        @Update("update users set email = #{email}, firstName = #{firstName}, middleName = #{middleName}, lastName = #{lastName}, city = #{city},"
                        + "phoneNumber = #{phoneNumber}, address = #{address}, enabled = #{enabled}, directorate = #{directorate}, createdBy = #{createdBy}, createdDate = #{createdDate} where username = #{username} ")
        void updateUser(UserModel user);

        @Update("update user_role set roleId = #{roleId} where username = #{username}")
        void updateRole(UserModel user);

        @Select("select * from users where username = #{username}")
        UserModel isUserExist(String username);

        @Select("select count(id) from users")
        int getNumOfUsers();

        @Select("select count(id) from users where enabled = '1'")
        int getNumOfActiveUsers();

        @Select("select count(id) from users where enabled = '0'")
        int getNumOfLockedUsers();

        @Update("update users set email = #{email}, firstName = #{firstName}, middleName = #{middleName}, lastName = #{lastName}, city = #{city},"
                        + "phoneNumber = #{phoneNumber}, address = #{address}, enabled = #{enabled}, directorate = #{directorate}, createdBy = #{createdBy},"
                        + " createdDate = #{createdDate}, password=#{password} where username = #{username} and isLoggedin != '1' ")
        void resetPassword(UserModel user);

        @Select("delete from login_history where username = #{username}")
        void removeHistory(UserModel user);

        @Update("update users set isLoggedin = '1' where username = #{username}")
        void userLogged(String username);

        @Update("update users set isLoggedin = '0' where username = #{username}")
        void UserLoggedOut(String username);

        @Select("SELECT isDelegated FROM users where username = #{username}")
        String getDelegation(String username);

        @Update("update users set isLoggedin = '0' where username = #{username}")
        void clearUser(String username);

        @Select("select u.username, r.roleId from users u, user_role r where u.username = r.username and r.roleId='Requester'")
        List<UserModel> getRequesters();

        @Select("select u.username, r.roleId from users u, user_role r where u.username = r.username and r.roleId='Request Authorizer'")
        List<UserModel> getAuthorizers();

        @Insert("insert into tblAdvRequesterApprover(requester, approver) values(#{requester}, #{authorizer})")
        void addMapping(ReqAppModel reqapp);

        @Select("select count(requester) from tblAdvRequesterApprover where requester=#{requester}")
        int isRequesterExist(ReqAppModel reqapp);

        @Update("update tblAdvRequesterApprover set approver = #{authorizer} where requester=#{requester}")
        void updateMapping(ReqAppModel reqapp);

}
