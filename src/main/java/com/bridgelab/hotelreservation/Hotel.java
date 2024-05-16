package com.bridgelab.hotelreservation;

public class Hotel {
    private String name;
    private double regularRate;

    public Hotel(String name, double regularRate) {
        this.name = name;
        this.regularRate = regularRate;
    }

    public Hotel(String name) {
        this.name = name;
    }

    public double getRegularRate() {
        return regularRate;
    }

    public void setRegularRate(double regularRate) {
        this.regularRate = regularRate;
    }

    public String getName() {
        return name;
    }

}

