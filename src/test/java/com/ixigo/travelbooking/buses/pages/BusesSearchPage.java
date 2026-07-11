package com.ixigo.travelbooking.buses.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ixigo.travelbooking.pages.BasePage;

public class BusesSearchPage extends BasePage {

	public BusesSearchPage(WebDriver driver) {
		super(driver);
		
	}

	By leaveFromTxt = By.xpath("//div[@id='search-from']//input[@placeholder='Leaving From']");
	
	By cityOptions =By.xpath("//div[contains(concat(' ', normalize-space(@class), ' '), ' auto-complete-drop-down ')]//*[normalize-space()='Bangalore']");
	By goingToTxt = By.xpath("//div[@id='search-to']//input[@placeholder='Going To']");
	By DepartureTxtLink = By.xpath("//div[@id='jaurney-date']//input");
	
	
	//By checkInDate = By.xpath("//abbr[contains(@aria-label,'"+userInfo.get("Checkin date")+"')]");
	By checkInDate = By.xpath("//abbr[contains(@aria-label,'July 28, 2026')]");
	
	public void busSearch()
	{
	elementsUtil.doClick(leaveFromTxt);
	//elementsUtil.doClickWhenVisible(cityOptions);
	String city = "Chennai";

	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(60));
	WebElement cityOption = wait.until(
	    ExpectedConditions.elementToBeClickable(
	        By.xpath("//div[@id='search-from']//div[contains(@class,'auto-complete')]//*[normalize-space()='" + city + "']")
	    )
	);
	cityOption.click();
	
}
}