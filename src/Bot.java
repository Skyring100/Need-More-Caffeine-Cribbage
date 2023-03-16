package src;

import java.util.ArrayList;


import src.card.Card;

public class Bot extends Player{
	public Bot() {
		super();
	}
	public Bot(String name){
		super(name);
	}

	
	/**
	 * this method will check for the best card to p;eg each time it is needed to do so, by returning the card which scores the highest.
	 * 
	 * @param pegList
	 * @param pegScore
	 * @return
	 */
	public Card pegAlgorithm(ArrayList<Card> pegList,int pegScore){
		
		ArrayList<Card> temp = new ArrayList<>();
		Card c1;
		Card c2;
		Card c3;
		Card c4;
		Card returnedCard = null;
		for (Card card : pegHand) { // goes through each card to make sure that the card is able to be played
			if (card.getCribCount() <= 31 - pegScore) {
				temp.add(card);
			}
		}
		
		if(temp.size() == 1) {
			returnedCard = temp.get(0); // returns the only card in the hand
		}
		if(temp.size() == 2) { // returns the higher scoring of the 2 cards
			c1 = temp.get(0);
			c2 = temp.get(1);
			if(Game.botPegPoints(pegList, c1) >= Game.botPegPoints(pegList, c2)) {
				returnedCard = c1;
			}else {
				returnedCard = c2;
			}
		}
		if(temp.size() == 3) { // returns the higher scoring of the three cards
			Card maxtemp = temp.get(0);
			c1 = temp.get(1);
			c2 = temp.get(2);
			
			
			if(Game.botPegPoints(pegList, maxtemp) <= Game.botPegPoints(pegList, c1)){
				maxtemp = c1;
			}
			if(Game.botPegPoints(pegList, maxtemp)<= Game.botPegPoints(pegList, c2)) {
				maxtemp = c2;
			}
			returnedCard = maxtemp;
		}
		if(temp.size() == 4) { // returns the highest scoring of the 4 cards
			Card maxtemp = temp.get(0);
			c1 = temp.get(1);
			c2 = temp.get(2);
			c3 = temp.get(3);
			
			
			if(Game.botPegPoints(pegList, maxtemp) <= Game.botPegPoints(pegList, c1)){
				maxtemp = c1;
			}
			if(Game.botPegPoints(pegList, maxtemp)<= Game.botPegPoints(pegList, c2)) {
				maxtemp = c2;
			}
			if(Game.botPegPoints(pegList, maxtemp)<= Game.botPegPoints(pegList, c3)) {
				maxtemp = c3;
			}
			
			returnedCard = maxtemp;
				
		}
		//if there is no card chose, just choose the first card
		if(returnedCard == null){
			return pegHand.get(0);
		}
		return returnedCard;
	}
	/**
	 * this method will find the best card to dsicard which results in the highest scoring hand.
	 */
	public Card discardAlgorithm(){
		ArrayList<ArrayList<Card>> sets = Game.makeSubset(this.hand);
		ArrayList<Card> highest = sets.get(0);
		ArrayList<Card> temp;

		for (ArrayList<Card> set : sets) { // goes through each subset

			if (set.size() == 4) { // if subset is length 4, check if the points from this hand is higher than of the previous highest hand
				temp = set;

				if (Game.countPoints(highest) <= Game.countPoints(temp)) {
					highest = temp;
				}
			}
		}
		// everything below this discards a card which is not contained within the 4 highest scoring cards
		for (Card card : hand) {
			if (!highest.contains(card)) {
				return card;
			}
		}
		return hand.get(0);
	}
	
}
//