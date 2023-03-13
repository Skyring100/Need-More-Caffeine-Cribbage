package src.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Current version
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
        //showGame();
    }
    public static void showGame(){
        introScreen.setVisible(false);
        window.add(mainScreen);
    }

}


