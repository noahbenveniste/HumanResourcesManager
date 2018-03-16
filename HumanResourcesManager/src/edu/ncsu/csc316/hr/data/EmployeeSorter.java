package edu.ncsu.csc316.hr.data;

/**
 * An object that combines information from a given Employee object
 * in the company hierarchy tree and information from the Resume
 * dictionary to allow for easy sorting when selecting an interim
 * supervisor for promotion.
 * 
 * @author Noah Benveniste
 */
public class EmployeeSorter implements Comparable<EmployeeSorter> {

	/** The Employee's number of years of experience; from the resume dictionary */
	private int years;
	/** The number of direct subordinates the Employee oversees; from the Employee's node in the GeneralTree */
	private int numSubordinates;
	/** An integer corresponding to the Employee's highest degree; from the resume dictionary */
	private int degree;
	/** The Employee's last name */
	private String last;
	/** The Employee's first name */
	private String first;
	
	/**
	 * Constructs an EmployeeSorter object given an Employee's years of experience, number
	 * of direct subordinates, degree level, last name and first name.
	 * 
	 * @param years years of experience
	 * @param numSubordinates number of direct subordinates
	 * @param degree 0 = none, 1 = associate's, 2 = BA, 3 = MA, 4 = PhD
	 * @param last last name
	 * @param first first name
	 */
	public EmployeeSorter(int years, int numSubordinates, int degree, String last, String first) {
		this.years = years;
		this.numSubordinates = numSubordinates;
		this.degree = degree;
		this.last = last;
		this.first = first;
	}

	/**
	 * Gets the years of experience
	 * 
	 * @return the years
	 */
	public int getYears() {
		return years;
	}

	/**
	 * Gets the number of subordinates
	 * 
	 * @return the numSubordinates
	 */
	public int getNumSubordinates() {
		return numSubordinates;
	}

	/**
	 * Gets the degree
	 * 
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * Gets the last name
	 * 
	 * @return the last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Gets the first name
	 * 
	 * @return the first name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Compares two employees to determine who to promote based on the following criteria:
	 * 1. Number of years of service
	 * 2. Number of supervised employees
	 * 3. Degree level
	 * 4. Last name alphabetically
	 * 5. First name alphabetically
	 * 
	 * @param o the other EmployeeSorter to compare against
	 * 
	 * @return an int < 0 if this employee would have be promoted over o,
	 * 		   an int > 0 if o would be promoted over thie employee,
	 *         or 0 if the two sorters are equivalent.
	 */
	@Override
	public int compareTo(EmployeeSorter o) {
		// 1. Sort by number of years of service
		if (this.years != o.getYears()) {
			return o.getYears() - this.years;
		}
		
		// 2. Sort by number of supervised employees
		if (this.numSubordinates != o.getNumSubordinates()) {
			return o.getNumSubordinates() - this.numSubordinates;
		}
		
		// 3. Sort by degree level
		if (this.degree != o.getDegree()) {
			return o.getDegree() - this.degree;
		}
		
		// 4. Sort by last name
		if (!this.last.equals(o.getLast())) {
			return this.last.compareTo(o.getLast());
		}
		
		// 5. Sort by first name
		if (!this.first.equals(o.getFirst())) {
			return this.first.compareTo(o.getFirst());
		}
		
		// Objects are equal
		return 0;
	}

}
