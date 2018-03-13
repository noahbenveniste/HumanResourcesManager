package edu.ncsu.csc316.hr.data;

/**
 * 
 * @author Noah Benveniste
 */
public class Resume implements Comparable<Resume> {
	/** */
	private int resID;
	/** */
	private int years;
	/** */
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
