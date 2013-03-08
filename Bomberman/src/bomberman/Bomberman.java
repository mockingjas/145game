
package bomberman;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Bomberman extends JFrame {
   JLayeredPane layeredPane;
   JPanel board;

    public Bomberman(){
      Dimension boardSize = new Dimension(600, 600);
      
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(boardSize);
      
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
        else square.setBackground( i % 2 == 1 ? Color.white : Color.gray );
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
       JLabel piece = new JLabel( new ImageIcon("data/walk1.gif") );
       JPanel panel = null;
       if( start == 0 ) panel = (JPanel)board.getComponent(0);
       else if( start == 1) panel = (JPanel)board.getComponent(10);
       else if( start == 2) panel = (JPanel)board.getComponent(110);
       else if( start == 3) panel = (JPanel)board.getComponent(120);
       panel.add(piece);          
       
       
    }
    
    public static void main(String[] args) {
        Bomberman b = new Bomberman();
        b.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        b.pack();
        b.setResizable(true);
        b.setLocationRelativeTo( null );
        b.setVisible(true);
    }
}
