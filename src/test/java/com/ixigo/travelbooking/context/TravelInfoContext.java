package com.ixigo.travelbooking.context;

public class TravelInfoContext {
	
	
	private String selectedDepartureTime;
	private String selectedDepartureLocation;
	private String selectedDepartureDate;

    public String getSelectedDepartureTime() {
        return selectedDepartureTime;
    }
    public void setSelectedDepartureTime(String selectedDepartureTime) {
        this.selectedDepartureTime = selectedDepartureTime;
    }
    public void setSelectedDepartureLocation(String selectedDepartureLocation) {
        this.selectedDepartureLocation = selectedDepartureLocation;
    }

    public String getSelectedDepartureLocation() {
        return selectedDepartureLocation;
    }

       
    
    public String getSelectedDepartureDate() {
        return selectedDepartureDate;
    }

    public void setSelectedDepartureDate(String selectedDepartureDate) {
        this.selectedDepartureDate = selectedDepartureDate;
    }
}
