package edu.ncsu.csc316.hr.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Noah Benveniste
 */
public class EmployeeSorterTest {

	/**
	 * 
	 */
	@Test
	public void testCompareTo() {
		EmployeeSorter s1 = new EmployeeSorter(5, 10, 2, "Smith", "John");
		EmployeeSorter s2 = new EmployeeSorter(6, 10, 2, "Smith", "John");
		EmployeeSorter s3 = new EmployeeSorter(5, 11, 2, "Smith", "John");
		EmployeeSorter s4 = new EmployeeSorter(5, 10, 3, "Smith", "John");
		EmployeeSorter s5 = new EmployeeSorter(5, 10, 2, "Daniels", "John");
		EmployeeSorter s6 = new EmployeeSorter(5, 10, 2, "Smith", "Jane");
		EmployeeSorter s7 = new EmployeeSorter(5, 10, 2, "Smith", "John");
		
		assertTrue(s1.compareTo(s2) > 0);
		assertTrue(s1.compareTo(s3) > 0);
		assertTrue(s1.compareTo(s4) > 0);
		assertTrue(s1.compareTo(s5) > 0);
		assertTrue(s1.compareTo(s6) > 0);
		assertTrue(s1.compareTo(s7) == 0);
	}

}
