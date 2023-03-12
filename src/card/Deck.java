package src.card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> discarded;
    private ArrayList<Card> cards = new ArrayList<Card>(52);
    public Deck() {
        int index = 0;
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
        Random random = new Random(0);
        int randomNumber = random.nextInt(52 - 1 + 1) + 1;
        cards.remove(randomNumber);
        return cards.get(randomNumber);
    }

    /**
     * Shuffles the discard pile back into the deck
     */
    public void shuffleDiscard(){
        Collections.shuffle(discarded);
    }
}
