package src;

import src.card.Card;

import java.io.File;
import java.util.ArrayList;

public class Player {
protected final String name;
protected int score;
protected ArrayList<Card> hand;

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

