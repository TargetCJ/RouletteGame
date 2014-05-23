package xmlmanipulator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class ComparatorTest {

	@Test
	public void comparatorTest() {
		XmlStructure x1 = new XmlStructure("Name1", 500);
		XmlStructure x2 = new XmlStructure("Name2", 100);
		XmlStructure x3 = new XmlStructure("Name1", 1525);
		XmlStructure x4 = new XmlStructure("Name3", 1500);
		
		MoneyComparator mc = new MoneyComparator();
		
		ArrayList< XmlStructure > list = new ArrayList<XmlStructure>();
		list.add(x1);
		list.add(x2);
		list.add(x3);
		list.add(x4);
		
		Collections.sort(list,mc);
		
		assertEquals(1525, list.get(0).getMoney());
		assertEquals(1500, list.get(1).getMoney());
		assertEquals(500, list.get(2).getMoney());
		assertEquals(100, list.get(3).getMoney());
	}

}
