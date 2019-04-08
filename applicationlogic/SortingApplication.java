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
	
	public static int TIME_STEP = 1000;
	
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
		
		int dataCounter = 0;
		
		while(running) {
			if(this.g.hasData()){
				System.out.println("Found data while running!");
				ArrayList<SimpleSortingInput<Integer>> dataset = this.sdp.parseStandardInput(this.g.getData());
				InsertionSorter<Integer> is = new InsertionSorter<Integer>(dataset);
				this.sm = new SortingManager(is);
				dataCounter = 0;
				this.g.setDone(false);
				this.sm.executeAlgorithm();
			}
			if(!this.sm.isRunning()) {
				ArrayList<ArrayList<Sortable>> steps = this.sm.getDataset();
				if(dataCounter < steps.size()) {
					this.g.setData(steps.get(dataCounter));
					dataCounter++;
					System.out.println("Increasing step: " + dataCounter);
				}else {
					this.g.setDone(true);
				}
			}
			this.g.repaint();
			try {
				Thread.sleep(SortingApplication.TIME_STEP);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
