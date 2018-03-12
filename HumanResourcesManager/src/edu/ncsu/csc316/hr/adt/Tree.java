package edu.ncsu.csc316.hr.adt;

/**
 * 
 * @author Noah Benveniste
 *
 * @param <E>
 */
public interface Tree<E> {
	
	/**
	 * 
	 * @param k
	 * @return
	 */
	public E lookUp(E k);
	
	/**
	 * 
	 * @param k
	 * @param v
	 */
	public void insert(E k, E v);
	
	/**
	 * 
	 * @param k
	 * @return
	 */
	public E remove(E k);
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @return
	 */
	public int size();
	
	/**
	 * 
	 * @return
	 */
	public boolean isRoot();
	
	/**
	 * 
	 * @return
	 */
	public boolean isLeaf();
	
	/**
	 * 
	 * @return
	 */
	public boolean isInternal();
}