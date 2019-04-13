package com.applicationlogic;

import java.util.ArrayList;
import java.util.Arrays;

import com.gui.*;
import com.sorters.*;
import com.sortingauxiliary.*;

/**
 * 
 * @author Maiko Bergman
 * @date 06-04-2019
 * 
 * The main execution point of the application. The main program loop is contained within this class.
 * It manages the GUI, manages the execution of sorting algorithms through references to @code(SortingManager)
 * objects which contain seperate threads of execution.
 * 
 * It continually pings the @code(GUI) for updates received from the user and applies them.
 * This class also facilitates switching between sorting algorithms and datasets.
 *
 */
public class SortingApplication {

	private SortingManager sm;
	private GUI g;
	private boolean running;
	
	public static int TIME_STEP = 1000;
	
	public SortingApplication() {
		this.g = new GUI();
	//	this.sdp = new SortingDataParser();
	//	Integer[] datatest = {1, 3, 2, 7, 53, 2, 6, 91};
	//	ArrayList<Integer> dataset = new ArrayList<Integer>(Arrays.asList(datatest));
	//	MergeSorter<Integer> is = new MergeSorter<Integer>();
	//	is.setData(dataset);
		
	//	g.setData(is.getData());
		
	//	this.sm = new SortingManager(is);
	//	this.sm.executeAlgorithm();
		this.running = true;
		this.run();
	}
	
	/**
	 * The main application loop, it continuously pings the gui for updates like new data or
	 * a new sorting algorithm being selected by the user. It delegates these updates to the appropriate classes.
	 * The main thread sleeps (so the @code(VisualizationPanel) halts drawing updates, but the GUI remains responsive).
	 * The sleep delay is configurable by the user through an @code(JSlider) contained in the @code(GUI) class. 
	 * This influences the static @code(TIME_STEP) variable.
	 */
	public void run() {
		
		int dataCounter = 0;
		
		while(running) {
	/*		if(this.g.hasData()){
				System.out.println("Found data while running!");
				ArrayList<SimpleSortingInput<Integer>> dataset = this.sdp.parseStandardInput(this.g.getData());
				//InsertionSorter<Integer> is = new InsertionSorter<Integer>(dataset);
				//this.sm = new SortingManager(is);
				dataCounter = 0;
				this.g.setDone(false);
				this.sm.executeAlgorithm();
			} */
	/*		if(!this.sm.isRunning()) {
				ArrayList<ArrayList<Sortable>> steps = this.sm.getDataset();
				if(dataCounter < steps.size()) {
					this.g.setData(steps.get(dataCounter));
					dataCounter++;
					System.out.println("Increasing step: " + dataCounter);
				}else {
					this.g.setDone(true);
				}
			} */
			this.g.playbackAlgos();
	/*		if(this.g.algorithmChanged()) {
				if(this.sm.isRunning()) {
					this.sm.stop();
				}
				String newAlgo = this.g.getCurrentAlgorithm();
				this.g.validateChangeReceived();
			} */
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
