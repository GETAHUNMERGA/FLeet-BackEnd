package com.FmsProject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.Mapper;
import com.FmsProject.models.ChangePasswordModel;
import com.FmsProject.models.DirectorateModel;
import com.FmsProject.models.ReqAppModel;
import com.FmsProject.models.RoleModel;
import com.FmsProject.models.UserModel;

@Service
public class UserService {
	@Autowired
	Mapper map;

	public List<RoleModel> getRoles() {
		// TODO Auto-generated method stub
		return map.getRoles();
	}

	public void addUser(UserModel user) {
		map.addUser(user);
		map.addUserRole(user);
	}

	public boolean authenticateUser(ChangePasswordModel change) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String oldPassword = encoder.encode(change.getOldPassword());
		change.setOldPassword(oldPassword);
		System.out.println(map.authenticateUser(change));
		UserModel user = new UserModel();
		user = map.authenticateUser(change);
		System.out.println(user);
		if (user.getPassword() == oldPassword) {
			return true;
		} else {
			return false;
		}

	}

	public void changePassword(ChangePasswordModel change) {
		// TODO Auto-generated method stub
		map.changePassword(change);
		Date date = new Date();
		change.setLoginDate(date);
		map.saveHistory(change);
	}

	public List<DirectorateModel> getDirectorates() {
		// TODO Auto-generated method stub
		return map.getDirectorates();
	}

	public List<UserModel> getAllUsers() {
		// TODO Auto-generated method stub
		return map.getAllUsers();
	}

	public UserModel deactivateUser(UserModel user) {
		// TODO Auto-generated method stub
		map.deactivateUser(user);
		return user;
	}

	public UserModel updateUser(UserModel user) {
		// TODO Auto-generated method stub
		Date createdDate = new Date();
		user.setCreatedDate(createdDate);
		if (user.getPassword() != null) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode(user.getPassword());
			user.setPassword(password);
			map.resetPassword(user);
			map.removeHistory(user);

		} else {
			map.updateUser(user);
		}
		map.updateRole(user);
		return user;
	}

	public boolean isUserExist(String username) {
		// TODO Auto-generated method stub
		if (map.isUserExist(username) != null) {
			return true;
		} else {
			return false;
		}
	}

	public int getNumOfUsers() {
		return map.getNumOfUsers();
	}

	public int getNumOfActiveUsers() {
		return map.getNumOfActiveUsers();
	}

	public int getNumOfLockedUsers() {
		return map.getNumOfLockedUsers();
	}

	public void isUserLoggedOut(String username) {
		map.UserLoggedOut(username);
	}

	public void clearUser(String username) {
		map.clearUser(username);
	}

	public List<UserModel> getRequesters() {
		return map.getRequesters();
	}

	public List<UserModel> getAuthorizers() {
		return map.getAuthorizers();
	}

	public ReqAppModel addMapping(ReqAppModel reqapp) {
		Integer isExist = map.isRequesterExist(reqapp);
		if (isExist > 0) {
			map.updateMapping(reqapp);
		} else {
			map.addMapping(reqapp);
		}
		return reqapp;
	}

}
