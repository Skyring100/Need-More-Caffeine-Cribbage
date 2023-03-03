package src;

import java.util.ArrayList;

public class Player {
private final String name;
private int score;
private ArrayList<Card> hand;

	public Player(String name) {
		this.name = name;
		this.score = 0;
	}
	public int getScore() {
		return this.score;
	}
	public void addScore(int points) {
		this.score += points;
	}
	public void setHand(ArrayList cards) {
		
		this.hand = cards;
		
	}
	public ArrayList getHand() {
		return this.hand;
	}
	public void discard(Card c) {
		hand.remove(hand.indexOf(c));
	}
	public void addCard(Card c) {
		hand.add(c);
	}

}

