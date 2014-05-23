package roulette;

/**
 * Class representing number and it's color for one of the table spots.
 */
public class TableStructure {

	/**
	 * One of the numbers from the roulette table.
	 */
	private int number;
	
	/**
	 * The color of the {@code number}.
	 */
	private String szin;

	
	/**
	 * Constructor for creating an {@code TableStructure} object.
	 * 
	 * @param number one of the numbers from the roulette table
	 * @param szin the color of {@code number}
	 */
	TableStructure(int number, String szin) {
		this.number = number;
		this.szin = szin;
	}

	/**
	 * Returns the number of the table spot.
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 * 
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Returns the color of the {@code Number}.
	 * 
	 * @return the szin
	 */
	public String getSzin() {
		return szin;
	}

	/**
	 * Sets the color.
	 * 
	 * @param szin the color to set
	 */
	public void setSzin(String szin) {
		this.szin = szin;
	}
	
	
	/**
	 * Returns the string representation for one of the table spots.
	 *
	 * @return the string representation for one of the table spots in the form
	 * number= <span><em>number</em><code>, color= </code><em>szin</em></span>
	 */
	@Override
	public String toString() {
		return "number= " + number + ", color= " + szin;
	}

}

