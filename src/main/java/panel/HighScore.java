package panel;

import javax.swing.JFrame;
import javax.swing.JLabel;

import xmlmanipulator.XmlStructure;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that builds the highscore window.
 */
public class HighScore extends JFrame implements ActionListener{
	
	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(HighScore.class);

	/**
	 * This is a JLabel of this frame.
	 */
	JLabel highestMoney;
	
	/**
	 * This is a JLabel of this frame.
	 */
	JLabel notExists;
	
	/**
	 * This is a JButton of this frame.
	 */
	JButton back;
	
	/**
	 * Constructor for creating an {@code HighScore} object.
	*/
	public HighScore() {
		setSize(500,750);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setLabels();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Sets the JFrame's components.
	 */
	public void setLabels(){
		JLabel highestMoney = new JLabel("Legtöbb pénzzel távoztak");
		highestMoney.setFont(new Font("Tahoma", Font.PLAIN, 16));
		highestMoney.setBounds(25, 50, 227, 32);
		getContentPane().add(highestMoney);
		
		back = new JButton("Vissza");
		back.setBounds(25, 623, 89, 23);
		back.addActionListener(this);
		getContentPane().add(back);
		
		writeScores();
		
		log.info("Highscore successfully created.");
	}
	
	/**
	 * Decides if a certain file exists.
	 * 
	 * @return <code>true</code> if files exists and <code>false</code> if file does not exists.
	 */
	public boolean letezik(){
		String home=System.getProperty("user.home");
		File prop = new File(home, ".roulettegame");
		File file= new File(prop,"highscores.xml");
		return file.exists();
	}
	
	/**
	 * Method writes the highscores to the highscore window.
	 */
	public void writeScores(){
		if(!letezik()){
			notExists = new JLabel("Még nincsenek highscoreok :(");
			notExists.setBounds(25, 100, 227, 32);
			getContentPane().add(notExists);
			
		}
		else{
			xmlmanipulator.XMLReader xml = new xmlmanipulator.XMLReader("highscores.xml");
			ArrayList< XmlStructure > list = new ArrayList<XmlStructure>();
			list = xml.read();
			String s = "";
			JLabel hspos[] = new JLabel[5];
			JLabel hsname[] = new JLabel[5];
			JLabel hsmoney[] = new JLabel[5];
			for(int i=0;i<list.size();i++){
				String tmp = (i+1) + ".";
				hspos[i] = new JLabel(tmp);
				hspos[i].setBounds(50, 100+(50*i), 20, 30);
				getContentPane().add(hspos[i]);
				hsname[i] = new JLabel(list.get(i).getName());
				hsname[i].setBounds(75, 100+(50*i), 100, 30);
				getContentPane().add(hsname[i]);
				tmp = list.get(i).getMoney() + "";
				hsmoney[i] = new JLabel(tmp + " $");
				hsmoney[i].setBounds(200, 100+(50*i), 75, 30);
				getContentPane().add(hsmoney[i]);
			}
		}
			
	}

	/**
	 * Decides what to do if an action has been performed.
	 * 
	 * @param e e is an ActionEvent Object
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back){
			log.info("MainMenu called by HighScore.");
			log.info("Highscore closed.");
			remove(this);
			dispose();
			roulette.Application.mainMenu();
		}
	}
}
