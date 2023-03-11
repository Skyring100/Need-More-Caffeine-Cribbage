package src.GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private int deckCount = 0, tableCount = 0;
    private boolean bot_won= false;
    private JPanel handPanel, deckPanel, tablePanel, boat_panel;
    private JButton[] cardButtons;

    private ImageIcon[] cardImages;
    public GamePanel(){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new GridLayout()); // setting the layout of the hand panel
        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel

        deckPanel = new JPanel();
        deckPanel.setBackground(Color.BLUE);

        tablePanel = new JPanel();
        tablePanel.setBackground(Color.BLACK);

        boat_panel = new JPanel();
        boat_panel.setBackground(Color.ORANGE);
        boat_panel.setLayout(new GridLayout(1,6));
        JLabel[] cards = new JLabel[6];
        for (int i = 0; i< 6 ; i++){
            cards[i] = new JLabel();
            cards[i].setIcon(new ImageIcon("blue.png"));

            if (i < 2) {
                deckPanel.add(cards[i]);
            }else{
                boat_panel.add(cards[i]);
            }

        }


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
                    boat_panel.remove(0);
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
        add(boat_panel,BorderLayout.NORTH);
    }
    public void move_component(int locationX,int locationY, int count, JPanel panel){
        for (int i1 = 0; i1 < count; i1++) {
            Component comp = panel.getComponent(i1);
            comp.setLocation(locationX, locationY);
            comp.setSize(comp.getWidth(), comp.getHeight());
            locationX += 100;
            locationY += 100;
            comp.setLocation(locationX, locationY);
        }}


}
