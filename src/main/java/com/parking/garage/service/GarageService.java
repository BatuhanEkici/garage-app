package com.parking.garage.service;

import com.parking.garage.excepiton.GarageException;
import com.parking.garage.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GarageService {

    private static Logger logger = LoggerFactory.getLogger(GarageService.class);
    private final int GARAGE_CAPACITY = 10;

    // Available slots list
    ArrayList<Integer> availableSlotList;
    Map<Car, List<Integer>> carMap = new HashMap<>();
    Map<Integer,Car> mapCar = new HashMap<>();

    Map<Integer, Map<Car,List<Integer>>> slotCar = new TreeMap<>();


    public String createGarage() {
        this.availableSlotList = new ArrayList<Integer>();
        for (int i=1; i<=GARAGE_CAPACITY; i++) {
            availableSlotList.add(i);
        }
        logger.info("Created Garage " + GARAGE_CAPACITY + " slots");
        return "Created Garage " + GARAGE_CAPACITY + " slots " +availableSlotList;
    }

    public String park(Car car) {
        int allocateSize = getAllocateSlotSize(car.getCarType());

        if (allocateSize > this.availableSlotList.size() ) {
           logger.error("Sorry, garage is full");
           throw new GarageException("Garage is full.");
        }
        else {
            Collections.sort(availableSlotList);
            int initial = availableSlotList.get(0);
            List<Integer> slots = removeSlot(initial,allocateSize);
            carMap.put(car,slots);
            mapCar.put(carMap.size(),car);
            slotCar.put(carMap.size(), carMap);

            logger.info("carMap.size(): "+carMap.size());
            logger.info("Allocated slot number: " + allocateSize);
            logger.info("Allocated slots: " + slots);
            return "Allocated slot number: " + allocateSize;
        }
    }

    private int getAllocateSlotSize(String carType) {
        if(carType.equalsIgnoreCase("Car"))
            return 1;
        else if(carType.equalsIgnoreCase("Jeep"))
            return 2;
        else if(carType.equalsIgnoreCase("Truck"))
            return 4;
        else return 0;
    }
    private List<Integer> removeSlot(int initial, int finish) {
        ArrayList slot = new ArrayList<Integer>();
        initial = initial-1;
        for(int i = initial; i < initial+finish+1; i++) {
            if(i < initial + finish)
                slot.add(this.availableSlotList.get(0));
            this.availableSlotList.remove(0);
        }
        return slot;
    }

    public void leaveCar(int slot) {
        carMap.get(mapCar.get(slot)).forEach(i-> {availableSlotList.add(i);});
        logger.info(availableSlotList.toString());
        this.carMap.remove((mapCar.get(slot)));
    }

    public String getStatus() {
        logger.info(carMap.toString() +"has been freed up " );
        return carMap.toString();
    }

}
