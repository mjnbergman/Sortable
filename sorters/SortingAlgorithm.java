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
public abstract class SortingAlgorithm<T extends Comparable<T>> {
	
	protected ArrayList<Sortable<T>> inputs;
	
	public SortingAlgorithm() {
		
	}
	
	public SortingAlgorithm(ArrayList<Sortable<T>> inputs) {
		this.inputs = inputs;
	}
	
	public abstract boolean isSorted();
	public abstract boolean sortStep();
	public abstract ArrayList<Sortable<T>> sort();
}
