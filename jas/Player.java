import java.awt.Color;
import javax.swing.*;

public class Player {
	
	JLabel piece;
	String name;
    int x, y;
    int startPos, loc;
	int bombLen, bombCount;
	boolean dead = false;

    public Player(String name) {
		piece = new JLabel( new ImageIcon("data/" + name + ".gif") );
        this.name = name;
		this.bombCount = 1;
		this.bombLen = 1;
    }
	
	public Player(String name, int direction, int x, int y, int bombCount, int bombLen) {
		switch (direction) {
			case 0:	piece = new JLabel( new ImageIcon("data/" + name + "_left.gif") ); break;
			case 1:	piece = new JLabel( new ImageIcon("data/" + name + "_right.gif") ); break;
			case 2: piece = new JLabel( new ImageIcon("data/" + name + ".gif") ); break;
			case 3:	piece = new JLabel( new ImageIcon("data/" + name + ".gif") ); break;
		}
		this.name  = name;
		this.x = x;
		this.y = y;
		this.bombCount = bombCount;
		this.bombLen = bombLen;
	}
	
	final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	public boolean move(Board board, int direction) {
		Color p;
		switch (direction) {
			case LEFT:
				if (y - 1 >= 0) {
					p = board.square[x][y-1].getBackground();
					if (p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) {
						y--;
						return true;
					}
				}
				break;
			case RIGHT:
				if (y + 1 <= 10) {
					p = board.square[x][y+1].getBackground();
					if (p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) {
						y++;
						return true;
					}
				}
				break;
			case UP:
				
				if (x - 1 >= 0) {
					p = board.square[x-1][y].getBackground();
					if (p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) {
						x--;
						return true;
					}
				}
				break;
			case DOWN:
				
				if (x + 1 <= 10) {
					p = board.square[x+1][y].getBackground();
					if (p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) {
						x++;
						return true;
					}
				}
				break;
		}
		return false;
	}
}
