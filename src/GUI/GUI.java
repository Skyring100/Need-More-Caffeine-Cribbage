package src.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Current version
 */
public class GUI extends JFrame{
    private final int START_WIDTH = 1000;
    private final int START_HEIGHT = 1500;
    private JPanel handPanel, deckPanel, tablePanel;
    private JButton[] cardButtons;
    private JLabel bot_Cards[];
    private ImageIcon[] cardImages;
    private int deckCount = 0, tableCount = 0;

    private WelcomePanel introScreen;
    private GamePanel mainScreen;
    public GUI() {
        setTitle("Cribbage");
        setSize(START_WIDTH, START_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//opens the window at center
        setResizable(false); // disabling the resizable function
        introScreen = new WelcomePanel();
        mainScreen = new GamePanel();
        add(introScreen);
        setVisible(true);
    }
    public void showGame(){
        introScreen.setVisible(false);
        add(mainScreen);
    }
}


