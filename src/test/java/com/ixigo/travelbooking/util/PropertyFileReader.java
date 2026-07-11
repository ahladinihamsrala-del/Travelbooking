package com.ixigo.travelbooking.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	Properties prop = new Properties();

	public String getFromPropertyFile(String key) throws IOException {
		try {
			// Establishing a connection with the File
			FileInputStream fileinput = new FileInputStream("./DataSource/config.property");
			// To load the file
			prop.load(fileinput);

		} catch (FileNotFoundException e) {
			System.out.print("Error accessing the Properties file");
			e.printStackTrace();
		}

//inbuilt method of the Properties class that will return the value
// of the key in the Property file
		return prop.getProperty(key);

	}

}
