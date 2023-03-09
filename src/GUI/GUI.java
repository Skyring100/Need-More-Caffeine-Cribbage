package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Current version
 */
public class GUI extends JFrame{
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1500;
    private JPanel handPanel, deckPanel, tablePanel;
    private JButton[] cardButtons;
    private ImageIcon[] cardImages;
    private int deckCount = 0, tableCount = 0;
    public GUI() {
        // frame
        setTitle("Cribbage");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//opens the window at center
        //setResizable(false); // disabling the resizable function
        GamePanel g = new GamePanel();
        add(g, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}


