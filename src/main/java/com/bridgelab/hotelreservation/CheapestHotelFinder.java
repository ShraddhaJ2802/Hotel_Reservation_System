package com.bridgelab.hotelreservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class CheapestHotelFinder {
    private List<Hotel> hotels;
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}[A-Za-z]{3}\\d{4}");

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

    public String findBestRatedHotel(String startDate, String endDate, boolean isRewardCustomer) throws IllegalArgumentException {
        if (!DATE_PATTERN.matcher(startDate).matches() || !DATE_PATTERN.matcher(endDate).matches()) {
            throw new IllegalArgumentException("Invalid date format. Please use ddMMMyyyy format.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/ dd/ yy");

        LocalDate start;
        LocalDate end;
        try {
            start = LocalDate.parse(startDate, formatter);
            end = LocalDate.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use ddMMMyyyy format.");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }

        return hotels.stream()
                .map(hotel -> new HotelRate(hotel, calculateTotalRate(hotel, start, end, isRewardCustomer)))
                .sorted(Comparator.comparing(HotelRate::getTotalRate)
                        .thenComparing((hr1, hr2) -> Integer.compare(hr2.getHotel().getRating(), hr1.getHotel().getRating())))
                .findFirst()
                .map(hr -> hr.getHotel().getName() + ", Rating: " + hr.getHotel().getRating() + " and Total Rates: $" + hr.getTotalRate())
                .orElseThrow(() -> new IllegalArgumentException("No hotels available."));
    }

    private int calculateTotalRate(Hotel hotel, LocalDate start, LocalDate end, boolean isRewardCustomer) {
        int totalRate = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (isRewardCustomer) {
                totalRate += (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
                        ? hotel.getRewardWeekendRate() : hotel.getRewardWeekdayRate();
            } else {
                totalRate += (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
                        ? hotel.getWeekendRate() : hotel.getWeekdayRate();
            }
        }
        return totalRate;
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

                String bestRatedHotel = finder.findBestRatedHotel(startDate, endDate, true);
                System.out.println("Best rated hotel for the given date range is: " + bestRatedHotel);
           // String bestRatedHotel = finder.findBestRatedHotel(startDate, endDate,"Reward");
           // System.out.println("Special rates for reward customers  is: " + bestRatedHotel);
        }
        catch (Exception e)
        {
            System.out.println( "Error:" +e.getMessage());

        }




    }

    }

