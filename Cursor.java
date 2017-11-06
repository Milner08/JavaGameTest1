import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * @author Tomas Milner
 *
 */
public class Cursor {

	public float x, y, r, rot = 0.1f;
	public boolean b = false;
	public Ellipse2D prim ;
	
	/**
	 * Constructor for the Cursor class, Pass in the direction for it to start in and the radius of it
	 * @param x
	 * @param y
	 * @param r
	 */
	public Cursor(float x, float y, float r)
	{
		this.x = x; this.y = y; this.r = r;
		
		prim = new Ellipse2D.Double(x-r, y-r, r*2, r*2);
	}
	
	/**
	 * Get the location of the cursor 
	 * @return the array, [0] is x, [1] is y, [2] is r.
	 */
	public float[] getLoc(){
		float[] location = new float[3];
		location[0] = x;
		location[1] = y;
		location[2] = r;
		return location;
	}
	
	/**
	 * Set the location of the cursor
	 * @param x
	 * @param y
	 */
	public void setLoc(float x, float y)
	{
		this.x = x; this.y = y;
		
		prim = new Ellipse2D.Double(x-r, y-r, r*2, r*2);
	}
	
	/**
	 * Nothing is going on here....
	 * @param timePassed
	 */
	public synchronized void update(long timePassed)
	{
		
	}
	
	/**
	 * Draw! 
	 * @param g
	 */
	public void draw(Graphics2D g)
	{
		g.setColor(Color.blue);
		g.fill(prim);
		g.setColor(Color.black);
		g.draw(prim);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

	}
}
