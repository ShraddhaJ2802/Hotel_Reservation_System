package com.bridgelab.hotelreservation;

import java.util.ArrayList;
import java.util.List;

public class CheapestHotelFinder {
    private List<Hotel> hotels;

    public CheapestHotelFinder(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String findCheapestHotel(String sd ,String ed)
    {
        double minRate = Integer.MAX_VALUE;
        String cheapestHotel = "";
        for(Hotel hotel : hotels)
        {
            double totalRate = hotel.getRegularRate();
            if(totalRate < minRate)
            {
                minRate = totalRate;
                cheapestHotel = hotel.getName();
            }
        }
        return cheapestHotel +" , Total Rates : $" + minRate;
    }

    public static void main(String[] args) {
        // Sample hotel data with rates
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood" ,220));
        hotelList.add(new Hotel("Bridgewood" ,180));
        hotelList.add(new Hotel("Ridgewood" ,200));

        //Given date range
        String startDate = "10Sep2020";
        String endDate = "11Sep2020";

        //ctrating object of CheapestHotelFinder
        CheapestHotelFinder finder =new CheapestHotelFinder(hotelList);

        //Finding the cheapest hotel

        String cheapHotel =finder.findCheapestHotel(startDate , endDate);
        System.out.println("Cheapest hotel for the given date range is:" + cheapHotel);

    }
}
