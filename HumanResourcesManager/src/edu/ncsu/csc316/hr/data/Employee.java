package edu.ncsu.csc316.hr.data;

/**
 * 
 * @author Noah Benveniste
 */
public class Employee implements Comparable<Employee> {

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
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}


	/**
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}


	/**
	 * @param resID the resID to set
	 */
	public void setResID(int resID) {
		this.resID = resID;
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
