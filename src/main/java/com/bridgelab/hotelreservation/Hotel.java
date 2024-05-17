package com.bridgelab.hotelreservation;

public class Hotel {
    private String name;
    private double regularRate;

    private double weekdayRate;
    private double weekendRate;


    public Hotel(String name, double regularRate) {
        this.name = name;
        this.regularRate = regularRate;
    }

    public Hotel(String name) {
        this.name = name;
    }

    public Hotel(String name , double weekdayRate , double weekendRate) {
        this.name = name;
       // this.regularRate = regularRate;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
    }

    public double getRegularRate() {
        return regularRate;
    }

    public void setRegularRate(double regularRate) {
        this.regularRate = regularRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getWeekdayRate() {
        return weekdayRate;
    }

    public void setWeekdayRate(double weekdayRate) {
        this.weekdayRate = weekdayRate;
    }

    public double getWeekendRate() {
        return weekendRate;
    }

    public void setWeekendRate(double weekendRate) {
        this.weekendRate = weekendRate;
    }

    public static void main(String[] args) {

        Hotel lakewood = new Hotel("lakewood" , 110 ,90);
        Hotel bridgewood = new Hotel("bridgewood" , 150 ,50);
        Hotel ridgewood = new Hotel("ridgewood" , 220 ,150);

        System.out.println("Hotel Name\tWeekday Rate \t Weekend Rate");
        System.out.println(lakewood.name + "\t " +lakewood.weekdayRate + "\t\t" +lakewood.weekendRate);
        System.out.println(bridgewood.name + "\t " +bridgewood.weekdayRate + "\t\t" +bridgewood.weekendRate);
        System.out.println(ridgewood.name + "\t " +ridgewood.weekdayRate + "\t\t" +ridgewood.weekendRate);
    }
}

