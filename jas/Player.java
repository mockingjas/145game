import java.awt.Color;
import javax.swing.*;

public class Player {
	
	JLabel piece;
	String name;
    int x, y, dx, dy;
    int startPos, loc;
	int bombLen, bombCount;
	boolean dead = false;

    public Player(String name) {
		piece = new JLabel( new ImageIcon("data/" + name + ".gif") );
        this.name = name;
		this.bombCount = 1;
		this.bombLen = 1;
    }
	
	public Player(String name, int direction, int loc, int bombCount, int bombLen) {
		switch (direction) {
			case 0:	piece = new JLabel( new ImageIcon("data/" + name + "_left.gif") ); break;
			case 1:	piece = new JLabel( new ImageIcon("data/" + name + "_right.gif") ); break;
			case 2: piece = new JLabel( new ImageIcon("data/" + name + ".gif") ); break;
			case 3:	piece = new JLabel( new ImageIcon("data/" + name + ".gif") ); break;
		}
		this.name  = name;
		this.loc = loc;
		this.bombCount = bombCount;
		this.bombLen = bombLen;
	}
	
	public int moveLeft(Board board, int loc) {
        dx = -1;
        if (loc - 1 >= 0 || loc % 11 != 0) {
            Color p = ((JPanel) board.getComponent(loc - 1)).getBackground();
            System.out.println(p);
			
			if  ((p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) && loc % 11 != 0) loc--;
			
			
			// Bawal green/gray/white
//            if (Color.green == p || Color.gray == p || Color.white == p || loc % 11 == 0) {}
//			else loc--;
        }
        return loc;
    }

    public int moveRight(Board board, int loc) {
        dx = 1;
        if(loc+1 < 121 || loc % 11 != 10) {
			Color p = ((JPanel)board.getComponent(loc+1)).getBackground();
			System.out.println(p);
			
			if  ((p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) && loc % 11 != 10) loc++;
			
			// Bawal green/gray/white
//			if(Color.green == p || Color.gray == p || Color.white == p || loc % 11 == 10 ) {}
//			else loc++;
        }
        return loc;
    }
	
	public int moveUp(Board board, int loc){
		dy = -1;
        if(loc-11 >= 0 || loc >= 11) {
            Color p = ((JPanel)board.getComponent(loc-11)).getBackground();
            System.out.println(p);
			
			if  ((p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) && loc >= 11) loc-=11;
			
			// Bawal green/gray/white
//			if(Color.green == p || Color.gray == p || Color.white == p || loc < 11 ) {}
//			else loc -= 11;
		}
		return loc;		
	}
	
	public int moveDown(Board board, int loc){
		dy = 1;
        if(loc+11 < 121 || loc <= 109) {
            Color p = ((JPanel)board.getComponent(loc+11)).getBackground();
            System.out.println(p);
			
			if  ((p == Color.red || p == Color.black || p == Color.orange || p == Color.yellow) && loc <= 109) loc+=11;
			
			int g = p.getGreen();
			int r = p.getRed();
			int b = p.getBlue();

			// Bawal green/gray/white
//			if(Color.green == p || Color.gray == p || Color.white == p || loc > 109 ) {}
//			else loc += 11;
		}
		return loc;			
	}
}
