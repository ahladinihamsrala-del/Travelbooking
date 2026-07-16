package com.ixigo.travelbooking.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.ixigo.travelbooking.context.TravelInfoContext;
import com.ixigo.travelbooking.driver.DriverManager;

public class SearchResultsPage extends BasePage {
	
	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	TravelInfoContext travelInfoContext = new TravelInfoContext();
	
	By departureFlight = By.xpath("(//div[contains(@class, 'iZJ_ta')]//div[@class='oZj1Wj'])[1]");
	By sortOption= By.xpath("//p[text()='Sort']");
	By returnFlight = By.xpath("//span[contains(text(),'IndiGo')]");
	By bookButton =By.xpath("//span[text()='Book']");
	By closeButton =By.xpath("//*[local-name()='svg' and @width='24' and @viewBox='0 0 24 24']");
	
	By continueButton =By.xpath("//h4[text()='Continue']");
	
	
	By fareDetailsDropdown =By.xpath("//span[text()='Flight Details']");
	By fareSummaryTab =By.xpath("//div[text()='Fare Summary']");
	By baggageTab =By.xpath("//div[text()='Baggage']");
	
	By selectedDeptTime =By.xpath("(//div[contains(@class, 'sc-aXZVg eaOVav flex flex-middle flex-nowrap flex-gap-2')]/p)[1]");
	
	//
	By fromCityHeader = By.xpath("//p[@data-testid='originId']");
	By toCityHeader = By.xpath("//p[@data-testid='destinationId']");
	By departureDateHeader = By.xpath("//p[@data-testid='departureDate']");
	By returnDateHeader = By.xpath("//p[@data-testid='returnDate']");
	By travellerClassHeader = By.xpath("//p[@data-testid='pax']");
	By recommendedFilterChx = By.xpath("(//input[@type='checkbox' and @value='6E'])[2]");
	
	//p[contains(normalize-space(.), 'Flights Available')]
	By resultsFiltermsg = By.xpath("//p[contains(normalize-space(.), 'Flights Available')][1]");
	By resultsFilternumber = By.xpath("substring-before(normalize-space(//p[contains(@class,'body-sm') and contains(@class,'text-secondary')]), ' Flights Available')");
		
	//By priceSortLink =By.xpath("//input[@name='oneWayType' and @value='cheapest']");
	By priceSortLink =By.xpath("//input[@name='oneWayType'][@value='cheapest']");
	By fastestSortLink =By.xpath("//input[@name='oneWayType' and @value='quickest']");
	By departSortLink =By.xpath("//input[@name='oneWayType' and @value='earliest']");
	By smartSortLink =By.xpath("//input[@name='oneWayType' and @value='best']");
	By priceTag =By.xpath("(//span[text()='Cheapest'])[1]");
	By speedTag =By.xpath("(//span[text()='Fastest'])[1]");
	By timeTag =By.xpath("(//span[text()='Earliest'])[1]");
	By onwardFlightNum =By.xpath("//p[contains(normalize-space(),'Onward')]//span");
	By returnFlightNum =By.xpath("//p[contains(normalize-space(),'Return')//span");
	By onwardDeptAndArrivalTime =By.xpath("//p[contains(normalize-space(),'Onward')]/following::h5[1]"); //13:10 16:00
	
	By RetDeptAndArrivalTime =By.xpath("//p[contains(normalize-space(),'Return')]/following::h5[1]"); //10:25 13:20
	By flightDetailsLink =By.xpath("//p[text()='Flight Details ']");
	By bookConfirmButton =By.xpath("(//button[text()='Book'])[2]");
	
		
	

	
	
	

	
	
	
	
	public String formXpathForSort(String sortBy)
	{
		String sortCriteria= String.format("//span[contains(text(),'%s')]", sortBy);
		return sortCriteria;
	}

public ItineraryPage chooseFlights(String deptSpecs,String retSpecs)
{
	
	elementsUtil.doClick(sortOption);
	
	
String	deptSortOption=formXpathForSort(deptSpecs);
	System.out.print("Departure specs xpath "+deptSortOption);
	elementsUtil.doClickJS(By.xpath(deptSortOption));
	
String	returnSortOption=formXpathForSort(retSpecs);
System.out.print("return specs xpath "+returnSortOption);
	elementsUtil.doClick(By.xpath(returnSortOption));	
	elementsUtil.doClick(closeButton);
	//travelInfoContext.setSelectedDepartureTime(elementsUtil.getVisibleText(selectedDeptTime));
	
	ExtentCucumberAdapter.addTestStepLog(
            "To and Fro Flights selected ");
	
	elementsUtil.doClick(continueButton);
	return new ItineraryPage(DriverManager.getDriver());
	
}

public String verifyFlightsPageLoaded()
{
	System.out.print("Page title" + elementsUtil.getPageTitle());
	return elementsUtil.getPageTitle();
}
public boolean verifyFlightDetailsInHeader(Map<String,String> userdetails)
{
if(elementsUtil.getVisibleText(fromCityHeader).contains(userdetails.get("From location")))
		
		if(elementsUtil.getVisibleText(toCityHeader).contains(userdetails.get("To location")))
if(elementsUtil.getVisibleText(travellerClassHeader).contains(userdetails.get("Travel Class")))
return true;
else 
	System.out.println("Flight details mismatched in header ");
return false;
			
}
public boolean checkFilters()
{
	//String txtInFilter=elementsUtil.getPresenceText(recommendedFilterChx);
	//System.out.println("txtInFilter " +txtInFilter);
	elementsUtil.doClickJSbyElementPresence(recommendedFilterChx);
	System.out.println("Filter msg"+elementsUtil.getVisibleText(resultsFiltermsg));
	//System.out.println("Filter msg Number"+elementsUtil.getVisibleText(resultsFilternumber));
	
	String filtertext=elementsUtil.getVisibleText(resultsFiltermsg);
	String count = filtertext.replaceAll("\\D+", "");
			System.out.println("Flights count" +count);
			if(count==null)
				return false;
			return true;
}
public String checkPriceSortOption()
{
	elementsUtil.doClickJSbyElementPresence(priceSortLink);
	
	//	System.out.println(elementsUtil.getPresenceText(priceTag));
		
		return elementsUtil.getPresenceText(priceTag);
}
public String checkSpeedSortOption()
{
	elementsUtil.doClickJSbyElementPresence(fastestSortLink);
	
	//	System.out.println(elementsUtil.getPresenceText(speedTag));
		
		return elementsUtil.getPresenceText(speedTag);
}
public String checkTimeSortOption()
{
	elementsUtil.doClickJSbyElementPresence(departSortLink);
	
	//	System.out.println(elementsUtil.getPresenceText(timeTag));
		
		return elementsUtil.getPresenceText(timeTag);
}
public void selectEarliestDeparture()
{
	elementsUtil.doClickJSbyElementPresence(departSortLink);
	elementsUtil.doClickJSbyElementPresence(timeTag);
}

public void selectCheapestDeparture()
{
	elementsUtil.doClickJSbyElementPresence(priceSortLink);
	elementsUtil.doClickJSbyElementPresence(priceTag);
}

public void selectSmartDeparture()
{
	elementsUtil.doClickJSbyElementPresence(smartSortLink);
	//elementsUtil.doClickJSbyElementPresence(priceTag);
}

public TravellerInfoPage confirmBooking()
{
	
	elementsUtil.doClickJS(flightDetailsLink);
	elementsUtil.doClickJS(bookConfirmButton);
	
	return new TravellerInfoPage(DriverManager.getDriver());
}

}