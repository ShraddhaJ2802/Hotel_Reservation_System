package com.bridgelab.hotelreservation;

public class HotelRate {
    private Hotel hotel;
    private int totalRate;

    public HotelRate(Hotel hotel, int totalRate) {
        this.hotel = hotel;
        this.totalRate = totalRate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getTotalRate() {
        return totalRate;
    }
}
