package com.ixigo.travelbooking.buses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ixigo.travelbooking.pages.BasePage;

public class BusPaymentsPage extends BasePage {

	public BusPaymentsPage(WebDriver driver) {
		super(driver);
		
	}
	
By fareSummaryTxt=By.xpath("//h4[text()='Fare Summary']");

public String getPaymentPageText()
{
	return elementsUtil.getVisibleText(fareSummaryTxt);
}

}
