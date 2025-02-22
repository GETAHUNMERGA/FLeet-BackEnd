package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceCostReportModel {
   private String fromMonth;
   private String toMonth;
   private String fromYear;
   private String toYear;
   private String plateNo;
}
