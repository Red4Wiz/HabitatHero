import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Pouya Karimi
 * @version 09-06-2023
 */
public class Leaderboard {
    /** Frame which is being drawn on */
    private JFrame frame = new JFrame("Main Menu");
    /** Drawing of the main menu */
    private Drawing drawing = new Drawing();
    /** Mouse listener for mouse activities on this frame.*/
    private MouseHandler mouseListener = new MouseHandler();
    /** Arraylists to store names and scores. */
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<Integer> scores = new ArrayList<Integer>();
    /** Image of the home button */
    private Image homeBtn = null;
    private String whiteBtnPath = "/Assets/homeButtonW.png";
    private String blackBtnPath = "/Assets/homeButton.png";

    /**
     * {@link Leaderboard} Constructor
     */
    public Leaderboard(){
        //Loading the home button
        try {
            homeBtn = ImageIO.read(LevelsScreen.class.getResource(blackBtnPath));
        } catch (IOException d){
            d.printStackTrace();
        }
        //reading all the info from the file
        readData();
        sortData(); //sorting data
        //Creating the frame and adding our drawing.
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }

    /**
     * Reading all the information from the .txt file.
     */
    public static void readData(){
        try {
            Scanner reader = new Scanner(Leaderboard.class.getResource("leaderboard.txt").openStream());
            while(reader.hasNext()){
                String name = reader.next(); //username
                int score = reader.nextInt(); //score
                names.add(name);
                scores.add(score);
            }
        }
        catch(IOException e){}
    }

    /**
     * Bubble sort method to sort all of the data.
     */
    public void sortData(){
        boolean swapped;
        for(int i = 0; i<names.size() - 1; i++){
            swapped = false; //boolean to check if a swap has occurred
            for(int j = 0; j < names.size() - 1 - i; j++){ //traversing the arraylist
                if(scores.get(j) < scores.get(j+1)){ //swap is needed
                    //moving both the names and the scores
                    int temp = scores.get(j);
                    scores.set(j, scores.get(j+1));
                    scores.set(j + 1, temp);
                    String tempS = names.get(j);
                    names.set(j, names.get(j+1));
                    names.set(j + 1, tempS);
                    swapped = true;
                }
            }
            if(!swapped) break; //no swap has occurred - arraylist is sorted
        }
    }

    /**
     * Class to handle all mouse events in the leaderboard screen.
     */
    class MouseHandler extends MouseAdapter{
        /**
         * Events when the mouse is clicked
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e){
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
     * Class to draw the graphics in the leaderboard class.
     */
    class Drawing extends JComponent{
        /**
         * Paining the leaderboard graphics.
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g){
            //Background
            g.setColor(new Color(227, 100, 100));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(new Color(255, 229, 153));
            g.fillRoundRect(75, 150, 1050, 500, 50, 50);

            //Loading font
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            } catch (Exception e) {}
            //Title
            g.setColor(Color.black);
            g.drawString("Leaderboard", (getWidth() - g.getFontMetrics().stringWidth("Leaderboard")) / 2,110);

            //Resizing font
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 40));
            } catch (Exception e) {}

            //Subheadings
            g.setColor(Color.gray);
            g.drawString("Player Name", 300 - (g.getFontMetrics().stringWidth("Player Name") / 2),220);
            g.drawString("Nights Survived", 844 - (g.getFontMetrics().stringWidth("Nights Survived") / 2),220);
            g.setColor(Color.black);

            int y = 290;
            //Writing the top 5 people on the leaderboard
            if(names.size() <= 5){
                for(int i = 0; i<names.size(); i++){
                    String name = names.get(i);
                    int nights = scores.get(i);
                    g.drawString(name, 300 - (g.getFontMetrics().stringWidth(name) / 2),y);
                    g.drawString(""+nights, 844 - (g.getFontMetrics().stringWidth(""+nights) / 2),y);
                    y += 70;
                }
            }
            else{
                y = 290;
                for(int i = 0; i<5; i++){
                    String name = names.get(i);
                    int nights = scores.get(i);
                    g.drawString(name, 300 - (g.getFontMetrics().stringWidth(name) / 2),y);
                    g.drawString(""+nights, 844 - (g.getFontMetrics().stringWidth(""+nights) / 2),y);
                    y += 70;
                }
            }
            g.drawImage(homeBtn, 10,20,60,60,null); //home button
        }
    }
}
