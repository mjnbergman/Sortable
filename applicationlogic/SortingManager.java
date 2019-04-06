package com.applicationlogic;

import java.util.ArrayList;

import com.sorters.*;
import com.sortingauxiliary.Sortable;

/**
 * 
 * @author Maiko Bergman
 * @date 24-3-2019
 * 
 * This class manages the execution of sorting algorithms. It allows
 * them to run in a seperate thread so as to not hinder the responsiveness
 * of the GUI.
 *
 */
public class SortingManager implements Runnable{
	
	private SortingAlgorithm algo;
	private String threadName;
	private Thread t;
	
	public SortingManager(SortingAlgorithm algo) {
		this.algo = algo;
		this.threadName = "SortingThread";
		this.t = new Thread(this, this.threadName);
	}

	@Override
	public void run() {
		if(!this.algo.isSorted()) {
			System.out.println("Dataset is not sorted yet!");
			this.algo.sort();
		}
		System.out.println("Data set is sorted!");
	}
	
	public ArrayList<Sortable> getDataset(){
		return this.algo.getData();
	}
	
	public void executeAlgorithm() {
		if(this.t != null) {
			this.t.start();
		}
	}

}
