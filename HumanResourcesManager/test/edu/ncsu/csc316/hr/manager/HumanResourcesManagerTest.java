package edu.ncsu.csc316.hr.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.hr.data.Resume;

/**
 * 
 * @author Noah Benveniste
 */
public class HumanResourcesManagerTest {

	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tests construction of the HumanResourcesManager as well as the
	 * resume dictionary and employee hierarchy tree.
	 */
	@Test
	public void testHumanResourcesManager() {
		Resume[] expectedDictionary = {new Resume("R000634703", 1, 'M'),
									   new Resume("R040123346", 1, 'B'),
									   new Resume("R123582991", 1, 'B'),
									   new Resume("R200581294", 1, 'N'),
									   new Resume("R228401745", 1, 'M'),
									   new Resume("R829476581", 1, 'A'),
									   new Resume("R891429182", 1, 'P')};
		HumanResourcesManager hrm = new HumanResourcesManager("input/sample.txt", "input/sample-resume.txt");
		@SuppressWarnings("rawtypes")
		Comparable[] actualKeys = hrm.getResumeDictionary().inOrder();
		for (int i = 0; i < expectedDictionary.length; i++) {
			assertEquals(expectedDictionary[i].getResID(), hrm.getResumeDictionary().lookUp((Integer) actualKeys[i]).getResID());
		}
		assertEquals(7, hrm.getEmployees().size());
		
		// Create a new manager using new input files
		hrm = null;
		hrm = new HumanResourcesManager("input/test-employees.txt", "input/test-resumes.txt");
		// Check that the dictionary is ordered properly
		actualKeys = hrm.getResumeDictionary().inOrder();
		for (int i = 1; i < actualKeys.length; i++) {
			assertTrue((Integer) actualKeys[i - 1] < (Integer) actualKeys[i]);
		}
		assertEquals(23, hrm.getEmployees().size());
	}
	
	/**
	 * Tests the functionality for generating an organizational profile
	 */
	@Test
	public void testGenerateOrganizationalProfile() {
		String exp1 = "OrganizationalProfile[\n" + 
				"   Sarah Jones\n" + 
				"   John Smith\n" + 
				"   Jane Doe\n" + 
				"   Suzanne Meadows\n" + 
				"   Thomas Webb\n" + 
				"   Jessica Daniels\n" + 
				"   Kyle DeMarcino\n" + 
				"]";
		HumanResourcesManager hrm = new HumanResourcesManager("input/sample.txt", "input/sample-resume.txt");
		assertEquals(exp1, hrm.generateOrganizationalProfile());
	}
	
}