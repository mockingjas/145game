package SimpleGame;
 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class SimpleGame extends BasicGame{
    
    Image plane = null;
    Image land = null;
    float x = 400;
    float y = 300;
    float scale = 1;
 
    public SimpleGame()
    {
        super("Slick2D Path2Glory - SlickBasicGame");
    }
 
    @Override
    public void init(GameContainer gc)
			throws SlickException {
        plane = new Image("data/plane.jpg");
        land = new Image("data/land.png");
    }
 
    @Override
    public void update(GameContainer gc, int delta)
			throws SlickException
    {
        Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_A))
        {
            plane.rotate(-0.2f * delta); //left
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
            plane.rotate(0.2f * delta); //right
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
            float hip = 0.4f * delta; //how quick it moves toward the direction
 
            float rotation = plane.getRotation(); //kung gano ka-slant/orientation
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
 
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_1))
        {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
    }
 
    public void render(GameContainer gc, Graphics g)
			throws SlickException
    {
        //x, y
        land.draw(0, 0);
        //x, y, multiplier
        plane.draw(x, y, scale);
    }
 
    public static void main(String[] args)
			throws SlickException
    {
         AppGameContainer app =
			new AppGameContainer( new SimpleGame() );
         // x, y, full screen
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}