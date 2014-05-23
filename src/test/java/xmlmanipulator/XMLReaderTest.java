package xmlmanipulator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class XMLReaderTest {
	
	@Test
	public void Reading(){
		XMLReader r = new XMLReader("testscores.xml");
		ArrayList< XmlStructure > list =  new ArrayList<XmlStructure>();
		list = r.read();
		
		assertEquals("Charlie.Sheen.Winning", list.get(0).getName());
		assertEquals(10000, list.get(0).getMoney());
		assertEquals("Hurley", list.get(1).getName());
		assertEquals(100000, list.get(1).getMoney());
		assertEquals("Starter", list.get(2).getName());
		assertEquals(500, list.get(2).getMoney());
		assertEquals("Looser", list.get(3).getName());
		assertEquals(25, list.get(3).getMoney());
	}
}
