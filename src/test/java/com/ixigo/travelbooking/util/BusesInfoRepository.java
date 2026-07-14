package com.ixigo.travelbooking.util;


import static com.ixigo.travelbooking.constants.Constants.BUS_DETAILS_SHEET;
import static com.ixigo.travelbooking.constants.Constants.BUS_COLUMNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusesInfoRepository {
	private static final Logger log = LogManager.getLogger(UserInfoRepository.class);

	public Map<String, String> getTravelinformation(String city) throws IOException {

		// Extracting the data from Excel sheet
		log.info("Looking up Bus info for city={}", city);
		ExcelReader excelReader = new ExcelReader();

		// Used for the returned Map from excel reader  having User name and Password key-value pairs
		List<Map<String, String>> fromExcel = new ArrayList<>();

		// calling the read excel method with ExcelSheet name and list of column headers
		fromExcel = excelReader.readExcel(BUS_DETAILS_SHEET, BUS_COLUMNS);
																	
			for (Map<String, String> row : fromExcel) {
			log.debug("Inspecting row={}", row);
			String excelUserType = row.get("Leaving from");

		//	System.out.println("UserType: " + excelUserType + " | Cucumber Utype " + travellerType);
			//System.out.println("From Location: " + row.get("From location") + " | Cucumber Utype " + travellerType);
			
			
			if (excelUserType != null && excelUserType.trim().equalsIgnoreCase(city.trim())) {
				log.info("Matching user found in Excel for  Traveller Type={}  ", city);
				return row;
			}
		}
		
		throw new IllegalArgumentException("No matching user found in Excel for userType: " + city);
	}
}

