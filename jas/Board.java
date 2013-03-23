import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Board extends JPanel {
    
//    Player playerMe, playerOpp;
    Timer t;
    int bomb;
	
    public Board(String walls) {
        Dimension thisSize = new Dimension(600, 600);
        
        this.setLayout(new GridLayout(11, 11));
        this.setPreferredSize(thisSize);
        this.setBounds(0, 0, thisSize.width, thisSize.height);
		
        // this, white - traversible, gray - pillars
        JPanel square;
		JLayeredPane squareLayer;
        for (int i = 0; i < 121; i++) {
			squareLayer = new JLayeredPane();
			squareLayer.setPreferredSize(new Dimension(55, 52));
            square = new JPanel(new BorderLayout());
            square.add(squareLayer);
			
			String file_bg = "";
            int row = (i / 11) % 2;
            if (row == 0) {
				if(walls.charAt(i) == '1') {
					square.setBackground(Color.green);
					file_bg = "data/bricks.png";
//					temp = new JLabel(new ImageIcon("data/bricks.png"));
//					((JPanel)this.getComponent(i)).add(temp);
				} else {
					square.setBackground(Color.black);
					file_bg = "data/grass.png";
				}
                
            }
            else {
                if (i % 2 == 1) {
					if(walls.charAt(i) == 1) {
						square.setBackground(Color.green);
						file_bg = "data/bricks.png";
					} else {
						square.setBackground(Color.black);
						file_bg = "data/grass.png";
					}
                } else {
                    square.setBackground(Color.gray);
                    file_bg = "data/blocks.png";
                }
            }
            JLabel temp = new JLabel(new ImageIcon(file_bg));
            temp.setVisible(true);
//            square.add(temp);
			int x;
			if ( file_bg.equals("data/bricks.png")) {
				squareLayer.add(new JLabel( new ImageIcon("data/grass.png") ), new Integer(1) );
				squareLayer.add(temp, new Integer(2));
			}
			else 
				squareLayer.add(temp, new Integer(1));
            this.add(square);
			temp.setBounds( 0, 0,  55, 55 );
			
			
			
        }
        
        this.setVisible(true);
    }
    
    public int addPlayer (Player p, int start) {
        JPanel panel = null;
        int loc = 0;
        if (start == 0) {
            loc = 0;
            panel = (JPanel) this.getComponent(0);
        } else if (start == 1) {
            loc = 10;
            panel = (JPanel) this.getComponent(10);
        } else if (start == 2) {
            loc = 110;
            panel = (JPanel) this.getComponent(110);
        } else if (start == 3) {
            loc = 120;
            panel = (JPanel) this.getComponent(120);
        }
        p.loc = loc;
        ((JLayeredPane) panel.getComponent(0)).add(p.piece, new Integer(2));
		p.piece.setBounds(0,0,55,55);
        validate();
        repaint();
        return p.loc;
    }
	
}
