package com.FmsProject.models;

import lombok.Data;

@Data
public class AuthenticationResponse {
  private final String jwt;
  private final String roleId;
  private final String directorate;
  private final String delegated;

  public AuthenticationResponse(String jwt, String roleId, String directorate, String delegated) {
    this.jwt = jwt;
    this.roleId = roleId;
    this.directorate = directorate;
    this.delegated = delegated;
  }

  /*
   * public String getJwt() { return jwt; }
   */

}
