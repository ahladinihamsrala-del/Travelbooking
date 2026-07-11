package com.ixigo.travelbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class PaymentPage extends BasePage {

	public PaymentPage(WebDriver driver) {
		super(driver);
		
	}
	
	By paymentPageTitleTxt =By.xpath("//p['Scan & Pay with UPI'][1]");
	
	
	public String getPaymentPageTitle()
	{
		ExtentCucumberAdapter.addTestStepLog(
	            "Extracting the Payment Page Title ");
		elementsUtil.getVisibleText(paymentPageTitleTxt);
		
		return elementsUtil.getPageTitle();
	}

}
