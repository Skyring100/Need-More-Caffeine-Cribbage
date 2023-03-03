package src.card;

/**
 * Represents a card in a standard deck of cards, containing a suit, symbol etc
 */
public class Card implements CardInterface {
    private final Rank rank;
    private final Suit suit;
    private final int cribCount;
    private final String symbol;

    /**
     * Creates a card in chronological order, suits being ordered alphabetically and rank ordered from ace to king
     * @param i
     */
    protected Card(int i){
        if (i < 1 || i > 52){
            //creating default
            this.rank = Rank.ACE;
            this.suit = Suit.DIAMONDS;
        }else{
            //Return a card in chronological order
            //basics.src.card.Suit goes from alphabetical
            //basics.src.card.Rank is from Ace to King
            //AC is highest and KS is the lowest
            //modulus 4 to get the suit
            //modulus 13 to get the rank
            //we have to remove the joker due to us adding it so the ranks
            Rank[] rawRanks = Rank.values();
            Rank[] ranks = new Rank[13];
            for(int j = 0; j < ranks.length; j++){
                ranks[j] = rawRanks[j+1];
            }
            Suit[] suits = Suit.values();
            this.rank = ranks[i%13];
            this.suit = suits[i%4];
        }
        this.cribCount = this.rank.getCount();
        this.symbol = this.rank.toShortString()+this.suit.toShortString();
    }

    /**
     * Creates a card using enums of ranks and suits
     * @param r
     * @param s
     */
    protected Card(Rank r, Suit s){
        this.rank = r;
        this.suit = s;
        this.cribCount = this.rank.getCount();
        this.symbol = this.rank.toShortString()+this.suit.toShortString();
    }
    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public int getCribCount() {
        return cribCount;
    }
    public int getValue() {
    	return this.rank.ordinal();
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof Card) return equals((Card) o);
        return false;
    }
    public boolean equals(Card c) {
        if(c == null) return false;
        return (this.getRank().equals(c.getRank()) && this.getSuit().equals(c.getSuit()));
    }
}
