import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

public class MyClient {
	String msg;
	Bomberman b;
	Player playerMe;
	Player playerOpp;
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
					if (msg.startsWith("/thisisme ")) {
						playerMe = new Player(msg.substring(10));
						System.out.println(playerMe.name);
					} 
					else if (msg.startsWith("/startpos ")) {
						int startMe = Integer.parseInt(msg.substring(10));
						playerMe.startPos = startMe;
					} 
					else if (msg.startsWith("/thisisopp ")) {
						playerOpp = new Player(msg.substring(11));
						System.out.println(playerOpp.name);
					} 
					else if (msg.startsWith("/opponentstartpos ")) {
						int startOpp = Integer.parseInt(msg.substring(18));
						playerOpp.startPos = startOpp;
					} 
					else if (msg.startsWith("/map ")) {
						b = new Bomberman(con, playerMe, playerOpp, msg.substring(5));
						b.setVisible(true);
						b.setTitle(playerMe.name + " - Bomberman");
						b.repaint();
						System.out.println(msg.substring(5));
					}
					else if (msg.startsWith("/startGame")) {
						b.startGame();
					}
					else if (msg.startsWith("/playerMoveLeft ")) {
						System.out.println("LEFT:" + msg.substring(16,21));
						String[] loc = msg.split(" ");
						if (msg.substring(16,21).equals(playerMe.name)) {
							b.updateBoard();
						} else if (msg.substring(16,21).equals(playerOpp.name)) {
							JPanel panel = (JPanel) b.board.getComponent(Integer.parseInt(loc[2]));
							panel.remove(b.playerOpp.piece);
							int newLoc = playerOpp.moveLeft(b.board, Integer.parseInt(loc[2]));
							panel = (JPanel) b.board.getComponent(newLoc);
							b.playerOpp = new Player(playerOpp.name, 0, newLoc);
							b.playerOpp.name = playerOpp.name;
							playerOpp.loc = newLoc;
							b.playerOpp.loc = newLoc;
							panel.add(b.playerOpp.piece);
						}
						b.validate();
						b.repaint();
						System.out.println(msg);
					}
					else if (msg.startsWith("/playerMoveRight ")){
						System.out.println("RIGHT:" + msg.substring(17,22));
						String[] loc = msg.split(" ");
						if (msg.substring(17,22).equals(playerMe.name)) {
							b.updateBoard();
						} else if (msg.substring(17,22).equals(playerOpp.name)) {
							JPanel panel = (JPanel) b.board.getComponent(Integer.parseInt(loc[2]));
							panel.remove(b.playerOpp.piece);
							int newLoc = playerOpp.moveRight(b.board, Integer.parseInt(loc[2]));
							panel = (JPanel) b.board.getComponent(newLoc);
							b.playerOpp = new Player(playerOpp.name, 1, newLoc);
							b.playerOpp.name = playerOpp.name;
							playerOpp.loc = newLoc;
							b.playerOpp.loc = newLoc;
							panel.add(b.playerOpp.piece);
						}
						b.validate();
						b.repaint();
						System.out.println(msg);						
					}
					else if (msg.startsWith("/playerMoveUp ")){
						System.out.println("UP:" + msg.substring(14,19));
						String[] loc = msg.split(" ");
						if (msg.substring(14,19).equals(playerMe.name)) {
							b.updateBoard();
						} else if (msg.substring(14,19).equals(playerOpp.name)) {
							JPanel panel = (JPanel) b.board.getComponent(Integer.parseInt(loc[2]));
							panel.remove(b.playerOpp.piece);
							int newLoc = playerOpp.moveUp(b.board, Integer.parseInt(loc[2]));
							panel = (JPanel) b.board.getComponent(newLoc);
							b.playerOpp = new Player(playerOpp.name, 2, newLoc);
							b.playerOpp.name = playerOpp.name;
							playerOpp.loc = newLoc;
							b.playerOpp.loc = newLoc;
							panel.add(b.playerOpp.piece);
						}
						b.validate();
						b.repaint();
						System.out.println(msg);						
					}
					else if (msg.startsWith("/playerMoveDown ")){
						System.out.println("DOWN:" + msg.substring(16,21));
						String[] loc = msg.split(" ");
						if (msg.substring(16,21).equals(playerMe.name)) {
							b.updateBoard();
						} else if (msg.substring(16,21).equals(playerOpp.name)) {
							JPanel panel = (JPanel) b.board.getComponent(Integer.parseInt(loc[2]));
							panel.remove(b.playerOpp.piece);
							int newLoc = playerOpp.moveDown(b.board, Integer.parseInt(loc[2]));
							panel = (JPanel) b.board.getComponent(newLoc);
							b.playerOpp = new Player(playerOpp.name, 3, newLoc);
							b.playerOpp.name = playerOpp.name;
							playerOpp.loc = newLoc;
							b.playerOpp.loc = newLoc;
							panel.add(b.playerOpp.piece);
						}
						b.validate();
						b.repaint();
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
