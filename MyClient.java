import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.lang.Object;
import java.io.*;
import java.net.*;

public class MyClient extends JFrame implements KeyListener {
	static Socket socket;
	static MyConnection conn;
	static int globalCtr = 0;
	static String[] clientList = new String[100];
	static String reply;
	
	// GUI
    JLayeredPane layeredPane;
	static JPanel board;
	JPanel square;
    int loc = 0;
    JLabel piece;
	static JLabel temp;
	ImageIcon mario;
	Dimension boardSize;
	
	Image image;
	private int dx;
    private int dy;
    private int x;
    private int y;
	
    public MyClient() {

		// GUI
        mario = new ImageIcon("data/mario.gif");
        piece = new JLabel(mario);
        boardSize = new Dimension(600, 600);
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        addKeyListener(this);
        board = new JPanel();
        layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
        board.setLayout(new GridLayout(11, 11));
        board.setPreferredSize(boardSize);
        board.setBounds(0, 0, boardSize.width, boardSize.height);

        // board, white - traversible, gray - pillars
        for (int i = 0; i < 121; i++) {
            square = new JPanel(new BorderLayout());
            String file_bg = "";
            int row = (i / 11) % 2;
            if (row == 0) {
                square.setBackground(Color.white);
                //file_bg = "data/floor.png";
                
            } //  else square.setBackground( i % 2 == 1 ? Color.white : Color.gray  );
            else {
                if (i % 2 == 1) {
                    square.setBackground(Color.white);
                    //file_bg = "data/floor.png";
                } else {
                    square.setBackground(Color.gray);
                    file_bg = "data/blocks.png";
                }
            }
            temp = new JLabel(new ImageIcon(file_bg));
            temp.setVisible(true);
            square.add(temp);
            board.add(square);

        }
	
		try{
			socket = new Socket("127.0.0.1", 8888);		
			System.out.println("Client tries to connect to server...");		
			conn = new MyConnection(socket);
			Thread t2 = new Thread(new allGet(socket));
			t2.start();
		}catch (Exception e) {
			e.printStackTrace();
		}		
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
	
    int bomb;
    Timer t;
    public void keyPressed(KeyEvent e) {
        JPanel panel = (JPanel) board.getComponent(loc);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            panel.remove(piece);
            if(loc-1 >= 0 || loc % 11 != 0) {
                Color p = ((JPanel)board.getComponent(loc-1)).getBackground();
                System.out.println(p);

                int g = p.getGreen();
                int r = p.getRed();
                int b = p.getBlue();

                // Bawal green
                if( (r == 0 && b == 0 && g == 255) || loc % 11 == 0 ){}
                //Bawal gray
                else if( (r == 128 && b == 128 && g == 128) || loc % 11 == 0 ){}
                else loc--;
            }
            panel = (JPanel) board.getComponent(loc);
            piece = new JLabel(new ImageIcon("data/mario_left.gif"));
            panel.add(piece);
            validate();
            repaint();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            panel.remove(piece);
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
            panel = (JPanel) board.getComponent(loc);
            piece = new JLabel(new ImageIcon("data/mario_right.gif"));
            panel.add(piece);
            validate();
            repaint();
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            panel.remove(piece);
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
            panel = (JPanel) board.getComponent(loc);
            panel.add(piece);
            validate();
            repaint();
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            panel.remove(piece);
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
            panel = (JPanel) board.getComponent(loc);
            panel.add(piece);
            validate();
            repaint();
        }
        
        if (key == KeyEvent.VK_SPACE) {
            panel.add(new JLabel( new ImageIcon("data/bomb.png") ));
            
            validate();
            repaint();
            int delay = 5000;
            bomb=loc;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    fire(bomb);
                }
            };
            t = new Timer(delay, taskPerformer);
            t.start();
        }

    }
    JPanel p;
    public void fire(int loc) {
        System.out.println("FIRE!");
        System.err.println(loc);
        p = (JPanel) board.getComponent(loc);
        p.removeAll();
        p.add(new JLabel(new ImageIcon("data/fire_mid.png")));
        validate();
        repaint();
        t.stop();
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                p.removeAll();
            }
        };
        Timer x = new Timer(2000, taskPerformer);
        //x.start();
        System.err.println("asdfasdf");
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
        
        if (key == KeyEvent.VK_SPACE) {
            
        }
    }		
	
	public static class allGet implements Runnable {
		Socket s;
		
		public allGet(Socket s){
			this.s = s;
		}
		
		public void run() {
			try {
				while(true) {
					reply = conn.getMessage();
					System.out.println(reply);
					
					// Show walls
					if( reply.equals("/start board") ){
						int wall[] = new int[121];
						int i = 0;
						reply = conn.getMessage();
						while( !reply.equals("/end board") ){
							wall[i] = Integer.parseInt(reply);
							System.out.println(wall[i]);
							i++;
							reply = conn.getMessage();
						}
						
						int row;
						for( i = 0; i < 121; i++ ){
							row = (i / 11) % 2;
							if( row != 0){
								if( i % 2 == 1 && wall[i] == 1 ){
									board.getComponent(i).setBackground(Color.green);
									JLabel temp = new JLabel(new ImageIcon("data/bricks.png"));
									((JPanel)board.getComponent(i)).add(temp);
								}
							}
							else{
								if( wall[i] == 1 ){
									board.getComponent(i).setBackground(Color.green);
									JLabel temp = new JLabel(new ImageIcon("data/bricks.png"));
									((JPanel)board.getComponent(i)).add(temp);	
								}
							}
						}
						
					}
					
					if( reply.equals("Game full.") ) break;
					
				}
				System.exit(0);
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}

				
	public static void main(String args[]) {
		MyClient b = new MyClient();
        b.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        b.setResizable(true);
        b.setLocationRelativeTo(null);
        b.pack();
        b.setVisible(true);
	}
	
    public void keyTyped(KeyEvent e) {
    }
	
}