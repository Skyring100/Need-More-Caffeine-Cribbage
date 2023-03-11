package src.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Another old version
 */
public class OldGUI1 {

    JPanel p,m;


    public OldGUI1(){
        create_a_frame();
    }
    private void create_a_frame(){
        JFrame frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1500,1000);
        p = new JPanel();
        m= new JPanel();
        p.setBackground(Color.red);
        m.setBackground(Color.LIGHT_GRAY);
        p.setOpaque(true);
        m.setOpaque(true);
        frame.add(new User_panel(),BorderLayout.SOUTH);
        frame.add(p,BorderLayout.EAST);
        frame.add(m,BorderLayout.EAST);






        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
