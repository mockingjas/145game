package rtype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.*;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Bomber bomber1;
    
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    
    private int[][] pos = { 
        {2380, 29}, {2500, 59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239}, 
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
     };
    
    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        ingame = true;
        
        bomber1 = new Bomber();

        timer = new Timer(5, this);
        timer.start();

        setLayout(new GridLayout(11, 11));
        setBounds(0, 0, 600, 600);

        for (int i = 0; i < 121; i++) {
            //JPanel square = new JPanel(new BorderLayout());
            //add(square);
            
            
            Wall square = new Wall();
            add(square);
            
            int row = (i / 11) % 2;
            if (row == 0) {
                //square.setBackground(Color.white);
                square.setVisible(false);
            } else {
                //square.setBackground(i % 2 == 1 ? Color.white : Color.gray);
                square.setVisible(i % 2 == 1 ? false : true);
            }
        }

        Random rand = new Random();

        int i = 0, wall;
        while (i < 121) {
            int row = (i / 11) % 2;
            if (row != 0) {
                if (i % 2 == 1) {
                    wall = rand.nextInt(2);
                    if (wall == 0 && i != 0 && i != 1 && i != 9 && i != 10 && i != 11 && i != 21 && i != 99 && i != 109 && i != 110 && i != 111 && i != 119 && i != 120) {
                        getComponent(i).setBackground(Color.green);
                    }
                }
            } else {
                wall = rand.nextInt(2);
                if (wall == 0 && i != 0 && i != 1 && i != 9 && i != 10 && i != 11 && i != 21 && i != 99 && i != 109 && i != 110 && i != 111 && i != 119 && i != 120) {
                    getComponent(i).setBackground(Color.green);
                }
            }
            i++;
        }
        
        
        
    }
    
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        /*
        if (ingame) {

            Graphics2D g2d = (Graphics2D)g;

            if (bomber1.isVisible())
                g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(),
                              this);

        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
                         B_HEIGHT / 2);
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        */
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(), this);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        bomber1.move();
        checkCollisions();
        repaint();
    }
    
    public void checkCollisions() {
/*
        Rectangle r3 = bomber1.getBounds();

        for (int j = 0; j<aliens.size(); j++) {
            Alien a = (Alien) aliens.get(j);
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
                bomber1.setVisible(false);
                a.setVisible(false);
                ingame = false;
            }
        }

        
        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);

            Rectangle r1 = m.getBounds();

            for (int j = 0; j<aliens.size(); j++) {
                Alien a = (Alien) aliens.get(j);
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    a.setVisible(false);
                }
            }
        }
*/
    }
    
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            bomber1.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            bomber1.keyPressed(e);
        }
    }
}