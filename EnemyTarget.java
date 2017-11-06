import java.awt.geom.Ellipse2D;


public class EnemyTarget extends Target {

	public EnemyTarget(){}
	
	public EnemyTarget(float x, float y, float r)
	{
		this.x = x; this.y = y; this.r = r;
		prim = new Ellipse2D.Double(x-r,y-r,r*2,r*2);
		rot = (float)(Math.random() * 360);
		bad = true;
		target = new Sprite(new Loader().loadImage("content/BadTarget.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
		hitTarget = new Sprite(new Loader().loadImage("content/BadTargetHit.png"),x,y,r,r,(r * 2) + 1,(r * 2) + 1,rot);
		hitTarget.setAlpha(0.0F);
		generateDirection();
	}

}
