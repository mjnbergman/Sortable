package com.sorters;

import java.util.ArrayList;

import com.sortingauxiliary.SimpleSortingInput;

/**
 * 
 * @author Maiko Bergman
 * @date 08-04-2019
 * 
 * A sorting algorithm for the classic merge sort algorithm.
 * Implements the algorithm as per the pseudocode on wikipedia.
 * Not in place.
 *
 */
public class MergeSorter<T extends Number & Comparable<T>> extends SortingAlgorithm{

	private int index;

	public MergeSorter(ArrayList<SimpleSortingInput<T>> inputs) {
		super(inputs);
		// TODO Auto-generated constructor stub
	}
	public MergeSorter() {
		super();
	}
	
	public void setData(ArrayList<T> inputs) {
		this.inputs = new ArrayList<SimpleSortingInput<T>>();
		for(int i = 0; i < inputs.size(); i++) {
			this.inputs.add(new SimpleSortingInput<T>(inputs.get(i)));
		}
	}

	
	private void swap(int i1, int i2) {
		SimpleSortingInput<T> temp = (SimpleSortingInput<T>)this.inputs.get(i1);
		this.inputs.remove(i1);
		this.inputs.add(i1, this.inputs.get(i2));
		this.inputs.remove(i2);
		this.inputs.add(i2, temp);
	}

	
	public void printDataset() {
		for(Object i : this.inputs) {
			SimpleSortingInput<T> t = (SimpleSortingInput<T>) i;
			System.out.print(t.getValue() + ", ");
		}
		System.out.println("");
	}
	
	
	@Override
	public boolean isSorted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList sortStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList sort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
