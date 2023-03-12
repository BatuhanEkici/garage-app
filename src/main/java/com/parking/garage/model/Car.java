package com.parking.garage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Car {
    private String plateNo;
    private String color;
    private String carType;

    @Override
    public String toString() {
        return plateNo + " " + color;
    }
}
