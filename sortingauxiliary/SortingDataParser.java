package com.sortingauxiliary;

import java.util.ArrayList;

/**
 * 
 * @author Maiko Bergman
 * @date 6-4-2019
 * 
 * A class that parses file data passed to it, usually obtained through @code(FileHandler) output.
 *
 */
public class SortingDataParser {
	
	ArrayList<SimpleSortingInput<Integer>> parseStandardInput(ArrayList<String> input) {
		ArrayList<SimpleSortingInput<Integer>> output = new ArrayList<SimpleSortingInput<Integer>>();
		
		for(String i : input) {
			SimpleSortingInput<Integer> si = new SimpleSortingInput<Integer>(Integer.parseInt(i));
			output.add(si);
		}
		
		return output;
	}

}
