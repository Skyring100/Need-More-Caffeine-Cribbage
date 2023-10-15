package src.GUI;

import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * creating a game log panel which shows how a player and bot made points
 * Sorry that we couldn't finish it on time but we gave our best
 */
public class Game_logs extends JPanel {

    private JPanel title_panel,logs_panel,button_panel;
    private final String[][] Player_data = {
            {"pairs","2"},{"Fifteens","12"},{"Straits","3"},{"your _score ","211"}
    };
    private final String[] Player_colum_names = {"your score", "Score"};
    private final String[][] Bot_data = {
            {"pairs","1"},{"Fifteens","2"},{"Straits","3"},{"Your mom's score","121"}
    };
    private final String[] Bot_colum_names = {"your Mom", "score"};
    public Game_logs(){
        create_user_table();
        create_buttons();
        create_title();

        this.setLayout(new BorderLayout());
        this.add(title_panel, BorderLayout.NORTH);
        this.add(logs_panel,BorderLayout.CENTER);
        logs_panel.add(button_panel,BorderLayout.SOUTH);


    }

    /**
     * creating a table for user and bot which shows how player made 15s and strait.
     */
    public void create_user_table(){

        logs_panel = new JPanel();
        logs_panel.setLayout(new GridLayout(3,2));
        logs_panel.setSize(700,600);

        JTable user_Score_table = new JTable(Player_data, Player_colum_names);

        user_Score_table.setRowHeight(50);
        user_Score_table.setBackground(Color.lightGray);
        user_Score_table.setPreferredScrollableViewportSize(user_Score_table.getPreferredSize());
        user_Score_table.setFont(new Font("Serif", Font.BOLD, 20));
        user_Score_table.setForeground(Color.BLUE);

        user_Score_table.setEnabled(false);

        user_Score_table.setFillsViewportHeight(true);

        logs_panel.add(user_Score_table);
        JTable bot_score_table = new JTable(Bot_data, Bot_colum_names);
        bot_score_table.setRowHeight(50);
        bot_score_table.setEnabled(false);
        bot_score_table.setBackground(new Color(0x0fff0f));
        bot_score_table.setForeground(Color.BLUE);
        bot_score_table.setFont(new Font("Serif", Font.BOLD, 20));
        bot_score_table.setPreferredScrollableViewportSize(bot_score_table.getPreferredSize());

        bot_score_table.setFillsViewportHeight(true);
        logs_panel.add(bot_score_table);


    }

    /**
     * creating buttons for start again or new game
     */
    private void create_buttons(){
        button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(1,2));

        JButton next_round_button = new JButton("Next round");
        next_round_button.setSize(200,50);
        JButton start_again_button = new JButton("Start Again");
        start_again_button.setSize(200,50);
        next_round_button.addActionListener(e -> {
            Game_logs.this.setVisible(false);
            WelcomePanel.frame.remove(Game_logs.this);
            WelcomePanel.frame.add(new GamePanel());
        });
        start_again_button.addActionListener(e -> {
            Game_logs.this.setVisible(false);
            WelcomePanel.frame.remove(Game_logs.this);
            WelcomePanel.frame.add(new GamePanel());
        });
        button_panel.add(start_again_button);
        button_panel.add(next_round_button);
    }

    /**
     * creating a title for the frame
     */
    private void create_title(){
        title_panel = new JPanel();
        JLabel title_label = new JLabel();
        title_label.setBackground(Color.ORANGE);
        title_label.setOpaque(true);
        title_label.setText("Example of the game");
        title_label.setSize(500,100);
        title_label.setFont(new Font("Arial",Font.ITALIC,30));
        title_panel.setBackground(Color.ORANGE);
        title_panel.add(title_label);
    }
    // same thing can be used for 15s as well

    /**
     * getting the string of 15s, strait, pairs
     * @param strait_cards cards for the points
     * @return string convert the cards to the string
     */
    private String get_cards(ArrayList<ArrayList<Card>> strait_cards){
        StringBuilder strait = new StringBuilder();
        for (ArrayList<Card> straitCard : strait_cards) {
            for (Card card : straitCard) {
                strait.append(card.toString()).append(" ");
            }
        }
        return strait.toString();
    }


}