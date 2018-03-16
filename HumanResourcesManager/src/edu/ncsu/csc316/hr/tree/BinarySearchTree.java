package edu.ncsu.csc316.hr.tree;

/**
 * A binary search tree class. Utilizes a linked node structure to achieve
 * best case O(logn) insert/lookup/remove operations. Can be used to implement
 * ordered dictionaries.
 * 
 * @author Noah Benveniste
 *
 * @param <S> generic type for key
 * @param <T> generic type for value
 */
public class BinarySearchTree<S extends Comparable<S>, T extends Comparable<T>> {
	
	/** Root node for the tree */
	private Node<S, T> root;
	/** The number of elements in the tree */
	private int size;
	/** Used to store the keys of the tree after an in-order traversal */
	private S[] inOrderKeys;
	/** Used as a counter variable for the recursive in-order traversal code */
	private int i;

	/**
	 * Constructs an empty BST with a null root
	 */
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Given a key, returns the corresponding value in the tree if it is found
	 * 
	 * @param k the input key
	 * 
	 * @return the value corresponding to the key/value pair
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
	 * Inserts a key/value pair into the tree in sorted order
	 * 
	 * @param k the key
	 * @param v the value
	 */
	public void insert(S k, T v) {
		// If the tree is empty, create a new root.
		if (root == null) {
			root = new Node<S, T>(k, v);
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
	 * Recursive helper method for the in order traversal
	 * 
	 * @return The keys in the tree in sorted order in an array
	 */
	private S[] inOrderHelper(Node<S, T> n) {
		if (n != null) {
			inOrderHelper(n.left);
			visit(n);
			inOrderHelper(n.right);
		}
		return this.inOrderKeys;
	}
	
	/**
	 * Used by the in-order traversal code; adds each element to the
	 * out array after being visited
	 * 
	 * @param n the node in the tree being examined
	 */
	private void visit(Node<S, T> n) {
		inOrderKeys[i] = n.key;
		i++;
	}
	

	/**
	 * Given a key, removes the key/value pair from the tree if they exist
	 * and returns the value.
	 * 
	 * @param k the key to search for removal
	 * 
	 * @return the value corresponding to the input key
	 */
	public T remove(S k) {
		
		/* REMOVE ALGORITHM */
		// 1. Find the node corresponding to the searched key
		// 2. Find the largest element in the left subtree of the node
		// 3. Replace the data in the node with the data in the largest element of the left subtree
		// 4. Delete the node corresponding to the largest element in the left subtree
		// 5. Decrement size
		// Special cases: A key is not found (throw an exception), the node containing k is a leaf or only has one subtree
		
		// Tree is empty
		if (isEmpty()) {
			throw new IllegalArgumentException("BST is empty");
		// Tree only contains root
		} else if (this.size == 1) {
			T temp = root.value;
			root = null;
			size--;
			return temp;
		// Removing the root
		} else if (k.equals(root.key)) {
			T temp = root.value;
			// If the root doesn't have a left subtree
			if (root.left == null) {
				root = root.right;
				size--;
				return temp;
			}
			Node<S, T> curr = root.left;
			Node<S, T> max = getMaxNode(curr);
			
			// Grab the element, save it
			T replaceData = max.value;
			S replaceKey = max.key;
			
			// Set this node to contain the saved data
			root.value = replaceData;
			root.key = replaceKey;
			
			// Remove the duplicate leaf node
			curr.removeHelper(replaceKey, root);
			
			// Return out
			return temp;
		} else {
			return root.removeHelper(k, null);
		}
	}
	
	/**
	 * Helper method for remove functionality. Gets the largest node
	 * in the passed subtree.
	 * 
	 * @param curr the current node being recursively examined
	 * 
	 * @return the largest (right-most) node in the subtree
	 */
	public Node<S, T> getMaxNode(Node<S, T> curr) {
		if (curr.right == null) {
			return curr;
		} else {
			return getMaxNode(curr.right);
		}
	}

	/**
	 * Checks if the tree has an elements
	 * 
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets the number of elements in the tree
	 * 
	 * @return the number of elements in the tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * The underlying node objects that comprise the BST. Also several
	 * recursive helper functions for insert, remove and lookup.
	 * 
	 * @author Noah Benveniste
	 *
	 * @param <S> generic type for key
	 * @param <T> generic type for value
	 */
	@SuppressWarnings("hiding")
	private class Node<S extends Comparable<S>, T extends Comparable<T>> {
		
		/** Left child of the node */
		public Node<S, T> left;
		/** Right child of the node */
		public Node<S, T> right;
		/** Data key */
		public S key;
		/** Data value */
		public T value;
		
		/**
		 * Constructs a node given a key/value pair, a left child node
		 * and a right child node
		 * 
		 * @param k the key
		 * @param v the value
		 * @param left the left child
		 * @param right the right child
		 */
		public Node(S k, T v, Node<S, T> left, Node<S, T> right) {
			
			this.key = k;
			this.value = v;
			this.left = left;
			this.right = right;
		}
		
		/**
		 * Constructs a node given only a key/value pair with no children
		 * (i.e. leaf node)
		 * 
		 * @param k the key
		 * @param v the value
		 */
		public Node(S k, T v) {
			this(k, v, null, null);
		}
		
		/**
		 * Recursive helper method for insert functionality
		 * 
		 * @param k the key to insert
		 * @param v the corresponding value to insert
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
		 * Recursive helper method for lookup functionality
		 * 
		 * @param k the key to search
		 * 
		 * @return the corresponding value if the key is found in the tree
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
		
		/**
		 * Recursive helper method for remove functionality
		 * 
		 * @param k the key corresponding to the key/value pair to be removed
		 * 
		 * @return the value associated with the removed k/v pair
		 */
		private T removeHelper(S k, Node<S, T> parent) {
			if (this.key.equals(k)) {
				// Save the data to return
				T temp = this.value;
				// If the node to delete is a leaf or doesn't have a left subtree
				if ((this.left == null && this.right == null) || this.left == null) {
					if (this.equals(parent.left)) {
						// Check that the node doesn't have a right sub tree
						if (this.right != null) {
							parent.left = this.right;
						} else {
							parent.left = null;
						}
						size--;
						return temp;
					} else {
						// Check that the node doesn't have a right sub tree
						if (this.right != null) {
							parent.right = this.right;
						} else {
							parent.right = null;
						}
						size--;
						return temp;
					}
				// The node to delete has a left subtree to search
				} else {
					// Iterate through the left subtree to the right-most element
					Node<S, T> curr = this.left;
					if (curr.right == null && this.right == null) {
						if (parent.left != null && parent.left.equals(this)) {
							parent.left = curr;
						} else {
							parent.right = curr;
						}
						size--;
						return temp;
					} else {
						Node<S, T> max = getMaxNode(curr);
						
						// Grab the element, save it
						T replaceData = max.value;
						S replaceKey = max.key;
						
						// Set this node to contain the saved data
						this.value = replaceData;
						this.key = replaceKey;
						
						// Remove the duplicate leaf node
						curr.removeHelper(replaceKey, this);
						
						// Return out
						return temp;
					}
				}
			} else if (this.key.compareTo(k) > 0) {
				if (this.left == null) {
					throw new IllegalArgumentException("Key is not contained in the BST");
				} else {
					return left.removeHelper(k, this);
				}
			} else {
				if (this.right == null) {
					throw new IllegalArgumentException("Key is not contained in the BST");
				} else {
					return right.removeHelper(k, this);
				}
			}
		}
		
		/**
		 * Helper method for remove functionality. Gets the largest node
		 * in the passed subtree.
		 * 
		 * @return the largest (right-most) node in the subtree
		 */
		public Node<S, T> getMaxNode(Node<S, T> curr) {
			if (curr.right == null) {
				return curr;
			} else {
				return getMaxNode(curr.right);
			}
		}
		
	}

}