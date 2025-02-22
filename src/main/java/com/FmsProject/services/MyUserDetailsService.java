package com.FmsProject.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.Mapper;
import com.FmsProject.models.AuthenticationRequest;
import com.FmsProject.models.LoginHistory;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private Mapper map;
	// @Autowired
	AuthenticationRequest userDetails = new AuthenticationRequest();

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		userDetails = map.getUserDetails(userName);
		return new User(userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>());
	}

	public String getRole(String username) {
		// TODO Auto-generated method stub
		return map.getRole(username);
	}

	public String getDirectorate(String username) {
		return map.getDirectorate(username);
	}

	public LoginHistory isLogged(String username) {
		// TODO Auto-generated method stub
		return map.isLogged(username);
	}

	public void userLogged(String username) {
		map.userLogged(username);
	}

	public String getDelegation(String username) {
		return map.getDelegation(username);
	}
}
