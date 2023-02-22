package src;

import java.util.ArrayList;

public class Game {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 500;
    
    
    public Game() {
    	
    }
    
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
    
    public static int countPairs(ArrayList<Card> list) {
		
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
    
    public static int countFlush(ArrayList<Card> list) {
    	if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit() && list.get(4).getSuit() == list.get(1).getSuit()) {
    		return 5;
    	}else if(list.get(0).getSuit() == list.get(1).getSuit() && list.get(2).getSuit() == list.get(1).getSuit() && list.get(3).getSuit() == list.get(1).getSuit()) {
    		return 4;
    	}else {
    		return 0;
    	}
    }
    
    public static int count15(ArrayList<Card> list) {
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
  }
  
