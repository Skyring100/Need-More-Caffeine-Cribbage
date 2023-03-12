package src.card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> discarded;
    private ArrayList<Card> cards = new ArrayList<>(52);
    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    /**
     * Shuffles the deck
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }

    /**
     * draws a random card from the deck
     * @return the drawn card
     */
    public Card draw(){
        return cards.get(0);
    }

    /**
     * Shuffles the discard pile back into the deck
     */
    public void shuffleDiscard(){
        Collections.shuffle(discarded);
        cards.addAll(discarded);
    }
}
