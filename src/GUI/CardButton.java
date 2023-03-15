package src.GUI;

import src.card.Card;

import javax.swing.*;

/**
 * Represents a clickable card in the gui
 */
public class CardButton extends JLabel {
    private Card card;
    private ImageIcon image;
    public CardButton(Card c){
        card = c;
        image = new ImageIcon("Card.images/card.fronts/" + card.toString() + ".png");
        //add listener or something here
    }

    public Card getCard() {
        return card;
    }

    public ImageIcon getImage() {
        return image;
    }
}
