package com.FmsProject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatcherReportModel {
	private String fieldfromDate;
	private String fieldtoDate;
	
	private String incityFromDate;
	private String incityToDate;
	
	private String offtimeFromDate;
	private String offtimeToDate;

}
