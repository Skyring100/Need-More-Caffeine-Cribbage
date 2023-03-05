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
		
	}
	public void addCard(Card c) {
		hand.add(c);
	}
}

