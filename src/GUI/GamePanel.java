package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel{
    private int deckCount = 0, tableCount = 0;
    private JPanel handPanel, deckPanel, tablePanel, bot_panel;
    JLabel[] user_cards = new JLabel[6];

    private ImageIcon[] cardImages;
    public GamePanel(){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
        create_user_panel();
        create_crib_panel();
        create_pegging_panel();
        create_bot_panel();
        // creating cards
        JLabel[] cards = new JLabel[6];
        bot_initialization(cards);


        add(handPanel, BorderLayout.SOUTH);
        add(deckPanel, BorderLayout.EAST);
        add(tablePanel, BorderLayout.CENTER);
        add(bot_panel,BorderLayout.NORTH);
    }
    private void create_user_panel(){
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new FlowLayout()); // setting the layout of the hand panel
        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel
        for (int i = 0; i < user_cards.length; i++){
            user_cards[i] = new JLabel();
            user_cards[i].setIcon(new ImageIcon("club 2_resized.jpg"));
            user_cards[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = 0, y = 0;
                    JLabel label = (JLabel) e.getSource();
                    if (deckCount < 2) {
                        deckPanel.remove(label);
                        deckPanel.add(label);
                        deckCount++;
                        validate();
                        repaint();
                    } else {
                        tableCount++;
                        tablePanel.add(new JLabel(new ImageIcon("club 3_resized (1).jpg")));
                        bot_panel.remove(0);
                        move_component(x,y,tableCount,tablePanel);
                        tablePanel.add(label);
                        move_component(x,y,tableCount,tablePanel);
                        validate();
                        repaint();
                    }
                }
            });
            handPanel.add(user_cards[i]);
        }
    }
    private void create_bot_panel(){
        bot_panel = new JPanel();
        bot_panel.setBackground(Color.ORANGE);
        bot_panel.setLayout(new FlowLayout());
    }
    private void create_pegging_panel(){
        tablePanel = new JPanel();
        tablePanel.setBackground(Color.BLACK);
    }
    private void move_component(int locationX,int locationY, int count, JPanel panel){
        for (int i = 0; i < count; i++) {
            Component comp = panel.getComponent(i);
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
        deckPanel.setSize(200,1000);
        deckPanel.setLayout(new GridLayout(2,2));
    }
    private void bot_initialization(JLabel[] cards){
        for (int i = 0; i< 6 ; i++){
            cards[i] = new JLabel();
            cards[i].setSize(100,150);
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("blue.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)); //100, 100 add your own size
            cards[i].setIcon(imageIcon1);
            if (i < 2) {
                deckPanel.add(cards[i]);
                move_component(0,0,deckCount,deckPanel);
            }else{
                bot_panel.add(cards[i]);
            }
        }
    }


}
