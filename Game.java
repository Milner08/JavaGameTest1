import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Game extends JPanel implements MouseListener, MouseMotionListener {
 
	private static final long serialVersionUID = -4013192919629459467L;
	private int w, h; 
	ArrayList<Target> Targets = new ArrayList<Target>();
	Cursor pointer;
	Point mPos = new Point(0,0);
	public boolean running = true;

	public Game(int w, int h) {
		this.w = w; this.h = h;
		setLayout(null);
	  	setSize(w,h);
	  	setPreferredSize(new Dimension(w,h));
	  	setBounds(0, 0, w, h);
	  	addMouseListener(this);
		addMouseMotionListener(this);
    	setFocusable(true);
    	setBackground(Color.GRAY);
    	setForeground(Color.WHITE);
    	setDoubleBuffered(true);
  		init();
  		repaint();	
  	}
	
	public void init(){
		populate(10,10,30,50);
		pointer = new Cursor(w/2,h/2,10);
	}

	public void populate(int numG,int numB, int minR, int maxR){
		/* Spawn good guys */
		for (int i = 0; i < numG; i++)
		{
			boolean added = false;
			while (!added)
			{
				float r = (float)((Math.random() * (maxR - minR)) + minR);
				float x = (float)((Math.random() * (w - (r * 2))) + r);
				float y = (float)((Math.random() * (h - (r * 2))) + r);
				Target tmp = new FriendlyTarget(x,y,r);
				
				boolean collided = false;
				for (int u = 0; u < Targets.size(); u++)
				{
					if (tmp.intersects(Targets.get(u)))
					{
						collided = true;
						u = Targets.size();
					}
				}
				if (!collided) 
				{
					Targets.add(tmp);
					added = true;
				}
			}
		}
		/*Spawn bad guys */
		for (int i = 0; i < numB; i++)
		{
			boolean added = false;
			while (!added)
			{
				float r = (float)((Math.random() * (maxR - minR)) + minR);
				float x = (float)((Math.random() * (w - (r * 2))) + r);
				float y = (float)((Math.random() * (h - (r * 2))) + r);
				Target tmp = new EnemyTarget(x,y,r);
				
				boolean collided = false;
				for (int u = 0; u < Targets.size(); u++)
				{
					if (tmp.intersects(Targets.get(u)))
					{
						collided = true;
						u = Targets.size();
					}
				}
				if (!collided) 
				{
					Targets.add(tmp);
					added = true;
				}
			}
		}
	}
	
	private int hitCount = 0;
	
	public synchronized void update(long timePassed) {
		pointer.setLoc((float)mPos.getX(), (float)mPos.getY());
		boolean unhit = false;
		for(Target t: Targets){
			if(!t.hit && t.intersects(pointer.getLoc())){
				t.hit();
				if(t.bad){
					if(hitCount > 0){
						hitCount--;
					}
				}else{
					hitCount++;
				}
			}
			t.update(w,h);
			if(!t.hit && !t.bad){unhit = true;}
		}
		if(unhit == false){running = false;}
		//pointer.update(timePassed);
		repaint();
	}
	
	public void paint(Graphics G) {
		Graphics2D g = (Graphics2D)G;
		
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		
		g.setColor(getBackground());
		g.fillRect(0, 0, w, h);
		draw(g);
		
		g.drawString("Score: " + hitCount, 20, 20);
		
		//Clean up rendering objects.
		g.setColor(getForeground());
		g.dispose();
		G.dispose();
	}
	
	public synchronized void draw(Graphics2D g)
	{
		for (Target t : Targets)
		{
			t.draw(g);
		}
		pointer.draw(g);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mPos = new Point(e.getX(), e.getY());		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Ao-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
