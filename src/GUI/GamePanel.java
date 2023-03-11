package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel{
    private int deckCount = 0, tableCount = 0;
    private JPanel handPanel, deckPanel, tablePanel;
    private JButton[] cardButtons;
    private ImageIcon[] cardImages;
    public GamePanel(){
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());
        handPanel = new JPanel();
        handPanel.setLayout(new GridLayout());
        handPanel.setBackground(Color.LIGHT_GRAY);

        deckPanel = new JPanel();
        deckPanel.setBackground(Color.BLUE);

        tablePanel = new JPanel();
        tablePanel.setBackground(Color.BLACK);

        cardButtons = new JButton[6];
        cardImages = new ImageIcon[52];
        for (int i = 0; i < 52; i++) {
            cardImages[i] = new ImageIcon("club 2_resized.jpg");
        }
        for (int i = 0; i < 6; i++) {
            cardButtons[i] = new JButton(new ImageIcon("club 2_resized.jpg") );
            cardButtons[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();
                button.setEnabled(false);

                if (deckCount < 2) {
                    deckPanel.add(button);
                    deckCount++;
                    validate();
                    repaint();
                } else {
                    tableCount++;
                    tablePanel.add(button);
                    validate();
                    repaint();

                    int x = 0, y = 0;
                    for (int i1 = 0; i1 < tableCount; i1++) {
                        Component comp = tablePanel.getComponent(i1);
                        comp.setLocation(x, y);
                        comp.setSize(comp.getWidth(), comp.getHeight());
                        x += 50;
                        y += 50;
                        comp.setLocation(x, y);
                    }
                }
            });
            handPanel.add(cardButtons[i]);
        }
        add(handPanel, BorderLayout.SOUTH);
        add(deckPanel, BorderLayout.EAST);
        add(tablePanel, BorderLayout.CENTER);
    }

}
