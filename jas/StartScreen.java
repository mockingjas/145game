import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class StartScreen extends JFrame{
	// GUI
	static GroupLayout layout;
	static Container thiss;
	MyConnection con;
	String walls, playerName;
	JLabel startBg = new JLabel();
	ImageIcon bg = new ImageIcon("data/bg.gif");
	JButton start = new JButton();
	ImageIcon btn = new ImageIcon("data/start.png");
	static int ctr;
		
    public StartScreen(MyConnection con, String walls, String playerName) {
		
		// GUI
		super("Bomberman");
		this.con = con;
		this.walls = walls;
		this.playerName = playerName;
		this.ctr = ctr;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
		startBg.setLayout(null);
		startBg.setIcon(bg);
		this.add(startBg);
		startBg.setBounds(0, 0, 800, 600);
				
		//SoundClipTest sc = new SoundClipTest();
		
		start.setBounds(300, 450, 170, 80);
		start.setIcon(btn);
		startBg.add(start);
		start.addActionListener(new startGame());		
			
    }			
	
	class startGame implements ActionListener{
		public void actionPerformed(ActionEvent event){		
		 	setVisible(false);
			con.sendMessage("/display");
			System.out.println("COUNT: " + ctr);
			if( ctr == 1 ){
				con.sendMessage("/startGame");
				con.sendMessage("/startTime");
			}
			ctr++;
		}
	}
	
	public class SoundClipTest {
   
	   public SoundClipTest() {
	   
		  try {
			File soundFile = new File("dumbshort.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		  } catch (UnsupportedAudioFileException e) {
			 e.printStackTrace();
		  } catch (IOException e) {
			 e.printStackTrace();
		  } catch (LineUnavailableException e) {
			 e.printStackTrace();
		  }
	   }
	}
			
}