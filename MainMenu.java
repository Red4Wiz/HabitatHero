/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Pouya Karimi
 * @version 09-06-2023
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Class to represent the main menu of our game.
 */
public class MainMenu{
    /** Frame which is being drawn on */
    private JFrame frame = new JFrame("Main Menu");
    /** Drawing of the main menu */
    private Drawing drawing = new Drawing();
    /** Mouse listener for mouse activities on this frame.*/
    private MouseHandler mouseListener = new MouseHandler();
    /** Colour variables to change the colour of a label when the mouse is hovering over it. */
    private Color label1 = Color.white, label2 = Color.white, label3 = Color.white, label4 = Color.white;

    /**
     * {@link MainMenu} Constructor
     */
    public MainMenu(){
        //Creating the frame and adding our drawing.
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }

    /**
     * MouseHandler class to handle mouse actions.
     */
    class MouseHandler extends MouseAdapter {
        /**
         * Event when the mouse is pressed
         * @param e the event to be processed
         */
        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=260){ //click on levels
                new LevelsScreen();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=310 && y<=410){ //click on play
                new Lesson();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=460 && y<=560){ //click on exit
                new ExitScreen();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=610 && y<=710){
                new Leaderboard();
                frame.dispose();
            }
        }

        /**
         * Event when the mouse is moved
         * @param e the event to be processed
         */
        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=260){ //hover on levels
                label1 = Color.black;
            }
            else if(x>=300 && x<=900 && y>=310 && y<=410){ //hover on play
                label2 = Color.black;
            }
            else if(x>=300 && x<=900 && y>=460 && y<=560){ //hover on exit
                label3 = Color.black;
            }
            else if(x>=300 && x<=900 && y>=610 && y<=710){ //hover on exit
                label4 = Color.black;
            }
            else {
                label1 = Color.white;
                label2 = Color.white;
                label3 = Color.white;
                label4 = Color.white;
            }
            drawing.repaint();
        }
    }

    /**
     * Drawing class of the main menu.
     */
    class Drawing extends JComponent {
        /**
         * Paint method to draw our main menu
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g){
            //Loading the font
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            }
            catch(Exception e){}
            //Drawing background
            g.setColor(new Color(255, 229, 153));
            g.fillRect(0,0, getWidth(), getHeight());
            //Drawing Labels
            g.setColor(new Color(224, 102, 102));
            g.fillRoundRect(getWidth()/2 - 300, 160, 600, 100, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 310, 600, 100, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 460, 600, 100, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 610, 600, 100, 40, 40);
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            }
            catch(Exception e){}
            g.setColor(Color.black);
            g.drawString("Main Menu", 290,110);
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 60f));
            }
            catch(Exception e){}
            g.setColor(label1);
            g.drawString("Levels", (getWidth() - g.getFontMetrics().stringWidth("Levels")) / 2, 230);
            g.setColor(label2);
            g.drawString("Play", (getWidth() - g.getFontMetrics().stringWidth("Play")) / 2, 380);
            g.setColor(label3);
            g.drawString("Exit", (getWidth() - g.getFontMetrics().stringWidth("Exit")) / 2, 530);
            g.setColor(label4);
            g.drawString("Leaderboard", (getWidth() - g.getFontMetrics().stringWidth("Leaderboard")) / 2, 680);
        }
    }
}
