package com.ixigo.travelbooking.stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.ixigo.travelbooking.buses.pages.BusPaymentsPage;
import com.ixigo.travelbooking.buses.pages.BusResultsPage;
import com.ixigo.travelbooking.buses.pages.BusesSearchPage;
import com.ixigo.travelbooking.buses.pages.PassengerInfoPage;
import com.ixigo.travelbooking.driver.DriverManager;
import com.ixigo.travelbooking.pages.HomePage;
import com.ixigo.travelbooking.util.BusesInfoRepository;
import com.ixigo.travelbooking.util.UserInfoRepository;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BusSearchSteps {
	
	private HomePage homePage;
	private BusesSearchPage busesSearchPage;
	private BusResultsPage busResultsPage;
	private PassengerInfoPage passengerInfoPage;
	private BusPaymentsPage busPaymentsPage;
	String filtertxt;
	Map<String, String> busInfo = new LinkedHashMap<>();
	
	
	
	@When("I search for Buses in departure {string}")
	public void i_search_for_buses_in_departure(String string) throws IOException {
		
		homePage = new HomePage(DriverManager.getDriver());
		busesSearchPage=homePage.navigateToBusesSearch();
		BusesInfoRepository busesInfoRepository = new BusesInfoRepository();// connects with Excel and gets the traveller infomration
		busInfo = busesInfoRepository.getTravelinformation(string);
		
		busResultsPage=	busesSearchPage.busSearch(busInfo);
	    
	}

	@Then("I am navigated to the available Buses page")
	public void i_am_navigated_to_the_available_buses_page() {
		
		String newURL=busResultsPage .getSearchPageURL();
		System.out.print("newURL: "+newURL);
		Assert.assertTrue(newURL.contains("bus_search"));
	    
	}
	@When("I apply Bus type filter and choose Bus Parnter")
	public void i_apply_bus_type_filter_and_choose_bus_parnter() {
		
		
		
		filtertxt=busResultsPage.applyBusFilters();
		System.out.print("filtertxt :"+filtertxt);
		
		
	}

	@Then("Search results show the applied bus type and Chosen Bus partner")
	public void search_results_show_the_applied_bus_type_and_chosen_bus_partner() {
		
		Assert.assertTrue(filtertxt.contains("AC"));
		Assert.assertTrue(filtertxt.contains("Sleeper"));
		
	    
	}

	@When("I choose the boarding and Departure points and book bus")
	public void i_choose_the_boarding_and_departure_points_and_book_bus() {
		passengerInfoPage=busResultsPage.bookBus(busInfo);
	}

	@When("Enter Passenger information")
	public void enter_passenger_information() {
		busPaymentsPage=passengerInfoPage.enterPassengerInfo(busInfo);
	}

	@Then("I am navigated to the Bus Payments page")
	public void i_am_navigated_to_the_bus_payments_page() {
		
		String paymentpagetext=busPaymentsPage.getPaymentPageText();
		
		Assert.assertTrue(paymentpagetext.contains("Fare Summary"));
	}

}
