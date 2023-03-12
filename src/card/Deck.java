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
        //draw the top card
        Card returnCard = cards.get(0);
        //remove the card from the deck
        cards.remove(0);
        return returnCard;
    }

    /**
     * Shuffles the discard pile back into the deck
     */
    public void shuffleDiscard(){
        //add all discarded cards back in
        cards.addAll(discarded);
        //shuffle the cards
        shuffle();
    }
}
