package edu.ncsu.csc316.hr.tree;

/**
 * 
 * @author Noah Benveniste
 *
 * @param <S, T>
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
	public Node<S, T> getMaxNode(Node<S, T> curr) {
		if (curr.right == null) {
			return curr;
		} else {
			return getMaxNode(curr.right);
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 *
	 * @param <E>
	 */
	@SuppressWarnings("hiding")
	private class Node<S extends Comparable<S>, T extends Comparable<T>> {
		
		/** */
		public Node<S, T> left;
		/** */
		public Node<S, T> right;
		/** */
		public S key;
		/** */
		public T value;
		
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
		
		/**
		 * 
		 * @param k
		 * @return
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
						if (parent.left.equals(this)) {
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
		 * 
		 * @return
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