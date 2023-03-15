package src.GUI;

import src.Game;
import src.OverlapLayout;
import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{

    private int cribCount = 0;
    private JPanel handPanel, cribpanel, tablePanel, bot_panel;
    ArrayList<JLabel> pegging_cards = new ArrayList<>();
    ArrayList<JLabel> player_cards = new ArrayList<>();

    public GamePanel(Game game){
        setBackground(Color.GRAY); // setting the background
        setLayout(new BorderLayout()); // setting the layout
//        create_user_panel(,game);
        create_user_panel(game);
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
    private void create_user_panel(Game game){
        OverlapLayout layout = new OverlapLayout(new Point(20, 0));
        layout.setPopupInsets(new Insets(20, 0, 0, 0));
        handPanel = new JPanel(layout); // creating a hand panel
//        handPanel.setBackground(Color.LIGHT_GRAY); // set background of the hand panel

    }
    // for adding cards
    public void addCards(Card card, JPanel panel){
        ImageIcon imageIcon = new ImageIcon( "Card.images/card.fronts/"+card.toString() +".png");
       JLabel card_image = new JLabel(imageIcon);
       panel.add(card_image);

    }
    // for user panel
    public void remove_card(Card card, JPanel panel){
        ImageIcon imageIcon = new ImageIcon( "Card.images/card.fronts/"+card.toString() +".png");
        JLabel card_image = new JLabel(imageIcon);
        panel.remove(card_image);
    }
    // for bot panel
    public void addCards(Card card, JPanel panel, JPanel remove_panel){
        ImageIcon imageIcon = new ImageIcon( "Card.images/card.fronts/"+card.toString() +".png");
        JLabel card_image = new JLabel(imageIcon);
        panel.add(card_image);
        remove_panel.remove(0);

    }


//        for (int i = 0; i < user_cards.length; i++){
//            user_cards[i] = new JLabel(); // creating card label
//            user_cards[i].setIcon(imageIcons.get(i)); // assigning it an image
//                int finalI = i;
//                user_cards[i].addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        JLabel label = (JLabel) e.getSource();
//                        if (cribCount < 2) {
//                            cribpanel.add(label);
//                            cribCount++;
//                            validate();
//                            repaint();
//                        } else {
//                            tablePanel.add(new JLabel());
//                            bot_panel.remove(0);
//                            tablePanel.add(label,FlowLayout.CENTER);
//                            validate();
//                            repaint();
//                        }
//                    }
//                });
//
//            }
//            handPanel.add(user_cards[i]);
//
//    }
    private void create_bot_panel(){
        OverlapLayout layout = new OverlapLayout(new Point(20, 0));
        layout.setPopupInsets(new Insets(20, 0, 0, 0));
        bot_panel = new JPanel(layout);
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
}
