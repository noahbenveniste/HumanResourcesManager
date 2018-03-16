package edu.ncsu.csc316.hr.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for Employee objects
 * 
 * @author Noah Benveniste
 */
public class EmployeeTest {

	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		Employee e1 = new Employee("Noah", "Benveniste", "R123456789");
		Employee e2 = new Employee("Daniel", "Mills", "R987654321");
		assertTrue(e1.compareTo(e2) < 0);
	}

}
