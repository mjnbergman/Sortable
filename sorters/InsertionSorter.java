package com.sorters;

import com.sortingauxiliary.Sortable;
import com.sortingauxiliary.SimpleSortingInput;

public class InsertionSorter<T extends Comparable<T>> extends SortingAlgorithm{

	public InsertionSorter(SimpleSortingInput<T>[] inputs) {
		super(inputs);
		// TODO Auto-generated constructor stub
	}
	public InsertionSorter(T[] inputs) {
		super();
		this.inputs = new SimpleSortingInput[inputs.length];
		for(int i = 0; i < this.inputs.length; i++) {
			this.inputs[i] = new SimpleSortingInput(inputs[i]);
		}
	}

	@Override
	public boolean isSorted() {
		for(int i = 1; i < this.inputs.length; i++) {
			if(this.inputs[i].compareTo(this.inputs[i - 1]) == -1) {
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
	public SimpleSortingInput<T>[] sort() {
		// TODO Auto-generated method stub
		return null;
	}

}
