import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * @author Tomas Milner
 * Targets! move at a  random path, Rebound of walls, etc etc.
 * A type of item, that can be hit with the mouse
 */
public class Target extends Item{
	
	public Sprite target, hitTarget;
	
	public Target(){}
		
	/**
	 * Constructor for Target
	 * @param x the X location of the target
	 * @param y the Y location of the target
	 * @param r the radius of the target
	 */
	public Target(float x, float y, float r)
	{
		this.x = x; this.y = y; this.r = r;
		prim = new Ellipse2D.Double(x-r,y-r,r*2,r*2);
		rot = (float)(Math.random() * 360);
		target = new Sprite(new Loader().loadImage("content/GoodTarget.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
		hitTarget = new Sprite(new Loader().loadImage("content/GoodTargetHit.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
		hitTarget.setAlpha(0.0F);
		generateDirection();
	}

	/**
	 * Mark the target as hit
	 */
	public void hit(){
		this.hit = true;
		hitTarget.setAlpha(1);
	}
	
	/**
	 * Checks if the target has collided with the edge of the screen, move the target, then redraw it.
	 * @param w the width of the screen
	 * @param h the height of the screen
	 */
	public void update(int w,int h){
		collision(w,h);
		x += dx;
		y += dy;
		float yd = (float)dy, xd = (float)dx;
		prim = new Ellipse2D.Double(x-r,y-r,r*2,r*2);
		target.move(xd, yd);
		hitTarget.move(xd, yd);
		target.rotCW(0.3F);
		hitTarget.rotCW(0.3F);
	}
	
	/**
	 * Draw, Set the colour of the target, fill it etc etc. 
	 * @param g
	 */
	public void draw(Graphics2D g)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		target.draw(g);
		if(hit) {
			hitTarget.draw(g);
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
}
