package roulette;

import java.util.Random;

import javax.swing.plaf.TableUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xmlmanipulator.XMLWriter;

/**
 * Class for evaluate the roll.
 */
public class Roulette {
	
	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(Roulette.class);
	
	/**
	 * Array contains every number and it's color of the roulette table.
	 */
	private TableStructure[] table = new TableStructure[37];
	
	/**
	 * Placed number.
	 */
	private String Number;
	
	/**
	 * Placed bet.
	 */
	private int Bet;
	
	/**
	 * Rolled number.
	 */
	private int Rand;
	
	/**
	 * Player playable money value before the roll.
	 */
	private int Cassa;

	/**
	 * Constructor for creating an {@code Roulette} object.
	 * 
	 * @param Number the placed number
	 * @param Bet the placed bet
	 * @param Cassa the player playable money value before the roll
	 * @param random the rolled number
	 */
	public Roulette(String Number, int Bet, int Cassa,int random) {
		this.Number = Number;
		this.Bet = Bet;
		this.Rand = random;
		tableUpload();
		this.Cassa = Cassa;
		log.info("Roulette object created.");
	}
	
	/**
	 * Returns the player playable money value after the roll.
	 * 
	 * @return the player playable money value after the roll
	 */
	public int newCassaValue() {
		int NewCassa = Cassa;
		NewCassa += gain();
		return NewCassa;
	}

	/**
	 * Returns this roll gain or loss.
	 * 
	 * @return this roll gain or loss
	 */
	public int gain() {
		int p=payout(Number, Rand, Bet);
		log.info("Player gain " + p + " with this roll.");
		return p;
	}

	/**
	 * Returns the player playable money value before the roll.
	 * 
	 * @return the player playable money value before the roll
	 */
	public int getCassa() {
		return Cassa;
	}

	/**
	 * Sets the player playable money value before the roll.
	 * 
	 * @param cassa the player playable money value before the roll
	 */
	public void setCassa(int cassa) {
		Cassa = cassa;
	}

	/**
	 * Returns the rolled number.
	 * 
	 * @return the rolled number
	 */
	public int getRand() {
		return Rand;
	}

	/**
	 * Sets the rolled number.
	 * 
	 * @param rand the rolled number
	 */
	public void setRand(int rand) {
		Rand = rand;
	}

	/**
	 * Returns the placed money.
	 * 
	 * @return the placed money
	 */
	public String getNumber() {
		return Number;
	}

	/**
	 * Sets the placed money.
	 * 
	 * @param number the placed money
	 */
	public void setNumber(String number) {
		Number = number;
	}

	/**
	 * Returns the placed bet.
	 * 
	 * @return the placed bet
	 */
	public int getBet() {
		return Bet;
	}

	/**
	 * Sets the placed bet.
	 * 
	 * @param bet the placed bet
	 */
	public void setBet(int bet) {
		Bet = bet;
	}

	
	/**
	 * Returns this roll gain or loss.
	 * 
	 * @param BetNumber the placed number
	 * @param WheelRollNumber the rolled number.
	 * @param Bet the placed bet
	 * @return this roll gain or loss
	 */
	public int payout(String BetNumber, int WheelRollNumber, int Bet) {
		int betn;
		if (isNumber(BetNumber)) {
			betn = Integer.parseInt(BetNumber);
		}
		else{
			betn=-1;
		}
		
		if (betn == WheelRollNumber) {
			return Bet * 35;
		}
		else if (BetNumber.matches("1st") && (WheelRollNumber % 3) == 1) {
			return Bet * 2;
		}
		else if (BetNumber.matches("2nd") && (WheelRollNumber % 3) == 2) {
			return Bet * 2;
		}
		else if (BetNumber.matches("3rd") && (WheelRollNumber % 3) == 0
				&& WheelRollNumber != 0) {
			return Bet * 2;
		}
		else if (BetNumber.matches("1-12") && WheelRollNumber > 0
				&& WheelRollNumber <= 12) {
			return Bet * 2;
		}
		else if (BetNumber.matches("13-24") && WheelRollNumber >= 13
				&& WheelRollNumber <= 24) {
			return Bet * 2;
		}
		else if (BetNumber.matches("25-36") && WheelRollNumber >= 25
				&& WheelRollNumber <= 36) {
			return Bet * 2;
		}
		else if (BetNumber.matches("1-18") && WheelRollNumber > 0
				&& WheelRollNumber <= 18) {
			return Bet;
		}
		else if (BetNumber.matches("19-36") && WheelRollNumber >= 19
				&& WheelRollNumber <= 36) {
			return Bet;
		}
		else if (BetNumber.matches("even") && (WheelRollNumber % 2) == 0
				&& WheelRollNumber != 0) {
			return Bet;
		}
		else if (BetNumber.matches("odd") && (WheelRollNumber % 2) == 1) {
			return Bet;
		}
		else if (BetNumber.matches("red")
				&& table[WheelRollNumber].getSzin().matches("red")) {
			return Bet;
		}
		else if (BetNumber.matches("black")
				&& table[WheelRollNumber].getSzin().matches("black")) {
			return Bet;
		}
		else
			return -Bet;
	}
	
	/**
	 * Upload {@code table} with the proper number and the number's color of the roulette table.
	 */
	public void tableUpload() {
		table[0] = new TableStructure(0, "green");
		table[1] = new TableStructure(1, "red");
		table[2] = new TableStructure(2, "black");
		table[3] = new TableStructure(3, "red");
		table[4] = new TableStructure(4, "black");
		table[5] = new TableStructure(5, "red");
		table[6] = new TableStructure(6, "black");
		table[7] = new TableStructure(7, "red");
		table[8] = new TableStructure(8, "black");
		table[9] = new TableStructure(9, "red");
		table[10] = new TableStructure(10, "black");
		table[11] = new TableStructure(11, "black");
		table[12] = new TableStructure(12, "red");
		table[13] = new TableStructure(13, "black");
		table[14] = new TableStructure(14, "red");
		table[15] = new TableStructure(15, "black");
		table[16] = new TableStructure(16, "red");
		table[17] = new TableStructure(17, "black");
		table[18] = new TableStructure(18, "red");
		table[19] = new TableStructure(19, "red");
		table[20] = new TableStructure(20, "black");
		table[21] = new TableStructure(21, "red");
		table[22] = new TableStructure(22, "black");
		table[23] = new TableStructure(23, "red");
		table[24] = new TableStructure(24, "black");
		table[25] = new TableStructure(25, "red");
		table[26] = new TableStructure(26, "black");
		table[27] = new TableStructure(27, "red");
		table[28] = new TableStructure(28, "black");
		table[29] = new TableStructure(29, "black");
		table[30] = new TableStructure(30, "red");
		table[31] = new TableStructure(31, "black");
		table[32] = new TableStructure(32, "red");
		table[33] = new TableStructure(33, "black");
		table[34] = new TableStructure(34, "red");
		table[35] = new TableStructure(35, "black");
		table[36] = new TableStructure(36, "red");
	}

	/**
	 * Decides if a {@code String} object could be translate to an {@code Integer} object. 
	 * 
	 * @param str the string to try to translate 
	 * @return <code>true</code> if translatable and <code>false</code> if not translatable
	 */
	public boolean isNumber(String str) {
		try {
			int n = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
}
