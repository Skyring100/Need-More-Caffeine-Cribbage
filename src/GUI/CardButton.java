package src.GUI;

import src.card.Card;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a clickable card in the gui
 */
public class CardButton extends JLabel {
    private Card card;
    private ImageIcon image;
    private ImageIcon backImage;
    private boolean backOnly = false;
    private GUI gui;
    public CardButton(Card c, GUI g){
        gui = g;
        card = c;
        image = new ImageIcon("Card/images/card/fronts/" + card.toString() + ".png");
        backImage = new ImageIcon("Card/images/card/backs/blue.png");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                g.setSelectedCard(card);
            }
        });
    }

    /**
     * If you just want a card back, we do not store a card
     * @param g the gui this card is connected to
     */
    public CardButton(GUI g){
        gui = g;
        backImage = new ImageIcon("Card/images/card/backs/blue.png");
        backOnly = true;
        //if we use this constructor, set to null as these are not intractable and only for display
        card = null;
        image = null;
    }

    public Card getCard() {
        return card;
    }

    public ImageIcon getImageRaw() {
        return image;
    }

    public ImageIcon getBackImageRaw() {
        return backImage;
    }
    public ImageIcon getImage(){
        if(!backOnly){
            return image;
        }
        return backImage;
    }
}
