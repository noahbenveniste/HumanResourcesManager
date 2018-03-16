package edu.ncsu.csc316.hr.ui;

import java.util.Scanner;

import edu.ncsu.csc316.hr.manager.HumanResourcesManager;

/**
 * UI class for HumanResourcesManager. Handles all direct interaction
 * with user as well as file IO and basic input error handling.
 * 
 * @author Noah Benveniste
 */
public class HumanResourcesManagerUI {
	
	/**
	 * Handles interaction with user including program startup,
	 * object creation, file IO, and executes the specific functions
	 * that the user selects.
	 * 
	 * @param args NONE
	 */
	public static void main(String[] args) {
		
		// Stores the file path strings
		String resumeFile = null;
		String employeeHierarchyFile = null;
		
		// Scanner for reading input
		Scanner in = new Scanner(System.in);
		
		// Prompt user for the file names
		System.out.print("Enter path to employee resume file: ");
		resumeFile = in.next();
		
		System.out.print("\nEnter path to company hierarchy file: ");
		employeeHierarchyFile = in.next();
		
		// Initialize the manager
		HumanResourcesManager manager = null;
		boolean filesFound = false;
		
		// Keep reprompting user until valid files are found
		while (!filesFound) {
			try {
				manager = new HumanResourcesManager(employeeHierarchyFile, resumeFile);
				filesFound = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				
				System.out.print("\nEnter path to employee resume file: ");
				resumeFile = in.next();
				
				System.out.print("\nEnter path to company hierarchy file: ");
				employeeHierarchyFile = in.next();
			}
		}
		
		boolean runAgain = true;
		
		while (runAgain) {
			System.out.println("\nEnter 1 to generate organizational profile or 2 to remove an employee from the company directory: ");
			int selection = in.nextInt();
			
			if (selection == 1) {
				System.out.println();
				System.out.println(manager.generateOrganizationalProfile());
				System.out.println();
			} else if (selection == 2) {
				System.out.println("Enter employee to remove's first name and last name separated by a space (case sensitive): ");
				String first = in.next();
				String last = in.next();
				System.out.println(manager.removeEmployee(first, last));
				System.out.println();
			} else {
				System.out.println("\nInvalid input");
			}
			
			System.out.println("\nRun again? (y/n): ");
			runAgain = in.next().toLowerCase().equals("y");
		}
		
		// Close input scanner
		in.close();
	}

}
