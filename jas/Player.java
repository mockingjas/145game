import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author joyamendoza
 */
public class Player extends JLabel {
	
	String name;
    int x, y, dx, dy;
    int startPos, loc;

    public Player(String name) {
        super(new ImageIcon("data/"+name+".gif"));
        this.name = name;
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
            if ((r == 0 && b == 0 && g == 255) || loc % 11 == 0) {
            } //Bawal gray
            else if ((r == 128 && b == 128 && g == 128) || loc % 11 == 0) {
            } else {
                loc--;
            }
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
			if( (r == 0 && b == 0 && g == 255) || loc % 11 == 10 ){}
			//Bawal gray
			else if( (r == 128 && b == 128 && g == 128) || loc % 11 == 10 ){}
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
			if( (r == 0 && b == 0 && g == 255) || loc < 11 ){}
			//Bawal gray
			else if( (r == 128 && b == 128 && g == 128) || loc < 11 ){}
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
			if( (r == 0 && b == 0 && g == 255) || loc > 109 ){}
			//Bawal gray
			else if( (r == 128 && b == 128 && g == 128) || loc > 109 ){}
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
