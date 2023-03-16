package src.GUI;

import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a clickable card in the gui
 */
public class CardButton extends JLabel {
    private final Card card;
    private final ImageIcon frontImage;
    private ImageIcon backImage = new ImageIcon("Card.images/card.backs/blue.png");
    private ImageIcon currentImage;
    private boolean backOnly = false;
    private boolean isFlipped = true;
    private final GUI gui;
    private final Dimension size = new Dimension(100,150);

    /**
     * Creates an intractable card for a GUI
     * @param c the card to transform into a card on a GUI
     * @param g the gui it is connected to
     */
    public CardButton(Card c, GUI g){
        gui = g;
        card = c;
        String frontPicPath = "Card.images/card.fronts/";
        frontImage = new ImageIcon(frontPicPath + card.toString() + ".png");
        backImage = new ImageIcon("Card.images/card.backs/blue.png");
        currentImage = frontImage;
        setSize(size);
        setIcon(currentImage);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                g.setSelectedCard(card);
            }
        });
    }

    /**
     * If you just want a card back, we do not store a card
     */
    public CardButton(){
        backImage = new ImageIcon("Card.images/card.backs/blue.png");
        currentImage = backImage;
        backOnly = true;
        isFlipped = false;
        setSize(size);
        setIcon(currentImage);
        //if we use this constructor, set to null as these are not intractable and only for display
        card = null;
        frontImage = null;
        //we do not need a gui reference sense there is no mouse listener
        gui = null;
    }

    public Card getCard() {
        return card;
    }

    public ImageIcon getFrontImageRaw() {
        return frontImage;
    }

    public ImageIcon getBackImageRaw() {
        return backImage;
    }
    public ImageIcon getImage(){
        return currentImage;
    }

    /**
     * Flips over the card
     */
    public void flipCard(){
        if(!backOnly){
            if(isFlipped){
                isFlipped = false;
                setIcon(backImage);
                return;
            }
            isFlipped = true;
            setCurrentImage(frontImage);
        }
    }
    private void setCurrentImage(ImageIcon i){
        currentImage = i;
        setIcon(currentImage);
    }
}
