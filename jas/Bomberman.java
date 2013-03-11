import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Bomberman extends JFrame implements KeyListener {
    
    Board board;
    JLayeredPane layeredPane;
    Player playerMe;
    int loc;
    int bomb;
    Timer t;
    MyConnection con;
    String walls; 
    
    public Bomberman (MyConnection con, Player p, Player o, String walls, int startMe, int startOpp) {
        
        this.con = con;
        setTitle("Bomberman");
        Dimension boardSize = new Dimension(600, 600);
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        addKeyListener(this);
        
        playerMe = p;
        this.walls = walls;
        
        board = new Board(walls);
        layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setResizable(true);
		setLocationRelativeTo(null);
		board.addPlayer(playerMe, startMe);
		board.addPlayer(o, startOpp);
    }
    
        public void keyPressed(KeyEvent e) {
        JPanel panel = (JPanel) board.getComponent(loc);
        int key = e.getKeyCode();
//         playerMe = board.getPlayer();
        int loc = board.getLoc();
        
        if (key == KeyEvent.VK_LEFT) {
        	con.sendMessage("/playerMoveLeft " + (loc-1) + " " + playerMe.name);
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            
        }

        if (key == KeyEvent.VK_UP) {
            
        }

        if (key == KeyEvent.VK_DOWN) {
            
        }
        
        if (key == KeyEvent.VK_SPACE) {
            
        }
        System.err.println(loc);
    }
        
    JPanel p;
    public void fire(int loc) {
        System.out.println("FIRE!");
        System.err.println(loc);
        p = (JPanel) this.getComponent(loc);
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
        System.err.println("asdfasdf");
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
//             playerMe.dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
//             playerMe.dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
//             playerMe.dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
//             playerMe.dy = 0;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
 
    /*
    
    public static void main(String[] args) {
        Bomberman b = new Bomberman();
        b.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        b.pack();
        b.setResizable(true);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        
        Player mario = new Player("data/mario.gif");
        b.board.addPlayer(mario);
    }
    */
}
