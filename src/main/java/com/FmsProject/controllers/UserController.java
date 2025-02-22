package com.FmsProject.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.ChangePasswordModel;
import com.FmsProject.models.DirectorateModel;
import com.FmsProject.models.ReqAppModel;
import com.FmsProject.models.RoleModel;
import com.FmsProject.models.UserModel;
import com.FmsProject.services.MyUserDetailsService;
import com.FmsProject.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/User/")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService userDetailsService;

	@GetMapping("/getRoles")
	public ResponseEntity<List<RoleModel>> getRoles() {
		List<RoleModel> roles = userService.getRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@GetMapping("/getDirectorates")
	public ResponseEntity<List<DirectorateModel>> getDirectorates() {
		List<DirectorateModel> directorates = userService.getDirectorates();
		return new ResponseEntity<>(directorates, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(user.getPassword());
		user.setPassword(password);
		Date createdDate = new Date();
		user.setCreatedDate(createdDate);

		if (userService.isUserExist(user.getUsername()) == false) {
			userService.addUser(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@PutMapping("/changePassword")
	public void changePassword(@RequestBody ChangePasswordModel change) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(change.getUsername(), change.getOldPassword()));

		} catch (BadCredentialsException ex) {
			throw new Exception("Incorrect Username or Password", ex);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(change.getUsername());
		if (userDetails.getUsername() != null) {
			String newPassword = encoder.encode(change.getNewPassword());
			change.setNewPassword(newPassword);

			userService.changePassword(change);
		} else {
			System.out.println("Old password is not authenticated!");
		}

	}

	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/deactivate")
	public ResponseEntity<UserModel> deactivateUser(@RequestBody UserModel user) {
		Date createdDate = new Date();
		user.setCreatedDate(createdDate);
		UserModel resp = userService.deactivateUser(user);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
		Date createdDate = new Date();
		user.setCreatedDate(createdDate);
		UserModel resp = userService.updateUser(user);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping("/noOfUsers")
	public int getNumOfUsers() {
		return userService.getNumOfUsers();
	}

	@GetMapping("/noActiveOfUsers")
	public int getNumOfActiveUsers() {
		return userService.getNumOfActiveUsers();
	}

	@GetMapping("/noLockedOfUsers")
	public int getNumOfLockedUsers() {
		return userService.getNumOfLockedUsers();
	}

	@GetMapping("/loggedout/{username}")
	public ResponseEntity<?> isUserLoggedOut(@PathVariable("username") String username) {
		userService.isUserLoggedOut(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/clearUser/{username}")
	public ResponseEntity<?> clearUser(@PathVariable("username") String username) {
		userService.clearUser(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/requesters")
	public ResponseEntity<List<UserModel>> getRequesters() {
		List<UserModel> users = userService.getRequesters();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/authorizers")
	public ResponseEntity<List<UserModel>> getAuthorizers() {
		List<UserModel> users = userService.getAuthorizers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/mapReqApp")
	public ResponseEntity<ReqAppModel> addMapping(@RequestBody ReqAppModel reqapp) {
		ReqAppModel response = userService.addMapping(reqapp);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @GetMapping("/userExist/{username}")
	// public boolean isUserExist(@PathVariable("username") String username){
	// UserModel resp = userService.isUserExist(username);
	// if(resp!=null) {
	// return true;
	// }else {
	// return false;
	// }
	// }
}
