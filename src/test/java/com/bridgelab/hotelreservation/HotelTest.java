package com.bridgelab.hotelreservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
