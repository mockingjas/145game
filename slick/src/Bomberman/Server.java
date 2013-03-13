/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Random;

public class Server {
	
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
			
			int start1 = 0, start2 = 0;
			
			rand = new Random();
			start1 = rand.nextInt(4);
			System.out.println("Start1: " + start1);
			do{
				rand = new Random();
				start2 = rand.nextInt(4);
			} while(start1 == start2);
			System.out.println("Start2: " + start2);
			
			System.out.println(walls.length());
			String[] playernames = {"mario", "luigi"};
			int count = 0;
			while(true) {
				Socket socket = ssocket.accept();
				System.out.println("Server: " + socket.getInetAddress() + " connect!\n");
				++count;
				Threads t;
				if(count == 1) {
					t = new Threads(socket);
				}
				else {
					t = new Threads(socket);
				}
				t.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
