package src.GUI;

import src.Bot;
import src.Game;
import src.card.Card;
import src.card.Deck;
import src.card.Rank;
import src.card.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Current version
 */
public class GUI{
    private final int START_WIDTH = 1500;
    private final int START_HEIGHT = 1000;

    private static JFrame window;
    private Card selectedCard;
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

    public GUI() {
        window = new JFrame();
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
    }
    public void showGame(Game g){
        game = g;
        introScreen.setVisible(false);
        window.remove(introScreen);
        window.add(mainScreen);
    }
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
    public void drawCard(boolean isPlayer1, Card c){
        if(isPlayer1){
            mainScreen.addPlayer1Card(c);
        }else{
            mainScreen.addPlayer2Cards(1);
        }
        window.repaint();
    }
    public void discard(boolean isPlayer1, Card c){

    }
    public void pegCard(boolean isPlayer1, Card c){

    }

    //FOR DEBUGGING GUI
    public static void main(String[] args) {
        GUI gui = new GUI();
        Game game = new Game(new Bot(), new  Bot(), gui);
        gui.showGame(game);
        Card c = new Deck().draw();
        System.out.println(c);
        gui.drawCard(true,c);
//        JPanel p = new JPanel();
//        p.setBackground(Color.BLACK);
//        p.setSize(100,110);
//        window.add(p);

    }
}

