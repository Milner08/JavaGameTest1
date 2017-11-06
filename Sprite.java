import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Sprite {
	
	private BufferedImage img; 
	private float x, y, w, h, ox, oy, a, alpha = 1; //x and y loc, width and hight, center point of image ox, oy, angle, and the rectangle that contains it
	private Rectangle r;

	public void setX(float x){this.x = x;}
	public float getX(){return this.x;}

	public void setY(float y){this.y = y;}
	public float getY(){return this.y;}

	public void setW(float w){this.w = w;}
	public float getW(){return this.w;}

	public void setH(float h){this.h = h;}
	public float getH(){return this.h;}

	public void setOX(float OX){this.ox = OX;}
	public float getOX(){return this.ox;}

	public void setOY(float OY){this.oy = OY;}
	public float getOY(){return this.oy;}

	public void setA(float a){this.a = a;}
	public float getA(){return this.a;}
	
	public void setAlpha(float alpha){
		this.alpha = alpha;
		if(this.alpha >= 1)this.alpha = 1;
		if(this.alpha <= 0)this.alpha = 0;
	}
	public float getAlpha(){return this.alpha;}
	
	/**
	 * Gets the image of the Sprite.
	 * @return Returns the image of the Sprite.
	 */
	public BufferedImage getImage() { return img; } 
	
	/**
	 * Sets the image of the Sprite.
	 * @param i The image to use for the Sprite.
	 */
	public void setImage(BufferedImage i) { img = i; }

	
	/**
	 * The Default Constructor for the Sprite class.
	 */
	public Sprite(){
	}
	
	/**
	 * A Constructor for the Sprite class.
	 * @param I The image to use for the Sprite.
	 * @param X The x coordinate of the Sprite.
	 * @param Y The y coordinate of the Sprite.
	 * @param W The width of the Sprite.
	 * @param H The height of the Sprite.
	 * @param A The angle of the Sprite.
	 */
	public Sprite(BufferedImage I, float X, float Y, float W, float H, float A) {
		x = X;
		y = Y;
		w = W;
		h = H;
		
		if (h == 0) {
			h = (float)(I.getHeight(null)/(I.getWidth(null)/W));
		}
		
		a = A;
		
		ox = w/2;
		oy = h/2;
		
		r = new Rectangle((int)(x-ox),(int)(y-oy),(int)(w),(int)(h));
		
		img = formatImage(I);
	}
	
	/**
	 * A Constructor for the Sprite class.
	 * @param I The image to use for the Sprite.
	 * @param X The x coordinate of the Sprite.
	 * @param Y The y coordinate of the Sprite.
	 * @param OX The x origin coordinate of the Sprite.
	 * @param OY The y origin coordinate of the Sprite.
	 * @param W The width of the Sprite.
	 * @param H The height of the Sprite.
	 * @param A The angle of the Sprite.
	 */
	public Sprite(BufferedImage I, float X, float Y, float OX, float OY, float W, float H, float A) {
		x = X;
		y = Y;
		w = W;
		h = H;
		
		if (h == 0) {
			h = (float)(I.getHeight(null)/(I.getWidth(null)/W));
		}
		
		a = A;
		
		ox = OX;
		oy = OY;
		
		r = new Rectangle((int)(x-ox),(int)(y-oy),(int)(w),(int)(h));
		
		img = formatImage(I);
	}
	
	/**
	 * Translates the Sprite by the given x and y values.
	 * @param xd The amount to translate the x coordinate by.
	 * @param yd The amount to translate the y coordinate by.
	 */
	public void move(float xd, float yd) {
		x += xd;
		y += yd;

		r = new Rectangle((int)(x-ox),(int)(y-oy),(int)(w),(int)(h));
	}

	/**
	 * Rotates the Sprite Clockwise by the given value.
	 * @param i The amount to rotate the Sprite by.
	 */
	public void rotCW(float i) {
		a += i;
		if (a >= 360) {
			a -= 360;
		}
	}
			
	/**
	 * Rotates the Sprite Counter-Clockwise by the given value.
	 * @param i The amount to rotate the Sprite by.
	 */
	public void rotCCW(float i) {
		a -= i;
		if (a <= 0) {
			a += 360;
		}
	}

	/**
	 * Formats an input image to be High Quality on the ARGB Colour Set.
	 * @param I The image to be formatted.
	 * @return Returns the input image formatted to the ARGB Colour Set.
	 */
	private BufferedImage formatImage(BufferedImage I) {
		BufferedImage II = new BufferedImage((int)w, (int) h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = II.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.drawImage(I, (int)0, (int)0, (int)w, (int)h, null);
		return II;
	}
	
	/**
	 * The draw function of the Sprite class.
	 * @param g The Graphics2D object to the window.
	 */
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		if (a != 0) {
			AffineTransform t = new AffineTransform();
			t.rotate(Math.toRadians(a),x,y);
			g.setTransform(t);
		}
		g.drawImage(img, (int)(x - ox), (int)(y - oy), (int)w, (int)h, null);
		g.setTransform(new AffineTransform());
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));                     
	}
} //End Sprite