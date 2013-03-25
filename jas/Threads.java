import java.lang.Thread;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.sound.sampled.*;

public class Threads extends Thread{
	Socket ss;
	MyConnection con;
	static ArrayList<MyConnection> clientList = new ArrayList<MyConnection>();
	
	String playerName;
	
	public void run() {
		try {
			String msg;
			while(true) {
				msg = con.getMessage();
					if(msg.equals(null)) continue;
					else {
						String[] options = { "Yes", "No" };
						if( msg.equals("/drawGame") ){
							SoundEffect se = new SoundEffect("data/draw.wav");
							int newGame = JOptionPane.showOptionDialog(null, "TIME'S UP: It's a draw!" + "\nRematch?", "Rematch", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
							System.out.println(newGame + "" + playerName);
							sendToAll("/drawResponse " + newGame + " " + playerName);
						}
						else sendToAll(msg);
						System.out.println("SEND TO ALL: " + msg);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendToAll(String msg) {
		for(int i = 0; i < clientList.size(); i++) {
			clientList.get(i).sendMessage(msg);
		}
	}
	
	public int count(String str, char c) {
		int cnt = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == c) cnt++;
		}
		return cnt;
	}
	
	public Threads (Socket s, int clientID, String playerName, String oppName, String walls, String bonuses, int startMe, int startOpp) {
		this.ss = s;
		this.playerName = playerName;
		try {
			this.con = new MyConnection(this.ss);
			this.con.playerName = playerName;
			this.clientList.add(this.con);
			sendToAll("Server message: " + this.playerName + " has connected");
 			con.sendMessage("/thisisme " + this.playerName);
			con.sendMessage("/startpos " + startMe);
			con.sendMessage("/thisisopp " + oppName);
			con.sendMessage("/opponentstartpos " + startOpp);
			StartScreen start = new StartScreen(con, walls, playerName);
			start.setVisible(true);
			con.sendMessage("/map " + walls + " " + bonuses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}