/**
 *	Assignment A for City&Guilds exam
 *
 *	@author Przemyslaw Piotrowiak
 */
 
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class GuessWord extends JFrame {
	
	GuessWordEvent guess = new GuessWordEvent(this);
	
	JPanel panel0 = new JPanel();
	JPanel panelN = new JPanel();
	JPanel panelW = new JPanel();
	JPanel panelE = new JPanel();
	JPanel panelS = new JPanel();
	
	JPanel panel1 = new JPanel();
	JLabel guessWordL = new JLabel("GUESS THE WORD");
	JTextField guessWord = new JTextField(24);
	JLabel guessesRemainingL = new JLabel("GUESSES REMAINING");
	JTextField guessesRemaining = new JTextField(3);
	
	JPanel panel2 = new JPanel();
	JPanel panel21 = new JPanel();
	JPanel panel22 = new JPanel();
	JButton[] buttons = new JButton[28]; 
	
	JPanel panel11 = new JPanel();
	JPanel panel111 = new JPanel();
	JPanel panel112 = new JPanel();
	JPanel panel113 = new JPanel();
	
	
	public GuessWord() {
		
		// call constructor of superclass to give a name for the window
		super("Guess Word");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(20,20));
		
		setSize(700, 400);
		setVisible(true);
		
		panel0.setLayout(new GridLayout(2,1));
		
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 60, 10));
		
		panel11.setLayout(new GridLayout(3,1));
		guessWordL.setForeground(Color.RED);
		panel111.add(guessWordL);
		// set as read only
		guessWord.setHorizontalAlignment(JTextField.RIGHT);
		guessWord.setEditable(false);
		panel112.add(guessWord);
		panel112.setLayout(new FlowLayout(FlowLayout.RIGHT));
		guessesRemainingL.setForeground(Color.RED);
		panel113.add(guessesRemainingL);
		guessesRemaining.setHorizontalAlignment(JTextField.RIGHT);
		guessesRemaining.setEditable(false);
		panel113.add(guessesRemaining);
		panel113.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		panel11.add(panel111);
		panel11.add(panel112);
		panel11.add(panel113);
		panel1.add(panel11);
		
		
		
		
		
		panel0.add(panel1);
		
		panel2.setLayout(new GridLayout(4,7,0,10));
		
		// add buttons with letters
		int i = 0;
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			buttons[i] = new JButton(Character.toString(letter));
			
			panel2.add(buttons[i]);
			i++;
		}
		
		// add special buttons
		buttons[i] = new JButton("START");
		panel2.add(buttons[i++]);
		buttons[i] = new JButton("ANSWER");
		panel2.add(buttons[i]);
		
		// add listeners
		for (int j = 0; j < buttons.length; j++) {
			buttons[j].addActionListener(guess);
		}
		
		panel0.add(panel2);
		
		add(panel0, BorderLayout.CENTER);
		add(panelN, BorderLayout.NORTH);
		add(panelW, BorderLayout.WEST);
		add(panelE, BorderLayout.EAST);
		add(panelS, BorderLayout.SOUTH);
		
		
	}
	
	public static void main(String [] args) {
		GuessWord program = new GuessWord();
	}
}