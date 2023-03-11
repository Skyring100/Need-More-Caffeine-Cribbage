package src;

import java.util.ArrayList;


import src.card.Card;

public class Bot extends Player{
	public Bot() {
		super("Bot");
	}

	@Override
	public void discard(Game game, ArrayList<Card> list) {
		
		ArrayList<ArrayList<Card>> sets = Game.makeSubset(this.hand);
		ArrayList<Card> discardedCards = new ArrayList<>();
		ArrayList<Card> highest = sets.get(0);
		ArrayList<Card> temp;
		
		for(int i = 0;i<sets.size();i++) { // goes through each subset
			
			if(sets.get(i).size() == 4) { // if subset is length 4, check if the points from this hand is higher than of the previous highest hand
				temp = sets.get(i);
				
				if(Game.countPoints(highest) <= Game.countPoints(temp)) {
					highest = temp;
				}
			}
		}
		// everything below this discards the cards from hand and adds them to crib
		for(int k = 0;k<hand.size();k++) {
			if(!highest.contains(hand.get(k))) {
				discardedCards.add(hand.get(k));
			}
		}
		setHand(highest);
		game.addToCrib(discardedCards);
		pegHand = highest;
	}
	public Card pegAlgorithm(ArrayList pegList,int pegScore){
		ArrayList<Card> temp = new ArrayList<>();
		ArrayList<Card> temp1 = new ArrayList<>();
		ArrayList<Card> temp2 = new ArrayList<>();
		for(int i = 0;i<pegHand.size();i++) {
			if(pegHand.get(i).getCribCount() <= 31-pegScore) {
				temp.add(pegHand.get(i));	
			}
		}
		
		if(temp.size() == 1) {
			return temp.get(0);
		}
		if(temp.size() == 2) {
			
		}
		return null;
	}
	public Card discardAlgorithm(){
		return null;
	}
	
}


