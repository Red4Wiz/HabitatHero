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
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Class to represent all of our lessons in the game.
 */
public class Lesson {
    /** Frame which is being drawn on */
    private JFrame frame = new JFrame("Main Menu");
    /** Drawing of the main menu */
    private Drawing drawing = new Drawing();
    /** Mouse listener for mouse activities on this frame.*/
    private MouseHandler mouseListener = new MouseHandler();
    /** Variable to know which screen should be drawn */
    private int choice = 0;
    /** Regular home button */
    private Image homeBtn = null;
    /** White version of the home button used when the mouse is hovering over it */
    private String whiteBtnPath = "/Assets/homeButtonW.png";
    private String blackBtnPath = "/Assets/homeButton.png";
    public Lesson(){
        //Loading the home button
        try {
            homeBtn = ImageIO.read(LevelsScreen.class.getResource(blackBtnPath));
        } catch (IOException d){
            d.printStackTrace();
        }
        //Creating and drawing the lessons screens
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyHandler());
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }

    /**
     * Class to handle key events on our frame.
     */
    class KeyHandler extends KeyAdapter {
        /**
         * Events when a key is pressed.
         * @param e the event to be processed
         */
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == 39){ //Click on the right arrow
                if(choice != 8) choice++; //Not on the final levels screen
                else{ //If on the final screen, leave the lessons and start the maze
                    new Maze();
                    frame.dispose();
                }
            }
            if(e.getKeyCode() == 37){ //Clicking on the left arrow
                if(choice != 0) choice--; //If not on the first screen, move back one
            }
            drawing.repaint();
        }
    }

    /**
     * Class to handle all mouse events on the lesson screens.
     */
    class MouseHandler extends MouseAdapter{
        /**
         * Events when the mouse is clicked.
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e){
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80 && choice != 5){ //Click on the home button
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
            //Hovering over the home button
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
     * Class to draw the lessons screens.
     */
    class Drawing extends JComponent {
        /**
         * Painting all screens onto the canvas.
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g){
            if(choice != 0 && choice != 8){ //Drawing the background as long as it is not the first or last screen
                //Drawing the sky
                g.setColor(new Color(142, 207, 234));
                g.fillRect(0,0,getWidth(), getHeight());
                int circleX = 200, circleY = 200, circleRad = 100;
                g.setColor(Color.white);
                //A Cloud
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);
                circleX += 60;
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);

                circleX = 0; circleY=545;
                //A Cloud
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);
                circleX += 60;
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);

                circleX = -150; circleY=475;
                //A Cloud
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);
                circleX += 60;
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);

                circleX = 850; circleY=575;
                //A Cloud
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);
                circleX += 60;
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);
                circleX = 650; circleY=600;
                //A Cloud
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);
                circleX += 60;
                g.fillOval(circleX,circleY, circleRad, circleRad-20);
                g.fillOval(circleX-45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX,circleY-25, circleRad, circleRad-20);
                g.fillOval(circleX+45,circleY, circleRad, circleRad-20);
                g.fillOval(circleX+10,circleY+15, circleRad, circleRad-20);

                //The ground/hill
                g.setColor(new Color(165, 196, 71));
                g.fillOval(-850,570,1600,300);
                g.fillOval(700,570,1600,300);
                g.setColor(new Color(174, 207, 102));
                g.fillRect(0,630,getWidth(), getHeight()-630);
            }
            if(choice == 0){ //Welcome screen
                //Loading font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 90f));
                }
                catch(Exception e){}
                //Drawing background
                g.setColor(new Color(255, 229, 153));
                g.fillRect(0,0, getWidth(), getHeight());
                //Drawing labels
                g.setColor(new Color(224, 102, 102));
                g.drawString("Welcome", 350, 130);
                g.fillRoundRect(125,170, 950,2*getHeight()/3-60, 50,50);
                //Resizing font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                //Welcome Message
                g.setColor(Color.black);
                g.drawString("Hello! Welcome to the tutorials of Habitat", (getWidth() - g.getFontMetrics().stringWidth("Hello! Welcome to the tutorials of Habitat")) / 2, 225);
                g.drawString("Hero. In this section you will learn all the", (getWidth() - g.getFontMetrics().stringWidth("Hero. In this section you will learn all the")) / 2, 275);
                g.drawString("necessary controls and information for you", (getWidth() - g.getFontMetrics().stringWidth("necessary controls and information for you")) / 2, 325);
                g.drawString("to succeed in this game. Use the left", (getWidth() - g.getFontMetrics().stringWidth("to succeed in this game. Use the left")) / 2, 375);
                g.drawString("and right arrow keys to move between", (getWidth() - g.getFontMetrics().stringWidth("and right arrow keys to move between")) / 2, 425);
                g.drawString("instructions and once you are satisfied", (getWidth() - g.getFontMetrics().stringWidth("instructions and once you are satisfied")) / 2, 475);
                g.drawString("move on to the testing portion to test out", (getWidth() - g.getFontMetrics().stringWidth("move on to the testing portion to test out")) / 2, 525);
                g.drawString("your skills! Click the right arrow key to move!", (getWidth() - g.getFontMetrics().stringWidth("your skills! Click the right arrow key to move!")) / 2, 575);
            }
            else if(choice == 1){
                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.fillRoundRect(75, 150, 1050, 500, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                g.drawString("One of the largest issues in the world is", (getWidth() - g.getFontMetrics().stringWidth("One of the largest issues in the world is")) / 2, 220);
                g.drawString("homelessness and shelter insecurity. In African", (getWidth() - g.getFontMetrics().stringWidth("homelessness and shelter insecurity. In African")) / 2, 260);
                g.drawString("developing countries such as Ethiopia, there", (getWidth() - g.getFontMetrics().stringWidth("developing countries such as Ethiopia, there")) / 2, 300);
                g.drawString("are over 150,000 street children, a million in", (getWidth() - g.getFontMetrics().stringWidth("are over 150,000 street children, a million in")) / 2, 340);
                g.drawString("Egypt, and over 250,000 in Kenya (Ogutu, 2020).", (getWidth() - g.getFontMetrics().stringWidth("Egypt, and over 250,000 in Kenya (Ogutu, 2020).")) / 2, 380);
                g.drawString("More than 80 million African children lack", (getWidth() - g.getFontMetrics().stringWidth("More than 80 million African children lack")) / 2, 420);
                g.drawString("access to healthy shelter, and 16 million", (getWidth() - g.getFontMetrics().stringWidth("access to healthy shelter, and 16 million")) / 2, 460);
                g.drawString("of these children are living on the streets.", (getWidth() - g.getFontMetrics().stringWidth("of these children are living on the street.")) / 2, 500);
                g.drawString("Press the right arrow to move on", (getWidth() - g.getFontMetrics().stringWidth("Press the right arrow to move on")) / 2, 570);
            }
            else if(choice == 2){
                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.fillRoundRect(75, 100, 1050, 600, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }

                g.setColor(new Color(0,0,0));
                g.drawString("There are various actions you may take to improve", (getWidth() - g.getFontMetrics().stringWidth("There are various actions you may take to improve")) / 2, 170);
                g.drawString("your chances of surviving if you ever find yourself", (getWidth() - g.getFontMetrics().stringWidth("your chances of surviving if you ever find yourself")) / 2, 210);
                g.drawString("in a situation where your shelter is insecure.", (getWidth() - g.getFontMetrics().stringWidth("in a situation where your shelter is insecure.")) / 2, 250);
                g.drawString("First and foremost, it's critical to analyse your", (getWidth() - g.getFontMetrics().stringWidth("First and foremost, it's critical to analyse your")) / 2, 290);
                g.drawString("surroundings and search for secure temporary", (getWidth() - g.getFontMetrics().stringWidth("surroundings and search for secure temporary")) / 2, 330);
                g.drawString("shelter options. This might apply to public buildings", (getWidth() - g.getFontMetrics().stringWidth("shelter options. This might apply to public buildings")) / 2, 370);
                g.drawString("community centres, or even deserted buildings that", (getWidth() - g.getFontMetrics().stringWidth("community centres, or even deserted buildings that")) / 2, 410);
                g.drawString("provide some sort of shelter from the weather.", (getWidth() - g.getFontMetrics().stringWidth("provide some sort of shelter from the weather.")) / 2, 450);
                g.drawString("Additionally, get in touch with neighbourhood", (getWidth() - g.getFontMetrics().stringWidth("Additionally, get in touch with neighbourhood")) / 2, 490);
                g.drawString("nonprofits, shelters, or support groups that", (getWidth() - g.getFontMetrics().stringWidth("nonprofits, shelters, or support groups that")) / 2, 530);
                g.drawString("focus on helping people with housing issues.", (getWidth() - g.getFontMetrics().stringWidth("focus on helping people with housing issues.")) / 2, 570);
                g.drawString("Press the right arrow to move on", (getWidth() - g.getFontMetrics().stringWidth("Press the right arrow to move on")) / 2, 630);
            }
            else if(choice == 3){
                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.fillRoundRect(75, 100, 1050, 600, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                g.drawString("It's critical to put your safety and well-being first", (getWidth() - g.getFontMetrics().stringWidth("It's critical to put your safety and well-being first")) / 2, 170);
                g.drawString("by asking friends or family for support. In the", (getWidth() - g.getFontMetrics().stringWidth("by asking friends or family for support. In the")) / 2, 210);
                g.drawString("meantime, consider carrying a compact emergency", (getWidth() - g.getFontMetrics().stringWidth("meantime, consider carrying a compact emergency")) / 2, 250);
                g.drawString("kit with essentials like water or a blanket.", (getWidth() - g.getFontMetrics().stringWidth("kit with essentials like water or a blanket.")) / 2, 290);
                g.drawString("Adaptability, resourcefulness, and maintaining a", (getWidth() - g.getFontMetrics().stringWidth("Adaptability, resourcefulness, and maintaining a")) / 2, 330);
                g.drawString("positive mindset will be key during this challenging", (getWidth() - g.getFontMetrics().stringWidth("positive mindset will be key during this challenging")) / 2, 370);
                g.drawString("period as you work towards securing a stable", (getWidth() - g.getFontMetrics().stringWidth("period as you work towards securing a stable")) / 2, 410);
                g.drawString("living situation. Remember, you are not alone.", (getWidth() - g.getFontMetrics().stringWidth("living situation. Remember, you are not alone.")) / 2, 450);
                g.drawString("there are resources available to help you", (getWidth() - g.getFontMetrics().stringWidth("there are resources available to help you")) / 2, 490);
                g.drawString("navigate through shelter insecurity.", (getWidth() - g.getFontMetrics().stringWidth("navigate through shelter insecurity.")) / 2, 530);
                g.drawString("You will now learn the game controls.", (getWidth() - g.getFontMetrics().stringWidth("You will now learn the game controls.")) / 2, 570);
                g.drawString("Press the right arrow to move on", (getWidth() - g.getFontMetrics().stringWidth("Press the right arrow to move on")) / 2, 630);
            }
            else if(choice == 4){ //Movement lesson
                //Loading font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                //Title
                g.setColor(new Color(67, 67, 67));
                g.drawString("Movement", (getWidth() - g.getFontMetrics().stringWidth("Movement")) / 2, 120);

                //Background for lesson
                g.setColor(new Color(224, 102, 102));
                g.fillRoundRect(3*getWidth()/4 - 350, getHeight()/2 - 200, 600,380, 50, 50);
                g.setColor(Color.black);
                //Resizing font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                //Lesson
                g.drawString("Movement can be achieved", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("Movement can be achieved"))/2, 240);
                g.drawString("by clicking on the WASD keys", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("by clicking on the WASD keys"))/2, 290);
                g.drawString("to control your character", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("to control your character:"))/2, 340);
                g.drawString("W - Up", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("W - Up:"))/2, 390);
                g.drawString("S - Down", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("S - Down:"))/2, 440);
                g.drawString("A - Left", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("A - Left:"))/2, 490);
                g.drawString("D - Right", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("D - Right:"))/2, 540);
                //Resizing font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/Roboto-Bold.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 40f));
                }
                catch(Exception e){}

                //Drawing the WASD Keys

                //W Key
                g.setColor(new Color(68,90,100));
                g.fillRoundRect(200,320,100,100, 20,20);
                g.setColor(new Color(85,109,122));
                g.fillRoundRect(210,330,80,80, 10, 10);
                g.setColor(Color.white);
                g.drawString("W", 215,365);
                //S Key
                g.setColor(new Color(68,90,100));
                g.fillRoundRect(200,425,100,100, 20,20);
                g.setColor(new Color(85,109,122));
                g.fillRoundRect(210,435,80,80, 10, 10);
                g.setColor(Color.white);
                g.drawString("S", 215,470);
                //A Key
                g.setColor(new Color(68,90,100));
                g.fillRoundRect(95,425,100,100, 20,20);
                g.setColor(new Color(85,109,122));
                g.fillRoundRect(105,435,80,80, 10, 10);
                g.setColor(Color.white);
                g.drawString("A", 110,470);
                //D Key
                g.setColor(new Color(68,90,100));
                g.fillRoundRect(305,425,100,100, 20,20);
                g.setColor(new Color(85,109,122));
                g.fillRoundRect(315,435,80,80, 10, 10);
                g.setColor(Color.white);
                g.drawString("D", 320,470);
            }
            else if(choice == 5){ //Action lesson
                //Loading font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                //Drawing the background for the lesson
                g.setColor(new Color(67, 67, 67));
                g.drawString("Action", (getWidth() - g.getFontMetrics().stringWidth("Action")) / 2, 120);
                g.setColor(new Color(224, 102, 102));
                //Left Box
                g.fillRoundRect(getWidth()/4-75, getHeight()/2 - 200, 350,500, 50, 50);
                //Right Box
                g.fillRoundRect(3*getWidth()/4-75, getHeight()/2 - 200, 350,500, 50, 50);

                g.setColor(Color.black);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                //First Text
                g.drawString("Use the P key", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("Use the P key"))/2, 240);
                g.drawString("to pick materials", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("to pick materials"))/2, 290);
                g.drawString("up once you are", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("up once you are"))/2, 340);
                g.drawString("close to them.", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("close to them."))/2, 390);
                g.drawString("The materials", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("The materials"))/2, 440);
                g.drawString("are then stored", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("are then stored"))/2, 490);
                g.drawString("in your", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("in your"))/2, 540);
                g.drawString("backpack unless", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("backpack unless"))/2, 590);
                g.drawString("it is full.", getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("it is full."))/2, 640);

                //Second Text
                g.drawString("Once you have", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("Once you have"))/2, 240);
                g.drawString("some materials,", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("some materials,"))/2, 290);
                g.drawString("use the B key", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("use the B key"))/2, 340);
                g.drawString("to build. This", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("to build. This"))/2, 390);
                g.drawString("will give you", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("will give you"))/2, 440);
                g.drawString("certain building", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("certain building"))/2, 490);
                g.drawString("options based", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("options based"))/2, 540);
                g.drawString("on the materials", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("on the materials"))/2, 590);
                g.drawString("in your bag.", 3*getWidth()/4 + 100 - (g.getFontMetrics().stringWidth("in your bag."))/2, 640);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/Roboto-Bold.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 40f));
                }
                catch(Exception e){}
                //P Key
                g.setColor(new Color(68,90,100));
                g.fillRoundRect(60,340,100,100, 20,20);
                g.setColor(new Color(85,109,122));
                g.fillRoundRect(70,350,80,80, 10, 10);
                g.setColor(Color.white);
                g.drawString("P", 75,385);
                //B Key
                g.setColor(new Color(68,90,100));
                g.fillRoundRect(670,340,100,100, 20,20);
                g.setColor(new Color(85,109,122));
                g.fillRoundRect(680,350,80,80, 10, 10);
                g.setColor(Color.white);
                g.drawString("B", 685,385);
            }
            else if(choice == 6){ //Backpack lesson
                //Loading font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                //Title
                g.setColor(new Color(67, 67, 67));
                g.drawString("Backpack", (getWidth() - g.getFontMetrics().stringWidth("Backpack")) / 2, 120);

                //Background for the lesson
                g.setColor(new Color(224, 102, 102));
                g.fillRoundRect(3*getWidth()/4 - 350, getHeight()/2 - 150, 600,330, 50, 50);
                g.setColor(Color.black);

                //Resizing font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}

                //Lesson
                g.drawString("The bag can only hold a set", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("The bag can only hold a set"))/2, 290);
                g.drawString("number of materials.", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("number of materials."))/2, 340);
                g.drawString("Your bag has a max weight", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("Your bag has a max weight"))/2, 390);
                g.drawString("of 25, with wood being", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("of 25, with wood being"))/2, 440);
                g.drawString("the lightest material and", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("the lightest material and"))/2, 490);
                g.drawString("cement the heaviest.", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("cement the heaviest."))/2, 540);

                //Drawing the backpack image
                try{
                    Image backpack = ImageIO.read(Lesson.class.getResource("/Assets/backpack.png"));
                    g.drawImage(backpack, 60, 170, 450,450, null);
                }
                catch(IOException d){}
            }
            else if(choice == 7){ //Materials lesson
                //Loading font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                //Title
                g.setColor(new Color(67, 67, 67));
                g.drawString("Materials", (getWidth() - g.getFontMetrics().stringWidth("Materials")) / 2, 120);

                //Drawing background for lesson
                g.setColor(new Color(224, 102, 102));
                g.fillRoundRect(100, 170, getWidth()-200,330, 50, 50);
                g.setColor(Color.black);
                //Resizing font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                //Lesson
                g.drawString("There are 4 materials, each with different", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("There are 4 materials, each with different"))/2, 220);
                g.drawString("strength which will boost your house.", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("strength which will boost your house."))/2, 270);
                g.drawString("The better the strength of the material,", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("The better the strength of the material,"))/2, 320);
                g.drawString("the less you can carry in your backpack.", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("the less you can carry in your backpack."))/2, 370);
                g.drawString("The order from least strength to most is:", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("The order from least strength to most is:"))/2, 420);
                g.drawString("Wood (5) - Brick (10) - Metal (15) - Concrete (20)", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("Wood (5) - Brick (10) - Metal (15) - Concrete (20)"))/2, 470);
                //Drawing images of the materials
                try{
                    Image brick = ImageIO.read(Lesson.class.getResource("/Assets/brick.png"));
                    Image cement = ImageIO.read(Lesson.class.getResource("/Assets/cement.png"));
                    Image metal = ImageIO.read(Lesson.class.getResource("/Assets/metal.png"));
                    Image wood = ImageIO.read(Lesson.class.getResource("/Assets/wood.png"));
                    g.drawImage(wood, 150, 520, 200, 200, null);
                    g.drawImage(brick, 370, 520, 200,200,null);
                    g.drawImage(metal, 590, 520, 200,200,null);
                    g.drawImage(cement, 810, 520, 200,200, null);
                }
                catch(IOException d){}
            }
            else if(choice == 8){ //Final screen - moving onto the maze
                //Loading font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 90f));
                }
                catch(Exception e){}
                //Drawing background and title
                g.setColor(new Color(255, 229, 153));
                g.fillRect(0,0, getWidth(), getHeight());
                g.setColor(new Color(224, 102, 102));
                g.drawString("Congrats!", (getWidth() - g.getFontMetrics().stringWidth("Congrats!")) / 2, 130);
                g.fillRoundRect(125,170, 950,2*getHeight()/3-80, 50,50);
                //Resizing font
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                //Congratulations message
                g.setColor(Color.black);
                g.drawString("You have successfully completed the learning", (getWidth() - g.getFontMetrics().stringWidth("You have successfully completed the learning")) / 2, 225);
                g.drawString("portion of the game. Now, it is time to", (getWidth() - g.getFontMetrics().stringWidth("portion of the game. Now, it is time to")) / 2, 275);
                g.drawString("put your skills to the test! By clicking", (getWidth() - g.getFontMetrics().stringWidth("put your skills to the test! By clicking")) / 2, 325);
                g.drawString("the right arrow key, you will be moved", (getWidth() - g.getFontMetrics().stringWidth("the right arrow key, you will be moved")) / 2, 375);
                g.drawString("to the next and second screen, a maze!", (getWidth() - g.getFontMetrics().stringWidth("to the next and second screen, a maze!")) / 2, 425);
                g.drawString("If you are still unsure about parts", (getWidth() - g.getFontMetrics().stringWidth("If you are still unsure about parts")) / 2, 475);
                g.drawString("of the game, click the left arrow", (getWidth() - g.getFontMetrics().stringWidth("of the game, click the left arrow")) / 2, 525);
                g.drawString("keys to move backwards.", (getWidth() - g.getFontMetrics().stringWidth("keys to move backwards.")) / 2, 575);
            }
            if(choice != 8){ //Drawing the home button if you are not on the final screen
                g.drawImage(homeBtn, 10,20,60,60,null);
            }
        }
    }
}

