package xmlmanipulator;

import java.util.Comparator;

/**
 * Class that compares two object.
 */
public class MoneyComparator implements Comparator{

	/**
	 * Compares two {@code XmlStructure} object by their getMoney() value and return an Integer value.
	 * 
	 * @param o1 an object to compare with o2
	 * @param o2 an object to compare with o1
	 * @return positive value if the first argument value is less than the second
	 * negative value if the first argument value is bigger than the second
	 * and 0 if the two argument are equal.
	 */
	public int compare(Object o1, Object o2) {
		XmlStructure a = (XmlStructure) o1;
		XmlStructure b = (XmlStructure) o2;

		return b.getMoney()-a.getMoney();
	}



}
