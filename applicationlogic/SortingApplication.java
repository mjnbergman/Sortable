package com.applicationlogic;

import com.gui.*;
import com.sorters.*;
import com.sortingauxiliary.*;

public class SortingApplication {

	private SortingManager sm;
	private GUI g;
	
	public SortingApplication() {
		this.g = new GUI();
		Integer[] dataset = new Integer[] {6, 5, 4};
		InsertionSorter<Integer> is = new InsertionSorter<Integer>(dataset);
		this.sm = new SortingManager(is);
		this.sm.executeAlgorithm();
	}
	
}
