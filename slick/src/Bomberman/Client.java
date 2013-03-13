/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Client extends BasicGame {
    String msg;
    String playerName;
    
    Image mario = null;
    Image marioLeft = null;
    Image marioRight = null;
    float x = 25;
    float y = 25;
    float scale = 1;
    
    boolean left = false, right = false;
    
    public static void main (String args[]) throws SlickException {
//		new Client();
                AppGameContainer app =
			new AppGameContainer( new Client() );
         // x, y, full screen
         app.setDisplayMode(1100, 1100, false);
         app.start();
	}
    
    
    public Client() {
        super("Slick2D Bomberman");
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

    @Override
    public void init(GameContainer gc) throws SlickException {
        mario = new Image("data/mario.gif");
        marioLeft = new Image("data/mario_left.gif");
        marioRight = new Image("data/mario_right.gif");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_DOWN))
        {
            float hip = 0.2f * delta;
            y += hip;
            
            
        }
        if(input.isKeyDown(Input.KEY_UP))
        {
            float hip = 0.2f * delta;
            y -= hip;
        }
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
            float hip = 0.2f * delta;
            x += hip;
            right = true;
        }
        if(input.isKeyDown(Input.KEY_LEFT))
        {
            float hip = 0.2f * delta;
            x -= hip;
            left = true;
        }
        
    }
    javax.swing.Timer t;
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        ActionListener stopper = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setFalse();
                }
            };
        if(left) {
            marioLeft.draw(x,y);
            
            t = new javax.swing.Timer(1000, stopper);
            t.start();
            
        }
        if(right) {
            marioRight.draw(x,y);
            t = new javax.swing.Timer(1000, stopper);
            t.start();
        }
        else mario.draw(x,y);
        
        
    }
    
    public void setFalse() {
        left = false;
        right = false;
        t.restart();
        t.stop();
//        mario.draw(x,y);
    }
    
    class clientThread extends Thread {
		MyConnection con;
		clientThread(MyConnection con) {
			this.con = con;
		}
		
		public void run() {
			try {
				while(!(msg=con.getMessage()).equals("/quit")) {
                                    System.out.println(msg);
				}
				if(msg.equals("/quit")) {
                                    System.exit(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
