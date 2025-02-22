package com.FmsProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.FmsProject.models.AuthenticationRequest;
import com.FmsProject.models.AuthenticationResponse;
import com.FmsProject.models.LoginHistory;
import com.FmsProject.services.MyUserDetailsService;
import com.FmsProject.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/User/")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private MyUserDetailsService userDetailsService;
  @Autowired
  private JwtUtil jwtTokenUtil;

  @RequestMapping(value = "authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
      throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
              authenticationRequest.getPassword()));

    } catch (BadCredentialsException ex) {

      throw new Exception("Incorrect Username or Password", ex);
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    userDetailsService.userLogged(authenticationRequest.getUsername());
    final String roleId = userDetailsService.getRole(authenticationRequest.getUsername());
    final String directorate = userDetailsService.getDirectorate(authenticationRequest.getUsername());
    final String delegated = userDetailsService.getDelegation(authenticationRequest.getUsername());
    return ResponseEntity.ok(new AuthenticationResponse(jwt, roleId, directorate, delegated));
  }

  @GetMapping("isLogged/{username}")
  public ResponseEntity<LoginHistory> isLogged(@PathVariable("username") String username) {
    LoginHistory history = userDetailsService.isLogged(username);
    return new ResponseEntity<>(history, HttpStatus.OK);
  }
}
