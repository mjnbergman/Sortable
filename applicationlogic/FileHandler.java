package com.applicationlogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Maiko Bergman
 * @date 6-4-2019
 * 
 * A class that handles all of the File I/O of the application. Allows users to open and close datasets.
 * Supported data formats are: .csv files
 * 
 * More data formats might be supported in the future.
 * This class is used through an object in the GUI class.
 *
 */
public class FileHandler {
	
	/*
	 * This String specifies what parameter the data points should be split on. Standard is a comma for .csv files
	 */
	private String delimiter = ",";

	/**
	 * Functions that opens a dataset, parses it on some basic delimiters to an @code(ArrayList<String>) and returns that to 
	 * the user. 
	 * @param file
	 * @return an @code(ArrayList<String>) containing a single datapoint per entry or @code(null) if no valid datapoints were found.
	 */
	public ArrayList<String> openDataSet(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			ArrayList<String> dataPoints = new ArrayList<String>();
			
	/*		String dataType = br.readLine();
			
			Class c = Class.forName(dataType).getClass();
			
	
			
			SimpleSortingInput<c.getClass()> si = new SimpleSortingInput<c>();
			
			System.out.println("The Data Type is of: " + dataType);
			
			while ((dataType = br.readLine()) != null) {
				System.out.println(dataType);
			}
			*/
			String data = "";
			
			while((data = br.readLine()) != null) {
				dataPoints.addAll(Arrays.asList(data.split(delimiter)));
			}
			
			return dataPoints;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
