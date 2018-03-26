package com.audition;

public class Coin {
    private double weight;
    private double diameter;

    public Coin(double weight, double diameter){
        this.weight = weight;
        this.diameter = diameter;

    }

    public double getDiameter() {
        return diameter;
    }

    public double getWeight() {
        return weight;
    }
}
