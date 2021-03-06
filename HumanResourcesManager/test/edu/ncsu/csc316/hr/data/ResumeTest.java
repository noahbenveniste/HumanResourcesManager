package edu.ncsu.csc316.hr.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for Resume objects
 * 
 * @author Noah Benveniste
 */
public class ResumeTest {

	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		Resume r1 = new Resume("R123456789", 1, 'A');
		Resume r2 = new Resume("R987654321", 1, 'A');
		assertTrue(r1.compareTo(r2) < 0);
	}

}
