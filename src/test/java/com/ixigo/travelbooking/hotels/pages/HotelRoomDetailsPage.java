package com.ixigo.travelbooking.hotels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ixigo.travelbooking.driver.DriverManager;
import com.ixigo.travelbooking.pages.BasePage;

public class HotelRoomDetailsPage extends BasePage {

	public HotelRoomDetailsPage(WebDriver driver) {
		super(driver);
		
		
	}
	
	By reserveButton =By.xpath("(//button[text()='Reserve 1 Room'])[2]");
	By viewAllRoomsButton =By.xpath("//button[text()='View All Rooms']");

	
	public HotelAddCustomerDetailsPage reserveRoom()
	{
		elementsUtil.doClickWhenVisible(viewAllRoomsButton);
		elementsUtil.doClickWhenVisible(reserveButton);
		
		return new HotelAddCustomerDetailsPage(DriverManager.getDriver());
	}
	
	
}
