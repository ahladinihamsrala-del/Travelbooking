package com.ixigo.travelbooking.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.ixigo.travelbooking.driver.DriverManager;
import com.ixigo.travelbooking.hotels.pages.HotelsSearchPage;
import com.ixigo.travelbooking.hotels.pages.HotelsSearchResultsPage;
import com.ixigo.travelbooking.pages.HomePage;
import com.ixigo.travelbooking.hotels.pages.HotelAddCustomerDetailsPage;
import com.ixigo.travelbooking.hotels.pages.HotelRoomDetailsPage;
import com.ixigo.travelbooking.util.HotelInfoRepository;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HotelsSearchSteps {

	private HomePage homePage;
	private HotelsSearchPage hotelsSearchPage;
	private HotelsSearchResultsPage hotelsSearchResultsPage;
	private HotelAddCustomerDetailsPage hotelAddCustomerDetailsPage;
	
	private HotelRoomDetailsPage hotelRoomDetailsPage;
	Map<String, String> hotelInfo = new LinkedHashMap<>();
	List<Double> actualPrices = new ArrayList<>();
	String excepTag;
	

@When("I search for Hotels in {string}")
public void i_search_for_hotels_in(String userCity) throws IOException {
	
	HotelInfoRepository hotelInfoRepository = new HotelInfoRepository();// connects with Excel and gets the Hotel Information
	hotelInfo = hotelInfoRepository.getHotelinformation(userCity);
	homePage = new HomePage(DriverManager.getDriver());
	hotelsSearchPage =homePage.navigateToHotelsSearch(); 
	hotelsSearchResultsPage=hotelsSearchPage.searchHotels(hotelInfo);
	
    
}
@Then("I am navigated to the available hotels list page")
public void i_am_navigated_to_the_available_hotels_list_page() {
String srpURL=	hotelsSearchResultsPage.getPageURL();
System.out.print(srpURL);
Assert.assertTrue(srpURL.contains("search/result"));

    
}
@When("I sort by the Prices Low to High")
public void i_sort_by_the_prices_low_to_high() {
	actualPrices =hotelsSearchResultsPage.getListedPrices();
	
    
}

@Then("hotels are displayed from Low to high prices")
public void hotels_are_displayed_from_lox_to_high_prices() {
	List<Double> ExpectedPrices=hotelsSearchResultsPage.SortListedPrices(actualPrices);
	Assert.assertEquals(actualPrices,ExpectedPrices);
}
@When("I apply the Exceptional Rating filter")
public void i_apply_the_exceptional_rating_filter() {
excepTag=hotelsSearchResultsPage.verifyFilters();
System.out.println(excepTag);
}

@Then("I can see all the Hotels rated Exceptional")
public void i_can_see_all_the_hotels_rted_exceptinal() {
	
	Assert.assertTrue(excepTag.contains("Exceptional"));
	
}
@When("I search by locality in the search box")
public void i_search_by_locality_in_the_search_box() {
	Assert.assertTrue(	hotelsSearchResultsPage.verifyFilters("Madhapur"));
    
}

@Then("I am navigated to the hotels by locality")
public void i_am_navigated_to_the_hotels_by_locality() {
    System.out.println("Completed search by locality successfully");
}
@When("I apply the Facility filter for wiFi")
public void i_apply_the_facility_filter_for_wi_fi() {
	Assert.assertTrue(	hotelsSearchResultsPage.verifyFacilityFilters());
}

@Then("I can see all the Hotels with the facility")
public void i_can_see_all_the_hotels_with_the_facility() {
	System.out.println("Facility filter applied successfully");
}
@When("I apply the Best price guarantee filter")
public void i_apply_the_best_price_guarantee_filter() {
	Assert.assertTrue(hotelsSearchResultsPage.verifyBestPriceGuaranteeFilter());
}

@Then("I can see all the Hotels with Best Price guarantee card")
public void i_can_see_all_the_hotels_with_best_price_guarantee_card() {
	System.out.println("BestPriceGuarantee filter applied successfully");
}
@When("I book the best guarantee hotel reserve room")
public void i_book_the_best_guarantee_hotel_reserve_room() {
	hotelRoomDetailsPage=hotelsSearchResultsPage.bookHotel();
	
	hotelAddCustomerDetailsPage=hotelRoomDetailsPage.reserveRoom();
    
}

@When("Add User details")
public void add_user_details() {
	hotelAddCustomerDetailsPage.addCustomerDetails();
}

@Then("I am navigated to the payment page")
public void i_am_navigated_to_the_payment_page() {
	System.out.println("User is navigated to the Payment page successfully");
}



}
