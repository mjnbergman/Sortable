package com.applicationlogic;

import java.util.ArrayList;
import java.util.Arrays;

import com.gui.*;
import com.sorters.*;
import com.sortingauxiliary.*;

public class SortingApplication {

	private SortingManager sm;
	private GUI g;
	
	public SortingApplication() {
		this.g = new GUI();
		Integer[] datatest = {2, 3, 4};
		ArrayList<Integer> dataset = (ArrayList<Integer>)Arrays.asList(datatest);
		InsertionSorter<Integer> is = new InsertionSorter<Integer>();
		is.setData(dataset);
		this.sm = new SortingManager(is);
		this.sm.executeAlgorithm();
	}
	
}
