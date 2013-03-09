
package bomberman;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Bomberman extends JFrame implements KeyListener{
   JLayeredPane layeredPane;
   JPanel board;
   private int dx;
   private int dy;
   private int x;
   private int y;
   private Image image;
   int loc=0;
   JLabel piece; 

    public Bomberman(){
       ImageIcon img = new ImageIcon("data/walk1.gif");
       piece = new JLabel( img );
      Dimension boardSize = new Dimension(600, 600);
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(boardSize);
      addKeyListener(this);
      board = new JPanel();
      layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
      board.setLayout( new GridLayout(11, 11) );
      board.setPreferredSize( boardSize );
      board.setBounds(0, 0, boardSize.width, boardSize.height);
      
      // board, white - traversible, gray - pillars
      for (int i = 0; i < 121; i++) {
        JPanel square = new JPanel( new BorderLayout() );
        board.add( square );
        
        int row = (i / 11) % 2;
        if (row == 0 ) square.setBackground( Color.white );
      //  else square.setBackground( i % 2 == 1 ? Color.white : Color.gray  );
        else{if(i%2==1){square.setBackground(Color.white);}
        else{square.setBackground(Color.gray); 
                JLabel invi= new JLabel();
                square.add(invi);}
            }
      }
      
      // Randomize wall positions (green)
      Random rand = new Random();
    
      int i = 0, wall;
      while( i < 121 ){
        int row = (i / 11) % 2;
        if( row != 0 ){
            if( i % 2 == 1 ){
               wall = rand.nextInt(2);
               if( wall == 0 && i != 0 && i != 1 && i != 9 && i != 10 && i != 11 && i != 21 && i != 99 && i != 109 && i != 110 && i != 111 && i != 119 && i != 120)
                    board.getComponent(i).setBackground(Color.green);    
            }
        }
        else{
           wall = rand.nextInt(2);
           if( wall == 0 && i != 0 && i != 1 && i != 9 && i != 10 && i != 11 && i != 21 && i != 99 && i != 109 && i != 110 && i != 111 && i != 119 && i != 120)
               board.getComponent(i).setBackground(Color.green);     
        }
        i++;
     }
       //Randomize Starting point
       int start = rand.nextInt(4);
       JPanel panel = null;
       if( start == 0 ) {loc=0; panel = (JPanel)board.getComponent(0);}
       else if( start == 1) {loc=10; panel = (JPanel)board.getComponent(10);}
       else if( start == 2) {loc=110; panel = (JPanel)board.getComponent(110);}
       else if( start == 3) {loc=120; panel = (JPanel)board.getComponent(120);}
       panel.add(piece);         
       
        image = img.getImage();
        x = 40;
        y = 60;
       // Move  
    }
    
     public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {
        JPanel panel= (JPanel)board.getComponent(loc);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            panel.remove(0);
            loc--;
            panel= (JPanel)board.getComponent(loc);
            panel.add(piece);
            validate();
            repaint();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            panel.remove(0);
            loc++;
            panel= (JPanel)board.getComponent(loc);
            panel.add(piece);
            validate();
            repaint();
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            panel.remove(0);
            loc-=11;
            panel= (JPanel)board.getComponent(loc);
            panel.add(piece);
            validate();
            repaint();            
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            panel.remove(0);
            loc+=11;
            panel= (JPanel)board.getComponent(loc);
            panel.add(piece);
            validate();
            repaint();
        }
            
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    public static void main(String[] args) {
        Bomberman b = new Bomberman();
        b.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        b.pack();
        b.setResizable(true);
        b.setLocationRelativeTo( null );
        b.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
        
    }
}
