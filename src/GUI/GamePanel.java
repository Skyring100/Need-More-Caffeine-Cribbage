package src.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * creating a main game panel where the actual playing happens
 *  * Sorry that we couldn't finish it on time, but we gave our best
 */

public class GamePanel extends JPanel{

    private int cribCount = 0;
    private JPanel handPanel, cribPanel, tablePanel, botPanel;
    JLabel[] userCards = new JLabel[6];
    public GamePanel(){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
        createUserPanel();
        createCribPanel();
        createPeggingPanel();
        createBotPanel();
        // creating cards
        JLabel[] cards = new JLabel[6];
        botInitialization(cards);



        add(handPanel, BorderLayout.SOUTH);
        add(cribPanel, BorderLayout.EAST);
        add(tablePanel, BorderLayout.CENTER);
        add(botPanel,BorderLayout.NORTH);
    }

    /**
     * creating a user panel and assign 6 cards 2 cards goes to the crib and other goes to the pegging area
     */
    private void createUserPanel(){
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new FlowLayout()); // setting the layout of the hand panel
//        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel
        for (int i = 0; i < userCards.length; i++){
            userCards[i] = new JLabel();
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/GUI/Card.images/card.fronts/C2.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
            userCards[i].setIcon(imageIcon1);

            userCards[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (cribCount < 2) {
                        cribPanel.add(label);
                        cribCount++;
                    } else {
                        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/GUI/Card.images/card.fronts/C2.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                        tablePanel.add(new JLabel(imageIcon1));
                        botPanel.remove(0);
                        tablePanel.add(label,FlowLayout.CENTER);
                    }
                    validate();
                    repaint();
                    if ( handPanel.getComponentCount() == 0){

                        if (WelcomePanel.frame.getContentPane() instanceof GamePanel){
                            WelcomePanel.frame.getContentPane().removeAll();
                            WelcomePanel.frame.getContentPane().add(new Game_logs());
                            WelcomePanel.frame.invalidate();
                            WelcomePanel.frame.validate();
                        }else{
                            WelcomePanel.frame.getContentPane().removeAll();
                            WelcomePanel.frame.getContentPane().add(GamePanel.this);
                            WelcomePanel.frame.invalidate();
                            WelcomePanel.frame.validate();
                        }
                    }
                }
            });

            handPanel.add(userCards[i]);

        }
    }

    /**
     * creating a bot panel and assign it's to cards to the crib and 4 to it's hand
     */
    private void createBotPanel(){
        botPanel = new JPanel();
//        bot_panel.setBackground(Color.ORANGE);
        botPanel.setLayout(new FlowLayout());
    }

    /**
     * creating a pegging panel in which the cards are going
     */
    private void createPeggingPanel(){
        tablePanel = new JPanel();
//        tablePanel.setBackground(Color.BLACK);
    }

    /**
     * creating a crib panel
     */
    private void createCribPanel(){
        cribPanel = new JPanel();
//        deckPanel.setBackground(Color.BLUE);
        cribPanel.setSize(200,400);
        cribPanel.setLayout(new FlowLayout());
    }

    /**
     * initializing a bot
     * @param cards array of cards
     */
    private void botInitialization(JLabel[] cards) {
        for (int i = 0; i < 6; i++) {
            cards[i] = new JLabel();
            cards[i].setSize(100, 150);
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("blue.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)); //100, 100 add your own size
            cards[i].setIcon(imageIcon1);
            if (i < 2) {
                cribPanel.add(cards[i]);
            } else {
                botPanel.add(cards[i]);
            }
        }
    }

}