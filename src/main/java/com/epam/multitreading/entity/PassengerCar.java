package com.epam.multitreading.entity;

public class PassengerCar extends Car{

    public PassengerCar(int size, int weight) {
        super(size, weight);
    }

    @Override
    public void run() {
        Ferryboat ferryboat = Ferryboat.getInstance();
        ferryboat.transfer(this);
    }
}
