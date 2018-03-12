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

}
