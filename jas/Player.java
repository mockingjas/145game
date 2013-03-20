import java.awt.Color;
import javax.swing.*;

public class Player {
	
	JLabel piece;
	String name;
    int x, y, dx, dy;
    int startPos, loc;
	int bombLen, bombCount;

    public Player(String name) {
//        super(new ImageIcon("data/"+name+".gif"));
		piece = new JLabel( new ImageIcon("data/" + name + ".gif") );
        this.name = name;
		this.bombCount = 1;
		this.bombLen = 1;
    }
	
	public Player(String name, int direction, int loc, int bombCount) {
		switch (direction) {
			case 0:	piece = new JLabel( new ImageIcon("data/" + name + "_left.gif") ); break;
			case 1:	piece = new JLabel( new ImageIcon("data/" + name + "_right.gif") ); break;
			case 2: piece = new JLabel( new ImageIcon("data/" + name + ".gif") ); break;
			case 3:	piece = new JLabel( new ImageIcon("data/" + name + ".gif") ); break;
		}
		this.name  = name;
		this.loc = loc;
		this.bombCount = bombCount;
	}

    public void move() {
        x += dx;
        y += dy;
    }
	
	public int moveLeft(Board board, int loc) {
        dx = -1;
        if (loc - 1 >= 0 || loc % 11 != 0) {
            Color p = ((JPanel) board.getComponent(loc - 1)).getBackground();
            System.out.println(p);

            int g = p.getGreen();
            int r = p.getRed();
            int b = p.getBlue();

            // Bawal green
            if ((r == 0 && b == 0 && g == 255) || loc % 11 == 0) {}
			//Bawal gray
            else if ((r == 128 && b == 128 && g == 128) || loc % 11 == 0) {}
			// Bawal white
			else if( (r == 255 && b == 255 && g == 255) || loc < 11 ) {}
			else loc--;
        }
        return loc;
    }

    public int moveRight(Board board, int loc) {
        dx = 1;
        if(loc+1 < 121 || loc % 11 != 10) {
			Color p = ((JPanel)board.getComponent(loc+1)).getBackground();
			System.out.println(p);

			int g = p.getGreen();
			int r = p.getRed();
			int b = p.getBlue();

			// Bawal green
			if( (r == 0 && b == 0 && g == 255) || loc % 11 == 10 ) {}
			//Bawal gray
			else if( (r == 128 && b == 128 && g == 128) || loc % 11 == 10 ) {}
			// Bawal white
			else if( (r == 255 && b == 255 && g == 255) || loc % 11 == 10 ) {}
			else loc++;
        }
        return loc;
    }
	
	public int moveUp(Board board, int loc){
		dy = -1;
        if(loc-11 >= 0 || loc >= 11) {
            Color p = ((JPanel)board.getComponent(loc-11)).getBackground();
            System.out.println(p);

			int g = p.getGreen();
			int r = p.getRed();
			int b = p.getBlue();

			// Bawal green
			if( (r == 0 && b == 0 && g == 255) || loc < 11 ) {}
			// Bawal gray
			else if( (r == 128 && b == 128 && g == 128) || loc < 11 ) {}
			// Bawal white
			else if( (r == 255 && b == 255 && g == 255) || loc < 11 ) {}
			else loc -= 11;
		}
		return loc;		
	}
	
	public int moveDown(Board board, int loc){
		dy = 1;
        if(loc+11 < 121 || loc <= 109) {
            Color p = ((JPanel)board.getComponent(loc+11)).getBackground();
            System.out.println(p);

			int g = p.getGreen();
			int r = p.getRed();
			int b = p.getBlue();

			// Bawal green
			if( (r == 0 && b == 0 && g == 255) || loc > 109 ) {}
			//Bawal gray
			else if( (r == 128 && b == 128 && g == 128) || loc > 109 ) {}
			// Bawal white
			else if( (r == 255 && b == 255 && g == 255) || loc > 109 ) {}
			else loc += 11;
		}
		return loc;			
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
