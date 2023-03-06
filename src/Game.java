package src;

import src.card.Card;
import src.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.smartcardio.CardChannel;

/**
 * This holds all necessary rules and functionality to play a game of cribbage
 */
public class Game {
	private final static int WINNING_SCORE = 121;
	private Player player1;
	private Player player2;
	private Player currentDealer;
	private Player currentPone;
	private Player winner = null;
	private ArrayList<Card> crib = new ArrayList<>();
	private ArrayList<Card> currentPegList = new ArrayList<>();
	private int currentPegValue;
	private Deck deck;
	

	/**
	 * Creates a two game with two human players
	 * @param one player one
	 * @param two player two
	 */
    public Game(Player one, Player two) {
		//this is an EXTREMELY compact way of getting a random dealer
		//this creates an array of the players and uses a random number generator to choose an index from that list
		player1 = one;
		player2 = two;
		currentDealer = new Player[]{player1,player2}[new Random().nextInt(0,2)];
		deck = new Deck();
		run();
    }

	/**
	 * Creates a single-player game with a human vs. a bot
	 * @param p the user
	 */
	public Game(Player p){
		player1 = p;
		player2 = new Bot();
		currentDealer = new Player[]{player1,player2}[new Random().nextInt(0,2)];
		deck = new Deck();
		run();
	}
	private void run(){

		switchDealer();
		run();
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
    /**
     * Counts the number of pairs in a hand
     * @param list is the hand which will be counted for pairs
     * @return the number of pairs which are in the hand
     */
    private static int countPairs(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	int count = 0;
		//goes through every subset
		for(int i = 0;i<sets.size();i++) {
			//per subset, loop through all their elements
			//check all subsets of size 2 and compare their face cards
			if(sets.get(i).size() == 2 && (sets.get(i).get(0).getRank() == sets.get(i).get(1).getRank())) {
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
		for(int i = 0;i<sets.size();i++) {
			c = 0;
			//add up all of that subset's values
			for(int j = 0;j<sets.get(i).size();j++) {
				c += sets.get(i).get(j).getCribCount();
			}
			//if 15, then there is a pair of 15's so increment the big count
			if(c == 15) {
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
		
		for(int i = 0;i<sets.size();i++) {
			ArrayList<Integer> Hand = new ArrayList<>(); // new hand to store integer values of each card
			for(int j = 0;j<sets.get(i).size();j++) {
				Hand.add(sets.get(i).get(j).getValue());
				
			}
			Collections.sort(Hand); // sorting the cards by value
			
			if(Hand.size() == 5) { // checking if it is a 5 length straight
				if(Hand.get(0) == (Hand.get(1)-1) && Hand.get(0) == (Hand.get(2)-2) && Hand.get(0) == (Hand.get(3)-3) && Hand.get(0) == (Hand.get(4)-4)) {
					return 5;
				}
			}
			if(Hand.size() == 4) { // checking if it is a 4 length straight
				if(Hand.get(0) == (Hand.get(1)-1) && Hand.get(0) == (Hand.get(2)-2) && Hand.get(0) == (Hand.get(3)-3)) {
					return 4;
				}
			}
			if(Hand.size() == 3) { // checking if it is a 3 length straight
				if(Hand.get(0) == (Hand.get(1)-1) && Hand.get(0) == (Hand.get(2)-2)) {
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
    
    
    private static ArrayList getPairs(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	ArrayList<ArrayList<Card>> allpoints = new ArrayList<>();
    	int count = 0;
		//goes through every subset
		for(int i = 0;i<sets.size();i++) {
			//per subset, loop through all their elements
			//check all subsets of size 2 and compare their face cards
			if(sets.get(i).size() == 2 && (sets.get(i).get(0).getRank() == sets.get(i).get(1).getRank())) {
				allpoints.add(sets.get(i));
				break;
			}
		}
		return allpoints;
	}
    /**
     * 
     * @param list the hand which will be checked for a flush
     * @return the number of points for the flush, either 4, 5, or 0
     */
    private static ArrayList getFlush(ArrayList<Card> list) {
		//might want to review this: might be some edge cases missed and could be cleaner
		//if all cards are the same
    	ArrayList<ArrayList<Card>> allpoints = new ArrayList<>();
		ArrayList<Card> cardsinhand = new ArrayList<>();
    	if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit() && list.get(4).getSuit() == list.get(1).getSuit()) {
    		cardsinhand.add(list.get(0));
    		cardsinhand.add(list.get(1));
    		cardsinhand.add(list.get(2));
    		cardsinhand.add(list.get(3));
    		cardsinhand.add(list.get(4));
    	    allpoints.add(cardsinhand);
    	    return allpoints;
		//if 4 of the cards are the same
		//this is where an edge case could be missed if the LAST card was the same as the other cards
		//the current code ignores the last card so the case (D D D S D) would not be counted as a flush
    	}else if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit()) {
    		cardsinhand.add(list.get(0));
    		cardsinhand.add(list.get(1));
    		cardsinhand.add(list.get(2));
    		cardsinhand.add(list.get(3));
    	    allpoints.add(cardsinhand);
    	    return allpoints;
		//else, there is no flush, so return 0
    	}else {
    		return allpoints;
    	}
    }

    /**
     * 
     * @param list the hand which will be checked for possible 15s
     * @return the number of 15s which are in the hand
     */
    private static ArrayList get15s(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	ArrayList<ArrayList<Card>> allpoints = new ArrayList<>();
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
				allpoints.add(sets.get(i));
			}
		}
		return allpoints;
	}
	/**
	 * 
	 * @param list hand which will be checked for cards part of scoring straights
	 * @return the cards part of scoring in straight
	 */
    public static ArrayList getStraight(ArrayList<Card> list) {
		int total = 0;
		ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	ArrayList<ArrayList<Card>> allpoints = new ArrayList<>();
		Collections.reverse(sets); // reverses the order of the sets so the length 5 sets will be counted before length 4 and 3 sets
		
		for(int i = 0;i<sets.size();i++) {
			ArrayList<Integer> Hand = new ArrayList<>(); // new hand to store integer values of each card
			for(int j = 0;j<sets.get(i).size();j++) {
				Hand.add(sets.get(i).get(j).getValue());
				
			}
			Collections.sort(Hand); // sorting the cards by value
			
			if(Hand.size() == 5) { // checking if it is a 5 length straight
				if(Hand.get(0) == (Hand.get(1)-1) && Hand.get(0) == (Hand.get(2)-2) && Hand.get(0) == (Hand.get(3)-3) && Hand.get(0) == (Hand.get(4)-4)) {
					allpoints.add(sets.get(i));
					return allpoints;
				}
			}
			if(Hand.size() == 4) { // checking if it is a 4 length straight
				if(Hand.get(0) == (Hand.get(1)-1) && Hand.get(0) == (Hand.get(2)-2) && Hand.get(0) == (Hand.get(3)-3)) {
					allpoints.add(sets.get(i));
					return allpoints;
				}
			}
			if(Hand.size() == 3) { // checking if it is a 3 length straight
				if(Hand.get(0) == (Hand.get(1)-1) && Hand.get(0) == (Hand.get(2)-2)) {
					allpoints.add(sets.get(i));
				}
			}
		}
		return allpoints;
		//hello
    }
	/**
	 * Deals 6 cards to each player
	 */
	public void dealPlayers(){
		for (int i = 0; i <= 5; i++) {
			player1.addCard(deck.draw());
			player2.addCard(deck.draw());
		}
	}
	/**
	 * Adds cards to the crib
	 * @param list the cards which will be added to the crib
	 */
	public void addToCrib(ArrayList<Card> list) {
		for(int i = 0;i<list.size();i++) {
			crib.add(list.get(i));
		}
	}
	/**
	 * 
	 * @param c card which will be added to the current peg cards in play
	 */
	public void addToPeglist(Card c) {
		currentPegList.add(c);
	}
	/**
	 * 
	 * @param c card whose value will be added to the current pegList value
	 */
	public void addToPegValue(Card c) {
		currentPegValue += c.getCribCount();
	}
	
	
}
  
