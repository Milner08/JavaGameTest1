import java.awt.geom.Ellipse2D;


public class FriendlyTarget extends Target {

	public FriendlyTarget(){}
	
	public FriendlyTarget(float x, float y, float r)
	{
		this.x = x; this.y = y; this.r = r;
		prim = new Ellipse2D.Double(x-r,y-r,r*2,r*2);
		rot = (float)(Math.random() * 360);
		target = new Sprite(new Loader().loadImage("content/GoodTarget.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
		hitTarget = new Sprite(new Loader().loadImage("content/GoodTargetHit.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
		hitTarget.setAlpha(0.0F);
		generateDirection();
	}

}
