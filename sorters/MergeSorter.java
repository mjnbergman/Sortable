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
	private int iteration = 1;
	private int stepSize = 2;
	private ArrayList<SimpleSortingInput<T>> aux1;

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
		for(int i = 1; i < this.inputs.size(); i++) {
			if(((SimpleSortingInput<T>)this.inputs.get(i)).compareTo((SimpleSortingInput<T>)this.inputs.get(i - 1)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Own implementation of MergeSort. Semi in-place (only uses one auxiliary array, always the same size of the main array).
	 * It's also stable, and does not rely on recursion.
	 */
	@Override
	public ArrayList<SimpleSortingInput<T>> sortStep() {
		
		this.aux1 = new ArrayList<>();
		
		System.out.println("Looping with stepsize: " + this.stepSize);
		
		for(int i = 0; i + this.stepSize <= this.inputs.size(); i += this.stepSize) {
			
			int secondCounter = (int)(i + Math.ceil(this.stepSize/2));
			System.out.println("De second counter is: " + secondCounter);
			
			int additionIndex = i;
			
			for(int j = i; j < i + Math.ceil(this.stepSize/2); j++) {
				
				System.out.println("In de tweede loop op: " + j);
				if(secondCounter >= (int)(i + Math.ceil(this.stepSize))) {
					this.aux1.add(additionIndex, (SimpleSortingInput<T>)this.inputs.get(j));
					additionIndex++;
					System.out.println(((SimpleSortingInput<T>)this.inputs.get(j)).getValue() + ", ");
					continue;
				}
				
				while(((SimpleSortingInput<T>)this.inputs.get(secondCounter)).compareTo((SimpleSortingInput<T>)this.inputs.get(j)) == -1) {
					this.aux1.add(additionIndex, (SimpleSortingInput<T>)this.inputs.get(secondCounter));
					additionIndex++;
					System.out.println(((SimpleSortingInput<T>)this.inputs.get(secondCounter)).getValue() + ", ");
					secondCounter++;
					if(secondCounter >= (int)(i + Math.ceil(this.stepSize))) {
						break;
					}
				}
				
				this.aux1.add(additionIndex, (SimpleSortingInput<T>)this.inputs.get(j));
				additionIndex++;
	//			this.inputs.remove(j);
				System.out.println(((SimpleSortingInput<T>)this.inputs.get(j)).getValue() + ", ");
				
			}
			System.out.println("Done met de main loop, de secondCounter = " + secondCounter + " en mag: " + (int)(i + Math.ceil(this.stepSize)) + " zijn!");
			while(secondCounter < (int)(i + Math.ceil(this.stepSize))) {
				this.aux1.add(secondCounter, (SimpleSortingInput<T>)this.inputs.get(secondCounter));
				System.out.println(((SimpleSortingInput<T>)this.inputs.get(secondCounter)).getValue() + ", ");
				secondCounter++;
			}
		}
		
		this.inputs = new ArrayList<>(this.aux1);
		
		System.out.println("Found the following complete sorted set: ");
		
		for(int i = 0; i < this.inputs.size(); i++) {
			System.out.print(((SimpleSortingInput<T>)(this.inputs.get(i))).getValue() + ", ");
		}
		
		this.iteration++;
		this.stepSize *= 2;
		
		
		return this.inputs;
	}

	@Override
	public ArrayList<ArrayList<SimpleSortingInput<T>>> sort() {
		
		this.aux1 = new ArrayList<>();
		
		ArrayList<ArrayList<SimpleSortingInput<T>>> outputSteps = new ArrayList<>();
		
		while(!this.isSorted()) {
			System.out.println("Looping through mergesort!");
		/*	int splitPoint = (int)(this.inputs.size()/(Math.pow(2, this.iteration)));
			ArrayList<SimpleSortingInput<T>> s1 = this.sortStep();
			ArrayList<SimpleSortingInput<T>> s2 = this.sortStep();*/
			outputSteps.add(this.sortStep()); 
			this.iteration++;
			this.stepSize *= 2;
		}
		
		return outputSteps;
	}

	@Override
	public ArrayList<SimpleSortingInput<T>> getData() {
		return this.inputs;
	}
	@Override
	public ArrayList<ArrayList<Integer>> getHighlights() {
		return this.highlights;
	}

}
