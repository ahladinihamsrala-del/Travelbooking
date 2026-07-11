package com.ixigo.travelbooking.stepdefinitions;

import com.ixigo.travelbooking.buses.pages.BusesSearchPage;
import com.ixigo.travelbooking.driver.DriverManager;
import com.ixigo.travelbooking.pages.HomePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BusSearchSteps {
	
	private HomePage homePage;
	private BusesSearchPage busesSearchPage;
	
	@When("I search for Buses in departure {string}")
	public void i_search_for_buses_in_departure(String string) {
		
		homePage = new HomePage(DriverManager.getDriver());
		busesSearchPage=homePage.navigateToBusesSearch();
		busesSearchPage.busSearch();
	    
	}

	@Then("I am navigated to the available Buses page")
	public void i_am_navigated_to_the_available_buses_page() {
	    
	}



}
