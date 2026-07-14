package com.ixigo.travelbooking.constants;

import java.util.List;

public final class Constants {

    private Constants() {
    }

    public static final String LOGIN_DETAILS_SHEET = "TravellerInfo";
    public static final String HOTEL_DETAILS_SHEET = "HotelBooking";
    public static final String BUS_DETAILS_SHEET = "BusBooking";


    public static final String COL_TRAVELLER_TYPE = "Travel Class";
    public static final String COL_FROM_LOCATION = "From location";
    public static final String COL_TO_LOCATION = "To location";
    public static final String COL_DEPARTURE_DATE = "Departure date";
    public static final String COL_RETURN_DATE = "Return Date";
    public static final String COL_FROM_LOCATION_AIRPORT = "From location airport";
    public static final String COL_TO_LOCATION_AIRPORT = "To location airport";
    public static final String COL_DEPARTURE_SPECS = "Departure specs";
    public static final String COL_RETURN_SPECS = "Return specs";
    public static final String CHROME_BROWSER = "chrome";
    public static final String EDGE_BROWSER = "edge";
    public static final String FIREFOX_BROWSER = "firefox";
    public static final String PAYMENT_PAGE_TEXT = "Pay and book";
    public static final String COL_TRAVELLER_ID = "Traveller id";
    public static final String COL_FIRST_NAME = "First name";
    public static final String COL_LAST_NAME = "Last name";
    public static final String COL_EMAIL_ID = "Email address";
    
    //Hotel sheet columns
    
    public static final String COL_CITY = "City";
    public static final String COL_LOCATION = "Location";
    public static final String COL_CHECKIN_DATE = "Checkin date";
    public static final String COL_CHECKOUT_DATE = "Checkout date";
    
//  Buses sheet columns
    public static final String COL_LEAVING_FROM = "Leaving from";
    public static final String COL_GOING_TO = "Going to";
    public static final String COL_BUS_DEPARTURE_DATE = "Departure Date";
    public static final String COL_BOARDING_POINT = "Boarding point";
    public static final String COL_DROPPING_POINT = "Dropping point";
    public static final String COL_EMAIL = "Email";
    public static final String COL_NAME = "Name";
    public static final String COL_AGE = "Age";
    

    public static final List<String> USER_COLUMNS =
            List.of(COL_TRAVELLER_TYPE, COL_FROM_LOCATION, COL_TO_LOCATION,COL_DEPARTURE_DATE,COL_RETURN_DATE,COL_FROM_LOCATION_AIRPORT,COL_TO_LOCATION_AIRPORT,COL_DEPARTURE_SPECS,
            		COL_RETURN_SPECS,COL_TRAVELLER_ID,COL_FIRST_NAME,COL_LAST_NAME,COL_EMAIL_ID );
    
    
    public static final List<String> HOTEL_COLUMNS =
            List.of(COL_CITY,COL_LOCATION,COL_CHECKIN_DATE,COL_CHECKOUT_DATE);
    
    public static final List<String> BUS_COLUMNS =
            List.of(COL_LEAVING_FROM,COL_GOING_TO,COL_BUS_DEPARTURE_DATE,COL_BOARDING_POINT,COL_DROPPING_POINT,
            		COL_NAME,COL_AGE,COL_EMAIL);
}
