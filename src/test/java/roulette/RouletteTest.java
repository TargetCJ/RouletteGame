package roulette;

import static org.junit.Assert.*;

import org.junit.Test;

public class RouletteTest {
	
	String Number = "1";
	int Bet= 25;
	int Cassa = 500;
	int random = 2;
	Roulette r = new Roulette(Number, Bet, Cassa, random);
	
	@Test
	public void constructorTest() {
	
		assertEquals(Number, r.getNumber());
		assertEquals(Bet , r.getBet());
		assertEquals(Cassa , r.getCassa());
		assertEquals(random , r.getRand());
	}
	
	@Test
	public void setNumberTest(){
		String number = "3";
		r.setNumber(number);
		assertEquals(number, r.getNumber());
	}
	
	@Test
	public void setBetTest(){
		int a =50;
		r.setBet(a);
		assertEquals(a, r.getBet());
	}
	
	@Test
	public void setCassaTest(){
		int a = 5000;
		r.setCassa(a);
		assertEquals(a, r.getCassa());
	}
	
	@Test
	public void setRandTest(){
		int a = 15;
		r.setRand(a);
		assertEquals(a, r.getRand());
	}
	
	@Test
	public void isNumberTest(){
		String number = "1";
		String notnumber = "1st";
		boolean a = r.isNumber(number);
		assertEquals(true, a);
		boolean b = r.isNumber(notnumber);
		assertEquals(false, b);
	}
	
	@Test
	public void payoutTest(){
		for(int bet = 25; bet<500; bet+=25){
			for(int i=0;i<37;i++){
				String s = i + "";
				
				int numberpayout = r.payout(s,i,bet);
				assertEquals(35*bet,numberpayout);
			}
			for(int i=1;i<37;i+=3){
				int payout1st = r.payout("1st",i,bet);
				assertEquals(2*bet, payout1st);
			}
			
			for(int i=2;i<37;i+=3){
				int payout2nd = r.payout("2nd",i,bet);
				assertEquals(2*bet, payout2nd);
			}
			
			for(int i=3;i<37;i+=3){
				int payout3rd = r.payout("3rd",i,bet);
				assertEquals(2*bet, payout3rd);
			}
			for(int i=1;i<13;i++){
				int payout12 = r.payout("1-12",i,bet);
				assertEquals(2*bet, payout12);
			}
			
			for(int i=13;i<25;i++){
				int payout24 = r.payout("13-24",i,bet);
				assertEquals(2*bet, payout24);
			}
			
			for(int i=25;i<37;i++){
				int payout36 = r.payout("25-36",i,bet);
				assertEquals(2*bet, payout36);
			}
			
			for (int i = 1; i < 19; i++) {
				int payout18 = r.payout("1-18", i, bet);
				assertEquals(bet, payout18);
			}
			
			for (int i = 19; i < 37; i++) {
				int payout36 = r.payout("19-36", i, bet);
				assertEquals(bet, payout36);
			}
			for(int i=1;i<37;i+=2){
				int payoutodd = r.payout("odd", i, bet);
				assertEquals(bet, payoutodd);
			}
			for(int i=2;i<37;i+=2){
				int payouteven = r.payout("even", i, bet);
				assertEquals(bet, payouteven);
			}
			int payoutred = r.payout("red", 1, bet);
			assertEquals(bet, payoutred);
			
			int payoutblack = r.payout("black", 2, bet);
			assertEquals(bet, payoutred);
			
			int nopayout = r.payout("1-12", 34, bet);
			assertEquals(-bet, nopayout);
			
			for(int i=0;i<36;i++){
				String s = i +"";
				int nopay = r.payout(s, i+1, bet);
			}
		}
	}
	
	@Test
	public void newCassaTest(){
		int bet = 100;
		int cassa = 500;
		int p = r.payout("1", 1, bet);
		Roulette rl = new Roulette("1", bet, cassa, 1);
		assertEquals(35*bet,rl.gain());
		
		assertEquals(rl.newCassaValue(), cassa + rl.gain());
	}
}
