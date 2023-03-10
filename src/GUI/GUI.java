package src.GUI;

import javax.swing.*;

/**
 * Responsible for coordination everything related to displaying a game of cribbage
 */
public class GUI{
    private final int START_WIDTH = 1500;
    private final int START_HEIGHT = 1000;
    private JPanel handPanel, deckPanel, tablePanel;
    private JButton[] cardButtons;
    private JLabel bot_Cards[];
    private ImageIcon[] cardImages;
    private int deckCount = 0, tableCount = 0;

    private static JFrame window;
    private static WelcomePanel introScreen;
    private static GamePanel mainScreen;
    public GUI() {
        window = new JFrame();
        window.setTitle("Cribbage");
        window.setSize(START_WIDTH, START_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);//opens the window at center
        window.setResizable(false); // disabling the resizable function
        introScreen = new WelcomePanel();
        mainScreen = new GamePanel();
        window.add(introScreen);
        window.setVisible(true);
    }
    public static void showGame(){
        introScreen.setVisible(false);
        window.add(mainScreen);
    }

}


