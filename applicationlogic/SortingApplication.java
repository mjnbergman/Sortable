package com.applicationlogic;

import java.util.ArrayList;
import java.util.Arrays;

import com.gui.*;
import com.sorters.*;
import com.sortingauxiliary.*;

public class SortingApplication {

	private SortingManager sm;
	private SortingDataParser sdp;
	private GUI g;
	private boolean running;
	
	public SortingApplication() {
		this.g = new GUI();
		this.sdp = new SortingDataParser();
		Integer[] datatest = {1, 3, 2, 7, 53, 2, 6, 91};
		ArrayList<Integer> dataset = new ArrayList<Integer>(Arrays.asList(datatest));
		InsertionSorter<Integer> is = new InsertionSorter<Integer>();
		is.setData(dataset);
		
		g.setData(is.getData());
		
		this.sm = new SortingManager(is);
		this.sm.executeAlgorithm();
		this.running = true;
		this.run();
	}
	
	public void run() {
		while(running) {
			if(this.g.hasData()){
				System.out.println("Found data while running!");
				ArrayList<SimpleSortingInput<Integer>> dataset = this.sdp.parseStandardInput(this.g.getData());
				InsertionSorter<Integer> is = new InsertionSorter<Integer>(dataset);
				this.sm = new SortingManager(is);
				this.sm.executeAlgorithm();
			}
		}
	}
	
}
