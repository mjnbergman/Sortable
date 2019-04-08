package com.applicationlogic;

import java.util.ArrayList;

import com.sorters.*;
import com.sortingauxiliary.SimpleSortingInput;
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
	private ArrayList<ArrayList<Sortable>> sortingProcess;
	private boolean isRunning;
	
	public SortingManager(SortingAlgorithm algo) {
		this.algo = algo;
		this.threadName = "SortingThread";
		this.t = new Thread(this, this.threadName);
		this.sortingProcess = new ArrayList<>();
		this.sortingProcess.add(new ArrayList<Sortable>(algo.getData()));
	}

	@Override
	public void run() {
		this.isRunning = true;
		while(!this.algo.isSorted() && !this.t.isInterrupted()) {
			System.out.println("Dataset is not sorted yet!");
			sortingProcess.add(this.algo.sortStep());
		}
		System.out.println("Data set is sorted!");
		this.isRunning = false;
	}
	
	public ArrayList<ArrayList<Sortable>> getDataset(){
		return this.sortingProcess;
	}
	
	public boolean isRunning() {
		return this.isRunning;
	}
	
	public void executeAlgorithm() {
		if(this.t != null) {
			this.t.start();
		}
	}
	
	public void stop() {
		this.t.interrupt();
	}

}
