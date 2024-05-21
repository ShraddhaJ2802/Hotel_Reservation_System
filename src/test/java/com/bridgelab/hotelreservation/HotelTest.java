package com.bridgelab.hotelreservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HotelTest {
    Hotel h;
    CheapestHotelFinder ch;

    @Test
    @DisplayName("To check hotel is not empty")
    public void checkNameNotEmpty()
    {
        h = new Hotel("Lakewood" ,1500);
        assertNotNull(h);

    }
    @Test
   public void  checkCheapestHotelRate()
    {
       // Sample hotel data with rates
        List<Hotel> hotelList = new ArrayList<>();
       hotelList.add(new Hotel("Lakewood" ,220));
        hotelList.add(new Hotel("Bridgewood" ,180));
        hotelList.add(new Hotel("Ridgewood" ,200));

       // Mocking input date range
       String startDate = "10Sep2020";
       String endDate ="11Sep2020";
        //Call the method to find the cheapest hotel
        ch =new CheapestHotelFinder(hotelList);
        String cheapestHotel = ch.findCheapestHotel(startDate , endDate);

       //Expected output
       String expectedHotel = "Bridgewood";

        //Assertion
        Assertions.assertEquals(expectedHotel , cheapestHotel );

    }

    @Test
     public void  testLakewoodWeekdayRate() {
        Hotel lakewood = new Hotel("lakewood", 110, 90);
        assertEquals(90 ,lakewood.getWeekdayRate());
    }

    @Test
    void testFindCheapestHotel_LakewoodAndBridgewood() throws ParseException {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Lakewood", 110, 90));
        hotels.add(new Hotel("Bridgewood", 150, 50));
        hotels.add(new Hotel("Ridgewood", 220, 150));

        CheapestHotelFinder finder = new CheapestHotelFinder(hotels);
        String cheapestHotel = finder.findCheapestHotel("11Sep2020", "12Sep2020");
        assertEquals("Lakewood", cheapestHotel);
    }

    @Test
    void testFindratings() throws ParseException {
        Hotel lakewood = new Hotel("lakewood" , 110 ,90,3);
        int rating =lakewood.getRating();
        assertEquals(3, rating);
    }

    @Test
    void testFindcheapesthotelbyratings() throws ParseException {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Lakewood", 110, 90, 3));
        hotels.add(new Hotel("Bridgewood", 150, 50, 4));
        hotels.add(new Hotel("Ridgewood", 220, 150, 5));

        CheapestHotelFinder finder = new CheapestHotelFinder(hotels);
        String cheapestHotel = finder.findCheapestHotelByWeekdayAndWeekendDay("10/ 11/ 20", "10/ 12/ 20");
        assertEquals("Ridgewood, Rating: 5 and Total Rates: $370", cheapestHotel);
    }

    @Test
    void testFindbestRatedHotel() throws ParseException {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Lakewood", 110, 90, 3));
        hotels.add(new Hotel("Bridgewood", 150, 50, 4));
        hotels.add(new Hotel("Ridgewood", 220, 150, 5));

        CheapestHotelFinder finder = new CheapestHotelFinder(hotels);
        String bestRatedHotel = finder.findBestRatedHotel("10/ 11/ 20", "10/ 12/ 20");
        assertEquals("Ridgewood, Rating: 5 and Total Rates: $370", bestRatedHotel);
    }

    @Test
    void testFindBestRatedHotelForRewardCustomer() throws ParseException {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Lakewood", 110, 90, 3, 80, 80));
        hotels.add(new Hotel("Bridgewood", 150, 50, 4, 110, 50));
        hotels.add(new Hotel("Ridgewood", 220, 150, 5, 100, 40));

        CheapestHotelFinder finder = new CheapestHotelFinder(hotels);
      //  String bestHotel = finder.findBestRatedHotel("10/ 11/ 20", "10/ 12/ 20", true);
       // assertEquals("Lakewood & Total Rates $83", bestHotel);
    }

    @Test
    void testFindCheapestRatedHotelForRewardCustomer() throws ParseException {

        try {
            List<Hotel> hotels = new ArrayList<>();
            hotels.add(new Hotel("Lakewood", 110, 90, 3, 80, 80));
            hotels.add(new Hotel("Bridgewood", 150, 50, 4, 110, 50));
            hotels.add(new Hotel("Ridgewood", 220, 150, 5, 100, 40));

            CheapestHotelFinder finder = new CheapestHotelFinder(hotels);
            String bestHotel = finder.findBestRatedHotel("10/ 11/ 20", "10/ 12/ 20", "Reward");
            assertEquals("Lakewood & Total Rates $83", bestHotel);
        }
        catch (Exception e)
        {
            System.out.println(" "+e.getMessage());
        }
    }
}
