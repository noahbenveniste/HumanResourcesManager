package edu.ncsu.csc316.hr.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BinarySearchTree functionality
 * 
 * @author Noah Benveniste
 */
public class BinarySearchTreeTest {

	/** BST to be used throughout tests */
	private BinarySearchTree<Integer, String> binTree;
	/** List of keys to be used for tests */
	private int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	/** List of values to be used for tests */
	private String[] values = {"ape", "bird", "cat", "dog", "fox", "goat", "hen", "pig", "rat"};
	
	/**
	 * Initializes an empty BST for testing
	 * 
	 * @throws Exception if binary tree cannot be initialized
	 */
	@Before
	public void setUp() throws Exception {
		binTree = new BinarySearchTree<Integer, String>();
	}

	/**
	 * Tests insert functionality for BST
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testInsert() {
		assertEquals(0, binTree.size());
		binTree.insert(5, "fox");
		binTree.insert(3, "cat");
		binTree.insert(2, "bird");
		binTree.insert(1, "ape");
		binTree.insert(4, "dog");
		assertEquals(5, binTree.size());
		Comparable[] actual1 = binTree.inOrder();
		for (int i = 0; i < actual1.length; i++) {
			assertEquals(keys[i], actual1[i]);
		}
		binTree.insert(7, "hen");
		binTree.insert(6, "goat");
		binTree.insert(8, "pig");
		binTree.insert(9, "rat");
		assertEquals(9, binTree.size());
		Comparable[] actual2 = binTree.inOrder();
		for (int i = 0; i < actual2.length; i++) {
			assertEquals(keys[i], actual2[i]);
		}
		
		// Try inserting a duplicate key
		try {
			binTree.insert(7, "lizard");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot insert duplicate elements into BST", e.getMessage());
		}
	}
	
	/**
	 * Tests look up functionality for BST
	 */
	@Test
	public void testLookUp() {
		assertEquals(0, binTree.size());
		// Try looking up in empty BST
		try {
			binTree.lookUp(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("BST is empty", e.getMessage());
		}
		
		binTree.insert(5, "fox");
		binTree.insert(3, "cat");
		binTree.insert(2, "bird");
		binTree.insert(1, "ape");
		binTree.insert(4, "dog");
		binTree.insert(7, "hen");
		binTree.insert(6, "goat");
		binTree.insert(8, "pig");
		binTree.insert(9, "rat");
		for (int i = 0; i < values.length; i++) {
			assertEquals(values[i], binTree.lookUp(i + 1));
		}
		
		// Try looking up keys not in the tree
		try {
			binTree.lookUp(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Key is not contained in the BST", e.getMessage());
		}
		
		try {
			binTree.lookUp(100);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Key is not contained in the BST", e.getMessage());
		}
	}
	
	/**
	 * Tests remove functionality for BST
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRemove() {
		// Insert to the tree
		binTree.insert(5, "fox");
		binTree.insert(3, "cat");
		binTree.insert(2, "bird");
		binTree.insert(1, "ape");
		binTree.insert(4, "dog");
		binTree.insert(7, "hen");
		binTree.insert(6, "goat");
		binTree.insert(8, "pig");
		binTree.insert(9, "rat");
		
		// Remove root
		assertEquals("fox", binTree.remove(5));
		assertEquals(8, binTree.size());
		String[] r1 = {"ape", "bird", "cat", "dog", null, "goat", "hen", "pig", "rat"};
		for (int i = 0; i < r1.length; i++) {
			if (r1[i] != null) {
				assertEquals(r1[i], binTree.lookUp(i + 1));
			}
		}
		
		// Remove a middle value
		assertEquals("bird", binTree.remove(2));
		assertEquals(7, binTree.size());
		String[] r2 = {"ape", null, "cat", "dog", null, "goat", "hen", "pig", "rat"};
		for (int i = 0; i < r2.length; i++) {
			if (r2[i] != null) {
				assertEquals(r2[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("rat", binTree.remove(9));
		assertEquals(6, binTree.size());
		String[] r3 = {"ape", null, "cat", "dog", null, "goat", "hen", "pig", null};
		for (int i = 0; i < r3.length; i++) {
			if (r3[i] != null) {
				assertEquals(r3[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("pig", binTree.remove(8));
		assertEquals(5, binTree.size());
		String[] r4 = {"ape", null, "cat", "dog", null, "goat", "hen", null, null};
		for (int i = 0; i < r4.length; i++) {
			if (r4[i] != null) {
				assertEquals(r4[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("hen", binTree.remove(7));
		assertEquals(4, binTree.size());
		String[] r5 = {"ape", null, "cat", "dog", null, "goat", null, null, null};
		for (int i = 0; i < r5.length; i++) {
			if (r5[i] != null) {
				assertEquals(r5[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("goat", binTree.remove(6));
		assertEquals(3, binTree.size());
		String[] r6 = {"ape", null, "cat", "dog", null, null, null, null, null};
		for (int i = 0; i < r6.length; i++) {
			if (r6[i] != null) {
				assertEquals(r6[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("ape", binTree.remove(1));
		assertEquals(2, binTree.size());
		String[] r7 = {null, null, "cat", "dog", null, null, null, null, null};
		for (int i = 0; i < r7.length; i++) {
			if (r7[i] != null) {
				assertEquals(r7[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("dog", binTree.remove(4));
		assertEquals(1, binTree.size());
		String[] r8 = {null, null, "cat", null, null, null, null, null, null};
		for (int i = 0; i < r8.length; i++) {
			if (r8[i] != null) {
				assertEquals(r8[i], binTree.lookUp(i + 1));
			}
		}
		
		assertEquals("cat", binTree.remove(3));
		assertEquals(0, binTree.size());
		
		/* Test a random sequence of inserts and removals */
		
		binTree = null;
		binTree = new BinarySearchTree<Integer, String>();
		String v = "";
		
		// Try removing from an empty BST
		try {
			binTree.remove(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("BST is empty", e.getMessage());
		}
		
		binTree.insert(13, v);
		assertEquals(1, binTree.size());
		assertTrue(validate(new Comparable[] {13}, binTree.inOrder()));
		
		binTree.insert(5, v);
		assertEquals(2, binTree.size());
		assertTrue(validate(new Comparable[] {5, 13}, binTree.inOrder()));
		
		binTree.insert(18, v);
		assertEquals(3, binTree.size());
		assertTrue(validate(new Comparable[] {5, 13, 18}, binTree.inOrder()));
		
		binTree.remove(13);
		assertEquals(2, binTree.size());
		assertTrue(validate(new Comparable[] {5, 18}, binTree.inOrder()));
		
		binTree.insert(4, v);
		assertEquals(3, binTree.size());
		assertTrue(validate(new Comparable[] {4, 5, 18}, binTree.inOrder()));
		
		binTree.remove(18);
		assertEquals(2, binTree.size());
		assertTrue(validate(new Comparable[] {4, 5}, binTree.inOrder()));
		
		binTree.remove(4);
		assertEquals(1, binTree.size());
		assertTrue(validate(new Comparable[] {5}, binTree.inOrder()));
		
		binTree.insert(3, v);
		assertEquals(2, binTree.size());
		assertTrue(validate(new Comparable[] {3, 5}, binTree.inOrder()));
		
		binTree.remove(5);
		assertEquals(1, binTree.size());
		assertTrue(validate(new Comparable[] {3}, binTree.inOrder()));
		
		binTree.insert(10, v);
		assertEquals(2, binTree.size());
		assertTrue(validate(new Comparable[] {3, 10}, binTree.inOrder()));
		
		binTree.remove(3);
		assertEquals(1, binTree.size());
		assertTrue(validate(new Comparable[] {10}, binTree.inOrder()));
		
		binTree.insert(5, v);
		binTree.insert(13, v);
		binTree.insert(1, v);
		binTree.insert(7, v);
		binTree.insert(12, v);
		binTree.insert(20, v);
		binTree.insert(0, v);
		binTree.insert(2, v);
		binTree.insert(6, v);
		binTree.insert(8, v);
		binTree.insert(11, v);
		binTree.insert(9, v);
		binTree.insert(16, v);
		binTree.insert(18, v);
		binTree.insert(14, v);
		binTree.insert(19, v);
		binTree.insert(3, v);
		binTree.insert(4, v);
		binTree.insert(15, v);
		binTree.insert(17, v);
		
		assertEquals(21, binTree.size());
		assertTrue(validate(new Comparable[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, binTree.inOrder()));
		
		// Try removing keys not in the tree
		try {
			binTree.remove(-1);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Key is not contained in the BST", e.getMessage());
		}
		
		try {
			binTree.remove(100);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Key is not contained in the BST", e.getMessage());
		}
		
		binTree.remove(20);
		assertEquals(20, binTree.size());
		assertTrue(validate(new Comparable[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(0);
		assertEquals(19, binTree.size());
		assertTrue(validate(new Comparable[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, binTree.inOrder()));
		
		binTree.insert(-5, v);
		binTree.insert(-3, v);
		assertEquals(21, binTree.size());
		assertTrue(validate(new Comparable[] {-5, -3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(-5);
		assertEquals(20, binTree.size());
		assertTrue(validate(new Comparable[] {-3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(8);
		assertEquals(19, binTree.size());
		assertTrue(validate(new Comparable[] {-3, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(12);
		assertEquals(18, binTree.size());
		assertTrue(validate(new Comparable[] {-3, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(16);
		assertEquals(17, binTree.size());
		assertTrue(validate(new Comparable[] {-3, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 14, 15, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(3);
		assertEquals(16, binTree.size());
		assertTrue(validate(new Comparable[] {-3, 1, 2, 4, 5, 6, 7, 9, 10, 11, 13, 14, 15, 17, 18, 19}, binTree.inOrder()));
		
		binTree.remove(11);
		assertEquals(15, binTree.size());
		assertTrue(validate(new Comparable[] {-3, 1, 2, 4, 5, 6, 7, 9, 10, 13, 14, 15, 17, 18, 19}, binTree.inOrder()));
		
		binTree.insert(8, v);
		binTree.insert(50, v);
		binTree.insert(-11, v);
		binTree.insert(23, v);
		assertEquals(19, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 1, 2, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(6);
		assertEquals(18, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 1, 2, 4, 5, 7, 8, 9, 10, 13, 14, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(14);
		assertEquals(17, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 1, 2, 4, 5, 7, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(4);
		assertEquals(16, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 1, 2, 5, 7, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(1);
		assertEquals(15, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 2, 5, 7, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(5);
		assertEquals(14, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 2, 7, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(7);
		assertEquals(13, binTree.size());
		assertTrue(validate(new Comparable[] {-11, -3, 2, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(-3);
		assertEquals(12, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 2, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(2);
		assertEquals(11, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 9, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(9);
		assertEquals(10, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 10, 13, 15, 17, 18, 19, 23, 50}, binTree.inOrder()));
		
		binTree.remove(19);
		assertEquals(9, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 10, 13, 15, 17, 18, 23, 50}, binTree.inOrder()));
		
		binTree.remove(18);
		assertEquals(8, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 10, 13, 15, 17, 23, 50}, binTree.inOrder()));
		
		binTree.remove(17);
		assertEquals(7, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 10, 13, 15, 23, 50}, binTree.inOrder()));
		
		binTree.remove(13);
		assertEquals(6, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 10, 15, 23, 50}, binTree.inOrder()));
		
		binTree.remove(50);
		assertEquals(5, binTree.size());
		assertTrue(validate(new Comparable[] {-11, 8, 10, 15, 23}, binTree.inOrder()));
		
		binTree.remove(-11);
		assertEquals(4, binTree.size());
		assertTrue(validate(new Comparable[] {8, 10, 15, 23}, binTree.inOrder()));
		
		binTree.remove(10);
		assertEquals(3, binTree.size());
		assertTrue(validate(new Comparable[] {8, 15, 23}, binTree.inOrder()));
		
		binTree.remove(15);
		assertEquals(2, binTree.size());
		assertTrue(validate(new Comparable[] {8, 23}, binTree.inOrder()));
		
		binTree.remove(8);
		assertEquals(1, binTree.size());
		assertTrue(validate(new Comparable[] {23}, binTree.inOrder()));
		
		binTree.remove(23);
		assertTrue(binTree.isEmpty());
	}
	
	/**
	 * Helper method used to validate the contents of a binary search tree
	 * using an in-order traversal
	 * 
	 * @param exp the expected array of keys in order
	 * @param act the actual array of keys generated from the in-order traversal
	 * 
	 * @return true if exp and act match element for element, false otherwise
	 */
	private boolean validate(Comparable<Integer>[] exp, Comparable<Integer>[] act) {
		if (exp.length != act.length) {
			return false;
		} else {
			for (int i = 0; i < exp.length; i++) {
				assertEquals(exp[i], act[i]);
			}
		}
		return true;
	}

}
