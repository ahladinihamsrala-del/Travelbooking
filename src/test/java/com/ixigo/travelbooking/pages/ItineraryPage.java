package com.ixigo.travelbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.ixigo.travelbooking.driver.DriverManager;

public class ItineraryPage extends BasePage {

	public ItineraryPage(WebDriver driver) {
		super(driver);
	}

	By departureTime = By.xpath("(//h2[@class='sc-gEvEer ksWbEg'])[1]");
	By continueButton = By.xpath("//button[@data-testid='info_continue_btn']");

	public String checkAirportDetails(String DestAirport) 
	{
		
		String destinationAirportLocator = String.format("//p[contains(text(),'%s')]", DestAirport);

		String destinationAirport = elementsUtil.getVisibleText(By.xpath(destinationAirportLocator));
		System.out.println("In Itinerary "+destinationAirport );
		ExtentCucumberAdapter.addTestStepLog(
	            "Destination Airport details found ");
		return destinationAirport;
	}
	
	public String deptTimeSelecteFlight()
	{
		return elementsUtil.getVisibleText(departureTime);
	}
	
	public TravellerInfoPage navigateToTravellerInfoPage()
	{
		elementsUtil.doClickJS(continueButton);
		return new TravellerInfoPage(DriverManager.getDriver());
	}
	

}
