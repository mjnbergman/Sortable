package com.applicationlogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileHandler {

	public void openDataSet(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String dataType = br.readLine();
			
			Class c = Class.forName(dataType).getClass();
			
	
			
			SimpleSortingInput<c.getClass()> si = new SimpleSortingInput<c>();
			
			System.out.println("The Data Type is of: " + dataType);
			
			while ((dataType = br.readLine()) != null) {
				System.out.println(dataType);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
