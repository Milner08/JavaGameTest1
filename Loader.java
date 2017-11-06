import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Loader {
	/**
	 * The Default Constructor of the Loader class.
	 */
	public Loader() {}	

	/**
	 * Gets the image at the given file path.
	 * @param dir The path to the desired file.
	 * @return Returns the image at the given file path.
	 */
	public BufferedImage loadImage(String dir) {
		try {
			Image i = (Image)new ImageIcon(this.getClass().getResource(dir)).getImage();
			int w = i.getWidth(null); int h = i.getHeight(null);

			BufferedImage ii = new BufferedImage((int)w, (int) h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = ii.createGraphics();
			g.drawImage(i,0,0,w,h,null);

			return ii;
		} catch(Exception ex) {}
		return null;
	}			
} //End Loader