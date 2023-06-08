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
    private int screen = 0;
    /** Plyaer object */
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
                materials.add(new Material(40,690, "brick"));
                materials.add(new Material(740,150,"metal"));
            }
            if((e.getKeyChar()+"").toLowerCase().equals("p")  && screen == 1){ //User attempts to pick something up
                for(int i = 0; i < materials.size(); i++){ //Checking if they are near a material
                    if(player.withinRange(materials.get(i), 50)){ //Picking up
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
            if(e.getKeyChar() == '\n' && screen == 2) { //User completes maze and moves onto final level
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
                        if (playerY < 750 && maze[(playerY-100+27) / cellSize][(playerX / cellSize)] != '#') {
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

        }

    }

    /**
     * Class to draw all of the graphics in this level.
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

                g.setColor(Color.BLUE);
                g.fillRect(playerX, playerY, playerSize, playerSize);
                try {
                    player.draw(g, direction, playerImg, 0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //Drawing the materials
                for(Material m : materials){
                    g.setColor(Color.RED);
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
            else if(screen == 2){ //Congratulations screen
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
