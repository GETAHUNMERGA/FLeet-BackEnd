package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportModel {
    private String reportType;
    private Date from;
    private Date to;
    private String mechanicReport;
    private String directorate;

}
