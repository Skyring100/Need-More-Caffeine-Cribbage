package src.GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private int deckCount = 0, tableCount = 0;
    private JPanel handPanel, deckPanel, tablePanel, bot_panel;
    private JButton[] cardButtons;

    private ImageIcon[] cardImages;
    public GamePanel(){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
        create_user_panel();
        create_crib_panel();
        create_pegging_panel();
        create_bot_panel();

        JLabel[] cards = new JLabel[6];
        bot_initialization(cards);


        cardButtons = new JButton[6];
        for (int i = 0; i < 6; i++) {
            cardButtons[i] = new JButton(new ImageIcon("club 2_resized.jpg") );// getting the image from the user
            cardButtons[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();
                int x = 0, y = 0;

                if (deckCount < 2) {
                    button.setEnabled(false);
                    deckPanel.add(button);
                    deckCount++;
                    validate();
                    repaint();
                } else {
                    tableCount++;
                    tablePanel.add(new JLabel(new ImageIcon("club 3_resized (1).jpg")));
                    bot_panel.remove(0);
                    move_component(x,y,tableCount,tablePanel);
                    tablePanel.add(button);
                    move_component(x,y,tableCount,tablePanel);
                    validate();
                    repaint();
                }
            });
            handPanel.add(cardButtons[i]);
        }
        add(handPanel, BorderLayout.SOUTH);
        add(deckPanel, BorderLayout.EAST);
        add(tablePanel, BorderLayout.CENTER);
        add(bot_panel,BorderLayout.NORTH);
    }
    private void create_user_panel(){
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new GridLayout()); // setting the layout of the hand panel
        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel
    }
    private void create_bot_panel(){
        bot_panel = new JPanel();
        bot_panel.setBackground(Color.ORANGE);
        bot_panel.setLayout(new GridLayout(1,6));
    }
    private void create_pegging_panel(){
        tablePanel = new JPanel();
        tablePanel.setBackground(Color.BLACK);
    }
    private void move_component(int locationX,int locationY, int count, JPanel panel){
        for (int i1 = 0; i1 < count; i1++) {
            Component comp = panel.getComponent(i1);
            comp.setLocation(locationX, locationY);
            comp.setSize(comp.getWidth(), comp.getHeight());
            locationX += 100;
            locationY += 100;
            comp.setLocation(locationX, locationY);
        }
    }
    private void create_crib_panel(){
        deckPanel = new JPanel();
        deckPanel.setBackground(Color.BLUE);
    }
    private void bot_initialization(JLabel[] cards){
        for (int i = 0; i< 6 ; i++){
            /*
            cards[i] = new JLabel();
            ImageIcon cardIcon = new ImageIcon("blue.png");
            Image cardImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(cardImage);
            cards[i].setIcon(scaledIcon);
             */
            cards[i] = new JLabel();
            cards[i].setIcon(new ImageIcon("Card.images/card.backs/blue.png"));
            if (i < 2) {
                deckPanel.add(cards[i]);
            }else{
                bot_panel.add(cards[i]);
            }
        }
    }


}
