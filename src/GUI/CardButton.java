package src.GUI;

import src.card.Card;

import javax.swing.*;

/**
 * Represents a clickable card in the gui
 */
public class CardButton extends JLabel {
    private final Card card;
    private final ImageIcon image;
    private final ImageIcon backImage;
    public CardButton(Card c){
        card = c;
        image = new ImageIcon("Card/images/card/fronts/" + card.toString() + ".png");
        backImage = new ImageIcon("Card/images/card/backs/blue.png");
        //add listener or something here
    }

    public Card getCard() {
        return card;
    }

    public ImageIcon getImage() {
        return image;
    }

    public ImageIcon getBackImage() {
        return backImage;
    }
}
