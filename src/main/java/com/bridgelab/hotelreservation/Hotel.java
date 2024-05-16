package com.bridgelab.hotelreservation;

public class Hotel {
    private String name;
    private  double regularRate;

    public Hotel(String name ,double regularRate)
    {
        this.name = name;
        this.regularRate = regularRate;
    }

    public Hotel(String name )
    {
        this.name = name;
    }

    public static void main(String[] args) {

        Hotel h1 = new Hotel("Lakewood" ,1500);
        System.out.println("Hotel name is :" +h1.name+" and rate is "+ h1.regularRate);

    }
}
