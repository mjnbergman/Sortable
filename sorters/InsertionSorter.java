package com.sorters;

import com.sortingauxiliary.Sortable;

import java.util.ArrayList;

import com.sortingauxiliary.SimpleSortingInput;

public class InsertionSorter<T extends Number & Comparable<T>> extends SortingAlgorithm{
	
	private int index;

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
	public ArrayList<SimpleSortingInput<T>> getData(){
		return this.inputs;
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
	
	private void swap(int i1, int i2) {
		SimpleSortingInput<T> temp = (SimpleSortingInput<T>)this.inputs.get(i1);
		this.inputs.remove(i1);
		this.inputs.add(i1, this.inputs.get(i2));
		this.inputs.remove(i2);
		this.inputs.add(i2, temp);
	}

	@Override
	public ArrayList<SimpleSortingInput<T>> sortStep() {
		int j = this.index;
		while(j > 0 && ((SimpleSortingInput<T>)(this.inputs.get(j - 1))).compareTo((SimpleSortingInput<T>)this.inputs.get(j)) == 1) {
			this.swap(j, j - 1);
			j--;
		}
		this.index++;
		return new ArrayList<SimpleSortingInput<T>>(this.inputs);
	}

	@Override
	public ArrayList<ArrayList<SimpleSortingInput<T>>> sort() {
		ArrayList<ArrayList<SimpleSortingInput<T>>> stepBuffer = new ArrayList<>();
		while(!this.isSorted()) {
			printDataset();
/*			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			stepBuffer.add(this.sortStep());
		}
		printDataset();
		return stepBuffer;
	}
	
	public void printDataset() {
		for(Object i : this.inputs) {
			SimpleSortingInput<T> t = (SimpleSortingInput<T>) i;
			System.out.print(t.getValue() + ", ");
		}
		System.out.println("");
	}

}
