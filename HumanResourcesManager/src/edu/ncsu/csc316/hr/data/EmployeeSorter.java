package edu.ncsu.csc316.hr.data;

/**
 * An object used to store employee data for sorting to determine
 * the employee to be promoted out of a group.
 * 
 * @author Noah Benveniste
 */
public class EmployeeSorter implements Comparable<EmployeeSorter> {

	private int years;
	private int numSubordinates;
	private int degree;
	private String last;
	private String first;
	
	/**
	 * 
	 */
	public EmployeeSorter(int years, int numSubordinates, int degree, String last, String first) {
		this.years = years;
		this.numSubordinates = numSubordinates;
		this.degree = degree;
		this.last = last;
		this.first = first;
	}

	/**
	 * @return the years
	 */
	public int getYears() {
		return years;
	}

	/**
	 * @return the numSubordinates
	 */
	public int getNumSubordinates() {
		return numSubordinates;
	}

	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return the first
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
