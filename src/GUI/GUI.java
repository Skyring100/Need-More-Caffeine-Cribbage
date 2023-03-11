package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Current version
 */
public class GUI extends JFrame implements ActionListener{
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
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
<<<<<<< HEAD
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
            cardButtons[i].addActionListener(this);
            handPanel.add(cardButtons[i]);
        }

        add(handPanel, BorderLayout.NORTH);
        add(deckPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);

=======
        //setResizable(false); // disabling the resizable function
        add(new GamePanel());
       // add(new WelcomePanel(this));
>>>>>>> branch 'main' of https://github.com/Skyring100/Need-More-Caffeine-Cribbage
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setEnabled(false);

        if (deckCount < 2) {
            deckPanel.add(button);
            deckCount++;
            validate();
            repaint();
        } else {
            tableCount++;
            tablePanel.add(button);
            validate();
            repaint();

            int x = 0, y = 0;
            for (int i = 0; i < tableCount; i++) {
                Component comp = tablePanel.getComponent(i);
                comp.setLocation(x, y);
                comp.setSize(71, 96);
                x += 30;
                y += 10;
            }

            animateCard(button, x-30, y-10);
        }
    }
    private void animateCard(JButton button, int x, int y) {
        int startX = button.getX();
        int startY = button.getY();

        for (int i = 0; i < 10; i++) {
            int dx = x - startX;
            int dy = y - startY;
            int stepX = dx / 10;
            int stepY = dy / 10;
            startX += stepX;
            startY += stepY;
            button.setLocation(startX, startY);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {}
        }
    }
    public static void main(String[] args) {
        new GUI();
    }
}


