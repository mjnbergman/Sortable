package com.sorters;

import java.util.ArrayList;

import com.sortingauxiliary.*;

/**
 * 
 * @author Maiko Bergman
 * @date 24-3-2019
 * 
 * This class is the abstract super class of all @code(SortingAlgorithms).
 * It provides the basic method templates for interfacing with the GUI.
 *
 */
public abstract class SortingAlgorithm<T extends Number & Comparable<T>> {
	
	protected ArrayList<Sortable<T>> inputs;
	protected ArrayList<ArrayList<Integer>> highlights;
	
	public SortingAlgorithm() {
		this.highlights = new ArrayList<>();
	}
	
	public SortingAlgorithm(ArrayList<Sortable<T>> inputs) {
		this.inputs = inputs;
		this.highlights = new ArrayList<>();
	}
	
	public abstract boolean isSorted();
	public abstract ArrayList<Sortable<T>> sortStep();
	public abstract ArrayList<ArrayList<Sortable<T>>> sort();
	public abstract ArrayList<ArrayList<Integer>> getHighlights();
	public abstract ArrayList<Sortable<T>> getData();
}
