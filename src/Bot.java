package src;

import java.util.ArrayList;


import src.card.Card;

public class Bot extends Player{
	public Bot() {
		super("Bot");
	}

	
	/**
	 * this will find the best card to peg whenever it is the bots turn to play a card
	 * 
	 */
	public Card pegAlgorithm(ArrayList pegList,int pegScore){
		ArrayList<Card> temp = new ArrayList<>();
		Card c1;
		Card c2;
		Card c3;
		Card c4;
		Card returnedCard = null;
		for(int i = 0;i<pegHand.size();i++) {
			if(pegHand.get(i).getCribCount() <= 31-pegScore) {
				temp.add(pegHand.get(i));	
			}
		}
		
		if(temp.size() == 1) {
			returnedCard = temp.get(0);
		}
		if(temp.size() == 2) {
			c1 = temp.get(0);
			c2 = temp.get(1);
			if(Game.botPegPoints(pegList, c1) >= Game.botPegPoints(pegList, c2)) {
				returnedCard = c1;
			}else {
				returnedCard = c2;
			}
		}
		if(temp.size() == 3) {
			c1 = temp.get(0);
			c2 = temp.get(1);
			c3 = temp.get(2);
			int maxOfNums = Math.max(Game.botPegPoints(pegList, c1), Math.max(Game.botPegPoints(pegList, c2),Game.botPegPoints(pegList, c3)));
			
			if(Game.botPegPoints(pegList, c1) == maxOfNums) {
				returnedCard = c1;
			}
			if(Game.botPegPoints(pegList, c2) == maxOfNums) {
				returnedCard = c2;
			}
			if(Game.botPegPoints(pegList, c3) == maxOfNums) {
				returnedCard = c3;
			}
			
			
		}
		if(temp.size() == 4) {
			c1 = temp.get(0);
			c2 = temp.get(1);
			c3 = temp.get(2);
			c4=temp.get(3);
			int maxOfNums = Math.max(Game.botPegPoints(pegList, c1), Math.max(Game.botPegPoints(pegList, c2),Math.max(Game.botPegPoints(pegList, c3),Game.botPegPoints(pegList, c4))));
			
			if(Game.botPegPoints(pegList, c1) == maxOfNums) {
				returnedCard = c1;
			}
			if(Game.botPegPoints(pegList, c2) == maxOfNums) {
				returnedCard = c2;
			}
			if(Game.botPegPoints(pegList, c3) == maxOfNums) {
				returnedCard = c3;
			}
			if(Game.botPegPoints(pegList, c4) == maxOfNums) {
				returnedCard = c4;
			}
			
			
		}
		pegHand.remove(pegHand.indexOf(returnedCard));
		return returnedCard;
		//
	}
	/*
	 * this method will find the best card to discard in the discard phase
	 */
	public Card discardAlgorithm(){
		ArrayList<ArrayList<Card>> sets = Game.makeSubset(this.hand);
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
		// everything below this discards a card which is not contained within the 4 highest scoring cards
		for(int k = 0;k<hand.size();k++) {
			if(!highest.contains(hand.get(k))) {
				return hand.get(k);
			}
		}
		return null;
		
	}
	
}
//


