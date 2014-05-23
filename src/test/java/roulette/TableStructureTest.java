package roulette;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableStructureTest {

	
	@Test
	public void constructorTest() {
		int number = 1;
		String szin = "red";
		TableStructure t = new TableStructure(number, szin);
		assertNotNull(t);
		assertEquals(number, t.getNumber());
		assertEquals(szin, t.getSzin());
	}
	
	@Test
	public void setNumberTest(){
		int number = 10;
		TableStructure t = new TableStructure(1, "red");
		t.setNumber(number);
		assertEquals(number, t.getNumber());
	}
	
	@Test
	public void setSzinTest(){
		String szin = "black";
		TableStructure t = new TableStructure(1, "red");
		t.setSzin(szin);
		assertEquals(szin, t.getSzin());
	}
	
	@Test
	public void toStringTest(){
		int number = 2;
		String szin = "black";
		String to = "number= " + number + ", color= " + szin;
		TableStructure t = new TableStructure(number,szin);
		assertEquals(to, t.toString());
	}

}

	
	
	
	
