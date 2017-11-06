import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author Thomas Milner
 *
 */
public class GameLoader extends JFrame {
	
	private static final long serialVersionUID = -3199067413349590852L;
	private boolean running;
	private int w, h;
	private Game game;
	private int wait = 10;
	private int state = 1;
	
	public static void main(String[] args) {
		new GameLoader();
	}
	
	public GameLoader(){
		init();
		gameLoop();
	}
	
	private void gameLoop() {
		long startTime = System.currentTimeMillis();
		long totalTime = startTime;
		while (running) {
			if(state == 1){
				Object[] options = {"Play","Quit","EDO!"};
				int n = JOptionPane.showOptionDialog(this, "What do you want to do?", "Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(n == 0){state=2;}
				else{
					System.exit(0);
				}
			}else if(state == 2){
				long currentTime = System.currentTimeMillis();
				long timePassed = currentTime - totalTime;
				totalTime += timePassed;
				game.update(timePassed);
				if(game.running == false){
					System.exit(0);
				}
				try {
					int sleep = wait - (int)(System.currentTimeMillis() - currentTime);
					if (sleep > 0)
					{
						Thread.sleep(sleep);					
					}				
				} catch (Exception ex) {}
			}
		}
	}

	private void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //How the game should close
		setLocationRelativeTo(null); //Not sure? Maybe you can set it to locate next to things etc
		setResizable(false); //Stop it from being resized 
		setFocusable(false); //Stop it from ...? :S
		setLayout(null); // Dont use any layout files etc
		setBackground(Color.GRAY); //Set Background colour
		setForeground(Color.WHITE); //Set foreground colour...
		setFont(new Font("Arial",Font.PLAIN,24)); //set the font to use
		setSize(600,600); //set the size of the window
		setTitle("2D Game - By Tom Milner"); //set the title
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2) - 300, (Toolkit.getDefaultToolkit().getScreenSize().height/2) - 300); //set where it should be placed initially 
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor")); //Set what the cursor should be
		setVisible(true); //Make it visible
		running  = true; //Its running...

		w = getWidth(); //Set w to its width
		h = getHeight(); //set h to its height
		game = new Game(w,h-23); //Create a new game object, pass it the width and height
		add(game); //Add the games JPannel to the JFrame
	}
	
	public void stop(){
		running = false;
	}

}
