/**
 *	This is event handling class for the GuessWord application
 *
 *	@author Przemyslaw Piotrowiak
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JLabel;


public class GuessWordEvent implements ActionListener {
	
	GuessWord gui;
	
	public GuessWordEvent(GuessWord in) {
		gui = in;
		
	}
	
	// instance variables
	String[] words = {"MEMORY", "COMPUTER", "PRINTER", "TROUSERS", "BUTTERCUP"}; //table of words to guess
	String toGuess;	// word to guess
	String toDisplay; // word to display
	int toGuessPosition; // I dunno :(
	int lives; // lives left
	
	// check what button was pressed on the screen
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if (command.equals("START")) {
			startPlaying();
		}
		else if (command.equals("ANSWER")) {
			checkAnswer();
		}
		else {
			checkLetter(command);
		}
		
	}
	
	private void startPlaying() {
		System.out.println("game starts");
		// reset number of lives
		lives = 8;
		// display number of lives
		gui.guessesRemaining.setText(Integer.toString(lives));
		
		// display word to guess, take from the array
		toGuess = words[(toGuessPosition++) % words.length];
		toDisplay = starIt(toGuess);
		gui.guessWord.setText(toDisplay);
		
	}
	
	// if Check button clicked show the word and unable to guess further
	private void checkAnswer() {
		System.out.println("Checking the answer");
		if (gui.guessWord.getText() != "") {
			gui.guessWord.setText(toGuess);
		}
		gui.guessesRemaining.setText("");
	}
	
	// check if clicked letter is in the word
	private void checkLetter(String letter) {
		System.out.println("Checking " + letter);
		
		// if no word to guess or Answer pressed leave the method and do nothing
		if ((gui.guessWord.getText().equals("")) || gui.guessesRemaining.getText().equals("")) return;
		
		// if clicked letter is not included in the actual word take 1 life and refresh remaining lives
		if (toGuess.indexOf(letter) == -1) {
			lives--;
			gui.guessesRemaining.setText(Integer.toString(lives));
			if (lives <= 0) {
				youLose();
			}
		}
		// if the word contains the litter clicked
		else {
			System.out.println("Guessed a letter");
			toDisplay = unstarLetter(toDisplay, toGuess, letter);
			gui.guessWord.setText(toDisplay);
		}
		
		// if no more asterisks, player wins
		if (toDisplay.indexOf('*') == -1) {
			youWin();
		}
			
	}
	
	private void youLose() {
		System.out.println("You lose");
		
		String message = "You Lose          ";
		JLabel msgLabel = new JLabel(message, JLabel.CENTER);
		msgLabel.setForeground(Color.RED);
		msgLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		JOptionPane.showMessageDialog(gui, msgLabel);
		
		// going back to the game:
		startPlaying();
	}
		
	private void youWin() {
		System.out.println("You win");
		
		String message = "You Win          ";
		JLabel msgLabel = new JLabel(message, JLabel.CENTER);
		msgLabel.setForeground(Color.RED);
		msgLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		JOptionPane.showMessageDialog(gui, msgLabel);
		
		// going back to the game:
		startPlaying();	
	}
	
	// prepare word to display starred
	private String starIt(String word) {
		String starred = "";
		
		if (word.length() > 0) {
			for (int i = 0; i < word.length(); i++) {
				starred += "*";
			}
		}
		return starred;
	}
	
	// if guess correct, unveil letter
	private String unstarLetter(String starredWord, String originalWord, String thisLetter) {
		System.out.println("Unstar letter " + thisLetter);
		char letter = thisLetter.charAt(0);
		System.out.println("char letter=" + letter);
		// because Strings are immutable convert it to array of characters
		System.out.println(starredWord);
		char[] starredWordChar = starredWord.toCharArray();
		char[] originalWordChar = originalWord.toCharArray();
		System.out.println("Strings converted to arrays");	
		// replace asterisks with letters from original word
		for (int i = 0; i < originalWordChar.length; i++) {
			if (originalWordChar[i] == letter) {
				starredWordChar[i] = originalWordChar[i];
			}
		}
		// convert array of char back to String
		starredWord = String.valueOf(starredWordChar);
		return starredWord;
	}
}
