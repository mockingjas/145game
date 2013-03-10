import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Board extends JPanel {
    
    Player player;
    Timer t;
    int loc = 0;
    int bomb;
    public Board(String walls) {
        Dimension thisSize = new Dimension(600, 600);
        
        this.setLayout(new GridLayout(11, 11));
        this.setPreferredSize(thisSize);
        this.setBounds(0, 0, thisSize.width, thisSize.height);
        
        // this, white - traversible, gray - pillars
        JPanel square;
        for (int i = 0; i < 121; i++) {
            square = new JPanel(new BorderLayout());
            String file_bg = "";
            int row = (i / 11) % 2;
            if (row == 0) {
                square.setBackground(Color.black);
                //file_bg = "data/floor.png";
                
            } 
            else {
                if (i % 2 == 1) {
                    square.setBackground(Color.black);
                    //file_bg = "data/floor.png";
                } else {
                    square.setBackground(Color.gray);
                    file_bg = "data/blocks.png";
                }
            }
            JLabel temp = new JLabel(new ImageIcon(file_bg));
            temp.setVisible(true);
            square.add(temp);
            this.add(square);
        }
        
        Random rand = new Random();

        for(int i = 0; i < 121; i++) {
        	if(walls.charAt(i) == '1') {
        		this.getComponent(i).setBackground(Color.green);
                JLabel temp = new JLabel(new ImageIcon("data/bricks.png"));
                ((JPanel)this.getComponent(i)).add(temp);
        	}
        }
        
        this.setVisible(true);
    }
    
    public void addPlayer (Player p) {
        player = p;
        Random rand = new Random();
        int start = rand.nextInt(4);
        JPanel panel = null;
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
        panel.add(p);
        validate();
        repaint();
    }
    
    public Player getPlayer () {
        return player;
    }
    
    public int getLoc() {
        return loc;
    }
}
