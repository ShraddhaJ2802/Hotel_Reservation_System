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
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/ dd/ yy");
        Date start = dateFormat.parse(sd);
        Date end = dateFormat.parse(ed);

        Calendar cal = Calendar.getInstance();

        int minRate = Integer.MAX_VALUE;
        String cheapestHotel = "";
        int highestRating = 0;

        for (Hotel hotel : hotels) {
            int totalRate = 0;

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

            if (totalRate < minRate || (totalRate == minRate && hotel.getRating() > highestRating)) {
                minRate = totalRate;
                cheapestHotel = hotel.getName();
                highestRating = hotel.getRating();
            }
        }

        return cheapestHotel + ", Rating: " + highestRating + " and Total Rates: $" + minRate;
    }

    public String findBestRatedHotel(String sd ,String ed) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/ dd/ yy");
        Date start = dateFormat.parse(sd);
        Date end = dateFormat.parse(ed);

        Calendar cal = Calendar.getInstance();

        int highestRating = 0;
        String bestRatedHotel = "";
        int totalRate = 0;


        for (Hotel hotel : hotels) {
            int currentHotelTotalRate = 0;

            for (Date date = start; !date.after(end); ) {
                cal.setTime(date);
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    currentHotelTotalRate += hotel.getWeekendRate();
                } else {
                    currentHotelTotalRate += hotel.getWeekdayRate();
                }
                cal.add(Calendar.DATE, 1);
                date = cal.getTime();
            }

            if (hotel.getRating() > highestRating) {
                highestRating = hotel.getRating();
                bestRatedHotel = hotel.getName();
                totalRate = currentHotelTotalRate;
            }
        }

        return bestRatedHotel + ", Rating: "+highestRating +" and Total Rates: $" + totalRate;
    }

    public String findBestRatedHotel(String startDate, String endDate, String customerType) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/ dd/ yy");
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        try {
            start = dateFormat.parse(startDate);
            end = dateFormat.parse(endDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use ddMMMyyyy format.");
        }

        if (start.after(end)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }



        Calendar cal = Calendar.getInstance();

        int highestRating = 0;
        String bestRatedHotel = "";
        int totalRate = 0;
        boolean isRewardCustomer =  customerType.equalsIgnoreCase("Reward");

        for (Hotel hotel : hotels) {
            int currentHotelTotalRate = 0;
            for (Date date = start; !date.after(end); ) {
                cal.setTime(date);
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                if (isRewardCustomer) {
                    if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                        currentHotelTotalRate += hotel.getRewardWeekendRate();
                    } else {
                        currentHotelTotalRate += hotel.getRewardWeekdayRate();
                    }
                } else {
                    if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                        currentHotelTotalRate += hotel.getWeekendRate();
                    } else {
                        currentHotelTotalRate += hotel.getWeekdayRate();
                    }
                }
                cal.add(Calendar.DATE, 1);
                date = cal.getTime();
            }

            if (hotel.getRating() > highestRating) {
                highestRating = hotel.getRating();
                bestRatedHotel = hotel.getName();
                totalRate = currentHotelTotalRate;
            }
        }

        return bestRatedHotel + " & Total Rates $" + totalRate ;
    }


    public static void main(String[] args) throws ParseException {
        // Sample hotel data with rates
       /* List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood" ,220));
        hotelList.add(new Hotel("Bridgewood" ,180));
        hotelList.add(new Hotel("Ridgewood" ,200));

        //Given date range


        //ctrating object of CheapestHotelFinder
       // CheapestHotelFinder finder =new CheapestHotelFinder(hotelList);

        //Finding the cheapest hotel

       // String cheapHotel =finder.findCheapestHotel(startDate , endDate);
      //  System.out.println("Cheapest hotel for the given date range is:" + cheapHotel);*/

        // Sample hotel data with rates
       /* List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood", 110, 90 ,3));
        hotelList.add(new Hotel("Bridgewood", 150, 50, 4));
        hotelList.add(new Hotel("Ridgewood", 220, 150,5));*/
        // Finding the cheapest hotel
        // String cheapestHotel = finder.findCheapestHotelByWeekdayAndWeekendDay(startDate, endDate);
        //  System.out.println("Cheapest hotel for the given date range is: " + cheapestHotel);

        //String bestReatedHotel = finder.findBestRatedHotel(startDate, endDate);
        //System.out.println("Best Rated hotel for the given date range is: " + bestReatedHotel);
        //String bestRatedHotel = finder.findBestRatedHotel(startDate, endDate,true);
        // System.out.println("Special rates for reward customers  is: " + bestRatedHotel);
        try {
            List<Hotel> hotelList = List.of(
                    new Hotel("Lakewood", 110, 90, 3, 80, 80),
                    new Hotel("Bridgewood", 150, 50, 4, 110, 50),
                    new Hotel("Ridgewood", 220, 150, 5, 100, 40)
            );

            CheapestHotelFinder finder = new CheapestHotelFinder(hotelList);

            String startDate = "10/ 11/ 20";
            String endDate = "10/ 12/ 20";
            String bestRatedHotel = finder.findBestRatedHotel(startDate, endDate,"Reward");
            System.out.println("Special rates for reward customers  is: " + bestRatedHotel);
        }
        catch (Exception e)
        {
            System.out.println( "Error:" +e.getMessage());

        }




    }

    }

