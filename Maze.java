/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Sailesh Badri
 * @version 09-06-2023
 * Sailesh coded all the mechanics and graphics required for the maze level
 */
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    /** Drawing object used to draw graphics */
    Drawing draw = new Drawing();
    /** JFrame used for the maze level */
    JFrame frame = new JFrame("Habitat Hero");
    /** Screen number to check the screen to be displayed within maze */
    private int screen = 2;
    /** Player object */
    private Player player;
    /** ArrayList of all the materials available within the maze */
    private ArrayList<Material> materials = new ArrayList<>();
    /** Building object the user will build */
    private Building b;

    /** Array for the Maze Structure */
    private char[][] maze; // Maze structure
    /** Width of the Maze */
    private int mazeWidth;
    /** Height of the Maze */
    private int mazeHeight;
    /** The size ratio of each cell within the window */
    private int cellSize;
    /** MouseHandler object for mose actions */
    MouseHandler mouseListener = new MouseHandler();
    /** Image for the home button on top right */
    Image homeBtn = null;
    /** Hovering version of the home button */
    File whiteBtn = new File("Assets/homeButtonW.png"), blackBtn = new File("Assets/homeButton.png");
    private String direction = "front";
    private boolean playerImg = false;

    /**
     * {@link Maze} Constructor
     */
    public Maze() {
        /** Creating the Maze Level */
        try {
            homeBtn = ImageIO.read(blackBtn);
        } catch (IOException d){}
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1215, 839);
        frame.add(draw);
        draw.addMouseListener(mouseListener);
        draw.addMouseMotionListener(mouseListener);
        frame.addKeyListener(new KeyHandler());
        frame.setResizable(false);
        frame.setVisible(true);

        /** Setting up the Maze for */
        mazeWidth = 24; // Adjust the width as needed
        mazeHeight = 14; // Adjust the height as needed
        //Maze (# represents a wall, ' ' represents open space)
        maze = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        cellSize = 50;
    }

    /**
     * Class to handle mouse activites on the canvas.
     */
    class MouseHandler extends MouseAdapter {
        /**
         * Method that handles whenever the mouse is clicked
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e) {
            if(screen == 1){
                if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){ //clicked on the home button
                    new MainMenu();
                    frame.dispose();
                }
            }
            int x = e.getX();
            int y = e.getY();
        }

        /**
         * Method that handles whenever the mouse is moved
         * @param e the event to be processed
         */
        public void mouseMoved(MouseEvent e){
            if(screen == 1){
                try {
                    if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){
                        homeBtn = ImageIO.read(whiteBtn); //hovering over home button
                    }
                    else{
                        homeBtn = ImageIO.read(blackBtn); //not hovering over home button
                    }
                } catch (IOException d){}
            }
            draw.repaint();
        }
    }

    /**
     * Class to handle key events in this level.
     */
    class KeyHandler extends KeyAdapter{
        /**
         * Method to handle when key is typed
         * @param e the event to be processed
         */
        public void keyTyped(KeyEvent e){
            if(e.getKeyChar() == '\n' && screen == 0) { //User moves from instructions screen to the maze
                screen = 1;
                draw.repaint();
                //Generating the materials and the player
                player = new Player(60, 350);
                materials.add(new Material(1060,390, "wood"));
                materials.add(new Material(40,690, "wood"));
                materials.add(new Material(740,150,"metal"));
            }
            if((e.getKeyChar()+"").toLowerCase().equals("p")  && screen == 1){ //User attempts to pick something up
                for(int i = 0; i < materials.size(); i++){ //Checking if they are near a material
                    if(player.pick(materials.get(i), 50)){ //Picking up
                        materials.remove(i);
                        break;
                    }

                }
                draw.repaint();
            }

            if((e.getKeyChar()+"").toLowerCase().equals("b") && screen == 1){ //User attempts to build
                //Checking that the user is in the build area
                if(player.getX() >= 450 && player.getX() <= 700 && player.getY() >= 400 && player.getY() <= 550 && materials.isEmpty()){
                    b = new Building(525,425);
                    maze[mazeHeight-2][mazeWidth-1] = ' ';
                    maze[mazeHeight-3][mazeWidth-1] = ' ';
                }
                draw.repaint();
            }
            if(e.getKeyChar() == '\n' && screen == 5) { //User completes maze and moves onto final level
                new FinalLevel();
                frame.dispose();
            }
        }

        /**
         * Method that handles whenever the key is pressed
         * @param e the event to be processed
         */
        public void keyPressed(KeyEvent e) {
            if (screen == 1) { //Inside the maze level
                String keyCode = (e.getKeyChar()+"").toLowerCase();
                int playerX = player.getX();
                int playerY = player.getY();
                //Checking for movement
                switch (keyCode) {
                    case "w":
                        if (playerY > 50 && maze[(playerY-107) / cellSize][(playerX / cellSize)] != '#') {
                            player.moveUp();
                            direction = "back";
                            playerImg = !playerImg;
                        }
                        break;
                    case "s":
                        if (playerY < 750 && maze[(playerY-100+40) / cellSize][(playerX / cellSize)] != '#') {
                            player.moveDown();
                            direction = "front";
                            playerImg = !playerImg;
                        }
                        break;
                    case "a":
                        if (playerX > 50 && maze[(playerY-100) / cellSize][(playerX-7) / cellSize] != '#') {
                            player.moveLeft();
                            direction = "left";
                            playerImg = !playerImg;
                        }
                        break;

                    case "d":
                        if (playerX < 1150 && maze[((playerY-100) / cellSize)][(playerX+27) / cellSize] != '#') {
                            player.moveRight();
                            direction = "right";
                            playerImg = !playerImg;
                        }
                        if(playerX >= 1150){
                            screen = 2;
                        }
                        break;
                }
                draw.repaint();
            }
            if(e.getKeyCode() == 39){ //Click on right arrow
                if(screen == 2 || screen == 3 || screen == 4){
                    screen++;
                }
                draw.repaint();
            }
            if(e.getKeyCode() == 37){ //Click on left arrow
                if(screen == 3 || screen == 4) screen--;
                draw.repaint();
            }
        }

    }

    /**
     * Class to draw all the graphics in this level.
     */
    class Drawing extends JComponent {
        /**
         * Method paint that draws out the graphics
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g) {
            if (screen == 0) { //Instructions screen
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                } catch (Exception e) {
                }
                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.drawString("Test", (getWidth() - g.getFontMetrics().stringWidth("Test")) / 2, 200);
                g.fillRoundRect(100, 300, 1000, 400, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }

                g.setColor(new Color(255, 229, 153));
                g.setColor(new Color(0,0,0));
                g.drawString("Hello! Now that you have learned all the", (getWidth() - g.getFontMetrics().stringWidth("Hello! Now that you have learned all the")) / 2, 370);
                g.drawString("controls, let's put them to the test. You will", (getWidth() - g.getFontMetrics().stringWidth("controls, let's put them to the test. You will")) / 2, 410);
                g.drawString("have to complete the maze’s challenges using", (getWidth() - g.getFontMetrics().stringWidth("have to complete the maze’s challenges using")) / 2, 450);
                g.drawString("your knowledge to move on to the game!", (getWidth() - g.getFontMetrics().stringWidth("your knowledge to move on to the game!")) / 2, 490);
                g.drawString("Press <enter> to move on", (getWidth() - g.getFontMetrics().stringWidth("Press <enter> to move on")) / 2, 620);
            }
            else if(screen == 1){ //Maze screen

                // Draw the maze
                for (int i = 0; i < mazeHeight; i++) {
                    for (int j = 0; j < mazeWidth; j++) {
                        int x = j * cellSize;
                        int y = (i * cellSize)+100;

                        if (maze[i][j] == '#') {
                            g.setColor(Color.BLACK);
                            g.fillRect(x, y, cellSize, cellSize);
                        } else {
                            g.setColor(Color.WHITE);
                            g.fillRect(x, y, cellSize, cellSize);
                        }
                    }
                }

                if(maze[mazeHeight-3][mazeWidth-1] == ' '){
                    g.setColor(Color.ORANGE);
                    g.fillRect(1150,650,50,100);

                }

                int playerSize = 20;
                int playerX = player.getX();
                int playerY = player.getY();

                try {
                    player.draw(g, direction, playerImg, 0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //Drawing the materials
                for(Material m : materials){

                    try {
                        m.draw(g, 0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                if(b!= null){
                    try {
                        b.draw(g, 150,150);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                //Maze instructions
                g.setColor(new Color(255, 229, 153));
                g.setColor(new Color(0,0,0));
                g.drawString("In order to leave the maze, you must first build a house in the middle square block.", (getWidth() - g.getFontMetrics().stringWidth("In order to leave the maze, you must first build a house in the middle square block.")) / 2, 25);
                g.drawString(" Find and pick up materials and return to the middle square to build.", (getWidth() - g.getFontMetrics().stringWidth("Find and pick up materials and return to the middle square to build.")) / 2, 55);
                g.drawString(" After you have built, the exit will open and you may leave!", (getWidth() - g.getFontMetrics().stringWidth("After you have built, the exit will open and you may leave!")) / 2, 85);

                //once the user builds
                if(maze[mazeHeight-3][mazeWidth-1] == ' '){
                    g.setColor(Color.ORANGE);
                    g.fillRect(1150,650,50,100);
                    g.setColor(Color.WHITE);
                    g.drawString("EXIT",1120,785);

                }
                g.drawImage(homeBtn, 10,20,60,60,null);
            }
            else if(screen == 2){ //first life lesson screen
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
            else if(screen == 3){ //second life lesson screen
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
            else if(screen == 4){ //third life lesson screen
                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.fillRoundRect(75, 100, 1050, 600, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }
                /*
                                It's critical to put your safety and wellbeing first by asking friends, family, or social services for
                                support. In the meantime, consider carrying a compact emergency kit with essentials like water,
                                non-perishable food, a sleeping bag or blanket, and basic hygiene supplies. Adaptability,
                                resourcefulness, and maintaining a positive mindset will be key during this challenging period as
                                you work towards securing a stable living situation. Remember, you are not alone, and there are
                                resources available to help you navigate through shelter insecurity.

                 */
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
                g.drawString("navigate through shelter insecurity.", (getWidth() - g.getFontMetrics().stringWidth("navigate through shelter insecurity.at")) / 2, 530);
                g.drawString("Press the right arrow to move on", (getWidth() - g.getFontMetrics().stringWidth("Press the right arrow to move on")) / 2, 630);
            }
            else if(screen == 5){ //Congratulations screen
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                } catch (Exception e) {
                }

                //Final message directing them to the final level
                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.drawString("Congratulations!", (getWidth() - g.getFontMetrics().stringWidth("Congratulations!")) / 2, 200);
                g.fillRoundRect(75, 300, 1050, 400, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }

                g.setColor(new Color(0,0,0));
                g.drawString("You have successfully completed the maze and", (getWidth() - g.getFontMetrics().stringWidth("You have successfully completed the maze and")) / 2, 370);
                g.drawString("proved your knowledge on the game! Now, it’s", (getWidth() - g.getFontMetrics().stringWidth("proved your knowledge on the game! Now, it’s")) / 2, 410);
                g.drawString("time for the real test. Time to see if you can", (getWidth() - g.getFontMetrics().stringWidth("time for the real test. Time to see if you can")) / 2, 450);
                g.drawString("survive every night, and become the Habitat Hero!", (getWidth() - g.getFontMetrics().stringWidth("survive every night, and become the Habitat Hero!")) / 2, 490);
                g.drawString("Press <enter> to move on", (getWidth() - g.getFontMetrics().stringWidth("Press <enter> to move on")) / 2, 620);
            }
        }
    }
}
