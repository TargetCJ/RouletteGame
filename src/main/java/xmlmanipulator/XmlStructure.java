package xmlmanipulator;

/**
 * Class representing an XML file's structure about the highscores.
 */
public class XmlStructure {

	/**
	 * Name of the record holder.
	 */
	private String name;
	
	/**
	 * Amount of money the record's been held. 
	 */
	private int money;
	
	/**
	 * Constructor for creating an {@code XmlStructure} object.
	 * 
	 * @param name the name of the highscore holder
	 * @param money the amount of money the highscore's been held
	 */
	public XmlStructure(String name, int money) {
		this.name = name;
		this.money = money;
	}

	/**
	 * Returns the name of the record holder.
	 * 
	 * @return the name of the record holder.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the record holder.
	 * 
	 * @param name of the record holder
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the money amount the record's been held.
	 * 
	 * @return the money amount the record's been held
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Sets the money amount the money amount the record's been held.
	 * 
	 * @param money amount the record's been held
	 */
	public void setMoney(int money) {
		this.money = money;
	}
}
