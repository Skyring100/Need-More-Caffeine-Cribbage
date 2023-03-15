package src.GUI;

import src.Game;

import javax.swing.*;

/**
 * Current version
 */
public class GUI{
    private final int START_WIDTH = 1500;
    private final int START_HEIGHT = 1000;

    private static JFrame window;
    private static WelcomePanel introScreen;
    private static GamePanel mainScreen;
    private Game game;
    public GUI(Game game) {
        window = new JFrame();
        this.game = game;
        window.setTitle("Cribbage");
        window.setSize(START_WIDTH, START_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);//opens the window at center
        window.setResizable(false); // disabling the resizable function
        introScreen = new WelcomePanel();
        mainScreen = new GamePanel(game);
        window.add(introScreen);
        window.setVisible(true);
        //this call is temporary, take it out later
        showGame();
    }
    public void showGame(){
        introScreen.setVisible(false);
        window.add(mainScreen);
    }

    /**
     * Moves a card from a hand to the pegging place
     */
    public void pegCard(){

    }
    public void discard(){

    }
}


