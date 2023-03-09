package src.GUI;

import src.GUI.myLabel;
import src.Player;
import src.card.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class User_panel extends JPanel{

    final String[] CARDS = { "CA","C2","C3","C4","C5","C6","C7","C8","C9","CT","CJ","CQ","CK",

            "DA","D2","D3","D4","D5","D6","D7","D8","D9","DT","DJ","DQ","DK",
            "HA","H2","H3","H4","H5","H6","H7","H8","H9","HT","HJ","HQ","HK",
            "SA","S2","S3","S4","S5","S6","S7","S8","S9","ST","SJ","SQ","SK"
    };
    final String[] IMAGES = { "CA","C2","C3","C4","C5","C6","C7","C8","C9","CT","CJ","CQ","CK",
            "cards/Dimond A.jpg","D2","D3","D4","D5","D6","D7","D8","D9","DT","DJ","DQ","DK",
            "HA","H2","H3","H4","H5","H6","H7","H8","H9","HT","HJ","HQ","HK",
            "SA","S2","S3","S4","S5","S6","S7","S8","S9","ST","SJ","SQ","SK"
    };

    public User_panel(){
        this.setLayout(new FlowLayout());
        this.setSize(1000, 1000);
        this.setBackground(Color.BLUE);
        this.setOpaque(true);
        this.add((new myLabel(new ImageIcon("club 2_resized.jpg"))));
        this.add((new myLabel(new ImageIcon("club 2_resized.jpg"))));
        this.add((new myLabel(new ImageIcon("club 2_resized.jpg"))));
        this.add((new myLabel(new ImageIcon("club 2_resized.jpg"))));
        this.add((new myLabel(new ImageIcon("club 2_resized.jpg"))));


    }
    public String get_Images(Player p){
        for (int i =0 ; i < p.getHand().size(); i++){
            for (int j = 0; j < CARDS.length; j++){
                if (CARDS[j].equals(p.getHand().get(i))){
                    return IMAGES[j];
                }
            }
        }
        return "";
    }


}
