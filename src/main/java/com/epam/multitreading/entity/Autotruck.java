package com.epam.multitreading.entity;

public class Autotruck extends Car{
    public Autotruck(int size, int weight) {
        super(size, weight);
    }

    @Override
    public void run() {
        Ferryboat ferryboat = Ferryboat.getInstance();
        ferryboat.transfer(this);
    }
}
