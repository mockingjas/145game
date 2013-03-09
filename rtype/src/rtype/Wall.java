package rtype;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Wall extends JPanel{

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    
    private BufferedImage img;
    
    public Wall() {
        ImageIcon ii = new ImageIcon("bricks.gif");
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        JLabel picLabel = new JLabel(ii);
        add( picLabel );
    }
    
    public Wall(int x, int y) {
        ImageIcon ii = new ImageIcon("bricks.gif");
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}