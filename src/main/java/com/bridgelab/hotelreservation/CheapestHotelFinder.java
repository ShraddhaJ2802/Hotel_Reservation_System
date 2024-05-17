package com.bridgelab.hotelreservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheapestHotelFinder {
    private List<Hotel> hotels;

    public CheapestHotelFinder(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public  String findCheapestHotel(String sd ,String ed)
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
        return cheapestHotel ;
                //" , Total Rates : " + minRate;
    }

    public String findCheapestHotelByWeekdayAndWeekendDay(String sd ,String ed) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("ddMMyyyy");
        Date start = dt.parse(sd);
        Date end = dt.parse(ed);

        Calendar cal = Calendar.getInstance();

        double minRate = Integer.MAX_VALUE;
        String cheapestHotel = "";

        for (Hotel hotel : hotels) {
            double totalRate = 0;

            for (Date date = start; !date.after(end); ) {
                cal.setTime(date);
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    totalRate += hotel.getWeekendRate();
                } else {
                    totalRate += hotel.getWeekdayRate();
                }
                cal.add(Calendar.DATE, 1);
                date = cal.getTime();
            }

            if (totalRate < minRate) {
                minRate = totalRate;
                cheapestHotel = hotel.getName();
            }
        }

        return cheapestHotel ;
                //+ ", Total Rates: $" + minRate;
    }


    public static void main(String[] args) throws ParseException {
        // Sample hotel data with rates
       /* List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood" ,220));
        hotelList.add(new Hotel("Bridgewood" ,180));
        hotelList.add(new Hotel("Ridgewood" ,200));

        //Given date range
        String startDate = "10Sep2020";
        String endDate = "11Sep2020";

        //ctrating object of CheapestHotelFinder
       // CheapestHotelFinder finder =new CheapestHotelFinder(hotelList);

        //Finding the cheapest hotel

       // String cheapHotel =finder.findCheapestHotel(startDate , endDate);
      //  System.out.println("Cheapest hotel for the given date range is:" + cheapHotel);*/

        // Sample hotel data with rates
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood", 110, 90));
        hotelList.add(new Hotel("Bridgewood", 150, 50));
        hotelList.add(new Hotel("Ridgewood", 220, 150));

        CheapestHotelFinder finder = new CheapestHotelFinder(hotelList);

        // Finding the cheapest hotel
        String cheapestHotel = finder.findCheapestHotelByWeekdayAndWeekendDay("11Sep2020", "12Sep2020");

        System.out.println("Cheapest hotel for the given date range is: " + cheapestHotel);
    }

    }

