package com.FmsProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelDetailDto {
    int id;
    String typeOfFuel;
    double price;
}
