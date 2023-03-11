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
    private ImageIcon[] cardImages;
    private int deckCount = 0, tableCount = 0;
    public GUI() {
        // frame
        setTitle("Cribbage");
        setSize(START_WIDTH, START_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//opens the window at center
        setResizable(false); // disabling the resizable function
        gameBoard();
    }
    
    private void gameBoard(){
        handPanel = new JPanel();
        handPanel.setBackground(Color.LIGHT_GRAY);
        handPanel.setSize(800,150);
        handPanel.setOpaque(true);

        deckPanel = new JPanel();
        deckPanel.setBackground(Color.BLUE);
        deckPanel.setSize(800,150);
        deckPanel.setOpaque(true);

        tablePanel = new JPanel();
        tablePanel.setBackground(Color.BLACK);
        tablePanel.setSize(500,500);
        tablePanel.setOpaque(true);

        cardButtons = new JButton[6];
        cardImages = new ImageIcon[52];
        for (int i = 0; i < 52; i++) {
            cardImages[i] = new ImageIcon("club 2_resized.jpg");
        }

        for (int i = 0; i < 6; i++) {
            cardButtons[i] = new JButton(cardImages[i]);
            cardButtons[i].addActionListener((ActionListener) this);
            handPanel.add(cardButtons[i]);
        }

        add(handPanel, BorderLayout.NORTH);
        add(deckPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);


        //setResizable(false); // disabling the resizable function
        add(new GamePanel());
        //add(new WelcomePanel(this));

        setVisible(true);
    }
//random comment
    public static void main(String[] args) {
        new GUI();
    }
}


