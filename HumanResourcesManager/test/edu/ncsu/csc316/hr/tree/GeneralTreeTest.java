package edu.ncsu.csc316.hr.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Noah Benveniste
 */
public class GeneralTreeTest {

	/**
	 * 
	 */
	@Test
	public void testCompareTo() {
		GeneralTree<String> tree = new GeneralTree<String>("A");
		GeneralTree<String>.Node<String> n1 = tree.new Node<String>("B");
		GeneralTree<String>.Node<String> n2 = tree.new Node<String>("C");
		assertEquals(0, n1.compareTo(n2));
	}
	
}
