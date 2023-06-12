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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class to represent the levels screen of our game.
 */
public class LevelsScreen {
    /** Frame to draw on. */
    private JFrame frame = new JFrame("Levels");
    /** Drawing of the levels screen */
    private Drawing drawing = new Drawing();
    /** Mouselistener for mouse actions in the levels screen */
    private MouseHandler mouseListener = new MouseHandler();
    /** Colour labels to change when hovering over a label */
    private Color label1 = Color.black, label2 = Color.black, label3 = Color.black;
    /** Image of the home button */
    private Image homeBtn = null;
    private String whiteBtnPath = "/Assets/homeButtonW.png";
    private String blackBtnPath = "/Assets/homeButton.png";

    /**
     * {@link LevelsScreen} Constructor
     */
    public LevelsScreen(){
        //Loading the home button
        try {
            homeBtn = ImageIO.read(LevelsScreen.class.getResource(blackBtnPath));
        } catch (IOException d){
            d.printStackTrace();
        }
        //Creating and drawing the levels screen
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }

    /**
     * Mousehandler class to handle mouse actions.
     */
    class MouseHandler extends MouseAdapter {
        /**
         * Events when the mouse is clicked
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=310){ //click on levels
                new Lesson();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=360 && y<=510){ //click on play
                new Maze();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=560 && y<=710){ //click on exit
                new FinalLevel();
                frame.dispose();
            }
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){ //Click on home button
                new MainMenu();
                frame.dispose();
            }
        }

        /**
         * Events when the mouse is moved
         * @param e the event to be processed
         */
        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=310){ //hover on levels
                label1 = Color.lightGray;
            }
            else if(x>=300 && x<=900 && y>=360 && y<=510){ //hover on play
                label2 = Color.lightGray;
            }
            else if(x>=300 && x<=900 && y>=560 && y<=710){ //hover on exit
                label3 = Color.lightGray;
            }
            else {
                label1 = Color.black;
                label2 = Color.black;
                label3 = Color.black;
            }
            //Hovering over home button
            try {
                if(x >= 10 && x <= 70 && y >= 20 && y <= 80){
                    homeBtn = ImageIO.read(LevelsScreen.class.getResource(whiteBtnPath));
                }
                else{
                    homeBtn = ImageIO.read(LevelsScreen.class.getResource(blackBtnPath));
                }
            } catch (IOException d){
                d.printStackTrace();
            }
            drawing.repaint();
        }
    }

    /**
     * Drawing class of the levels screen.
     */
    class Drawing extends JComponent {
        /**
         * Pain method to create our levels screen.
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g){
            //Loading our font
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            }
            catch(Exception e){}
            //Drawing the background
            g.setColor(new Color(224, 102, 102));
            g.fillRect(0,0, getWidth(), getHeight());
            //Drawing the labels
            g.setColor(new Color(255, 229, 153));
            g.fillRoundRect(getWidth()/2 - 300, 160, 600, 150, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 360, 600, 150, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 560, 600, 150, 40, 40);
            g.setColor(Color.white);
            g.drawString("Levels", getWidth()/2 - (g.getFontMetrics().stringWidth("Levels")/2),110);
            g.setColor(label1);
            g.drawString("Learn", getWidth()/2 - (g.getFontMetrics().stringWidth("Learn")/2), 270);
            g.setColor(label2);
            g.drawString("Maze", getWidth()/2 - (g.getFontMetrics().stringWidth("Maze")/2), 470);
            g.setColor(label3);
            g.drawString("Game", getWidth()/2 - (g.getFontMetrics().stringWidth("Game")/2), 670);
            //Drawing the home button
            g.drawImage(homeBtn, 10, 20, 60, 60, null);
        }
    }
}
