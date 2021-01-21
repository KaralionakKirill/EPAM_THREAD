package com.epam.multitreading.entity;

public class Autotruck extends Thread implements Car{
    private int size;
    private int weight;

    public Autotruck(int size, int weight) {
        this.size = size;
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
