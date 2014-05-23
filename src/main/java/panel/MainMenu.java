package panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roulette.Application;

import java.awt.Color;

/**
 * Class responsible for building the main menu window.
 */
public class MainMenu extends JFrame implements ActionListener{
	
	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(MainMenu.class);
	
	/**
	 * This is a JFrame of this frame.
	 */
	private JFrame f = new JFrame("Roulette Game");
	
	/**
	 * This is a JButton of this frame.
	 */
	private JButton newGame;
	
	/**
	 * This is a JLabel of this frame.
	 */
	private JLabel gameName;
	
	/**
	 * This is a JButton of this frame.
	 */
	private JButton exitbutton;
	
	/**
	 * This is a JButton of this frame.
	 */
	private JButton highscore;
	
	/**
	 * Constructor for creating an {@code MainMenu} object.
	*/
	public MainMenu() {
		f.setContentPane(new JLabel(new ImageIcon(RouletteTable.class.getClassLoader().getResource("casino-05.jpg"))));	
		setMenu();
		f.setVisible(true);
		log.info("Main Menu successfully created");
	}
	
	/**
	 * Sets the JFrame and it's components.
	 */
	public void setMenu(){
		f.setSize(600,700);
		f.setLocationRelativeTo(null);
		gameName = new JLabel("Ultimate Roulette Game 2000");
		gameName.setForeground(Color.RED);
		gameName.setFont(new Font("Old English Text MT", Font.PLAIN, 30));
		gameName.setHorizontalAlignment(SwingConstants.CENTER);
		gameName.setBounds(57, 70, 466, 103);
		f.getContentPane().add(gameName);
		
		newGame = new JButton("Start Game");
		newGame.setFont(new Font("Tahoma", Font.PLAIN, 17));
		newGame.setBounds(170, 223, 241, 85);
		newGame.addActionListener(this);
		f.getContentPane().add(newGame);
		
		highscore = new JButton("Highscore");
		highscore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		highscore.setBounds(170, 352, 241, 85);
		highscore.addActionListener(this);
		f.getContentPane().add(highscore);
		
		exitbutton = new JButton("Exit");
		exitbutton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		exitbutton.setBounds(170, 534, 241, 85);
		exitbutton.addActionListener(this);
		f.getContentPane().add(exitbutton);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	/**
	 * Decides what to do if an action has been performed.
	 * 
	 * @param e e is an ActionEvent Object
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newGame){
			log.info("Player name window called from Main Manu.");
			roulette.Application.setName(f);
		}
		if(e.getSource()==exitbutton){
			log.info("Game closed by Exit button.");
			System.exit(0);
		}
		if(e.getSource()==highscore){
			log.info("Highscore called by Main Menu.");
			log.info("Main Menu closed.");
			f.remove(this);
			f.dispose();
			roulette.Application.HishScore();		
		}
		
		
	}
}
