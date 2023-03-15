package src;

import src.GUI.GUI;
import src.card.Card;
import src.card.Deck;
import src.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This holds all necessary rules and functionality to play a game of cribbage
 */
public class Game {
	private final static int WINNING_SCORE = 121;
	private final Player player1;

	public Player getPlayer1() {
		return player1;
	}


	public ArrayList<Card> getCurrentPegList() {
		return currentPegList;
	}

	private final Player player2;
	private Player currentDealer;
	private Player currentPone;
	private Player winner = null;
	private ArrayList<Card> crib = new ArrayList<>();
	private ArrayList<Card> currentPegList = new ArrayList<>();
	private Card flippedCard;
	private int currentPegValue;
	private final Deck deck;
	private GUI gui;

	/**
	 * Creates a two game with two human players
	 * @param one player one
	 * @param two player two
	 */
    public Game(Player one, Player two) {
		player1 = one;
		player2 = two;
		//this is an EXTREMELY compact way of getting a random dealer
		//this creates an array of the players and uses a random number generator to choose an index from that list
		currentDealer = new Player[]{player1,player2}[new Random().nextInt(0,2)];
		if(currentDealer == player1){
			currentPone = player2;
		}else{
			currentPone = player1;
		}
		deck = new Deck();
		javax.swing.SwingUtilities.invokeLater(()-> gui = new GUI(this));
		run();
    }
	/**
	 * Creates a single-player game with a human vs. a bot
	 * @param p the user
	 */
	public Game(Player p){
		this(p, new Bot());
	}

	/**
	 * The main game method, which executes a round in cribbage
	 */
	private void run(){
		System.out.println("\nNew Round!\n");
		
		dealPlayers();
		
		System.out.println("\nDealing");
		System.out.println(player1+": "+player1.getHand());
		System.out.println(player2+": "+player2.getHand());
		discardPhase();
		System.out.println("\nDiscarding");
		System.out.println(player1+": "+player1.getHand());
		System.out.println(player2+": "+player2.getHand());
		flippedCard = deck.draw();
		System.out.println("Flipped card: "+flippedCard);
		if(flippedCard.getRank() == Rank.JACK) {
			currentPone.addScore(2);
		}
		peg();
		System.out.println(player1+": "+player1.getScore());
		System.out.println(player2+": "+player2.getScore());
		winner = checkWinner();
		if(winner != null){
			System.out.println(winner+" is the winner");
		}else if(player1 instanceof Bot && player2 instanceof Bot){
			//if there are just bots playing, we can let them do the whole game without the need for a gui
			reRun();
		}
	}

	/**
	 * To go onto the next round, use this method to set up everything again
	 */
	public void reRun(){
		//this part might be added into the gui class when a "submit" button or something is pressed
		//that way, the game will not run until the player presses a button
		switchDealer();
		System.out.println(deck.showDeck());
		deck.shuffleDiscard();
		run();
	}

	private void discardPhase() {
		Card currentCard;
		//loop through all 2 players
		for(Player p : new Player[]{player1,player2}){
			//each player discards 2 cards
			for(int i = 1; i <= 2; i++){
				if(p instanceof Bot){
					currentCard =  p.discard(((Bot) p).discardAlgorithm());
				}else{
					//let the player choose a card
					currentCard = null;
				}
				crib.add(currentCard);
			}
		}
	}

	private void peg(){
		System.out.println("\nPegging start");
		// this is the pegging section, hasn't been tested, however I do believe that it should work

		Player currentPlayer;
		//sets the pegging hands of all players. This will be manipulated and checked as pegging occurs
		currentPone.readyPegging();
		currentDealer.readyPegging();
		//A turn counter
		int counter = 0;
		do {
			//if it's an even turn, the pone goes, else the dealer goes
			if(counter % 2 == 0) {
				currentPlayer = currentPone;
				
				
			}else {
				currentPlayer = currentDealer;
				
			}

			System.out.println(currentPlayer+"'s cards: "+currentPlayer.getPegHand());
			System.out.println("Peg List:"+currentPegList);

			//checks if the player has cards and is able to play a card
			if(currentPlayer.getPegHand().size() != 0 && currentPlayer.canPeg(this)) {
				Card peggingCard;
				//if a player is a bot, use an algorithm to find a suitable card for pegging
				if(currentPlayer instanceof Bot) {
					peggingCard =  ((Bot) currentPlayer).pegAlgorithm(currentPegList,currentPegValue);
				}
				else {
					peggingCard = currentPlayer.getPegHand().get(0);
					// the card for this method will need to be changed to the card selected
				}
				currentPlayer.pegCard(this,peggingCard);
				//gui for pegged card
				currentPlayer.addScore(pegPoints(currentPegList));
				if(checkWinner()!= null) {
					//we want to return instead of break; we do not care about any extra points now that someone has already won
					//if we do not return, there is a chance the other player might appear to win as well which is not possible
					return;
				}
			}
			System.out.println("Peg List: "+currentPegList+" Peg value: "+currentPegValue);
			System.out.println(currentPlayer+"'s cards: "+currentPlayer.getPegHand());
			System.out.println(currentPlayer+"'s score "+currentPlayer.getScore());
			System.out.println();
			counter++;

			if(!currentDealer.canPeg(this) && !currentPone.canPeg(this)) { // checking to see if both players can't play a card
				System.out.println("No possible plays from either player, new peg list");
				if(currentPegValue == 31) {
					currentPlayer.addScore(2);
					//gui for hitting 31 exactly
				}else {
					currentPlayer.addScore(1);
					//gui for finishing last
				}
				
				if(checkWinner() != null) {
					//we want to return instead of break; we do not care about any extra points now that someone has already won
					return;
				}
					currentPegList.clear();
					currentPegValue = 0;
					
			}
		}while(currentDealer.getPegHand().size() != 0 || currentPone.getPegHand().size() != 0); // do the pegging while a player has at least 1 card in their hand
		System.out.println("\nDone pegging");
		System.out.printf("Crib: %s%n%s (Dealer): %s%n%s (Pone): %s%n",crib,currentDealer,currentDealer.getHand(),currentPone,currentPone.getHand());
		System.out.println("Flipped card: "+flippedCard);
		//adding the flipped card to the scoring hands
		ArrayList<Card> tempHandScoring = combineFlippedCard(currentPone.getHand());
		currentPone.addScore(countPoints(tempHandScoring)+countNob(tempHandScoring));

		tempHandScoring = combineFlippedCard(crib);

		currentPone.addScore(countPoints(tempHandScoring)+countNob(tempHandScoring));
		//in case the pone won here, return immediately
		//this prevents both the pone and dealer winning at the same time
		if(isWin(currentPone)){
			return;
		}

		tempHandScoring = combineFlippedCard(currentDealer.getHand());

		currentDealer.addScore(countPoints(tempHandScoring)+countNob(tempHandScoring));
		//clear the crib for the next round
		crib.clear();
	}
	/**
	 * determines a winner if a player has enough points
	 * @return the player who won, or null if there isn't one
	 */
	private Player checkWinner() {
		if(isWin(currentPone)){
			return currentPone;
		}else if(isWin(currentDealer)){
			return currentDealer;
		}else {
			return null;
		}
	}
	private void switchDealer(){
		Player temp = currentDealer;
		currentDealer = currentPone;
		currentPone = temp;
	}

	/**
	 * Checks if a player has enough points to win
	 * @param p player to check
	 * @return if that player has won
	 */
	private static boolean isWin(Player p){
		return p.getScore() >= WINNING_SCORE;
	}
	/**
	 * Deals 6 cards to each player
	 */
	public void dealPlayers(){
		player1.getHand().clear();
		player2.getHand().clear();
		for (int i = 0; i <= 5; i++) {
			player1.addCard(deck.draw());
			//do gui card animation
			player2.addCard(deck.draw());
			//do gui card animation
		}
	}
	/**
	 * Adds cards to the crib
	 * @param list the cards which will be added to the crib
	 */
	public void addToCrib(ArrayList<Card> list) {
		for (Card card : list) {
			crib.add(card);
		}
	}
	/**
	 * 
	 * @param c card which will be added to the current peg cards in play
	 */
	public void addToPegList(Card c) {
		currentPegList.add(c);
		currentPegValue += c.getCribCount();
	}
	public int getPegValue() {
		return currentPegValue;
	}
	
	public static int botPegPoints(ArrayList<Card> list, Card c) {
		ArrayList<Card> pegList = copyCards(list);
		pegList.add(c);
		int points = pegPoints(pegList);
		pegList.remove(pegList.size()-1);
		
		
		
		return points;
		
	}
	
	
	
	
	//
    /**
     * Creates the power-set from a list. Used to find all combination possibilities
     * @param list is the hand which will be sorted into subsets
     * @return the arraylist containing all the arraylists of possible hand combinations
     */
    public static ArrayList<ArrayList<Card>> makeSubset(ArrayList<Card> list) {
		ArrayList<ArrayList<Card>> sets = new ArrayList<>();
		    for(int i=0; i<(1<<list.size()); i++) {
		    	ArrayList<Card> temp = new ArrayList<>();
		        for(int j=0; j<list.size(); j++) {
		            if((i&(1<<j)) > 0) {
		                temp.add(list.get(j));
		            }
		        }
		        sets.add(temp);    
		    }
		    return sets;
	}
    //
    /**
     * Counts the number of pairs in a hand
     * @param list is the hand which will be counted for pairs
     * @return the number of pairs which are in the hand
     */
    private static int countPairs(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	int count = 0;
		//goes through every subset
		for (ArrayList<Card> set : sets) {
			//per subset, loop through all their elements
			//check all subsets of size 2 and compare their face cards
			if (set.size() == 2 && (set.get(0).getRank() == set.get(1).getRank())) {
				count++;
				break;
			}
		}
		return count;
	}
    /**
     * Checks if there is a flush in a hand of cards
     * @param list the hand which will be checked for a flush. The list should be 5 cards long, the last index representing the flipped card
     * @return the number of points for the flush, either 4, 5, or 0
     *
     */
    private static int countFlush(ArrayList<Card> list) {
		//check to see if there are five cards to count from
    	if(list.size() == 5) {
			//if all cards are the same
			if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit() && list.get(4).getSuit() == list.get(1).getSuit()) {
				return 5;
				
			}}
			//check if there are 4 cards to count from
			//we do NOT count the last index (the 4th index) here due to this representing the flipped card
			if(list.size() == 4) {
    if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit()) {
				return 4;
			}
		}
    	
		//else, there is no flush, so return 0
    	return 0;
    }

    /**
     * Count the amount of 15's can be made in a hand
     * @param list the hand which will be checked for possible 15s
     * @return the number of 15s which are in the hand
     */
    private static int count15s(ArrayList<Card> list) {
		ArrayList<ArrayList<Card>> sets = makeSubset(list);
		int count = 0;
		int c;
		//for each subset of the list
		for (ArrayList<Card> set : sets) {
			c = 0;
			//add up all of that subset's values
			for (Card card : set) {
				c += card.getCribCount();
			}
			//if 15, then there is a pair of 15's so increment the big count
			if (c == 15) {
				count++;
			}
		}
		return count;
	}

	/**
	 * checks if a hand has a straight
	 * @param list the hand to be checked
	 * @return the points obtained from a straight
	 */
    private static int countStraight(ArrayList<Card> list) {
		int total = 0;
		ArrayList<ArrayList<Card>> sets = makeSubset(list);
		Collections.reverse(sets); // reverses the order of the sets so the length 5 sets will be counted before length 4 and 3 sets

		for (ArrayList<Card> set : sets) {
			ArrayList<Integer> Hand = new ArrayList<>(); // new hand to store integer values of each card
			for (Card card : set) {
				Hand.add(card.getValue());

			}
			Collections.sort(Hand); // sorting the cards by value

			if (Hand.size() == 5) { // checking if it is a 5 length straight
				if (Hand.get(0) == (Hand.get(1) - 1) && Hand.get(0) == (Hand.get(2) - 2) && Hand.get(0) == (Hand.get(3) - 3) && Hand.get(0) == (Hand.get(4) - 4)) {
					return 5;
				}
			}
			if (Hand.size() == 4) { // checking if it is a 4 length straight
				if (Hand.get(0) == (Hand.get(1) - 1) && Hand.get(0) == (Hand.get(2) - 2) && Hand.get(0) == (Hand.get(3) - 3)) {
					return 4;
				}
			}
			if (Hand.size() == 3) { // checking if it is a 3 length straight
				if (Hand.get(0) == (Hand.get(1) - 1) && Hand.get(0) == (Hand.get(2) - 2)) {
					total += 3;
				}
			}
		}
		
		
		return total;
	}

	/**
	 * Determines how many points a hand has based on all way to score
	 * @param list a hand of cards
	 * @return the total amount of points
	 */
	public static int countPoints(ArrayList<Card> list) {
    	return count15s(list) * 2 + countFlush(list) + countPairs(list) * 2 + countStraight(list);
    }
	public int countNob(ArrayList<Card> list) {
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getSuit() == flippedCard.getSuit() && list.get(i).getRank() == Rank.JACK) {
				return 1;
			}
		}
		return 0;
	}
    
    
    private static ArrayList<ArrayList<Card>> getPairs(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	ArrayList<ArrayList<Card>> allPoints = new ArrayList<>();
    	int count = 0;
		//goes through every subset
		for (ArrayList<Card> set : sets) {
			//per subset, loop through all their elements
			//check all subsets of size 2 and compare their face cards
			if (set.size() == 2 && (set.get(0).getRank() == set.get(1).getRank())) {
				allPoints.add(set);
				break;
			}
		}
		return allPoints;
	}
    /**
     * 
     * @param list the hand which will be checked for a flush
     * @return the number of points for the flush, either 4, 5, or 0
     */
    private static ArrayList<ArrayList<Card>> getFlush(ArrayList<Card> list) {
		//might want to review this: might be some edge cases missed and could be cleaner
		//if all cards are the same
    	ArrayList<ArrayList<Card>> allPoints = new ArrayList<>();
		ArrayList<Card> cardsInHand = new ArrayList<>();
    	if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit() && list.get(4).getSuit() == list.get(1).getSuit()) {
    		cardsInHand.add(list.get(0));
    		cardsInHand.add(list.get(1));
    		cardsInHand.add(list.get(2));
    		cardsInHand.add(list.get(3));
    		cardsInHand.add(list.get(4));
    	    allPoints.add(cardsInHand);
    	    return allPoints;
		//if 4 of the cards are the same
		//this is where an edge case could be missed if the LAST card was the same as the other cards
		//the current code ignores the last card so the case (D D D S D) would not be counted as a flush
    	}else if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit()) {
    		cardsInHand.add(list.get(0));
    		cardsInHand.add(list.get(1));
    		cardsInHand.add(list.get(2));
    		cardsInHand.add(list.get(3));
    	    allPoints.add(cardsInHand);
    	    return allPoints;
		//else, there is no flush, so return 0
    	}else {
    		return allPoints;
    	}
    }

    /**
     * 
     * @param list the hand which will be checked for possible 15s
     * @return the number of 15s which are in the hand
     */
    private static ArrayList<ArrayList<Card>> get15s(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	ArrayList<ArrayList<Card>> allPoints = new ArrayList<>();
		int count = 0;
		int c;
		//for each subset of the list
		for(int i = 0;i<sets.size();i++) {
			c = 0;
			//add up all of that subset's values
			for(int j = 0;j<sets.get(i).size();j++) {
				c += sets.get(i).get(j).getCribCount();
			}
			//if 15, then there is a pair of 15's so increment the big count
			if(c == 15) {
				count++;
				allPoints.add(sets.get(i));
			}
		}
		return allPoints;
	}
	/**
	 * 
	 * @param list hand which will be checked for cards part of scoring straights
	 * @return the cards part of scoring in straight
	 */
    public static ArrayList<ArrayList<Card>> getStraight(ArrayList<Card> list) {
		int total = 0;
		ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	ArrayList<ArrayList<Card>> allPoints = new ArrayList<>();
		Collections.reverse(sets); // reverses the order of the sets so the length 5 sets will be counted before length 4 and 3 sets

		for (ArrayList<Card> set : sets) {
			ArrayList<Integer> Hand = new ArrayList<>(); // new hand to store integer values of each card
			for (Card card : set) {
				Hand.add(card.getValue());

			}
			Collections.sort(Hand); // sorting the cards by value

			if (Hand.size() == 5) { // checking if it is a 5 length straight
				if (Hand.get(0) == (Hand.get(1) - 1) && Hand.get(0) == (Hand.get(2) - 2) && Hand.get(0) == (Hand.get(3) - 3) && Hand.get(0) == (Hand.get(4) - 4)) {
					allPoints.add(set);
					return allPoints;
				}
			}
			if (Hand.size() == 4) { // checking if it is a 4 length straight
				if (Hand.get(0) == (Hand.get(1) - 1) && Hand.get(0) == (Hand.get(2) - 2) && Hand.get(0) == (Hand.get(3) - 3)) {
					allPoints.add(set);
					return allPoints;
				}
			}
			if (Hand.size() == 3) { // checking if it is a 3 length straight
				if (Hand.get(0) == (Hand.get(1) - 1) && Hand.get(0) == (Hand.get(2) - 2)) {
					allPoints.add(set);
				}
			}
		}
		return allPoints;
		//hello
    }

	/**
	 * The for loops in the method assign integer values to an arrayList, which allows them to be checked
	 * @param pegList the current pegging list which will be checked if there is currently a straight in the pegging list
	 * @return the value of the straight, or 0 if there is no straight
	 */
	public static int pegStraight(ArrayList<Card> pegList) {
		//create a copy of the arraylist, so we do not modify the original
		ArrayList<Card> list = copyCards(pegList);
		Collections.reverse(list);
		if(list.size() == 8) { // a straight of 8 cannot exist
			list.remove(0);
		}
		if(list.size() == 1 || list.size() == 2 || list.size() == 0) {
			return 0;
		}

		ArrayList<Integer> hand = new ArrayList<>();
		if(list.size() >= 7) {
			for(int i = 0;i<7;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 7) { // checking if it is a 7 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3) && hand.get(0) == (hand.get(4)-4) && hand.get(0) == (hand.get(5)-5)  && hand.get(0) == (hand.get(6)-6) ) {
					return 7;
				}
			}
		}else if(list.size() >=6) {
			hand.clear();
			for(int i = 0;i<6;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 6) { // checking if it is a 6 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3) && hand.get(0) == (hand.get(4)-4) && hand.get(0) == (hand.get(5)-5)) {
					return 6;
				}
			}
		}else if(list.size() >= 5) {
			hand.clear();
			for(int i = 0;i<5;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 5) { // checking if it is a 5 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3) && hand.get(0) == (hand.get(4)-4)) {
					return 5;
				}
			}
		}else if(list.size() == 4) {
			hand.clear();
			for(int i = 0;i<4;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 4) { // checking if it is a 4 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3)) {
					return 4;
				}
			}
		}else if(list.size() == 3) {
			hand.clear();
			for(int i = 0;i<3;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 3) { // checking if it is a 3 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2)) {
					return 3;
				}
			}
		}
		
		
		
		return 0;
		
	}
	/**
	 * l
	 * @param pegList the current pegging list which will be checked for pairs in pegging
	 * @return the value of points for pairs
	 */
	public static int pegPairs(ArrayList<Card> pegList) {
		//create a copy of the arraylist, so we do not modify the original
		ArrayList<Card> list = copyCards(pegList);
		// if the pegging list is of size 1, it will return 0 as there are no possible pair combination
		if(list.size() == 1 || list.size() == 0){
			return 0;
		}
		// will remove the first index of the arraylist until there are 4 cards remaining
		if(list.size() >= 5) {
			for(int i = list.size();i>4;i--) {
				
				list.remove(0);
				
			}
		}
		// will check if it is of length 4, then will see if all the values are the same, if they are, it will return 12
		if(list.size() == 4) {
			if(list.get(list.size()-1).getValue() == list.get(list.size()-2).getValue() && list.get(list.size()-1).getValue() == list.get(list.size()-3).getValue() && list.get(list.size()-1).getValue() == list.get(list.size()-4).getValue()  ) {
				return 12;
			}
		}
		// will remove the first index to reduce the number of cards to 3, then will check if all three cards are the same value
			
			if(list.size() == 3) {
				if(list.get(list.size()-1).getValue() == list.get(list.size()-2).getValue() && list.get(list.size()-1).getValue() == list.get(list.size()-3).getValue() ) {
					return 6;
				}
			}
			// will remove the first index to reduce the number of cards to 2, then will check if the 2 cards are the same value
		
		
		if(list.size() == 2) {
			if(list.get(list.size()-1).getValue() == list.get(list.size()-2).getValue()) {
				return 2;
			}
		}
		return  0;
}
	public static int peg15(ArrayList<Card> list) {
		int counter = 0;
		for (Card card : list) {
			counter += card.getCribCount();
		}
		if(counter == 15) {
			return 2;
		}
		
		return 0;
	}
	public static int pegPoints(ArrayList<Card> list) {
		return peg15(list) + pegPairs(list) + pegStraight(list);
	}

	/**
	 * Duplicates a hand of cards. This is needed for the reference type nature of ArrayList where directly assigning
	 * a card will result in the original being modified
	 * @param src the hand that will be copied
	 * @return a duplicate version of the hand that is free to modify
	 */
	private static ArrayList<Card> copyCards(ArrayList<Card> src){
		ArrayList<Card> list = new ArrayList<>();
		for(Card c : src){
			list.add(c);
		}
		return list;
	}

	/**
	 * Creates a new temporary hand that includes the flipped card for scoring calculations
	 * @param hand the hand the flipped card should be added to
	 * @return a copy of the hand that includes the flipped card
	 */
	private ArrayList<Card> combineFlippedCard(ArrayList<Card> hand){
		ArrayList<Card> list = copyCards(hand);
		list.add(flippedCard);
		return list;
	}
}