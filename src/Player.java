package src;

import src.card.Card;

import java.io.File;
import java.util.ArrayList;


public class Player {
protected final String name;
protected int score;
protected ArrayList<Card> hand;
protected ArrayList<Card> pegHand;
	public Player(String name) {
		this.name = name;
		this.score = 0;
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
	public void discard(Game game, ArrayList<Card> list) {
		
		for(int i = 0;i<list.size();i++) {
			hand.remove(hand.indexOf(list.get(i)));
		}
		game.addToCrib(list);
		pegHand = hand;
		
	}
	public void removeCard(Game game,Card c) {
		
		pegHand.remove(pegHand.indexOf(c));
		game.addToPeglist(c);
		game.addToPegValue(c);
		
		
	}
	/**
	 * 
	 * @param game game object to access the variables
	 * @return true if a card is able to be played, and false if a card is not able to be played
	 */
	public boolean checkAllCard(Game game) {
		
		for(int i = 0;i<pegHand.size();i++) {
			if(pegHand.get(i).getValue() <= 31-game.getPegValue()) {
				
				return true;
				
			}
			
		}
		return false;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
}

