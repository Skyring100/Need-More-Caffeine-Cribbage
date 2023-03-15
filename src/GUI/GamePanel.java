package src.GUI;

import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{

    private JPanel hand_Panel, crib_panel, pegPanel, bot_panel;
    ArrayList<Card> pegging_cards ,player_cards, crib_card;

  private Card selected_Card;

    public Card getSelected_Card() {
        return selected_Card;
    }

    private void setSelected_Card(Card selected_Card) {
        this.selected_Card = selected_Card;
    }

    public GamePanel(GUI gui){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout

        create_user_panel();
        create_crib_panel();
        create_pegging_panel();
        create_bot_panel();
        // creating cards
        JLabel[] cards = new JLabel[6];
        bot_initialization(cards);
            player_cards = gui.getPlayer1Cards();
            pegging_cards = gui.getPeggingCards();



        add(handPanel, BorderLayout.SOUTH);
        add(cribpanel, BorderLayout.EAST);
        add(peggingPanel, BorderLayout.CENTER);
        add(bot_panel,BorderLayout.NORTH);
    }
    private void create_user_panel() {
        hand_Panel = new JPanel(); // creating a hand panel
        hand_Panel.setLayout(new FlowLayout()); // setting the layout of the hand panel
        add_action_to_button();
        hand_Panel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel

    }
    public void add_action_to_button(){
         for (int i = 0; i < hand_Panel.getComponentCount(); i++){
             JLabel label = (JLabel) hand_Panel.getComponent(i);
             int finalI = i; //  to see which card has selected
             label.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent e) {
                     create_panel(player_cards, hand_Panel);
                     create_panel(pegging_cards,pegPanel);
                     setSelected_Card(player_cards.get(finalI)); // get the selected card

                 }
             });
         }
    }

//    // for user panel
//    public void remove_card(Card card, JPanel panel){
//        ImageIcon imageIcon = new ImageIcon( "Card.images/card.fronts/"+card.toString() +".png");
//        JLabel card_image = new JLabel(imageIcon);
//        panel.remove(card_image);
//    }// for bot panel
    public void create_panel(ArrayList<Card> cards, JPanel panel){
        for (Card card : cards) {
            panel.removeAll();
            ImageIcon imageIcon = new ImageIcon("Card.images/card.fronts/" + card.toString() + ".png");
            JLabel card_image = new JLabel(imageIcon);
            panel.add(card_image);
        }
    }
    private void create_bot_panel(){
        bot_panel = new JPanel();
//        bot_panel.setBackground(Color.ORANGE);
        bot_panel.setLayout(new FlowLayout());
    }
    private void create_pegging_panel(){
        OverlapLayout layout = new OverlapLayout(new Point(20, 0));
        layout.setPopupInsets(new Insets(20, 0, 0, 0));
        peggingPanel = new JPanel(layout);
//        tablePanel.setBackground(Color.BLACK);
    }
    private void create_crib_panel(){
        crib_panel = new JPanel();
//        deckPanel.setBackground(Color.BLUE);
        crib_panel.setSize(200,400);
        crib_panel.setLayout(new FlowLayout());

    }

    private void bot_initialization(JLabel[] cards){
        for (int i = 0; i< 6 ; i++){
            cards[i] = new JLabel();
            cards[i].setSize(100,150);
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("blue.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)); //100, 100 add your own size
            cards[i].setIcon(imageIcon1);
            if (i < 2) {
                crib_panel.add(cards[i]);
            }else{
                bot_panel.add(cards[i]);
            }
        }
    }

}
