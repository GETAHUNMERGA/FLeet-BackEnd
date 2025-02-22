package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChangePasswordModel {
 private String username;
 private String oldPassword;
 private String newPassword;
 private String confirmPassword;
 private Date loginDate;
 
 
}
