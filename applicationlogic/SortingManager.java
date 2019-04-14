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
	private ArrayList<ArrayList<Integer>> sortingHighlights;
	private boolean isRunning;
	
	public SortingManager(SortingAlgorithm algo) {
		this.algo = algo;
		this.threadName = "SortingThread";
		this.t = new Thread(this, this.threadName);
		this.sortingProcess = new ArrayList<>();
		this.sortingProcess.add(new ArrayList<Sortable>(algo.getData()));
		this.sortingHighlights = new ArrayList<>();
	}
	public SortingManager(String algoName, ArrayList<? extends Sortable> data) {
		switch(algoName.toLowerCase()) {
		case "insertionsort":
			this.algo = new InsertionSorter(data);
			System.out.println("Ready to sort with insertionsort!!!");
			break;
		case "mergesort":
			this.algo = new MergeSorter(data);
			break;
		default:
			this.algo = new InsertionSorter(data);
			break;
		}
		
		this.threadName = "SortingThread";
		this.t = new Thread(this, this.threadName);
		this.sortingProcess = new ArrayList<>();
		this.sortingProcess.add(new ArrayList<Sortable>(algo.getData()));
		this.sortingHighlights = new ArrayList<>();
	}

	@Override
	public void run() {
		this.isRunning = true;
		while(!this.algo.isSorted() && !this.t.isInterrupted()) {
			System.out.println("Dataset is not sorted yet!");
			sortingProcess.add(this.algo.sortStep());
		}
		System.out.println("Data set is sorted!");
		this.sortingHighlights = this.algo.getHighlights();
		this.isRunning = false;
	}
	
	public ArrayList<ArrayList<Sortable>> getDataset(){
		return this.sortingProcess;
	}
	
	public ArrayList<ArrayList<Integer>> getHighlights(){
		return this.sortingHighlights;
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
