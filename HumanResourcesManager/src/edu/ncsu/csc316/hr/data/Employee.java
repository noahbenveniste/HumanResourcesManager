package edu.ncsu.csc316.hr.data;

/**
 * A container object to store basic data about an Employee that exists
 * in the company hierarchy tree. Allows all relevant information to be
 * stored in a single data field in a GeneralTree node.
 * 
 * @author Noah Benveniste
 */
public class Employee implements Comparable<Employee> {

	/** The Employee's first name */
	private String first;
	/** The Employee's last name */
	private String last;
	/** The Employee's resume ID number (converted to a 9 digit int from the input string) */
	private int resID;
	
	/**
	 * Constructs and Employee object given a first name, last name and
	 * resume ID number
	 * 
	 * @param first the employee's first name
	 * @param last the employee's last name
	 * @param resID the employee's resume ID as a string starting with R
	 *        and followed by nine integer digits
	 */
	public Employee(String first, String last, String resID) {
		this.first = first;
		this.last = last;
		this.resID = Integer.parseInt(resID.substring(1));
	}
	
	
	/**
	 * Gets the Employee's first name
	 * 
	 * @return the first name
	 */
	public String getFirst() {
		return first;
	}


	/**
	 * Gets the Employee's last name
	 * 
	 * @return the last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Gets the Employee's resume ID number
	 * 
	 * @return the resume ID number
	 */
	public int getResID() {
		return resID;
	}

	/**
	 * Sets the Employee's first name
	 * 
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}


	/**
	 * Sets the Employee's last name
	 * 
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}


	/**
	 * Sets the Employee's resume ID number
	 * 
	 * @param resID the resID to set
	 */
	public void setResID(int resID) {
		this.resID = resID;
	}


	/**
	 * Returns a string representation of the Employee object
	 * 
	 * @return the employee's first name and last name connected by a single whitespace
	 * 		   and followed by a newline character
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("   ");
		sb.append(this.first).append(" ").append(this.last).append("\n");
		return sb.toString();
	}

	
	/**
	 * Compares two Employee objects alphabetically by last name and then first name
	 * 
	 * @param e the other Employee to compare to
	 * @return a negative integer if this Employee comes before e lexicographically,
	 * 		   a positive integer if this Employee comes after e lexicographically,
	 *         zero if this and e have the same first and last name
	 */
	@Override
	public int compareTo(Employee e) {
		if (!this.last.equals(e.getLast())) {
			return this.last.compareTo(e.getLast());
		} else {
			return this.first.compareTo(e.getFirst());
		}
	}

}
