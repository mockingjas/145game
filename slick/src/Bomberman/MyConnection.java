/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import java.io.*;
import java.net.*;

public class MyConnection {
	OutputStream out;
	OutputStreamWriter outwriter;
	PrintWriter pwriter;
	InputStream in;
	InputStreamReader inreader;
	BufferedReader breader;
	String playerName;
		
	public MyConnection(Socket s) throws Exception{
		out = s.getOutputStream();
		outwriter = new OutputStreamWriter(out);
		pwriter = new PrintWriter(outwriter);
		
		in = s.getInputStream();
		inreader = new InputStreamReader(in);
		breader = new BufferedReader(inreader); 
	}
	
	public boolean sendMessage(String msg) {
		pwriter.println(msg);
		pwriter.flush();
		return true;
	}
	
	public String getMessage() throws Exception{
		String msg = breader.readLine();
		return msg;
	}
}