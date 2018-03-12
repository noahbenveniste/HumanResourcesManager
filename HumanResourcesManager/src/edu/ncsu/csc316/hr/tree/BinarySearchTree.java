package edu.ncsu.csc316.hr.tree;

/**
 * 
 * @author Noah Benveniste
 *
 * @param <E>
 */
public class BinarySearchTree<S extends Comparable<S>, T extends Comparable<T>> {
	
	/** */
	private Node<S, T> root;
	/** */
	private int size;
	/** */
	private S[] inOrderKeys;
	/** */
	private int i;

	/**
	 * 
	 */
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * 
	 * @param k
	 * @return
	 */
	public T lookUp(S k) {
		if (isEmpty()) {
			throw new IllegalArgumentException("BST is empty");
		} else if (this.size == 1) {
			return root.value;
		} else {
			return root.lookUpHelper(k);
		}
	}

	/**
	 * 
	 */
	public void insert(S k, T v) {
		// If the tree is empty, create a new root.
		if (root == null) {
			root = new Node<S, T>(k, v);
			root.isRoot = true;
			root.isLeaf = true;
			root.isInternal = false;
			size++;
		} else {
			try {
				root.insertHelper(k, v); // Otherwise, call method that handles recursion
				size++;
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}
	
	/**
	 * Performs an in-order traversal of the BST, returning a 1D
	 * array keys in sorted order.
	 * 
	 * @return The keys in the tree in sorted order in an array.
	 */
	@SuppressWarnings("unchecked")
	public S[] inOrder() {
		inOrderKeys = (S[]) new Comparable[size];
		i = 0;
		return inOrderHelper(root);
	}
	
	/**
	 * 
	 * @return
	 */
	private S[] inOrderHelper(Node<S, T> n) {
		if (n == null) {
			// Do nothing
		} else {
			inOrderHelper(n.left);
			visit(n);
			inOrderHelper(n.right);
		}
		return this.inOrderKeys;
	}
	
	/**
	 * 
	 * @param n
	 */
	private void visit(Node<S, T> n) {
		inOrderKeys[i] = n.key;
		i++;
	}
	

	/**
	 * 
	 */
	public T remove(S k) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * 
	 * @return
	 */
	public boolean isInternal() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 *
	 * @param <E>
	 */
	private class Node<S extends Comparable<S>, T extends Comparable<T>> {
		
		/** */
		public Node<S, T> left;
		/** */
		public Node<S, T> right;
		/** */
		public S key;
		/** */
		public T value;
		/** */
		private boolean isInternal;
		/** */
		private boolean isLeaf;
		/** */
		private boolean isRoot;
		
		/**
		 * 
		 * @param data
		 * @param left
		 * @param right
		 */
		public Node(S k, T v, Node<S, T> left, Node<S, T> right) {
			
			this.key = k;
			this.value = v;
			this.left = left;
			this.right = right;
		}
		
		/**
		 * 
		 * @param data
		 */
		public Node(S k, T v) {
			this(k, v, null, null);
		}
		
		/**
		 * 
		 * @param k
		 * @param v
		 */
		private void insertHelper(S k, T v) {
			if (this.key.compareTo(k) > 0) {
				// Base case check
				if (this.left == null) {
					this.left = new Node<S, T>(k, v);
					return;
				} else {
					this.left.insertHelper(k, v);
				}
			} else if (this.key.compareTo(k) < 0) {
				// Base case check
				if (this.right == null) {
					this.right = new Node<S, T>(k, v);
				} else {
					this.right.insertHelper(k, v);
					return;
				}
			} else {
				throw new IllegalArgumentException("Cannot insert duplicate elements into BST");
			}
		}
		
		/**
		 * 
		 * @param k
		 * @return
		 */
		private T lookUpHelper(S k) {
			if (this.key.equals(k)) {
				return this.value;
			} else if (this.key.compareTo(k) > 0) {
				if (this.left == null) {
					throw new IllegalArgumentException("Key is not contained in the BST");
				} else {
					return left.lookUpHelper(k);
				}
			} else {
				if (this.right == null) {
					throw new IllegalArgumentException("Key is not contained in the BST");
				} else {
					return right.lookUpHelper(k);
				}
			}
		}
		
	}

}