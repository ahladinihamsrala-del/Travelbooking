package com.ixigo.travelbooking.stepdefinitions;

import static com.ixigo.travelbooking.constants.Constants.PAYMENT_PAGE_TEXT;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.ixigo.travelbooking.context.TravelInfoContext;
import com.ixigo.travelbooking.driver.DriverManager;
import com.ixigo.travelbooking.pages.ItineraryPage;
import com.ixigo.travelbooking.pages.PaymentPage;
import com.ixigo.travelbooking.pages.TravellerInfoPage;
import com.ixigo.travelbooking.util.UserInfoRepository;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightBookingSteps
{
	
	private ItineraryPage itineraryPage;
	private TravelInfoContext travelInfoContext;
	private TravellerInfoPage travellerInfoPage;
	private PaymentPage paymentPage;
	
	Map<String, String> userInfoRepo  = new LinkedHashMap<>();
	
	@When("I verify the time of departure on Itinerary page")
	public void i_verify_the_time_of_departure_on_itinerary_page() {
		
		itineraryPage= new ItineraryPage(DriverManager.getDriver());
		travelInfoContext = new TravelInfoContext();
		//Assert.assertEquals(itineraryPage.deptTimeSelecteFlight(),travelInfoContext.getSelectedDepartureTime());
		travellerInfoPage=itineraryPage.navigateToTravellerInfoPage();
	    
	}

	@When("I add Traveller information of the travellers")
	public void i_add_traveller_information_of_the_travellers() throws IOException {
UserInfoRepository userInfoRepository = new UserInfoRepository();// connects with Excel and gets the traveller infomration
userInfoRepo = userInfoRepository.getTravelinformation("Economy");
		travellerInfoPage.addTravellerInfo(userInfoRepo);
	}

	@Then("validate the correct traveller information is shown in the payments page")
	public void validate_the_correct_traveller_information_is_shown_in_the_payments_page() {
		
		TravelInfoContext travelInfoContext=new TravelInfoContext();
		
		
		//Assert.assertEquals(travellerInfoPage.reviewDeptTime(), travelInfoContext.getSelectedDepartureTime());
		paymentPage=travellerInfoPage.navigateToPaymentPage();
		
		Assert.assertEquals(paymentPage.getPaymentPageTitle(), PAYMENT_PAGE_TEXT);
		
	}

}
