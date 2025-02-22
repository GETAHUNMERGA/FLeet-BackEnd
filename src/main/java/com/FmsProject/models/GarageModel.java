package com.FmsProject.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarageModel {
	private Long id;
	private String name;
	private String location;
	private String phone;
}
