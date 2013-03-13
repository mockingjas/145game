/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import java.lang.Thread;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Random;
import java.util.ArrayList;

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
						sendToAll(msg);
						System.out.println(msg);
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
	
	public Threads (Socket s) {
		this.ss = s;
		this.playerName = playerName;
		try {
			this.con = new MyConnection(this.ss);
			this.con.playerName = playerName;
			this.clientList.add(this.con);
			sendToAll("Server message: " + this.playerName + " has connected");
 			con.sendMessage("/thisisme " + this.playerName);
//			con.sendMessage("/startpos " + startMe);
//			con.sendMessage("/thisisopp " + oppName);
//			con.sendMessage("/opponentstartpos " + startOpp);
//			con.sendMessage("/map " + walls);
			if(playerName == "luigi") {
				sendToAll("/startGame");
			}
			for(int i = 0; i < clientList.size(); i++) {
				//clientList.get(i).
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}