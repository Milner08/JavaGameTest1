import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * @author Tomas Milner
 * Targets! move at a  random path, Rebound of walls, etc etc.
 */
public class Item {
	
	public float x, y, r;
	public double dx = 0, dy = 0;
	public Ellipse2D prim;
	public Color bg = Color.blue;
	public boolean hit = false;
	public boolean bad = false;
	public Sprite item;
	public float rot;
	
	public Item(){}
	
	/**
	 * Constructor for Target
	 * @param x the X location of the target
	 * @param y the Y location of the target
	 * @param r the radius of the target
	 */
	public Item(float x, float y, float r)
	{
		this.x = x; this.y = y; this.r = r;
		prim = new Ellipse2D.Double(x-r,y-r,r*2,r*2);
		rot = 0F;
		item = new Sprite(new Loader().loadImage("content/GoodTarget.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
	}
	
	public void generateDirection(){
		if(Math.random() < 0.5){
			dx += Math.random()*2;
		}else{
			dx -= Math.random()*2;
		}
		if(Math.random() < 0.5){
			dy += Math.random()*2;
		}else{
			dy -= Math.random()*2;
		}
	}
	
	/**
	 * @param location An array where [0] is the x location, [1] is y, [2] is radius
	 * @return Bool, true if intersects, otherwise false.
	 */
	public boolean intersects(float[] location)
	{
		if (Math.sqrt(Math.pow(location[0]-x,2) + Math.pow(location[1]-y,2)) < (r + location[2])) return true;
		return false;
	}
	
	/**
	 * @param obj A target with which to check
	 * @return a Bool which is true if it intersects false is not
	 */
	public boolean intersects(Target obj)
	{
		if (Math.sqrt(Math.pow(obj.x-x,2) + Math.pow(obj.y-y,2)) < (r + obj.r)) return true;
		return false;
	}
	
	/**
	 * Check if the target has collided with something, Passing in the x and y location to check with. 
	 * @param x the x location to check
	 * @param y the y location to check
	 */
	public void collision(int x, int y){
		if(this.x <= 0+r || this.x >= (x - (r * 2)) + r) {
			dx *= -1;
		}
		if(this.y <= 0+r || this.y >= (y - (r * 2)) + r) {
			dy *= -1;
		}
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
		prim = new Ellipse2D.Double(x-r,y-r,r*2,r*2);
		item.move((float)dx, (float)dy);
		item.rotCW(0.3F);
	}
	
	/**
	 * Draw, Set the colour of the target, fill it etc etc. 
	 * @param g
	 */
	public void draw(Graphics2D g)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		item.draw(g);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
}
