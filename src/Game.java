package src;

import java.util.ArrayList;

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
    			    for(int i = 0;i<sets.size();i++) {
    					
    					for(int j = 0;j<sets.get(i).size();j++) {
    						
    						if(sets.get(i).size() == 2 && (sets.get(i).get(0).getRank() == sets.get(i).get(1).getRank())) {
    							count++;
    							break;
    							
    						}
    					
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
		ArrayList<ArrayList<Card>> sets = new ArrayList<>();
		sets = makeSubset(list);
		int count = 0;
		int c = 0;
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
    
    public static int countPoints(ArrayList<Card> list) {
    	return count15(list) * 2 + countFlush(list) + countPairs(list) * 2;
    }
    
    
  }
  
