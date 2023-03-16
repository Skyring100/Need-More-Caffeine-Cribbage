package src.GUI;

import com.sun.security.auth.UnixNumericUserPrincipal;
import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GamePanel extends JPanel{

    private int cribCount = 0;
    private int hand_count = 0;
    private JPanel handPanel, cribPanel, tablePanel, botPanel;
    JLabel[] userCards = new JLabel[6];


    private ImageIcon[] cardImages;
    public GamePanel(){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
//        create_user_panel(,game);
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
    private void createUserPanel(){
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new FlowLayout()); // setting the layout of the hand panel
//        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel
        for (int i = 0; i < userCards.length; i++){
            userCards[i] = new JLabel();
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/GUI/Card.images/card.fronts/C2.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
            userCards[i].setIcon(imageIcon1);

//            if (p.checkAllCard(g)){

            userCards[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (cribCount < 2) {
                        cribPanel.add(label);
                        cribCount++;
                        hand_count++;
                        validate();
                        repaint();
                    } else {
                        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/GUI/Card.images/card.fronts/C2.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                        tablePanel.add(new JLabel(imageIcon1));
                        botPanel.remove(0);
                        hand_count++;
                        tablePanel.add(label,FlowLayout.CENTER);
                        validate();
                        repaint();
                    }
                    if (hand_count == 6){

                        if (WelcomePanel.frame.getContentPane().equals(GamePanel.this)){
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
                        hand_count=0;

                    }
                }
            });

//        }
//            else {
//                this.setToolTipText("Sorry the count is more than 31");
//            }
            handPanel.add(userCards[i]);

        }
    }
    private void createBotPanel(){
        botPanel = new JPanel();
//        bot_panel.setBackground(Color.ORANGE);
        botPanel.setLayout(new FlowLayout());
    }
    private void createPeggingPanel(){
        tablePanel = new JPanel();
//        tablePanel.setBackground(Color.BLACK);
    }
    private void createCribPanel(){
        cribPanel = new JPanel();
//        deckPanel.setBackground(Color.BLUE);
        cribPanel.setSize(200,400);
        cribPanel.setLayout(new FlowLayout());
    }

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
