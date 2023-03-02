package src;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 500;
    
    
    public Game() {
    	
    }
    /**
     * 
     * @param list is the hand which will be sorted into subsets
     * @return the arraylist containing all the arraylists of possible hand combinations
     */
    private static ArrayList<ArrayList<Card>> makeSubset(ArrayList<Card> list) {
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
     * 
     * @param list is the hand which will be counted for pairs
     * @return the number of pairs which are in the hand
     */
    private static int countPairs(ArrayList<Card> list) {
    	ArrayList<ArrayList<Card>> sets = makeSubset(list);
    	int count = 0;
		//goes through every subset
		for(int i = 0;i<sets.size();i++) {
			//per subset, loop through all their elements
			
				if(sets.get(i).size() == 2 && (sets.get(i).get(0).getRank() == sets.get(i).get(1).getRank())) {
					count++;
					break;
				

			}

		}
		return count;
	}
    /**
     * 
     * @param list the hand which will be checked for a flush
     * @return the number of points for the flush, either 4, 5, or 0
     */
    private static int countFlush(ArrayList<Card> list) {
    	if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit() && list.get(4).getSuit() == list.get(1).getSuit()) {
    		return 5;
    	}else if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit()) {
    		return 4;
    	}else {
    		return 0;
    	}
    }
    /**
     * 
     * @param list the hand which will be checked for possible 15s
     * @return the number of 15s which are in the hand
     */
    private static int count15(ArrayList<Card> list) {
		ArrayList<ArrayList<Card>> sets = makeSubset(list);
		int count = 0;
		int c;
		for(int i = 0;i<sets.size();i++) {
			c = 0;
			for(int j = 0;j<sets.get(i).size();j++) {
				c += sets.get(i).get(j).getCribCount();
			}
			if(c == 15) {
				count++;
			}
		}
		return count;
	}
	
    public static int countStraight(ArrayList<Card> list) {
		int total = 0;
		ArrayList<ArrayList<Card>> sets = makeSubset(list); // an arraylist containing all the possible card combinations
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
    
    public static int countPoints(ArrayList<Card> list) {
    	return count15(list) * 2 + countFlush(list) + countPairs(list) * 2 + countStraight(list);
    }
    
    
}
  
