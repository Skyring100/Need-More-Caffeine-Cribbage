package src;
import src.card.Card;
import src.card.Deck;
import src.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * This holds all necessary rules and functionality to play a game of cribbage
 */
public class Game {
	private final static int WINNING_SCORE = 121;
	private final Player player1;
	private final Player player2;
	private Player currentDealer;
	private Player currentPone;
	private Player winner = null;
	private ArrayList<Card> crib = new ArrayList<>();
	private ArrayList<Card> currentPegList = new ArrayList<>();
	private Card flippedCard;
	private int currentPegValue;
	private final Deck deck;
	private Scanner input;
	private boolean debugMode;
	/**
	 * Sets up a game of cribbage between two players
	 * @param one player one
	 * @param two player two
	 * 
	 */
    public Game(Player one, Player two) {
		//set debugging mode to true if both players are bots
		if(one instanceof Bot && two instanceof Bot){
			debugMode = true;
		}else{
			debugMode = false;
		}
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
		System.out.println("Dealer: "+currentDealer);
		System.out.println("Pone: "+currentPone);
		deck = new Deck();
		//to get input from the user, we use a scanner
		input = new Scanner(System.in);
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
	 * Allows the creator to specify a game with debugging on, revealing data that is normally non-viewable
	 * @param p the user
	 * @param isDebug if debug is turned on manually or not
	 */
	public Game(Player p, boolean isDebug){
		this(p);
		debugMode = isDebug;
	}
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public ArrayList<Card> getCurrentPegList() {
		return currentPegList;
	}

	public ArrayList<Card> getCrib() {
		return crib;
	}

	/**
	 * The main game method, which executes a round in cribbage
	 */
	private void run(){
		showScores();
		System.out.println("Dealing cards");
		dealPlayers();
		handUpdate();
		discardPhase();
		System.out.println("Crib: "+crib);
		handUpdate();
		flippedCard = deck.draw();
		System.out.println("Flipping the deck's top card");
		System.out.println("Flipped card: "+flippedCard);
		if(flippedCard.getRank() == Rank.JACK) {
			currentPone.addScore(2);
			System.out.println("The pone ("+currentPone+") scored two points for flipping a jack");
		}
		showScores();
		//let the user continue when done looking at flipped card
		System.out.println("Press enter when ready");
		input.nextLine();
		System.out.println("Pegging cards");
		peg();
		System.out.println("Finished pegging");
		showScores();
		winner = checkWinner();
		if(winner != null){
			System.out.println(winner+" is the winner");
		}else{
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
		deck.shuffleDiscard();
		run();
	}
	/**
	 * Deals 6 cards to each player
	 */
	public void dealPlayers(){
		player1.getHand().clear();
		player2.getHand().clear();
		Card c;
		for (int i = 0; i <= 5; i++) {
			c = deck.draw();
			player1.addCard(c);

			c = deck.draw();
			player2.addCard(c);
		}
	}

	/**
	 * Lets the player select one of the cards
	 * @return the selected card
	 */
	private Card selectCard(ArrayList<Card> hand){
		System.out.println("Select a card");
		//print all cards in the hand
		for(int i = 1; i <= hand.size(); i++){
			System.out.print("("+i+"): "+hand.get(i-1)+" ");
		}
		System.out.println();
		int index = -100;
		String text;
		//ask the user for input until there is a valid card selected
		do{
			System.out.println("Input the number corresponding to the card you want");
			text = input.nextLine().strip();
			if(isNumber(text)){
				index = Integer.valueOf(text);
			}
		}while(index < 1 || index > hand.size());
		index -= 1;
		return hand.get(index);
	}
	/**
	 * Checks if a string can be converted to a number
	 * @param check the string to check
	 * @return if the string is a number
	 */
	private boolean isNumber(String check){
		char[] intWords = new char[]{'0','1','2','3','4','5','6','7','8','9'};
		char[] letters = check.toCharArray();
		//if the string is empty, it is not a number
		if(check.equals("")){
			return false;
		}
		//for each letter, check if it is a digit
		for(char l : letters){
			boolean isDigit = false;
			for(char n : intWords){
				//if there is a digit found, we know it isnt the other digits so break
				if(l == n){
					isDigit = true;
					break;
				}
			}
			if(!isDigit){
				return false;
			}
		}
		return true;
	}

	/**
	 * Shows a player's hand to the console
	 * @param p the player that will have their hand shown
	 */
	private void showHand(Player p){
		//if this is not a bot, show their hand
		if(!(p instanceof Bot)){
			System.out.println(p+"'s hand:");
			for(Card c : p.getHand()){
				System.out.print(c+" ");
			}
			//in case of two players, we will clear the screen when the user is done looking
			clearScreen();
		}
	}
	private void handUpdate(){
		showHand(player1);
		showHand(player2);
	}
	private void showScores(){
		System.out.println("\nCurrent scores");
		System.out.println(player1+"'s score: "+player1.getScore());
		System.out.println(player2+"'s score: "+player2.getScore());
		System.out.println();
	}
	/**
	 * Clear the screen of the user when enter is pressed
	 */
	private void clearScreen(){
		System.out.println("\nPress enter to continue");
		input.nextLine();
		for(int i = 1; i <= 50; i++){
			System.out.println();
		}
	}
	/**
	 * Makes players discard two cards into the crib
	 */
	private void discardPhase() {
		Card currentCard;
		//loop through all 2 players
		for(Player p : new Player[]{player1,player2}){
			//each player discards 2 cards
			for(int i = 1; i <= 2; i++){
				if(p instanceof Bot){
					currentCard =  p.discard(((Bot) p).discardAlgorithm());
					System.out.println(p+" discards "+currentCard);
				}else{
					System.out.println("Discard phase");
					currentCard = p.discard(selectCard(p.getHand()));
					System.out.println("You discard "+currentCard);
				}
				crib.add(currentCard);
			}
		}
	}

	private void peg(){
		Player currentPlayer;
		//sets the pegging hands of all players. These are temporary will be manipulated and checked as pegging occurs
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
			System.out.println("-------------------------------------------------");
			System.out.println("Pegging list (current value: "+currentPegValue+")");
			System.out.println(currentPegList);

			//checks if the player has cards and is able to play a card (if they have cards and can play at least one of those cards)
			if(currentPlayer.getPegHand().size() != 0 && currentPlayer.canPeg(this)) {
				Card peggingCard;
				//if a player is a bot, use an algorithm to find a suitable card for pegging
				if(currentPlayer instanceof Bot) {
					peggingCard =  ((Bot) currentPlayer).pegAlgorithm(currentPegList,currentPegValue);
					System.out.println(currentPlayer+" pegged "+peggingCard);
				}
				else {
					do {
						System.out.println("Pick a card to peg");
						peggingCard = selectCard(currentPlayer.getPegHand());
						//if the player chose an invalid card, loop again
						if(!peggableCard(peggingCard)){
							System.out.println("This card is too high, select another card");
						}
					}while(!peggableCard(peggingCard));
					System.out.println("You pegged "+peggingCard);
				}
				currentPlayer.pegCard(this,peggingCard);
				currentPlayer.addScore(pegPoints(currentPegList));
				System.out.println("Peg list: "+currentPegList+" (value: "+currentPegValue+")\nPress enter to continue");
				input.nextLine();
				showScores();
				if(checkWinner()!= null) {
					//we want to return instead of break; we do not care about any extra points now that someone has already won
					//if we do not return, there is a chance the other player might appear to win as well which is not possible
					return;
				}
			}
			counter++;
			//checking to see if both players can't play a card
			if(!currentDealer.canPeg(this) && !currentPone.canPeg(this)) {
				System.out.println("No possible plays from either player");
				//if you get exactly 31, get bonus points
				if(currentPegValue == 31) {
					currentPlayer.addScore(2);
				}else {
					//if you end last, get an extra point for it
					currentPlayer.addScore(1);
				}
				showScores();
				System.out.println("Press enter to continue");
				input.nextLine();
				if(checkWinner() != null) {
					//we want to return instead of break; we do not care about any extra points now that someone has already won
					return;
				}
				//clear the pegging cards and start another round
				currentPegList.clear();
				currentPegValue = 0;
			}
		}while(currentDealer.getPegHand().size() != 0 || currentPone.getPegHand().size() != 0); // do the pegging while a player has at least 1 card in their hand
		System.out.println("\nDone pegging\nScoring hands");
		System.out.printf("%s(Dealer)%n%s(Pone)%n",currentDealer,currentPone);
		System.out.println("Crib: "+crib);
		System.out.println("Flipped card: "+flippedCard);
		System.out.println("Pres enter to continue");
		input.nextLine();
		//----------------------------------------------------------------------------SHOW HOW MUCH SCORE YOU GAIN HERE----------------
		//adding the flipped card to the scoring hands
		ArrayList<Card> tempHandScoring = combineFlippedCard(currentPone.getHand());
		currentPone.addScore(countPoints(tempHandScoring)+countNob(tempHandScoring));
		System.out.println("Pones Hand");
		printScores(tempHandScoring);
		tempHandScoring = combineFlippedCard(crib);
		System.out.println("Crib Hand (to pone)");
		printScores(tempHandScoring);
		currentPone.addScore(countPoints(tempHandScoring)+countNob(tempHandScoring));
		//in case the pone won here, return immediately
		//this prevents both the pone and dealer winning at the same time
		if(isWin(currentPone)){
			return;
		}

		tempHandScoring = combineFlippedCard(currentDealer.getHand());
		System.out.println("Dealer Hand");
		printScores(tempHandScoring);
		currentDealer.addScore(countPoints(tempHandScoring)+countNob(tempHandScoring));
		//clear the crib for the next round
		crib.clear();
	}
	public void printScores(ArrayList hand) {
		for(int i = 0;i<getStraight(hand).size();i++) {
			System.out.println("Straight"+getStraight(hand).get(i));
		}
		for(int i = 0;i<get15s(hand).size();i++) {
			System.out.println("15s " + get15s(hand).get(i));
		}
		for(int i = 0;i<getPairs(hand).size();i++) {
			System.out.println("Pairs " +getPairs(hand).get(i));
		}
		for(int i = 0;i<getFlush(hand).size();i++) {
			System.out.println("Flush " + getFlush(hand).get(i));
		}
	}

	/**
	 * Determines if a card is playable in the current pegging list
	 * @param card the card to be checked
	 * @return if the card is able to be played
	 */
	private boolean peggableCard(Card card){
		return card.getCribCount() <= 31 - getPegValue();
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
		ArrayList<Card> pegList = new ArrayList<>();
		pegList.addAll(list);
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

	/**
	 * Gives a point if a nob occurred
	 * @param list the hand to be checked
	 * @return the amount of points gained
	 */
	public int countNob(ArrayList<Card> list) {
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getSuit() == flippedCard.getSuit() && list.get(i).getRank() == Rank.JACK) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Finds the cards that give a pair. was supposed to be used for the GUI
	 * @param list the hand to be checked for pairs
	 * @return an array of pairs
	 */
	
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
			}
		}
		return allPoints;
	}
    /**
     * Finds cards that cause a flush. was supposed to be used for the GUI
     * @param list the hand which will be checked for a flush
     * @return the number of points for the flush, either 4, 5, or 0
     */
    private static ArrayList<ArrayList<Card>> getFlush(ArrayList<Card> list) {
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
     * Finds cards that result in a 15. was supposed to be used for the GUI
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
	 * Finds cards which form a straight in a hand. was supposed to be used for the GUI
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
		//
    }

	/**
	 * The for loops in the method assign integer values to an arrayList, which allows them to be checked
	 * @param pegList the current pegging list which will be checked if there is currently a straight in the pegging list
	 * @return the value of the straight, or 0 if there is no straight
	 */
	public static int pegStraight(ArrayList<Card> pegList) {
		//create a copy of the arraylist, so we do not modify the original
		ArrayList<Card> list = new ArrayList<>();
		list.addAll(pegList);
		
		if(list.size() == 8) { // a straight of 8 cannot exist
			list.remove(0);
		}
		if(list.size() == 1 || list.size() == 2 || list.size() == 0) {
			return 0;
		}
		Collections.reverse(list);
		ArrayList<Integer> hand = new ArrayList<>();
		if(list.size() == 7) {
			for(int i = 0;i<7;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 7) { // checking if it is a 7 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3) && hand.get(0) == (hand.get(4)-4) && hand.get(0) == (hand.get(5)-5)  && hand.get(0) == (hand.get(6)-6) ) {
					return 7;
				}
			}else {
				list.remove(list.size()-1);
			}
		}else if(list.size() ==6) {
			hand.clear();
			for(int i = 0;i<6;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 6) { // checking if it is a 6 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3) && hand.get(0) == (hand.get(4)-4) && hand.get(0) == (hand.get(5)-5)) {
					return 6;
				}
			}else {
				list.remove(list.size()-1);
			}
		}else if(list.size() == 5) {
			hand.clear();
			for(int i = 0;i<5;i++) {
				hand.add(list.get(i).getValue());
			}
			Collections.sort(hand);
			if(hand.size() == 5) { // checking if it is a 5 length straight
				if(hand.get(0) == (hand.get(1)-1) && hand.get(0) == (hand.get(2)-2) && hand.get(0) == (hand.get(3)-3) && hand.get(0) == (hand.get(4)-4)) {
					return 5;
				}
			}else {
				list.remove(list.size()-1);
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
			}else {
				list.remove(list.size()-1);
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
		ArrayList<Card> list = new ArrayList<>();
		list.addAll(pegList);
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
			}else {
				list.remove(0);
			}
		}
		// will remove the first index to reduce the number of cards to 3, then will check if all three cards are the same value
			
			if(list.size() == 3) {
				if(list.get(list.size()-1).getValue() == list.get(list.size()-2).getValue() && list.get(list.size()-1).getValue() == list.get(list.size()-3).getValue() ) {
					return 6;
				}else {
					list.remove(0);
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
	 * Creates a new temporary hand that includes the flipped card for scoring calculations
	 * @param hand the hand the flipped card should be added to
	 * @return a copy of the hand that includes the flipped card
	 */
	private ArrayList<Card> combineFlippedCard(ArrayList<Card> hand){
		ArrayList<Card> list = new ArrayList<>() ;
		list.add(flippedCard);
		list.addAll(hand);
		
		return list;
	}
}