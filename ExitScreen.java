/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Pouya Karimi
 * @version 09-06-2023
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExitScreen {
    /** Frame to draw on. */
    JFrame frame = new JFrame("Exit Screen");
    /** Drawing of the levels screen */
    Drawing drawing = new Drawing();
    /** Starting time for the counter to automatically close the screen.*/
    long start = 0;
    /**
     * {@link ExitScreen} Constructor
     */
    public ExitScreen(){
        start = System.currentTimeMillis();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawing);
        frame.setVisible(true);
    }

    /**
     * Drawing class to draw the exit screen.
     */
    class Drawing extends  JComponent{
        /**
         * Painting the exit screen onto the canvas.
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g){
            //Loading the font
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            }
            catch(Exception e){}
            //Drawing the background
            g.setColor(new Color(224, 102, 102));
            g.fillRect(0,0,getWidth(), getHeight());
            //Drawing the thank you message
            g.setColor(new Color(255, 229, 153));
            g.drawString("Thanks For", getWidth()/2 - g.getFontMetrics().stringWidth("Thanks For")/2, 150);
            g.drawString("Playing", getWidth()/2 - g.getFontMetrics().stringWidth("Playing")/2, 250);
            //Resizing font
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 50f));
            }
            catch(Exception e){}
            g.setColor(Color.white);
            //Credits
            g.drawString("Game Made By:", getWidth()/2, 400);
            g.drawString("Pouya Karimi &", getWidth()/2, 470);
            g.drawString("Sailesh V. Badri", getWidth()/2, 540);
            //Drawing our company logo and house graphic
            try{
                Image logo = ImageIO.read(new File("Assets/logo.png"));
                g.drawImage(logo, 800, 530, 450, 300, null);
                Image house = ImageIO.read(new File("Assets/exitHome.png"));
                g.drawImage(house, 100, 300, null);
            }
            catch (IOException e){}
            //Checking if the screen has been open for at least 3 second, if so the program ends automatically
            if(System.currentTimeMillis() - start >= 3000){
                frame.dispose();
            }
            else { //Exit screen should continue being displayed
                repaint();
            }
        }
    }
}
