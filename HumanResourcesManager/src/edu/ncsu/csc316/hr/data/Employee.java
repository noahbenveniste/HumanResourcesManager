package edu.ncsu.csc316.hr.data;

/**
 * 
 * @author Noah Benveniste
 */
public class Employee implements Comparable<Employee> {

	// Years of service
	
	// Degree
	private String first;
	private String last;
	private int resID;
	
	/**
	 * 
	 */
	public Employee(String first, String last, String resID) {
		this.first = first;
		this.last = last;
		this.resID = Integer.parseInt(resID.substring(1));
	}

	
	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}


	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return the resID
	 */
	public int getResID() {
		return resID;
	}

	/**
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("   ");
		sb.append(this.first).append(" ").append(this.last).append("\n");
		return sb.toString();
	}

	/**
	 * 
	 * @param e
	 * @return
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
