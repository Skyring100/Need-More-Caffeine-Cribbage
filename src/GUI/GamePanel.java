package src.GUI;

import src.Bot;
import src.Game;
import src.Player;
import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{

    private int cribCount = 0;
    private JPanel handPanel, cribpanel, tablePanel, bot_panel;
    JLabel[] user_cards = new JLabel[6];

    public GamePanel(Player player, Game game, Bot bot){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
//        create_user_panel(,game);
        create_user_panel(game,player, bot);
        create_crib_panel();
        create_pegging_panel();
        create_bot_panel();
        // creating cards
        JLabel[] cards = new JLabel[6];
        bot_initialization(cards);


        add(handPanel, BorderLayout.SOUTH);
        add(cribpanel, BorderLayout.EAST);
        add(tablePanel, BorderLayout.CENTER);
        add(bot_panel,BorderLayout.NORTH);
    }
    private void create_user_panel(Game game, Player player, Bot bot){
        handPanel = new JPanel(); // creating a hand panel
        handPanel.setLayout(new FlowLayout()); // setting the layout of the hand panel
//        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel

        ArrayList<ImageIcon> imageIcons_player = get_cards_from_user(player); // images for player
        ArrayList<ImageIcon> imageIcons_bot = get_cards_from_bot(bot); // images for bot

        // creating user cards

        for (int i = 0; i < user_cards.length; i++){
            user_cards[i] = new JLabel(); // creating card label
            user_cards[i].setIcon(imageIcons_player.get(i)); // assigning it an image
            if (player.canPeg(game)){ // checking if the
                int finalI = i;
                user_cards[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (cribCount < 2) {
                        cribpanel.add(label);
                        cribCount++;
                        validate();
                        repaint();
                    } else {
                        tablePanel.add(new JLabel(imageIcons_bot.get(finalI)));
                        bot_panel.remove(0);
                        tablePanel.add(label,FlowLayout.CENTER);
                        validate();
                        repaint();
                    }
                }
            });
        }
            else {
                tablePanel.add(new JLabel(imageIcons_bot.get(i)));
                bot_panel.remove(0);
                validate();
                repaint();
                this.setToolTipText("Sorry the count is more than 31");
            }
            handPanel.add(user_cards[i]);
        }
    }
    private void create_bot_panel(){
        bot_panel = new JPanel();
//        bot_panel.setBackground(Color.ORANGE);
        bot_panel.setLayout(new FlowLayout());
    }
    private void create_pegging_panel(){
        tablePanel = new JPanel();
//        tablePanel.setBackground(Color.BLACK);
    }
    private void create_crib_panel(){
        cribpanel = new JPanel();
//        deckPanel.setBackground(Color.BLUE);
        cribpanel.setSize(200,400);
        cribpanel.setLayout(new FlowLayout());

    }
    private void bot_initialization(JLabel[] cards){
        for (int i = 0; i< 6 ; i++){
            cards[i] = new JLabel();
            cards[i].setSize(100,150);
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("blue.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)); //100, 100 add your own size
            cards[i].setIcon(imageIcon1);
            if (i < 2) {
                cribpanel.add(cards[i]);
            }else{
                bot_panel.add(cards[i]);
            }
        }
    }
    private ArrayList<ImageIcon> get_cards_from_user(Player p){
        ArrayList<ImageIcon> imageIcons = new ArrayList<>();
        ArrayList<Card> cards_images = p.getHand();
        for (Card cardsImage : cards_images) {
            ImageIcon imageIcon = new ImageIcon( "Card.images/card.fronts/"+cardsImage.toString() +"4.png");
            imageIcons.add(imageIcon);
        }
        return imageIcons;
    }
    private ArrayList<ImageIcon> get_cards_from_bot(Bot bot){
        ArrayList<ImageIcon> imageIcons = new ArrayList<>();
        ArrayList<Card> cards_images = bot.getPegHand();
        for (Card cardsImage : cards_images) {
            ImageIcon imageIcon = new ImageIcon("Card.images/card.fronts/"+cardsImage.toString() +"4.png");
            imageIcons.add(imageIcon);
        }
        return imageIcons;
    }
}
