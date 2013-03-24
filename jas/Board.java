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
	JPanel[][] square;
	
    public Board(String walls, String bonuses) {
        Dimension thisSize = new Dimension(600, 600);
        
        this.setLayout(new GridLayout(11, 11));
        this.setPreferredSize(thisSize);
        this.setBounds(0, 0, thisSize.width, thisSize.height);
		
        // this, white - traversible, gray - pillars
		this.square = new JPanel[11][11];
		
		for (int row = 0; row < 11; row++) {
			for (int col = 0; col < 11; col++) {
				this.square[row][col] = new JPanel(new BorderLayout());
				String file_bg = "";
				// even rows have no pillars
				if (row % 2 == 0) {
					file_bg = "data/grass.jpg";
					square[row][col].setBackground(Color.black);
				}
				// odd rows have alternating pillars
				else {
					if (col % 2 == 0) {
						file_bg = "data/grass.jpg";
						square[row][col].setBackground(Color.black);
					} else {
						square[row][col].setBackground(Color.gray);
						file_bg = "data/blocks.png";
					}
				}
				// for walls and bonuses
				if ( square[row][col].getBackground() == Color.black ) {
					if ( walls.charAt( (row*11)+col ) == '1' ) {
						file_bg = "data/bricks.png";
						if ( bonuses.charAt( (row*11)+col ) == '1' || bonuses.charAt( (row*11)+col ) == '2' ) {
							square[row][col].setBackground(Color.blue);
						}
						else {
							square[row][col].setBackground(Color.green);
						}
					}
				}
				square[row][col].add( new JLabel( new ImageIcon(file_bg) ) );
				this.add(square[row][col]);
			}
		}
        this.setVisible(true);
    }
    
    public int addPlayer (Player p, int start) {

        int loc = 0;
        if (start == 0) {
            loc = 0;
			p.x = 0; p.y = 0;
        } else if (start == 1) {
            loc = 10;
			p.x = 0; p.y = 10;
        } else if (start == 2) {
            loc = 110;
			p.x = 10; p.y = 0;
        } else if (start == 3) {
            loc = 120;
			p.x = 10; p.y = 10;
        }
        p.loc = loc;
		this.square[p.x][p.y].removeAll();
		square[p.x][p.y].add(new JLabel( new ImageIcon("data/grass.jpg")));
		square[p.x][p.y].add(p.piece);
//        validate();
//        repaint();
        return p.loc;
    }
	
}
