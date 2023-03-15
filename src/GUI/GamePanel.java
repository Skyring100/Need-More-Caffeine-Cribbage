package src.GUI;

import src.OverlapLayout;
import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{

    private JPanel handPanel, cribPanel, pegPanel, botPanel;
    private ArrayList<Card> pegging_cards ,player_cards, crib_card;
    private GUI gui;


    public GamePanel(GUI gui){
        this.gui = gui;
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
        add(cribPanel, BorderLayout.EAST);
        add(pegPanel, BorderLayout.CENTER);
        add(botPanel,BorderLayout.NORTH);
    }
    private void create_user_panel() {
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new FlowLayout()); // setting the layout of the hand panel
        add_action_to_button();
        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel

    }

    public void add_action_to_button(){
         for (int i = 0; i < handPanel.getComponentCount(); i++){
             JLabel label = (JLabel) handPanel.getComponent(i);
             int finalI = i; //  to see which card has selected
             label.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent e) {
                     create_panel(player_cards, handPanel);
                     create_panel(pegging_cards,pegPanel);
                     gui.setSelectedCard(player_cards.get(finalI)); // get the selected card

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
        botPanel = new JPanel();
//        bot_panel.setBackground(Color.ORANGE);
        botPanel.setLayout(new FlowLayout());
    }
    private void create_pegging_panel(){
        OverlapLayout layout = new OverlapLayout(new Point(20, 0));
        layout.setPopupInsets(new Insets(20, 0, 0, 0));
        pegPanel = new JPanel(layout);
//        tablePanel.setBackground(Color.BLACK);
    }
    private void create_crib_panel(){
        cribPanel = new JPanel();
//        deckPanel.setBackground(Color.BLUE);
        cribPanel.setSize(200,400);
        cribPanel.setLayout(new FlowLayout());

    }

    private void bot_initialization(JLabel[] cards){
        for (int i = 0; i< 6 ; i++){
            cards[i] = new JLabel();
            cards[i].setSize(100,150);
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("blue.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)); //100, 100 add your own size
            cards[i].setIcon(imageIcon1);
            if (i < 2) {
                cribPanel.add(cards[i]);
            }else{
                botPanel.add(cards[i]);
            }
        }
    }

}
