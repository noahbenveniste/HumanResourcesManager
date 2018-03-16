package edu.ncsu.csc316.hr.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.hr.data.Resume;

/**
 * Unit tests for the HumanResourcesManager class.
 * 
 * @author Noah Benveniste
 */
public class HumanResourcesManagerTest {

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
		hrm = null;
		hrm = new HumanResourcesManager("input/test-employees.txt", "input/test-resumes.txt");
		String exp2 = "OrganizationalProfile[\n" +
				"   Rob Smith\n" +
				"   Maria Garcia\n" +
				"   David Smith\n" +
				"   Mary Smith\n" +
				"   Noah Benveniste\n" +
				"   James Johnson\n" + 
				"   John Brown\n" +
				"   Susan Hill\n" +
				"   Maria Parker\n" +
				"   Deb Roberts\n" +
				"   Sandra Baker\n" +
				"   Jeff Carter\n" +
				"   Laura Evans\n" +
				"   Sarah Baker\n" +
				"   Jen Wilson\n" +
				"   Brian Moore\n" +
				"   Kevin Harris\n" +
				"   Don Miller\n" +
				"   David James\n" +
				"   Carol Smith\n" + 
				"   Pat Davids\n" +
				"   Jason Adams\n" +
				"   Jeff Scott\n" +
				"]";
		assertEquals(exp2, hrm.generateOrganizationalProfile());
	}
	
	/**
	 * Tests the functionality for removing a given employee from the hierarchy and having all
	 * openings automatically filled by interim supervisors.
	 */
	@Test
	public void testRemoveEmployee() {
		HumanResourcesManager hrm = new HumanResourcesManager("input/sample.txt", "input/sample-resume.txt");
		// Test removing CEO
		assertEquals("Jane Doe", hrm.removeEmployee("Sarah", "Jones"));
		String exp1 = "OrganizationalProfile[\n" + 
				"   Jane Doe\n" + 
				"   John Smith\n" + 
				"   Thomas Webb\n" + 
				"   Suzanne Meadows\n" + 
				"   Jessica Daniels\n" + 
				"   Kyle DeMarcino\n" + 
				"]";
		assertEquals(exp1, hrm.generateOrganizationalProfile());
		try {
			hrm.getResumeDictionary().lookUp(200481294);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Key is not contained in the BST", e.getMessage());
		}
		
		hrm.removeEmployee("John", "Smith");
		String exp2 = "OrganizationalProfile[\n" + 
				"   Jane Doe\n" + 
				"   Thomas Webb\n" + 
				"   Suzanne Meadows\n" + 
				"   Jessica Daniels\n" + 
				"   Kyle DeMarcino\n" + 
				"]";
		assertEquals(exp2, hrm.generateOrganizationalProfile());
		
		hrm = null;
		
		hrm = new HumanResourcesManager("input/short-employees.txt", "input/short-resumes.txt");
		String exp3 = "OrganizationalProfile[\n" +
				      "   Lloyd Davids\n" +
				      "   Josef Yang\n" + 
				      "   Donny Francisco\n" +
				      "]";
		String result = hrm.removeEmployee("Bianca", "Smith");
		assertEquals("Lloyd Davids", result);
		assertEquals(exp3, hrm.generateOrganizationalProfile());
		
	}
	
}