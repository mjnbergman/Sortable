package com.sortingauxiliary;

/**
 * 
 * @author Maiko Bergman
 * @date 31-3-2019
 * 
 * An Abstract Data Type representing sortable inputs. These sortables are used as inputs for the sorting algorithms. They at the very least
 * should be comparable, gettable and settable.
 *
 * @param <T>
 */
public interface Sortable<T extends Comparable<T>> extends Comparable<Sortable<T>> {
	
	public T getValue();
	void setInputs(T inputs);
	@Override
	public int compareTo(Sortable<T> o);

}
