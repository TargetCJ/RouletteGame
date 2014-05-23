package panel;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roulette.Application;
import roulette.Roulette;
import xmlmanipulator.MoneyComparator;
import xmlmanipulator.XMLWriter;
import xmlmanipulator.XmlStructure;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A JFrame class that builds the game surface and controls the game.
 */
public class RouletteTable extends JFrame implements ActionListener, MouseListener {

	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(RouletteTable.class);

	/**
	 * This is a JButton of this frame.
	 */
	JButton spin;
	
	/**
	 * This is a JButton of this frame.
	 */
	JButton exit;
	
	/**
	 * This is a MouseEvent of this frame.
	 */
	MouseEvent ml;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel yourMoneyStr;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel rolledNumber;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel yourMoneyValue;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel yourBetStr;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel kihuzottLabel;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel name;
	
	/**
	 * This is a JSpinner of this frame.
	 */
	JSpinner yourBetSpinner;
	/**
	 * The amount of money the player has after a roll.
	 */
	int arCassa;
	/**
	 * The amount of money the player actually has.
	 */
	private int yourCassa;
	/**
	 * The amount of money the player placed on a bet.
	 */
	private int backBet = 0;
	/**
	 * The name of the player.
	 */
	private String playerName;
	/**
	 * The spinner basic value.
	 */
	private int initalValue = 0;
	/**
	 * The minimum value on the spinner.
	 */
	private int minValue = 0;
	/**
	 * The step on the spinner.
	 */
	private int spinnerStep = 25;
	/**
	 * Flag that decides whether the chips needs to be deleted or not.
	 */
	private boolean chipFlag = false;
	/**
	 * A lists contains the player bets.
	 */
	private ArrayList< ArrayList<Object> > backList = new ArrayList< ArrayList<Object> >();
	/**
	 * A list contains the placed chips labels.
	 */
	private ArrayList<JLabel> chipList = new ArrayList< JLabel >();
	
	

	/**
	 * Constructor for creating an {@code RouletteTable} object.
	 * 
	 * @param Cassa the amount of money that the game starts.
	 */
	public RouletteTable(int Cassa) {
		this.arCassa = this.yourCassa = Cassa;
		setSize(1233, 573);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackGround();
		setComponents();
		getContentPane().addMouseListener(this);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		log.info("RouletteTable successfully created.");
	}
	
	/**
	 * Sets the background.
	 */
	public void setBackGround() {
		
		setContentPane(new JLabel(new ImageIcon(RouletteTable.class.getClassLoader().getResource("roulette1.png"))));		
	}

	
	/**
	 * Sets the JFrame's components.
	 */
	public void setComponents() {
		
		setTitle("Roulette");
		
		yourMoneyStr = new JLabel("Pénzed: ");
		yourMoneyStr.setBounds(1050, 15, 75, 30);
		getContentPane().add(yourMoneyStr);

		setYourMoney();

		yourBetStr = new JLabel("Tét: ");
		yourBetStr.setBounds(1075, 50, 50, 30);
		add(yourBetStr);

		spin = new JButton("Pörgetek");
		spin.setBounds(15, 90, 100, 30);
		spin.addActionListener(this);
		add(spin);
		
		exit = new JButton("Kiszállok");
		exit.setBounds(125, 90, 100, 30);
		exit.addActionListener(this);
		add(exit);
		
		kihuzottLabel = new JLabel("Pörgetett szám: ");
		kihuzottLabel.setBounds(10, 25, 135, 52);
		add(kihuzottLabel);

		int maxValue = this.yourCassa;
		SpinnerNumberModel model1 = new SpinnerNumberModel(initalValue,
				minValue, maxValue, spinnerStep);
		yourBetSpinner = new JSpinner(model1);
		yourBetSpinner.setBounds(1150, 50, 48, 30);
		add(yourBetSpinner);

	}
	
	/**
	 * Sets the player name in the program and on the screen.
	 * 
	 * @param Name the name of the player
	 */
	public void setName(String Name){
		playerName = Name;
		int labelWidth = 200;
		int x = (int)((getWidth()/2)-labelWidth/2);
		name = new JLabel("Játékos: " + playerName);
		name.setBounds(x,15,labelWidth,30);
		add(name);
		log.info(playerName + " has started to play.");
	}
	
	/**
	 * Returns the name of the player.
	 * 
	 * @return the name of the player.
	 */
	public String getName(){
		return playerName;
	}
	
	/**
	 * Sets the money value the player has.
	 */
	private void setYourMoney() {
		String str = this.yourCassa + "";
		yourMoneyValue = new JLabel(str);
		yourMoneyValue.setBounds(1150, 15, 48, 30);
		getContentPane().add(yourMoneyValue);
	}
	
	/**
	 * Refreshes the money value the player has.
	 * 
	 * @param Cassa the amount of the money the player has
	 */
	public void RefreshYourMoneyValue(int Cassa) {
		getContentPane().remove(yourMoneyValue);
		this.yourCassa=Cassa;
		refresh();
		String str = Cassa + "";
		yourMoneyValue = new JLabel(str);
		yourMoneyValue.setBounds(1150, 15, 48, 30);
		getContentPane().add(yourMoneyValue);
		refreshBetSpinner(Cassa);
		refresh();
	}
	
	/**
	 * Refreshes the JSpinner with the new upper bound which is the amount of the money the player actually has.
	 * 
	 * @param maxValue the upper bound of the spinner which is also the amount of the money the player actually has
	 */
	public void refreshBetSpinner(int maxValue){
		refresh();
		getContentPane().remove(yourBetSpinner);
		SpinnerNumberModel model1 = new SpinnerNumberModel(initalValue,
				minValue, maxValue, spinnerStep);
		yourBetSpinner = new JSpinner(model1);
		yourBetSpinner.setBounds(1150, 50, 48, 30);
		getContentPane().add(yourBetSpinner);
		refresh();
	}

	/**
	 * Refreshes the screen.
	 */
	private void refresh() {
		revalidate();
		repaint();
	}
	
	/**
	 * Shows on the screen which number has been rolled.
	 * 
	 * @param number the rolled number
	 */
	public void writeRolled(int number){
		String s = "numbers/" + number + ".jpg";
		rolledNumber = new JLabel();
		rolledNumber.setBounds(145, 25, 74, 52);
		rolledNumber.setIcon(new ImageIcon(RouletteTable.class.getClassLoader().getResource(s)));
		getContentPane().add(rolledNumber);
	}

	/**
	 * Decides what to do if mouse clicked.
	 * 
	 * @param e e is a MouseEvent Object
	 */
	public void mouseClicked(MouseEvent e) {

		if(chipFlag){
			for(JLabel l : chipList){
				freeChips(l);
				getContentPane().remove(rolledNumber);
			}
			chipList.clear();
			chipFlag=false;
		}
	
		int x = e.getX();
		int y = e.getY();
		
		Object actualBet = yourBetSpinner.getValue();
		
		int checkbet = (Integer) actualBet;
		if(checkbet>0){
			
			String backValue=where(x,y);
			
			
			if (!backValue.matches("none")) {
				
				log.info(backValue + " choosen from the table as bet type with " + checkbet + " as bet");
				
				backBet = checkbet;
				int tmpCassa = this.yourCassa - backBet;
				refreshBetSpinner(tmpCassa);
				RefreshYourMoneyValue(tmpCassa);
				ArrayList<Object> actualList = new ArrayList<Object>();
				actualList.add(backValue);
				actualList.add(backBet);
				backList.add(actualList);
				
			}
		}
	}
	
	/**
	 * Returns the clicked table position.
	 * 
	 * @param x x is the x coordinate of the click
	 * @param y y is the y coordinate of the click
	 * @return the clicked table position 
	 */
	public String where(int x,int y){
		String backValue="none";
		int felsoX = 310;
		int felsoY = 117;
		int columnWidth = 60;
		int columnHeight = 74;
		int barWidth = 7;
		int tmp = 0;
		int i,j;
		if(y>felsoY && y<felsoY+(3*columnHeight + 2 * barWidth) && x>felsoX && x<felsoX + (13 * columnWidth) + (12 * barWidth)){
			int alsoY =  felsoY+(3*columnHeight + 2*barWidth);
			for(i=0;i<12;i++){
				for(j=0;j<=2;j++){
					tmp++;
					if(y<alsoY-(j*columnHeight + j * barWidth) &&  y>alsoY-((j+1)*columnHeight + j * barWidth) 
							&& x>felsoX + (i * columnWidth) + (i * barWidth) && x< felsoX+((i+1)*columnWidth) + i * barWidth){
								backValue = tmp + "";
								loadChip(getMidX(i,columnWidth,barWidth),getMidY(j,columnHeight,barWidth));
								break;
						}
				}
			}
			for(j=0;j<=2;j++){
				if(y<alsoY-(j*columnHeight + j * barWidth) &&  y>alsoY-((j+1)*columnHeight + j * barWidth) 
						&& x>felsoX + (i * columnWidth) + (i * barWidth) && x< felsoX+((i+1)*columnWidth) + i * barWidth){
							if(j==0){
								backValue = "1st";
								loadChip(getMidX(i,columnWidth,barWidth),getMidY(j,columnHeight,barWidth));
								break;
							}
							else if(j==1){
								backValue = "2nd";
								loadChip(getMidX(i,columnWidth,barWidth),getMidY(j,columnHeight,barWidth));
								break;
							}
							else{
								backValue = "3rd";
								loadChip(getMidX(i,columnWidth,barWidth),getMidY(j,columnHeight,barWidth));
								break;
							}
				}
				
			}
			
		}
		if (y > 360 && y < 434) {
			if (x > felsoX && x < felsoX + (4 * columnWidth) + (3 * barWidth)) {
				backValue = "1-12";
				loadChip(getMidX(0,4*columnWidth,4*barWidth),getMidY(-1,columnHeight,barWidth));
			}
			if (x > felsoX + (4 * columnWidth) + (4 * barWidth)
					&& x < felsoX + (8 * columnWidth) + (7 * barWidth)) {
				
						backValue = "13-24";
						loadChip(getMidX(1,4*columnWidth,4*barWidth),getMidY(-1,columnHeight,barWidth));
			}
			if (x > felsoX + (8 * columnWidth) + (8 * barWidth)
					&& x < felsoX + (12 * columnWidth) + (11 * barWidth)) {
				
						backValue = "25-36";
						loadChip(getMidX(2,4*columnWidth,4*barWidth),getMidY(-1,columnHeight,barWidth));
			}

		}
		if (y > 441 && y < 515) {
			int n = 0;
			if (x > felsoX + (0 * columnWidth) + (0 * barWidth)
					&& x < felsoX + (2 * columnWidth) + (1 * barWidth)) {
				backValue = "1-18";
				loadChip(getMidX(n,2*columnWidth,2*barWidth),getMidY(-2,columnHeight,barWidth));
			}
			n++;
			if (x > felsoX + (2 * columnWidth) + (2 * barWidth)
					&& x < felsoX + (4 * columnWidth) + (3 * barWidth)) {
				backValue = "even";
				loadChip(getMidX(n,2*columnWidth,2*barWidth),getMidY(-2,columnHeight,barWidth));
			}
			n++;
			if (x > felsoX + (4 * columnWidth) + (4 * barWidth)
					&& x < felsoX + (6 * columnWidth) + (5 * barWidth)) {
				backValue = "red";
				loadChip(getMidX(n,2*columnWidth,2*barWidth),getMidY(-2,columnHeight,barWidth));
			}
			n++;
			if (x > felsoX + (6 * columnWidth) + (6 * barWidth)
					&& x < felsoX + (8 * columnWidth) + (7 * barWidth)) {
				backValue = "black";
				loadChip(getMidX(n,2*columnWidth,2*barWidth),getMidY(-2,columnHeight,barWidth));
			}
			n++;
			if (x > felsoX + (8 * columnWidth) + (8 * barWidth)
					&& x < felsoX + (10 * columnWidth) + (9 * barWidth)) {
				backValue = "odd";
				loadChip(getMidX(n,2*columnWidth,2*barWidth),getMidY(-2,columnHeight,barWidth));
			}
			n++;
			if (x > felsoX + (10 * columnWidth) + (10 * barWidth)
					&& x < felsoX + (12 * columnWidth) + (11 * barWidth)) {
				backValue = "19-36";
				loadChip(getMidX(n,2*columnWidth,2*barWidth),getMidY(-2,columnHeight,barWidth));
			}
		}
		if (x > felsoX-barWidth-columnWidth && x < felsoX-barWidth && y > felsoY && y < felsoY+(3*columnHeight+2*barWidth)) {
			backValue = "0";
			loadChip((int)((felsoX-barWidth-columnWidth)+(felsoX-barWidth))/2,(int)(felsoY+(felsoY+3*columnHeight+2*barWidth))/2);
		}
		return backValue;
	}
	
	
	/**
	 * Place a chip on the screen to a certain place.
	 * 
	 * @param x the X coordinate of the placement
	 * @param y the Y coordinate of the placement
	 */
	public void loadChip(int x, int y){
		
		
        URL url = RouletteTable.class.getClassLoader().getResource("chip.gif");
        JLabel chip = new JLabel();
        chip.setBounds(x,y,25,25);
        chip.setIcon(new ImageIcon(url)); 
        getContentPane().add(chip);    
        getContentPane().setLayout(null);
        chipList.add(chip);
	}
	
	/**
	 * Removes the placed chips from the screen.
	 * 
	 * @param l the JLabel of the chip
	 */
	public void freeChips(JLabel l){
		getContentPane().remove(l);
		refresh();
	}
	
	/**
	 * Returns the middle X coordinate at a specified point.
	 * 
	 * @param i specifies where to set the middle X coordinate
	 * @param cw the column width
	 * @param bw the border width
	 * @return the middle X coordinate at a specified point
	 */
	public int getMidX(int i, int cw, int bw){
		return (int)(310+((i)*cw+i*bw)+(cw/2))-12;
	}

	/**
	 * Returns the middle X coordinate at a specified point.
	 * 
	 * @param j specifies where to set the middle X coordinate
	 * @param ch the column height
	 * @param bw the border width
	 * @return the middle X coordinate at a specified point
	 */
	public int getMidY(int j, int ch, int bw){
		return (int)(353-((j)*ch+j*bw)-(ch/2))-12;
	}
	
	
	/**
	 * Returns the amount of money of the current bet.
	 * 
	 * @return the amount of money of the current bet
	 */
	public int getBackBet() {
		return backBet;
	}

	/**
	 * Sets the amount of money of the bet.
	 * 
	 * @param backBet the amount of money of the bet
	 */
	public void setBackBet(int backBet) {
		this.backBet = backBet;
	}


	/**
	 * Returns the player actual amount of money.
	 * 
	 * @return the player actual amount of money
	 */
	public int getYourCassa() {
		return yourCassa;
	}

	/**
	 * Sets the player actual amount of money.
	 * 
	 * @param yourCassa the player actual amount of money
	 */
	public void setYourCassa(int yourCassa) {
		this.yourCassa = yourCassa;
	}
	
	/**
	 * Not used method.
	 * 
	 * @param e e is a MouseEvent object
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Not used method.
	 * 
	 * @param e e is a MouseEvent object
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Not used method.
	 * 
	 * @param e e is a MouseEvent object
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Not used method.
	 * 
	 * @param e e is a MouseEvent object
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Returns a random number between 0 and 36.
	 * 
	 * @return a random number
	 */
	public static int randomGenerator(){
		int r;
		Random rand = new Random();
		r = rand.nextInt(37);
		log.info("Number " + r + " has been rolled.");
		return r;
	}
	
	/**
	 * Updates the highscore.
	 */
	public void xmlmethod(){
		String home=System.getProperty("user.home");
		File prop = new File(home, ".roulettegame");
		File file= new File(prop,"highscores.xml");
		ArrayList< XmlStructure> xmlcontainer = new ArrayList< XmlStructure >();
		xmlmanipulator.XMLReader xml = new xmlmanipulator.XMLReader("highscores.xml");
		XmlStructure x;
		
		if(file.exists()){
			xmlcontainer = xml.read();
		}
		
		x=new XmlStructure(playerName, yourCassa);
		xmlcontainer.add(x);

		MoneyComparator mc = new MoneyComparator();
		Collections.sort(xmlcontainer,mc);
		XMLWriter write = new XMLWriter(xmlcontainer,"highscores.xml");
		
		write.write();
	}
	
	/**
	 * This is a JButton Object.
	 */
	JButton menu;
	
	/**
	 * This is a JFrame Object.
	 */
	JFrame f;
	/**
	 * Decides what to do if an action has been performed.
	 * 
	 * @param e e is an ActionEvent object
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == spin) {
			if(backList.size()!=0){
				log.info(playerName + " named player has " + arCassa + " money before the roll.");
				log.info("The wheel has been rolled.");
				String Number;
				int Bet;
				int random = randomGenerator();
				writeRolled(random);
				for(ArrayList l : backList){
					Number = (String) l.get(0);
					Bet = (Integer) l.get(1);
					Roulette r = new Roulette(Number, Bet, arCassa, random);
					arCassa=r.newCassaValue();
				}
				RefreshYourMoneyValue(arCassa);
				backList.clear();
				chipFlag = true;
				backBet = 0;
				log.info(playerName + " named player has " + arCassa + " money after the roll.");
				if(arCassa==0){
					log.info(playerName + " named player has run out of money. The game has ended");
					f = new JFrame("Játék vége");
					f.setSize(500,300);
					f.setLocationRelativeTo(null);
					JLabel end = new JLabel("Sajnos elfogyott a pénzed!");
					end.setHorizontalAlignment(SwingConstants.CENTER);
					int endWidth=200;
					int x = (int)((f.getWidth()/2)-endWidth/2);
					end.setBounds(x, 50, endWidth, 20);
					f.add(end);
					
					JLabel end2 = new JLabel("De ne búsúlj bármikor kezdhetsz új játékot :)");
					int endWidth2=325;
					end2.setHorizontalAlignment(SwingConstants.CENTER);
					x = (int)((f.getWidth()/2)-endWidth2/2);
					end2.setBounds(x, 70, endWidth2, 20);
					f.add(end2);
					
					menu = new JButton("Vissza a főmenübe");
					int buttonWidth=175;
					x = (int)((f.getWidth()/2)-buttonWidth/2);
					menu.addActionListener(this);
					menu.setBounds(x,200,buttonWidth,30);
					f.add(menu);
					
					JLabel apcalypse = new JLabel("");
					apcalypse.setBounds(10, 10, 100, 20);
					f.add(apcalypse);
					f.setVisible(true);
				}
			}
		}
		if(e.getSource() == exit ){
			log.info(playerName + " named player finished the game.");
			log.info("MainMenu called by RouletteTable.");
			log.info("RouletteTable closed.");
			xmlmethod();
			remove(this);
			dispose();
			roulette.Application.mainMenu();
		}
		if(e.getSource() == menu){
			log.info("MainMenu called by RouletteTable.");
			log.info("RouletteTable closed.");
			f.dispose();
			dispose();
			roulette.Application.mainMenu();
		}
	}
}