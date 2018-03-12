package edu.ncsu.csc316.hr.list;

import java.util.Random;

/**
 * A custom implemented array list class that is able to adjust size
 * automatically, elements can be added to the front, end or middle. Duplicate
 * elements in the array are automatically rejected form the ArrayList.
 * 
 * @param <E>
 *            Specifies that the ArrayList can contain any object type
 *            
 * @author Noah Benveniste, further edits made by Noah Benveniste for HumanResourcesManager
 * @author Kevin Hildner
 * 
 */
public class ArrayList<E extends Comparable<? super E>> {
	/**
	 * The array's current size, based on the number of non-null elements present
	 */
	private static final int INIT_SIZE = 10;
	/** The underlying array for the ArrayList */
	private E[] list;
	/** The number of elements that has been placed in the array */
	private int size;
	/** The total capacity of the underlying array */
	private int capacity;

	/**
	 * Constructs an empty ArrayList of size zero with an initial capacity of ten
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		this.list = (E[]) new Comparable[INIT_SIZE];
		this.capacity = list.length;
	}

	/**
	 * Adds a desired element to the ArrayList at a specified index, right-shifting
	 * necessary values
	 * 
	 * @param idx
	 *            the zero-based index to add the new element at
	 * @param element
	 *            the element to add to the ArrayList
	 * @throws NullPointerException
	 *             if the added element is null
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than zero or greater than the ArrayList's
	 *             size
	 * @throws IllegalArgumentException
	 *             if the added element is a duplicate of an element already in the
	 *             list
	 */
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null elements");
		}
		if (idx < 0 || idx > this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the accepatble range");
		}
		if (this.size() == this.capacity) { // Grow the array if list is full
			this.growArray();
		}
		if (idx == this.size()) { // Adding an element to the end of the list
			list[idx] = element;
		} else { // Add an element to the front or middle of the list
			// Right shift all values before the index to insert the new element
			for (int i = this.size; i > idx; i--) {
				list[i] = list[i - 1];
			}
			// Add the element to the desired index
			list[idx] = element;
		}
		// Increment the size of the ArrayList
		this.size++;
	}
	
	/**
	 * Appends an element to the end of the list
	 * @param element
	 */
	public void add(E element) {
		if (this.size() == this.capacity) { // Grow the array if list is full
			this.growArray();
		}
		list[size] = element;
		this.size++;
	}

	/**
	 * Used to grow the array if size == capacity; Doubles the capacity by default
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		// Update capacity
		this.capacity *= 2;
		// Create a new Comparable array of double the capacity of the current array
		E[] temp = (E[]) new Comparable[this.capacity];
		// Assign the elements from the old array to the same index in the new array
		for (int i = 0; i < this.size(); i++) {
			temp[i] = this.list[i];
		}
		// Assign the new array to the list field
		this.list = temp;
	}

	/**
	 * Removes an element from the ArrayList at a specified index, left-shifting all
	 * remaining elements and returning the removed element
	 * 
	 * @param idx
	 *            indicates the index of the element to remove from the ArrayList
	 * @return the removed element
	 * @throws IndexOutOfBoundsException
	 *             if the specified index is out of bounds
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the accepatble range");
		}
		// Get the element at the specified index
		E temp = list[idx];
		for (int i = idx; i < this.size() - 1; i++) {
			list[i] = list[i + 1];
		}
		// Set the repeated element at the end of the list to null
		list[this.size() - 1] = null;
		// Decrement the size
		this.size--;
		// Return the removed element
		return temp;
	}

	/**
	 * Overwrites the element at a specified index with a new element
	 * 
	 * @param idx
	 *            the index in the array that is being over written
	 * @param element
	 *            the new value/object for that position in the array
	 * @return the old element at the specified index
	 * @throws NullPointerException
	 *             if the added element is null
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than zero or greater than the ArrayList's
	 *             size
	 * @throws IllegalArgumentException
	 *             if the added element is a duplicate of an element already in the
	 *             list
	 */
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException("Cannot set null elements");
		}
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the acceptable range");
		}
		E temp = list[idx];
		list[idx] = element;
		return temp;
	}

	/**
	 * Gets an element at a specified index
	 * 
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException
	 *             if the specified index is out of bounds
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the acceptable range");
		}
		return this.list[idx];
	}

	/**
	 * Getter for the size field
	 * 
	 * @return the size of the ArrayList i.e. how many elements it contains
	 */
	public int size() {
		return this.size;
	}
	
	/**
     * Sorts the list in ascending order
     */
    public void quickSort()  {
        quickSortHelper(0, this.size - 1);
    }
    
    /**
     * A recursive quick sort algorithm.
     * Source of algorithm explanation: https://www.cp.eng.chula.ac.th/~vishnu/datastructure/QuickSort.pdf
     * @param low the lowest index of the subarray
     * @param high the highest index of the subarray
     */
    private void quickSortHelper(int low, int high) {
        // Base case 1: sub array with fewer than two elements
        if (high <= low) {
            return;
        // Base case 2: sub array with 2 elements
        } else if (high - (low + 1) == 0) {
            if (list[low].compareTo(list[high]) > 0) {
                E temp = list[low];
                list[low] = list[high];
                list[high] = temp;
            }
            return;
        }
        
        // Bounds for generating random pivot index
        int min = low + 1;
        int max = high;
        
        // Randomly generate a pivot index
        Random rand = new Random();
        int pivotIdx = rand.nextInt(max - min) + min;
        
        // Get the pivot value
        E pivot = list[pivotIdx];
        
        // Swap the pivot value with the first element in the array
        list[pivotIdx] = list[low];
        list[low] = pivot;
        
        // first points to the first value that is greater than the pivot. After all comparisons are done,
        // the value before first will correspond to a value less than the pivot
        int first = low + 1;
        for (int i = low + 1; i <= high; i++) {
            if (list[i].compareTo(pivot) < 0) {
                // Swap list[i] with list[first]
                E temp = list[i];
                list[i] = list[first];
                list[first] = temp;
                // Increment first
                first++;
            }
        }
        
        // Swap pivot with value before first
        list[low] = list[first - 1];
        list[first - 1] = pivot;
        
        // Recursive calls
        // pivot is located at index first - 1
        quickSortHelper(low, first - 1); // subarray left of and including the pivot
        quickSortHelper(first, high); // subarray right of the pivot
    }
    
}
