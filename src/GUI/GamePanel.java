package src.GUI;

import src.OverlapLayout;
import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GamePanel extends JPanel{

    private JPanel player1Panel, cribPanel, pegPanel, player2Panel, crib_board;
    //private ArrayList<Card> pegging_cards ,player_cards, crib_card;
    private ArrayList<CardButton> player1Hand = new ArrayList<>();
    private ArrayList<CardButton> player2Hand = new ArrayList<>();
    private GUI gui;


    public GamePanel(GUI gui){
        this.gui = gui;
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout

        create_user_panel();
        create_crib_panel();
        create_pegging_panel();
        create_bot_panel();




        add(player1Panel, BorderLayout.SOUTH);
        add(cribPanel, BorderLayout.EAST);
        add(pegPanel, BorderLayout.CENTER);
        add(player2Panel,BorderLayout.NORTH);
    }
    private void create_user_panel() {
        player1Panel = new JPanel(); // creating a hand panel
        player1Panel.setLayout(new FlowLayout()); // setting the layout of the hand panel
        //add_action_to_button();
        player1Panel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel
    }

    /**
     * Removes a card from the controlling player's hand
     * @param removeThis the card that will be removed
     */
    public void removePlayer1Card(Card removeThis){
        for(CardButton c : player1Hand){
            if(c.getCard().equals(removeThis)){
                player1Hand.remove(c);
                player1Panel.remove(c);
                return;
            }
        }
        //if not found, there is an error
        throw new NoSuchElementException();
    }

    /**
     * Removes a card from the other player. It does not matter which one, so remove the first one
     */
    public void removePlayer2Card(){
        player2Panel.remove(player2Hand.get(0));
        player2Hand.remove(player2Hand.get(0));
    }
    /**
     * Adds a new hand to the gui
     * @param hand the hand to add
     */
    public void addPlayer1Cards(ArrayList<Card> hand){
        for(Card c : hand){
            CardButton clickable = new CardButton(c,gui);
            //tracking the button in a variable
            player1Hand.add(clickable);
            //adding the button to the screen
            player1Panel.add(clickable);
        }
    }
    /**
     * Adds a new card to the gui
     * @param card the card to add
     */
    public void addPlayer1Card(Card card){
        CardButton clickable = new CardButton(card,gui);
        //tracking the button in a variable
        player1Hand.add(clickable);
        //adding the button to the screen
        player1Panel.add(clickable);
    }

    /**
     * Adds cards to the opposing player. These are non-intractable cards that do nothing but show the other player has cards
     * @param cardAmount the amount of cards to add
     */
    public void addPlayer2Cards(int cardAmount){
        for(int i = 1; i <= cardAmount; i++){
            CardButton noClick = new CardButton();
            player2Hand.add(noClick);
            player2Panel.add(noClick);
        }
    }



    public void create_panel(ArrayList<Card> cards, JPanel panel){
        for (Card card : cards) {
            panel.removeAll();
            ImageIcon imageIcon = new ImageIcon("Card.images/card.fronts/" + card.toString() + ".png");
            JLabel card_image = new JLabel(imageIcon);
            panel.add(card_image);
        }
    }
    private void create_bot_panel(){
        player2Panel = new JPanel();
        player2Panel.setLayout(new FlowLayout());
    }
    private void create_pegging_panel(){
        OverlapLayout layout = new OverlapLayout(new Point(20, 0));
        layout.setPopupInsets(new Insets(20, 0, 0, 0));
        pegPanel = new JPanel(layout);
    }
    private void create_crib_panel(){
        cribPanel = new JPanel();
        cribPanel.setSize(200,400);
        cribPanel.setLayout(new FlowLayout());

    }
    private void create_board(){
        crib_board = new JPanel();
        JLayeredPane layeredPane = new JLayeredPane();

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
                player2Panel.add(cards[i]);
            }
        }
    }

}
