package com.bridgelab.hotelreservation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HotelTest {
    Hotel h , h1;

    @Test
    @DisplayName("To check hotel is not empty")
    public void checkNameNotEmpty()
    {
        h = new Hotel("Lakewood" ,1500);
        assertNotNull(h);


    }
}
