package com.epam.multitreading.factory;

import com.epam.multitreading.entity.Autotruck;
import com.epam.multitreading.entity.Car;
import com.epam.multitreading.entity.PassengerCar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarFactory {
    private static final Logger logger = LogManager.getLogger(CarFactory.class);

    private static CarFactory instance = new CarFactory();


    private CarFactory() {
    }

    public static CarFactory getInstance() {
        return instance;
    }

    public Car createVehicle(int size, int weight) {
        Car vehicle;
        if(size > 4 || weight > 4){
            vehicle = new Autotruck(size, weight);
            logger.info("Create new Car: {}", vehicle);
        }
        else {
            vehicle = new PassengerCar(size, weight);
            logger.info("Create new Truck: {}", vehicle);
        }
        return vehicle;
    }
}
