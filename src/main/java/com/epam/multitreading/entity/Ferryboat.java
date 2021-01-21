package com.epam.multitreading.entity;

import java.util.ArrayList;
import java.util.List;

public class Ferryboat {
    private static final Ferryboat instance = new Ferryboat();
    private int carryingCapacity;
    private int square;

    private final List<Car> cars;

    private Ferryboat() {
        cars = new ArrayList<>();
    }

    public static Ferryboat getInstance(){
        return instance;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
