package src.card;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> discarded;
    private ArrayList<Card> cards;
    public Deck(){
        //add all cards to the deck
    }

    /**
     * Shuffles the deck
     */
    public void shuffle(){
    }

    /**
     * draws a card from the deck
     * @return the drawn card
     */
    public Card draw(){
        //PLACE HOLDER, SWITCH THIS
        return new Card(0);
    }

    /**
     * Shuffles the discard pile back into the deck
     */
    public void shuffleDiscard(){

    }
}
