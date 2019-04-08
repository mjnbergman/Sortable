package com.sortingauxiliary;

/**
 * 
 * @author Maiko Bergman
 * @date 31-3-2019
 * 
 * An implementation of the abstract data type Sortable. This is used for the most basic functionality a sorting input could have.
 * It only provides methods for setting, getting and comparing the generic input type.
 *
 * @param <T>
 */
public class SimpleSortingInput<T extends Number & Comparable<T>> implements Sortable<T>{

	private T inputs;
	
	public SimpleSortingInput(T inputs) {
		this.inputs = inputs;
	}
	
	public T getValue() {
		return this.inputs;
	}
	public void setInputs(T inputs) {
		this.inputs = inputs;
	}

	@Override
	public int compareTo(Sortable<T> o) {
		return (this.inputs).compareTo(o.getValue());
	}
}
