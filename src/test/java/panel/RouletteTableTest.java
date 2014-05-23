package panel;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RouletteTableTest {

	int Cassa = 500;
	public RouletteTable r = new RouletteTable(Cassa);
	
	@Test
	public void constructorTest() {
		assertEquals(Cassa, r.getYourCassa());
	}
	
	@Test
	public void setNameTest(){
		String name = "TestName";
		r.setName(name);
		assertEquals(name, r.getName());
	}
	
	@Test
	public void refreshCassaTest(){
		for(int i=0;i<=10000;i+=500){
			r.RefreshYourMoneyValue(i);
			assertEquals(i, r.getYourCassa());
		}
	}
}
