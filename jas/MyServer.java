import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Random;

public class MyServer {
	
	public static void main (String args[]) {
		try {
			System.out.println("\nServer: Starting server...");
			ServerSocket ssocket = new ServerSocket(8080);
			System.out.println("Server: Waiting for connections...");
			
			// Randomize walls
			String walls = "";
			Random rand = new Random();
			for( int i = 0; i < 121; i++){
				rand = new Random();
				int r = rand.nextInt(2);
				if( r == 0 && i != 0 && i != 1 && i != 9 && i != 10 && i != 11 && i != 21 && i != 99 && i != 109 && i != 110 && i != 111 && i != 119 && i != 120)
					walls += "1";	// may wall
				else walls += "0";	// walang wall
			}
			int start = 0, start1 = 0, start2 = 0;
			System.out.println(walls.length());
			String[] playernames = {"mario", "luigi"};
			int count = 0;
			while(true) {
				Socket socket = ssocket.accept();
				System.out.println("Server: " + socket.getInetAddress() + " connect!\n");
				++count;					
				if( count == 1 ){
					rand = new Random();
					start = rand.nextInt(4);
					start1 = start;
					System.out.println("Start1: " + start);
				}
				if( count == 2 ){
					do{
						rand = new Random();
						start = rand.nextInt(4);
					} while(start1 == start);
					start2 = start;
					System.out.println("Start2: " + start);
				}
				
				Threads t = new Threads(socket, count, playernames[count-1], walls);
				
				t.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
