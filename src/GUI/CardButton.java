package src.GUI;

import src.card.Card;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a clickable card in the gui
 */
public class CardButton extends JLabel {
    private final Card card;
    private final ImageIcon image;
    private final ImageIcon backImage;
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
