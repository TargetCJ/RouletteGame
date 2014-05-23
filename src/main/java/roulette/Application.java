package roulette;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import panel.RouletteTable;
import xmlmanipulator.XMLWriter;


/**
 * Class contains the main method of the project.
 */
public class Application {

	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * This is the main method of the project.
	 * 
	 * @param args0 args0 is a String array Object
	 */
	public static void main(String[] args0) {
		log.info("The program started.");
		log.info("Creating MainMenu window.");
		mainMenu();
		
	}
	
	/**
	 * This is a static method that creates {@code RouletteTable} Object.
	 * 
	 * @param name the name of the player
	 */
	public static void game(String name){
		int Cassa = 500;
		log.info("Creating RouletteTable window.");
		RouletteTable ss = new RouletteTable(Cassa);
		ss.setName(name);
	}
	
	/**
	 * This is a static method that creates {@code MainMenu} Object.
	 */
	public static void mainMenu(){
		log.info("Creating MainMenu window.");
		panel.MainMenu mm = new panel.MainMenu();
	}
	
	/**
	 * This is a static method that creates {@code PlayerName} Object.
	 * 
	 * @param f f is a JFrame Object	 
	 */
	public static void setName(JFrame f){
		log.info("Creating PlayerName window.");
		panel.PlayerName pn = new panel.PlayerName(f);
	}
	
	/**
	 * This is a static method that creates {@code HighScore} Object.
	 */
	public static void HishScore(){
		log.info("Creating HishScore window.");
		panel.HighScore hs = new panel.HighScore();
	}
	

}
