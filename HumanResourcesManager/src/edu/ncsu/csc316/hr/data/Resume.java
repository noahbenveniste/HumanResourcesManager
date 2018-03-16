package edu.ncsu.csc316.hr.data;

/**
 * A container object that stores information from an employee's
 * resume in a single object for storage in a binary search tree-
 * based dictionary. The employee's resume id is used as a search
 * key and the Resume object is used as the corresponding value.
 * 
 * @author Noah Benveniste
 */
public class Resume implements Comparable<Resume> {
	
	/** The employee's resume ID, represented by a 9-digit integer */
	private int resID;
	/** The number of years of experience */
	private int years;
	/** The degree level; 0 = none, 1 = associate's, 2 = BA, 3 = MA, 4 = PhD */
	private int degree;
	

	/**
	 * 
	 */
	public Resume(String resID, int years, char degree) {
		this.resID = Integer.parseInt(resID.substring(1));
		this.years = years;
		if (degree == 'N') {
			this.degree = 0;
		} else if (degree == 'A') {
			this.degree = 1;
		} else if (degree == 'B') {
			this.degree = 2;
		} else if (degree == 'M') {
			this.degree = 3;
		} else {
			this.degree = 4;
		}
	}

	/**
	 * @return the resID
	 */
	public int getResID() {
		return resID;
	}

	/**
	 * @return the years
	 */
	public int getYears() {
		return years;
	}

	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Resume r) {
		return this.resID - r.getResID();
	}

}
