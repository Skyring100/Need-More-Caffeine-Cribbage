package src.GUI;

import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game_logs extends JPanel {
    JTable User_Score_table ;
    JTable Bot_score_table ;

    JButton next_round_button, start_again_button;
    
    JPanel title_panel,logs_panel,button_panel;
    String[][] Player_data = {
            {"pairs",},{"Fifteens",},{"Straits","3"},{"your _score ","211"}
    };
    String[] Player_colum_names = {"your score", "Score"};
    String[][] Bot_data = {
            {"pairs","1"},{"Fifteens","2"},{"Straits","3"},{"Your mom's score","121"}
    };
    String[] Bot_colum_names = {"your Mom", "score"};
    public Game_logs(){
        create_user_table();
        create_buttons();
        create_title();

        this.setLayout(new BorderLayout());
        this.add(title_panel, BorderLayout.NORTH);
        this.add(logs_panel,BorderLayout.CENTER);
        logs_panel.add(button_panel,BorderLayout.SOUTH);


    }
    public void create_user_table(){

        logs_panel = new JPanel();
        logs_panel.setLayout(new GridLayout(3,2));
        logs_panel.setSize(700,600);

    User_Score_table = new JTable(Player_data,Player_colum_names);

    User_Score_table.setRowHeight(50);
    User_Score_table.setBackground(Color.lightGray);
    User_Score_table.setPreferredScrollableViewportSize(User_Score_table.getPreferredSize());
    User_Score_table.setFont(new Font("Serif", Font.BOLD, 20));
    User_Score_table.setForeground(Color.BLUE);

    User_Score_table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(User_Score_table);
        User_Score_table.setFillsViewportHeight(true);

        logs_panel.add(scrollPane);
    Bot_score_table = new JTable(Bot_data,Bot_colum_names);
    Bot_score_table.setRowHeight(50);
    Bot_score_table.setEnabled(false);
    Bot_score_table.setBackground(new Color(0x0fff0f));
    Bot_score_table.setForeground(Color.BLUE);
    Bot_score_table.setFont(new Font("Serif", Font.BOLD, 20));
    Bot_score_table.setPreferredScrollableViewportSize(Bot_score_table.getPreferredSize());
    scrollPane = new JScrollPane(Bot_score_table);
    Bot_score_table.setFillsViewportHeight(true);
        logs_panel.add(scrollPane);


    }
    private void create_buttons(){
        button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(1,2));

        next_round_button = new JButton("Next round");
        next_round_button.setSize(200,50);
        start_again_button = new JButton("Start Again");
        start_again_button.setSize(200,50);
        next_round_button.addActionListener(e -> {
        });
        start_again_button.addActionListener(e -> {

        });
        button_panel.add(start_again_button);
        button_panel.add(next_round_button);
    }
    private void create_title(){
        title_panel = new JPanel();
        JLabel title_label = new JLabel();
        title_label.setBackground(Color.ORANGE);
        title_label.setOpaque(true);
        title_label.setText("this Round's Scores");
        title_label.setSize(500,100);
        title_label.setFont(new Font("Arial",Font.ITALIC,30));
        title_panel.setBackground(Color.ORANGE);
        title_panel.add(title_label);
    }
    // same thing can be used for 15s as well

    private String get_strait(ArrayList<ArrayList<Card>> strait_cards){
        StringBuilder strait = new StringBuilder();
        for (ArrayList<Card> straitCard : strait_cards) {
            for (Card card : straitCard) {
                strait.append(card.toString()).append(" ");
            }
        }
        return strait.toString();
    }

}
