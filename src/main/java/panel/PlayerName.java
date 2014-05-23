package panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A JFrame class create window to set player name.
 */
public class PlayerName extends JFrame implements ActionListener{
	
	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(PlayerName.class);
	
	/**
	 * This is a JTextField of this frame.
	 */
	private JTextField textField;
	
	/**
	 * This is a JButton of this frame.
	 */
	private JButton button;
	
	/**
	 * This is a JLabel of this frame.
	 */
	private JLabel label;
	
	/**
	 * This is a JFrame of this frame.
	 */
	JFrame main;
	/**
	 * Constructor for creating an {@code PlayerName} object.
	 * 
	 * @param f JFrame of the Main Menu
	 */
	public PlayerName(JFrame f) {
		this.main=f;
		setSize(350,250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	
		label = new JLabel("Add meg a neved!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		int labelWidth=150;
		int x = (int)((getWidth()/2)-labelWidth/2);
		label.setBounds(x, 58, labelWidth, 25);
		getContentPane().add(label);
		
		textField = new JTextField();
		int textFWidth=200;
		x = (int)((getWidth()/2)-textFWidth/2);
		textField.setBounds(x, 90, textFWidth, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("OK");
		int buttonWidth=80;
		x = (int)((getWidth()/2)-buttonWidth/2);
		button.setBounds(x, 150, 80, 23);
		button.addActionListener(this);
		getContentPane().add(button);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		log.info("Player name successfully created.");
	}
	
	/**
	 * Decides what to do if an action has been performed.
	 * 
	 * @param e e is an ActionEvent object
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			String tmp = textField.getText();
			log.info("RouletteTable called by PlayerName.");
			log.info("PlayerName and Main menu closed.");
			roulette.Application.game(tmp);
			remove(this);
			dispose();
			main.dispose();
		}
	}
}
