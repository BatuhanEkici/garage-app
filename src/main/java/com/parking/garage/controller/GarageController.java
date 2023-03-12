package com.parking.garage.controller;

import com.parking.garage.model.Car;
import com.parking.garage.service.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/garage")
@AllArgsConstructor
public class GarageController {

    private final GarageService garageService;

    @GetMapping
    public String createGarage() {
        return garageService.createGarage();
    }

    @PostMapping("/park")
    public String park(@RequestBody Car car) {
        return garageService.park(car);
    }

    @DeleteMapping("{id}")
    public void leaveCar(@PathVariable int id) {
        garageService.leaveCar(id);
    }

    @GetMapping("/status")
    public String status() {
        return garageService.getStatus();
    }
}
