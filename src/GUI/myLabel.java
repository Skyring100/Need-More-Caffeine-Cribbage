package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class myLabel extends JLabel{
    Timer timer;

    int xVelocity = 5;
    int yVelocity = 5;
    int x = this.getX();
    int y = this.getY();
    ImageIcon imageIcon1;

    myLabel(ImageIcon imageIcon){
        imageIcon1 = imageIcon;
        this.setIcon(imageIcon1);
        this.setSize(100,150);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                timer = new Timer(10, e1 -> {
                    x = x+ xVelocity;
                    y = y+ yVelocity;
                    myLabel.this.setLocation(x,y);
                    repaint();
                });
                timer.start();
            }
        });
        this.setVerticalAlignment(JLabel.BOTTOM);
        this.setHorizontalAlignment(JLabel.TRAILING);



    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)  g;
        g2d.drawImage(imageIcon1.getImage(),x,y,null);

    }

}
