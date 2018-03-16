package edu.ncsu.csc316.hr.tree;

import edu.ncsu.csc316.hr.list.ArrayList;

/**
 * 
 * @author Noah Benveniste
 */
public class GeneralTree<E extends Comparable<E>> {
	
	/** */
	private Node<E> root;
	/** */
	private int size;

	/**
	 * 
	 */
	public GeneralTree(E rootData) {
		root = new Node<E>(rootData);
		size = 1;
	}
	
	/**
	 * 
	 */
	public void incSize() {
		this.size++;
	}
	
	/**
	 * 
	 */
	public Node<E> getRoot() {
		return this.root;
	}
	
	/**
	 * 
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	@SuppressWarnings("hiding")
	public class Node<E> implements Comparable<Node<E>>{
		/** */
		private E data;
		/** */
		private ArrayList<Node<E>> children;
		
		/**
		 * 
		 * @param data
		 */
		public Node(E data) {
			this.data = data;
			this.children = new ArrayList<Node<E>>();
		}
		
		/**
		 * 
		 * @return
		 */
		public ArrayList<Node<E>> getChildren() {
			return this.children;
		}
		
		/**
		 * 
		 * @return
		 */
		public E getData() {
			return this.data;
		}

		/**
		 * 
		 */
		@Override
		public int compareTo(Node<E> o) {
			return 0;
		}
	}
	
}
