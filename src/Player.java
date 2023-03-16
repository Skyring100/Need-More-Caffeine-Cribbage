package src;

import src.card.Card;

import java.util.ArrayList;
import java.util.Random;


public class Player {

	private static String[] randomName = new String[]{"Joe","Wizard","Bonk","Snake","Mad Lad","USB Lad","Mastermind", "Fire","Ice","Nerd"};
	protected String name;
	protected int score;
	protected ArrayList<Card> hand;
	protected ArrayList<Card> pegHand;
	
	/**
	 * Player constructor that takes in a name
	 * @param name user provided name
	 */
	public Player(String name) {
		this.name = name;
		this.score = 0;
		hand = new ArrayList<>();
		pegHand = new ArrayList<>();
	}

	/**
	 * Player constructor that generates a random player name
	 */
	public Player(){
		this(randomName[new Random().nextInt(0,randomName.length)]);
	}

	/**
	 * Returns player name in String format
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Returns the score of player that called the method
	 * @return Score in int format
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Adds an integer value to the player who called the methods points
	 * @param points The amount of points to add
	 */
	public void addScore(int points) {
		this.score += points;
	}

	/**
	 * Sets the player who called the methods hand to the given arraylist of cards
	 * @param cards the arraylist of cards to set.
	 */
	public void setHand(ArrayList<Card> cards) {
		this.hand = cards;
	}

	/**
	 * Returns the Hand arraylist of the player that called the method
	 * @return The arraylist of cards to get
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}

	/**
	 * Returns the pegging hand arraylist of the current round
	 * @return
	 */
	public ArrayList<Card> getPegHand(){
		return pegHand;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	public Card discard(Card c){
		hand.remove(c);
		return c;
	}
	/**
	 * Checks whether or not the current player can play a card
	 * @param game game object to access the variables
	 * @return A boolean corresponding to whether the user can peg or not
	 */
	public boolean canPeg(Game game) {

		for (Card card : pegHand) {
			if (card.getCribCount() <= 31 - game.getPegValue()) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Removes Card from the Peg hand arraylist and adds to scoring list
	 * @param game The game being played
	 * @param c card to manipulate
	 */
	public void pegCard(Game game, Card c) {
		pegHand.remove(pegHand.indexOf(c));
		game.addToPegList(c);
	}

	/**
	 * Resets the pegging hand.
	 */
	public void readyPegging(){
		pegHand.clear();
		for(Card c : hand){
			pegHand.add(c);
		}
	}
}