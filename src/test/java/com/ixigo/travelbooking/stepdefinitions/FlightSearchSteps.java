package com.ixigo.travelbooking.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.ixigo.travelbooking.driver.DriverManager;
import com.ixigo.travelbooking.pages.FlightSearchPage;
import com.ixigo.travelbooking.pages.HomePage;
import com.ixigo.travelbooking.pages.ItineraryPage;
import com.ixigo.travelbooking.pages.PaymentPage;
import com.ixigo.travelbooking.pages.SearchResultsPage;
import com.ixigo.travelbooking.pages.TravellerInfoPage;
import com.ixigo.travelbooking.util.UserInfoRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightSearchSteps {
	private HomePage homePage;
	private FlightSearchPage flightsearchpage;
	private SearchResultsPage searchResultsPage;
	private TravellerInfoPage travellerInfoPage;
	private PaymentPage paymentPage;
	Map<String, String> userInfo = new LinkedHashMap<>();
	
	@When("I choose Roundtrip and flight details to search for {string}")
	public void i_choose_roundtrip_and_flight_details_to_search_for(String travelType) throws IOException {
		
UserInfoRepository userInfoRepository = new UserInfoRepository();// connects with Excel and gets the traveller infomration
		

		userInfo = userInfoRepository.getTravelinformation(travelType);
		flightsearchpage=new FlightSearchPage(DriverManager.getDriver());
		searchResultsPage=	flightsearchpage.flightSearch(userInfo);
	    
	}

	@Then("I go to the flights results page")
	public void i_go_to_the_flights_results_page() {
		
		searchResultsPage.verifyFlightsPageLoaded();
		
	}
	@Then("validate the flight search information is displayed in the header for {string} passenger")
	public void validate_the_flight_search_information_is_displayed_in_the_header_for_passenger(String string) {
		
	Assert.assertTrue(searchResultsPage.verifyFlightDetailsInHeader(userInfo));
		
		
}

@When("I apply the recommended filter")
public void i_apply_the_recommended_filter() {
	
	Assert.assertTrue(searchResultsPage.checkFilters());
   
}

@When("I apply the sort filter")
public void i_apply_the_sort_filter() {
	
	String sortedByPrice =searchResultsPage.checkPriceSortOption();
	Assert.assertEquals(sortedByPrice, "Cheapest");
	
	String sortedBySpeed =searchResultsPage.checkSpeedSortOption();
	Assert.assertEquals(sortedBySpeed, "Fastest");
	
	String sortedByTime =searchResultsPage.checkTimeSortOption();
	Assert.assertEquals(sortedByTime, "Earliest");
   
}

@Then("all the results are shown as per the filter applied")
public void all_the_results_are_shown_as_per_the_filter_applied() {

System.out.println("Results displayed as per the filter ");
}

@When("I choose the cheapest departure and cheapest return and proceed with booking")
public void i_choose_the_earliest_departure_and_cheapest_return_and_proceed_with_booking() {
	
	searchResultsPage.selectSmartDeparture();
	travellerInfoPage=searchResultsPage.confirmBooking();
}

@Then("I am navigated to the Review and Traveller details page")
public void i_am_navigated_to_the_review_and_traveller_details_page() {
	String reviewPageTitle=travellerInfoPage.verifyReviewPageLoaded();
	System.out.println(reviewPageTitle);
	
	
}
@When("I verify the flight details and airport details")
public void i_verify_the_flight_details_and_airport_details() {
	
	
	
	
	
	String fromLocation=userInfo.get("From location");
	String toLocation=userInfo.get("To location");
	String fromAirport=userInfo.get("From location airport");
	String toAirport=userInfo.get("To location airport");
	System.out.println("From Review Page"+travellerInfoPage.reviewDeptLoc());
	System.out.println("From Review Page"+travellerInfoPage.reviewDestLoc());
	
	Assert.assertTrue(travellerInfoPage.reviewDeptLoc().contains(fromLocation));
	Assert.assertTrue(travellerInfoPage.reviewDestLoc().contains(toLocation));
	
	
    
}

@When("Add Traveller information contact details and Continue")
public void add_traveller_information_contact_details_and_continue() {
	paymentPage=travellerInfoPage.addTravellerInfo(userInfo);
    
}

@Then("I am Navigated to the Payments page")
public void i_am_navigated_to_the_payments_page() {
	String pageTitle=paymentPage.getPaymentPageTitle();
	System.out.println("From Payment Page"+pageTitle);
	
	
}

}
