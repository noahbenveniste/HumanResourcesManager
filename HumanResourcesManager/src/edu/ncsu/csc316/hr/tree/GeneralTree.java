package edu.ncsu.csc316.hr.tree;

import edu.ncsu.csc316.hr.list.ArrayList;

/**
 * Class for a general tree. Each node can have an arbitrary number of child nodes.
 * Nodes aren't maintained in sorted order.
 * 
 * @author Noah Benveniste
 * 
 * @param <E> generic type for node data
 */
public class GeneralTree<E extends Comparable<E>> {
	
	/** The root of the tree */
	private Node<E> root;
	/** The number of elements in the tree */
	private int size;

	/**
	 * Constructs an empty general tree given a piece of root data
	 * 
	 * @param rootData the data to be contained in the root
	 */
	public GeneralTree(E rootData) {
		root = new Node<E>(rootData);
		size = 1;
	}
	
	/**
	 * Increments the tree's size field
	 */
	public void incSize() {
		this.size++;
	}
	
	/**
	 * Gets the tree's root
	 * 
	 * @return the root
	 */
	public Node<E> getRoot() {
		return this.root;
	}
	
	/**
	 * Gets the number of elements in the tree
	 * 
	 * @return the number of elements
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Underlying node structure for the general tree. Can have
	 * an arbitrary number of children.
	 * 
	 * @author Noah Benveniste
	 * 
	 * @param <E> generic type for node data
	 */
	@SuppressWarnings("hiding")
	public class Node<E> implements Comparable<Node<E>> {
		/** The node's data */
		private E data;
		/** List of direct children */
		private ArrayList<Node<E>> children;
		
		/**
		 * Constructs a node given a piece of data to be contained
		 * 
		 * @param data the node's data
		 */
		public Node(E data) {
			this.data = data;
			this.children = new ArrayList<Node<E>>();
		}
		
		/**
		 * Gets the list of direct children to the node
		 * 
		 * @return the list of direct children
		 */
		public ArrayList<Node<E>> getChildren() {
			return this.children;
		}
		
		/**
		 * Gets the data contained in the node
		 * 
		 * @return the data
		 */
		public E getData() {
			return this.data;
		}

		/**
		 * Method used to compare two nodes (currently unused)
		 * 
		 * @param o the other node to compare to
		 * 
		 * @return 0
		 */
		@Override
		public int compareTo(Node<E> o) {
			return 0;
		}
	}
	
}
