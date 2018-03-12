package edu.ncsu.csc316.hr.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.hr.data.*;
import edu.ncsu.csc316.hr.tree.*;

/**
 * 
 * @author Noah Benveniste
 */
public class HumanResourcesManager {
	/** */
	private BinarySearchTree<Integer, Resume> resumeDictionary;
	/** */
	private GeneralTree<Employee> employees;

	/**
	 * Constructs a new HR manager with the given input files
	 * 
	 * @param pathToEmployeeFile
	 *            - the path to the employee input file
	 * @param pathToResumeFile
	 *            - the path to the resume input file
	 */
	public HumanResourcesManager(String pathToEmployeeFile, String pathToResumeFile) {
		
		/* BUILDING THE RESUME DICTIONARY */
		
		// Try to build a file scanner for the resume file
		Scanner resumes = null;
		try {
			resumes = new Scanner(new FileInputStream(pathToResumeFile));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Employee file not found.");
		}
		
		// Initialize empty BST with resume ID (int) as the key and Resume objects as the value
		resumeDictionary = new BinarySearchTree<Integer, Resume>();
		
		// Skip the first line of the resume file
		resumes.nextLine();
		
		// For each line in the file, create a resume object.
		while(resumes.hasNextLine()) {
			String curr = resumes.nextLine();
			Scanner resume = new Scanner(curr);
			resume.useDelimiter(", ");
			
			// TODO: possible performance improvements when reading in resume dictionary
			
			String id = resume.next();
			int years = resume.nextInt();
			char degree = resume.next().charAt(0);
			
			Resume r = new Resume(id, years, degree);
			
			// Insert the resume object into the BST as the value with the ID as the key
			resumeDictionary.insert(r.getResID(), r);
			// Close the scanner for the line
			resume.close();
		}
		
		// Close the file scanner
		resumes.close();
		
		/* BUILDING THE EMPLOYEE HIEARCHY TREE */
		
		// Try to build a file scanner for the employee file
		Scanner empHierarchy = null;
		try {
			empHierarchy = new Scanner(new FileInputStream(pathToEmployeeFile));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Employee file not found.");
		}
		empHierarchy.useDelimiter(",");
		buildTree(empHierarchy);
		empHierarchy.close();
	}
	
	/**
	 * 
	 */
	private void buildTree(Scanner empHierarchy) {
		// First employee in file should be the root of the tree
		String firstEmpStr = empHierarchy.nextLine();
		Scanner firstEmp = new Scanner(firstEmpStr);
		firstEmp.useDelimiter(",");
		String first = firstEmp.next().trim();
		String last = firstEmp.next().trim();
		String id = firstEmp.next().trim();
		Employee ceo = new Employee(first, last, id);
		firstEmp.close();
		employees = new GeneralTree<Employee>(ceo);
		
		// Read in the first "(" character
		empHierarchy.nextLine();
		
		// Call method that handles recursion
		buildTreeHelper(empHierarchy, employees.getRoot());
	}
	
	private void buildTreeHelper(Scanner empHierarchy, GeneralTree<Employee>.Node<Employee> parent) {
		/* For each employee read in, build an employee object by reading the line */
		
		GeneralTree<Employee>.Node<Employee> lastChildInstantiated = null;
		
		// Try to parse the next line of input. Catch and handle NSE exceptions. If there are no
		// more lines left in the file, return.
		if (empHierarchy.hasNextLine()) {
			String curr = empHierarchy.nextLine().trim();
			
			// While in a given level of the hierarchy, keep adding children to the current parent.
			while (!curr.equals(")")) {
				// If the line is "(", perform a recursive call to go one level deeper into the hierarchy
				if (curr.equals("(")) {
					buildTreeHelper(empHierarchy, lastChildInstantiated);
				} else { // If the line contains employee data, parse it and create an employee object
					try {
						Scanner employee = new Scanner(curr);
						employee.useDelimiter(",");
						// If the current line is an employee, create an Employee object Node adding it as a child
						// to the parents passed to the method
						Employee e = new Employee(employee.next().trim(), employee.next().trim(), employee.next().trim());
						GeneralTree<Employee>.Node<Employee> eNode = employees.new Node<Employee>(e);
						lastChildInstantiated = eNode;
						
						// TODO: add an insert to end method to arraylist class to improve performance here
						parent.getChildren().add(parent.getChildren().size(), eNode);
						
						// Increment the size of the employee tree
						employees.incSize();
						// Close the line scanner
						employee.close();
					} catch (NoSuchElementException e) {
						// Do nothing
					}
				}
				// Get next line of input
				curr = empHierarchy.nextLine().trim();
			}
			return;	
		} else {
			return;
		}
	}
	
	/**
	 * Returns a string representation of the interim employee
	 * who replaces the removed employee.
	 * 
	 * @param first - the first name of the employee to remove
	 * @param last - the last name of the employee to remove
	 * @return the name of the employee who was promoted to interim supervisor
	 */
	public String removeEmployee(String first, String last) {
	    // Search for the employee to remove
		
		// If the employee is found, sort its children based on the following criteria:
		// 1. the years of service the employee in each child node has (look up in dictionary)
		// 2. the number of child nodes each node in the child list has (from the node object)
		// 3. degree (look up in dictionary)
		// 4. last name (from the Employee object)
		// 5. first name (from the Employee object)
		return null;
	}
	
	/**
	 * Returns the string representation of the organizational
	 * profile of the company using the given input employee file.
	 * 
	 * @return the organizational profile
	 */
	public String generateOrganizationalProfile() {
		/* Need to perform a level-order traversal of the general tree.
		   Use a linked list-based queue. */
	    // TODO: Add your code here
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public BinarySearchTree<Integer, Resume> getResumeDictionary() {
		return this.resumeDictionary;
	}
	
	/**
	 * 
	 * @return
	 */
	public GeneralTree<Employee> getEmployees() {
		return this.employees;
	}

}
