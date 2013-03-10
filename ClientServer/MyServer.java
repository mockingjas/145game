import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class MyServer {

	static Vector <MyConnection> all = new Vector <MyConnection>();
	static String[] clientName = new String[100];
	static int allCtr = 0;
	static int wall[] = new int[121];
	static int start1, start2;
	
	public static void sendToAll(String allMsg) throws Exception{
		for( int i = 0; i < all.size(); i++ ){
			all.get(i).sendMessage(allMsg);
		}
	}
		
	public static class clientHandler implements Runnable {
	
		Socket s;
		String tempmsg, msg = "";
		
		public clientHandler(Socket s){
			this.s = s;
		}

		public void run(){
			try{
				MyConnection conn = new MyConnection(s);
				while(true) {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}	
	
	public static void printList() {
		// Diagnostic messages
		System.out.println("\nThis is the client list:");
		for( int j = 0; j < clientName.length; j++){
			if(clientName[j] != null ) System.out.println(clientName[j]);
		}
		System.out.println("This ends the client list.\n");
	}
	
	public static void gameBoard() throws Exception{
		
		Random rand;
		int r;
					
		for( int i = 0; i < 121; i++){
			rand = new Random();
			r = rand.nextInt(2);
			if( r == 0 && i != 0 && i != 1 && i != 9 && i != 10 && i != 11 && i != 21 && i != 99 && i != 109 && i != 110 && i != 111 && i != 119 && i != 120)
				wall[i] = 1;	// may wall
			else wall[i] = 0;	// walang wall
		}
		
	}
	
	public static void sendPos() throws Exception {	
		sendToAll("/startpos");
		for( int i = 0; i < clientName.length; i++){
			if(clientName[i] != null ){
				if( i == 1 ){
					System.out.println("client: "+clientName[i] + " " + start1);
					sendToAll("/pos1" + " " + start1);
				}
				else if( i == 2 ){
					System.out.println("client: "+clientName[i] + " " + start2);
					sendToAll("/pos2" + " " + start2);
				}
			}
		}
		sendToAll("/endpos");
	}
	
	public static void main(String args[]) throws Exception {
		try {
			System.out.println("Server message: Starting Server...");
			ServerSocket ssocket = new ServerSocket(8888);
			System.out.println("Server message: Waiting for connections...");
				
			gameBoard(); // generate board
			
			while(true) {
				allCtr++;
				Socket socket = ssocket.accept();			
				MyConnection conn = new MyConnection(socket);
				
				if(allCtr == 3)
					conn.sendMessage("Game full.");
				else{
					all.add(conn);
					clientName[allCtr] = "Client" + Integer.toString(allCtr);
					conn.sendMessage("/clientname" + clientName[allCtr]);
					sendToAll("Server message: " + clientName[allCtr] + " has connected!");
					printList();
					
					// send board config
					sendToAll("/startboard");
					for( int i = 0; i < 120; i++ ){
						System.out.print(wall[i]);
						sendToAll(Integer.toString(wall[i]));
					}
					sendToAll("/endboard");			
				
					// position player	
					Random rand;					
					if( allCtr == 1 ){
						rand = new Random();
						start1 = rand.nextInt(4);
						System.out.println("Start1: " + start1);
						sendPos();
					}
					if( allCtr == 2 ){
						do{
							rand = new Random();
							start2 = rand.nextInt(4);
						} while(start1 == start2);
						System.out.println("Start1: " + start1 + "Start2: "+start2);
						sendPos();
					}
						
					Thread t1 = new Thread(new clientHandler(socket));
					t1.start();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}