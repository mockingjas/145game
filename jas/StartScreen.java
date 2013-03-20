import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class StartScreen extends JFrame{
	// GUI
	static GroupLayout layout;
	static Container thiss;
	MyConnection con;
	String walls;
	String playerName;
	Threads t;
	JLabel startBg = new JLabel();
	ImageIcon bg = new ImageIcon("data/bg.gif");
	JButton start = new JButton();
	ImageIcon btn = new ImageIcon("data/start.png");
		
    public StartScreen(MyConnection con, String walls, String playerName) {
		
		// GUI
		super("Bomberman");
		this.con = con;
		this.walls = walls;
		this.playerName = playerName;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
		thiss = getContentPane();
		thiss.setLayout(null);
		thiss.setBackground(new Color(240,240,240));
		
		startBg.setLayout(null);
		startBg.setIcon(bg);
		this.add(startBg);
		startBg.setBounds(0, 0, 800, 600);
				
		start.setBounds(300, 450, 170, 80);
		start.setIcon(btn);
		startBg.add(start);
		start.addActionListener(new startGame());		
			
    }			
	
	class startGame implements ActionListener{
		public void actionPerformed(ActionEvent event){		
		 	setVisible(false);
			con.sendMessage("/display");
		}
	}
			
}