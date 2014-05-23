package xmlmanipulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class XmlStructureTest {

	@Test
	public void testXmlStructure() {
		String name = "BasicName";
		int money = 1025;
		XmlStructure x = new XmlStructure(name,money);
		assertEquals(name, x.getName());
		assertEquals(money, x.getMoney());
	}


	@Test
	public void testSetName() {
		String name = "BasicName";
		int money = 1025;
		String nametest = "testName";		
		XmlStructure x = new XmlStructure(name,money);
		x.setName(nametest);
		assertEquals(nametest, x.getName());
	}


	@Test
	public void testSetMoney() {
		String name = "BasicName";
		int money = 1025;
		int moneytest = 550;	
		XmlStructure x = new XmlStructure(name,money);
		x.setMoney(moneytest);
		assertEquals(moneytest, x.getMoney());
	}
}
