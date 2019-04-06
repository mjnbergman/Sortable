package com.sorters;

import com.sortingauxiliary.Sortable;

import java.util.ArrayList;

import com.sortingauxiliary.SimpleSortingInput;

public class InsertionSorter<T extends Comparable<T>> extends SortingAlgorithm{

	public InsertionSorter(ArrayList<SimpleSortingInput<T>> inputs) {
		super(inputs);
		// TODO Auto-generated constructor stub
	}
	public InsertionSorter() {
		super();
	}
	
	public void setData(ArrayList<T> inputs) {
		this.inputs = new ArrayList<SimpleSortingInput<T>>();
		for(int i = 0; i < inputs.size(); i++) {
			this.inputs.add(new SimpleSortingInput<T>(inputs.get(i)));
		}
	}

	@Override
	public boolean isSorted() {
		for(int i = 1; i < this.inputs.size(); i++) {
			if(((SimpleSortingInput<T>)this.inputs.get(i)).compareTo((SimpleSortingInput<T>)this.inputs.get(i - 1)) == -1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public SimpleSortingInput<T> sortStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SimpleSortingInput<T>> sort() {
		// TODO Auto-generated method stub
		return null;
	}

}
