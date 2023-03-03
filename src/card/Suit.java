package src.card;

/**
 * basics.src.card.Suit has values corresponding to a suit in cards
 */
public enum Suit {
    CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");
    private final String symbol;
    Suit(String s){
        this.symbol = s;
    }
    public String toShortString(){
        return symbol;
    }
}
