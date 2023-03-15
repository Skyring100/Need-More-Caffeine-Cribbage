package src.GUI;

import src.Game;
import src.card.Card;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Current version
 */
public class GUI{
    private final int START_WIDTH = 1500;
    private final int START_HEIGHT = 1000;

    private static JFrame window;
    private Card selectedCard;
    /**
     * gets a currently selected card. Will reset the card everytime it is called
     * @return the selected card
     */
    public Card getSelectedCard() {
        //as soon as the card has been read, reset it
        Card temp = selectedCard;
        setSelectedCard(null);
        return temp;
    }

    public void setSelectedCard(Card card) {
        selectedCard = card;
    }


    private WelcomePanel introScreen;
    private GamePanel mainScreen;

    private Game game;

    public ArrayList<Card> getPeggingCards() {
        return game.getCurrentPegList();
    }

    public ArrayList<Card> getPlayer1Cards() {
        return game.getPlayer1().getHand();
    }

    public ArrayList<Card> getCribCards() {
        return game.getCrib();
    }

    public GUI(Game game) {
        window = new JFrame();
        this.game = game;
        selectedCard = null;
        window.setTitle("Cribbage");
        window.setSize(START_WIDTH, START_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);//opens the window at center
        window.setResizable(false); // disabling the resizable function
        introScreen = new WelcomePanel(this);
        mainScreen = new GamePanel(this);
        window.add(introScreen);
        window.setVisible(true);
        //this call is temporary, take it out later
        //showGame();
    }
    public void showGame(){
        introScreen.setVisible(false);
        window.add(mainScreen);
    }
}

