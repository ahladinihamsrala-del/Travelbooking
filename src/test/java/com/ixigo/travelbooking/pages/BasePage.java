package com.ixigo.travelbooking.pages;

import org.openqa.selenium.WebDriver;

import com.ixigo.travelbooking.util.ElementsUtil;

public class BasePage {

	protected final WebDriver driver;
    protected final ElementsUtil elementsUtil;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.elementsUtil = new ElementsUtil(driver);
		
	}
	

}
