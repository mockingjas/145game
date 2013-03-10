import java.io.*;
import java.net.*; 

public class MyConnection {
	 
	Socket s;	
	String msg;
	InputStream a;		
	InputStreamReader b;
	BufferedReader c;
	OutputStream aa;		
	OutputStreamWriter bb;
	PrintWriter cc;
	String message;
	
 	public MyConnection(Socket s) throws Exception {
	
		this.s = s;
		
		a = s.getInputStream();
		b = new InputStreamReader(a);
		c = new BufferedReader(b);
			
		aa = s.getOutputStream();		
		bb = new OutputStreamWriter(aa);
		cc = new PrintWriter(bb);
			
	}
	
	public boolean sendMessage(String msg) throws Exception {
	
		this.msg = msg;
		cc.println(msg);
		cc.flush();
		return true;
		
	}
	
	public String getMessage() throws Exception {
					
		message = c.readLine();
		return message;
		
	}
}
