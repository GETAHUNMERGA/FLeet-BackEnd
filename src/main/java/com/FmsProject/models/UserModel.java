package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
  private Long id;
  private String email;
  private String firstName;
  private String middleName;
  private String lastName;
  private String city;
  private String phoneNumber;
  private String address;
  private String enabled;
  private String username;
  private String password;
  private String roleId;
  private String directorate;
  private String createdBy;
  private Date createdDate;
  private String isLoggedin;
  private String isDelegated;
  private String status;
}
