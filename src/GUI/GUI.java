package src.GUI;

import src.Game;
import src.Player;
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
    private Card selected_card_by_player;

    public Card getSelected_card_by_player() {
        return selected_card_by_player;
    }


    private WelcomePanel introScreen;
    private GamePanel mainScreen;
    private ArrayList<Card> pegging_cards ,player_cards, crib_card;

    private Game game;

    public void setPegging_cards(ArrayList<Card> pegging_cards) {
        this.pegging_cards = pegging_cards;
    }

    public void setPlayer_cards(ArrayList<Card> player_cards) {
        this.player_cards = player_cards;
    }

    public void setCrib_card(ArrayList<Card> crib_card) {
        this.crib_card = crib_card;
    }

    public ArrayList<Card> getPegging_cards() {
        return pegging_cards;
    }

    public ArrayList<Card> getPlayer_cards() {
        return player_cards;
    }

    public ArrayList<Card> getCrib_card() {
        return crib_card;
    }

    public GUI(Game game) {
        window = new JFrame();
        this.game = game;
        window.setTitle("Cribbage");
        window.setSize(START_WIDTH, START_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);//opens the window at center
        window.setResizable(false); // disabling the resizable function
        introScreen = new WelcomePanel(this);
        mainScreen = new GamePanel(this);
        window.add(introScreen);
        window.setVisible(true);
        selected_card_by_player = mainScreen.getSelected_Card();
        setPegging_cards(game.getCurrentPegList());
        setPlayer_cards(game.getPlayer1().getPegHand());
        //this call is temporary, take it out later
        //showGame();
    }
    public void showGame(){
        introScreen.setVisible(false);
        window.add(mainScreen);
    }
}

