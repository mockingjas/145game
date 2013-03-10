import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
	String msg;
	Bomberman b;
	Player p;
	String playerName;
	
	public static void main (String args[]) {
		new MyClient();
	}
	
	public MyClient() {
		try {
			System.out.println("\nClient: Connecting to server...");
			Socket socket = new Socket("127.0.0.1",8080);
			
			MyConnection con = new MyConnection(socket);
			System.out.println("Client: I connected! ^_^\n");
			
			clientThread getThread = new clientThread(con);
			getThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class clientThread extends Thread {
		MyConnection con;
		clientThread(MyConnection con) {
			this.con = con;
		}
		
		public void run() {
			try {
				while(!(msg=con.getMessage()).equals("/quit")) {
					if(msg.startsWith("/thisisme")) {
						p = new Player(msg.substring(10, msg.length()));
					} else if(msg.startsWith("/map ")) {
						b = new Bomberman(con, p, msg.substring(5));
						b.setVisible(true);
						b.setTitle(p.name + " - Bomberman");
						b.repaint();
						System.out.println(msg.substring(5));
					} else if(msg.startsWith("/playerMoveLeft")) {
// 						int newLoc = b.player.moveLeft(b.board, Integer.parseInt(msg.substring(16)));
// 						JPanel panel = (JPanel) b.board.getComponent(newLoc);
// 						b.player = new Player("data/"+player.name+"_left.gif");
// 						panel.add(player);
// 						b.validate();
// 						b.repaint();
						System.out.println(msg);
					}
					else {
						System.out.println(msg);
					}
					msg = "";
				}
				if(msg.equals("/quit")) {
					//c.setVisible(false);
					System.exit(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
