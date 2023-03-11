package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Old version
 */
public class OldGUI2 implements ActionListener {
    JFrame frame; // CREATING A FRAME
    JPanel user_panel,bot_panel,table_panel,crib_panel,crib_bord_panel; // creating various panels
    private JButton[] cardButtons; // creating card buttons
    private JButton move_button, b; //  creating move button to move the card into the crib
    private int crib_count = 0; // creating crib count
    public OldGUI2(){
        create_a_frame();
    }
    // creating a string
    private void create_a_frame(){
        // Creating a frame
        frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setLayout(new BorderLayout());
        frame.add(Crib_bord_panel(),BorderLayout.EAST);
        frame.add(Crib_screen(),BorderLayout.WEST);
        frame.add(User_panel(Color.pink),BorderLayout.SOUTH);
        frame.add(Bot_screen(),BorderLayout.NORTH);
        frame.add(Table_screen(),BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private JPanel User_panel(Color c){
        user_panel = new JPanel();
        user_panel.setSize(1000,600);
        user_panel.setBackground(c);
        user_panel.setLayout(new GridLayout(1,6));
        cardButtons = new JButton[6];
        move_button = new JButton("Move to the crib");

        move_button.setEnabled(false);
//        move_button.addActionListener(this);
        move_button.addActionListener(e ->
        {
            // check if two cards have already been selected
            // enable the move button if two cards have been selected
            if (crib_count == 2) {
                move_button.setEnabled(true);
            }
        });
        user_panel.add(move_button);
        for (int i = 0; i < 6; i++) {
            cardButtons[i] = new JButton();
            b = cardButtons[i];
            b.setIcon(new ImageIcon("club 2_resized.jpg"));
            b.addActionListener(this);
            b.setSize(100,150);
            user_panel.add(b);
        }



        user_panel.setOpaque(true);
        return user_panel;
    }
    private JPanel Bot_screen(){
        bot_panel = new JPanel();
        bot_panel.setBackground(Color.BLUE);
        bot_panel.setSize(1000,100);
        bot_panel.setOpaque(true);
        return bot_panel;
    }
    private JPanel Table_screen(){
        table_panel = new JPanel();
        table_panel.setBackground(Color.BLACK);
        table_panel.setSize(500,500);
        table_panel.setOpaque(true);
        return table_panel;
    }
    private JPanel Crib_bord_panel(){
        crib_bord_panel = new JPanel();
        crib_bord_panel.setBackground(Color.MAGENTA);
        crib_bord_panel.setSize(200,1000);
        crib_bord_panel.setOpaque(true);
        return crib_bord_panel;
    }
    private JPanel Crib_screen(){
        crib_panel = new JPanel();
        crib_panel.setBackground(Color.LIGHT_GRAY);
        crib_panel.setOpaque(true);

        return crib_panel ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        // check if a card button was clicked
        if (sourceButton != move_button) {
            // check if two cards have already been selected
            // enable the move button if two cards have been selected
            if (crib_count == 2) {
                move_button.setEnabled(true);
            }
            if (crib_count >= 2) {
                JOptionPane.showMessageDialog(frame, "You can only select two cards at a time.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // select the card and update the button's text
            sourceButton.setText("Card Selected");
            sourceButton.setEnabled(false);
            crib_count++;
        } else {
            for (int i = 0; i < 6; i++) {
                if (!cardButtons[i].isEnabled()) {
                    user_panel.remove(cardButtons[i]);
                    crib_panel.add(cardButtons[i]);
                    cardButtons[i].setEnabled(true);
                }
            }
        }
    }
}
