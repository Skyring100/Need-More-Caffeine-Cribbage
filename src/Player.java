package src;

import src.card.Card;

import java.util.ArrayList;
import java.util.Random;


public class Player {
	private static String[] randomName = new String[]{"Joe","Wizard","Bonk","Snake","Mad Lad","USB Lad","Mastermind",
			"Fire","Ice","Nerd"};
	protected String name; // name of the player
	protected int score;
	protected ArrayList<Card> hand;
	protected ArrayList<Card> pegHand;
	public Player(String name) {
		this.name = name;
		this.score = 0;
		hand = new ArrayList<>();
		pegHand = new ArrayList<>();
	}
	public Player(){
		this(randomName[new Random().nextInt(0,randomName.length)]);
	}
	@Override
	public String toString() {
		return name;
	}
	public int getScore() {
		return this.score;
	}
	public void addScore(int points) {
		this.score += points;
	}
	public void setHand(ArrayList<Card> cards) {
		this.hand = cards;
	}
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	public ArrayList<Card> getPegHand(){
		return pegHand;
	}
	public void discard(Game game, ArrayList<Card> list) {
		
		for(int i = 0;i<list.size();i++) {
			hand.remove(hand.indexOf(list.get(i)));
		}
		game.addToCrib(list);
		pegHand = hand;
	}
	public Card discard(Card c){
		hand.remove(c);
		return c;
	}
	
	/**
	 * 
	 * @param game game object to access the variables
	 * @return true if a card is able to be played, and false if a card is not able to be played
	 */
	public boolean checkAllCard(Game game) {
		
		for(int i = 0;i<pegHand.size();i++) {
			if(pegHand.get(i).getCribCount() <= 31-game.getPegValue()) {
				
				return true;
				
			}
			
		}
		return false;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	public void pegCard(Game game, Card c) {
		int index = pegHand.indexOf(c);
		if(index == -1){
			System.out.println("Error, card not found");
			System.out.println("Card to be pegged: "+c);
			System.out.println(this+"'s hand: "+hand);
		}
		pegHand.remove(index);
		game.addToPegList(c);
		game.addToPegValue(c);
	}

	/**
	 * Reset the pegging hand. This should be done everytime a pegging round starts
	 */
	public void readyPegging(){
		pegHand.clear();
		for(Card c : hand){
			pegHand.add(c);
		}
	}
}

