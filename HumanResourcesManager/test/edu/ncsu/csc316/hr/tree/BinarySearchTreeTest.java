package edu.ncsu.csc316.hr.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.hr.tree.*;

/**
 * 
 * @author Noah Benveniste
 */
public class BinarySearchTreeTest {

	/** */
	private BinarySearchTree<Integer, String> binTree;
	/** */
	private int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	/** */
	private String[] values = {"ape", "bird", "cat", "dog", "fox", "goat", "hen", "pig", "rat"};
	
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		binTree = new BinarySearchTree<Integer, String>();
	}

	/**
	 * 
	 */
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
	}
	
	/**
	 * 
	 */
	@Test
	public void testLookUp() {
		assertEquals(0, binTree.size());
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
	}
	
	/**
	 * 
	 */
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
	}

}
