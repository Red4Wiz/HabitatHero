/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Sailesh Badri & Pouya Karimi
 * @version 09-06-2023
 * Individual parts of the program done by each person are inline commented to show who did what.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class to represent the third and final level of our game.
 */
public class FinalLevel {
    /** Frame which the final level is drawn on. */
    JFrame frame = new JFrame("Final Level");
    /** Drawing of our final level. */
    Drawing drawing = new Drawing();
    /** The current screen being displayed. */
    private int screen = 0;
    /** Radius of the circle that gets bigger in the death screen. */
    private int radius = 0;
    /** Image of the home button. */
    private Image homeBtn = null;
    /** Booleans to know whether the player has built a house or is currently inside the house. */
    private boolean builtAHouse = false, insideHouse = false;
    /** Mousehandler to handle all mouse actions in this level. */
    private MouseHandler mouseHandler = new MouseHandler();
    /** File directories of both white and black home buttons. */
    private String whiteBtnPath = "/Assets/homeButtonW.png";
    private String blackBtnPath = "/Assets/homeButton.png";
    /** Booleans to know whether it is currently daytime or whether it just turned day. */
    private boolean dayTime = true, justTurnedDay = false;
    /** Player in the game */
    private Player player;
    /** All the materials in the game. */
    ArrayList<Material> materials = new ArrayList<>();
    /** The amount of time it is day in the game */
    private final long MAX_DAYTIME = 30;
    /** Time being displayed on the timer */
    private int displayTime = (int) MAX_DAYTIME; //in seconds
    /** Temporary variable to note the start of every second */
    private long timeTicker;
    /** Changing colours for hovering over labels. */
    private Color color1 = Color.white, color2 = Color.white;
    /** Starting time of the transition to the death screen. */
    private long secondStartTime;
    /** Number of nights that have passed in the game */
    private int numOfNights = 0;
    /** The direction the player is moving in. */
    private String direction;
    /** Whether the player should be drawn. */
    private boolean playerImage = false;
    /** The house in the game */
    Building house = new Building(500,300);
    /** Amount of wood being selected to build with */
    private int woodCount = 0;
    /** Amount of brick being selected to build with */
    private int brickCount = 0;
    /** Amount of metal being selected to build with */
    private int metalCount = 0;
    /** Amount of concrete being selected to build with */
    private int concreteCount = 0;
    /** The last screen that was being displayed */
    private int lastScreen = 0;
    /** Dimness of the transition from day to night */
    private int dimness = 0;
    /** Starting time of the program */
    private long startTime;
    /** Boolean to know whether you should be asking for their name. */
    private boolean rightName = false;
    private boolean moveUp = false, moveDown = false, moveLeft = false, moveRight = false;

    /**
     * {@link FinalLevel} Constructor
     */
    public FinalLevel(){
        timeTicker = System.currentTimeMillis();
        try {
            homeBtn = ImageIO.read(LevelsScreen.class.getResource(blackBtnPath));
        } catch (IOException d){
            d.printStackTrace();
        }
        //Creating and drawing the screens
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(mouseHandler);
        frame.addMouseMotionListener(mouseHandler);
        frame.addKeyListener(new KeyHandler());
        frame.add(drawing);
        frame.setVisible(true);

        player = new Player(600, 450, 1);

        //start of program
        startTime = System.currentTimeMillis();
        materialGeneration();
        direction = "front";
    }

    /**
     * Class to handle all key events in the program.
     */
    class KeyHandler extends KeyAdapter{
        /**
         * Method called whenever a key is typed
         * @param e the event to be processed
         * Coded by Sailesh Badri
         */
        public void keyTyped(KeyEvent e){
            if((e.getKeyChar()+"").toLowerCase().equals("p")){ //User attempts to pick something up
                if(screen != 1) { //Not nighttime
                    for (int i = 0; i < materials.size(); i++) {
                        if (player.pick(materials.get(i), 100)) { //Checking whether the player is near an object
                            materials.remove(i);
                            screen = 3;
                            break;
                        }
                        else if(player.withinRange(materials.get(i),100) && player.isFull(materials.get(i).getWeight())){ //Player is whithin range but their backpack is full
                            screen = 4;
                            break;
                        }
                    }
                }
                drawing.repaint();
            }

            if((e.getKeyChar()+"").toLowerCase().equals("b")){ //User wants to build
                if(screen != 1 && screen != 6 && player.hasMaterials()){ //Not night time and the user actually has material
                    lastScreen = screen;
                    screen = 7;
                    woodCount = 0;
                    brickCount = 0;
                    metalCount = 0;
                    concreteCount = 0;
                }
            }
            
            drawing.repaint();
        }

        /**
         * Method called whenever a key is pressed
         * @param e the event to be processed
         * Coded by Sailesh Badri.
         */
        public void keyPressed(KeyEvent e) {
            if (screen != 6) { //Not building screen
                String keyCode = (e.getKeyChar()+"").toLowerCase();
                int playerX = player.getX();
                int playerY = player.getY();

                //Player's movement
                switch (keyCode) {
                    case "w":
                        if (playerY > 107) {
                            moveUp = true;
                           // player.moveUp();
                            direction = "back";
                            playerImage = !playerImage;
                        }
                        else{
                            moveUp = false;
                        }
                        break;
                    case "s":
                        if (playerY < 750) {
                            moveDown = true;
                          //  player.moveDown();
                            direction = "front";
                            playerImage = !playerImage;
                        }
                        else{
                            moveDown = false;
                        }
                        break;
                    case "a":
                        if (playerX > 50) {
                            moveLeft = true;
                         //   player.moveLeft();
                            direction = "left";
                            playerImage = !playerImage;
                        }
                        else{
                            moveLeft = false;
                        }
                        break;

                    case "d":
                        if (playerX < 1150) {
                            moveRight = true;
                          //  player.moveRight();
                            direction = "right";
                            playerImage = !playerImage;
                        }
                        else{
                            moveRight = false;
                        }
                        break;
                }
                //Checking if the player is inside their house
                if(player.withinRange(house)) insideHouse = true;
                else insideHouse = false;
                drawing.repaint();
            }
        }
        public void keyReleased(KeyEvent e){
            String keyCode = (e.getKeyChar()+"").toLowerCase();
            switch (keyCode) {
                case "w" -> moveUp = false;
                case "s" -> moveDown = false;
                case "a" -> moveLeft = false;
                case "d" -> moveRight = false;
            }
            drawing.repaint();
        }
    }

    /**
     * Class to handle all mouse events in the final level.
     * Coded by Sailesh Badri
     */
    class MouseHandler extends MouseAdapter{
        /**
         * Method called whenever the mouse is clicked.
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e){
            //Clicking on the home button
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80 && screen != 6 && screen != 7){
                new MainMenu();
                frame.dispose();
            }
            if(screen == 6){
                if(e.getX() >= 300 && e.getX() <= 900 && e.getY() >= 350 && e.getY() <= 450){ //Click on restart level
                    new FinalLevel();
                    frame.dispose();
                }
                else if(e.getX() >= 300 && e.getX() <= 900 && e.getY() >= 470 && e.getY() <= 570){ //Click on restart level
                    new MainMenu();
                    frame.dispose();
                }
            }
            if(screen == 7){ //In the build screen
                //up and down arrows
                if (e.getX() >= 150 && e.getX() <= 210) {
                    if(e.getY() >= 230 && e.getY() <= 270 && woodCount < player.getWood()){
                        woodCount++;
                    }
                    else if(e.getY() >= 490 && e.getY() <= 530 && woodCount > 0) {
                        woodCount--;
                    }
                } else if (e.getX() >= 430 && e.getX() <= 490) {
                    if(e.getY() >= 230 && e.getY() <= 270 && brickCount < player.getBrick()){
                        brickCount++;
                    }
                    else if(e.getY() >= 490 && e.getY() <= 530 && brickCount > 0) {
                        brickCount--;
                    }
                } else if (e.getX() >= 710 && e.getX() <= 770) {
                    if(e.getY() >= 230 && e.getY() <= 270 && metalCount < player.getMetal()){
                        metalCount++;
                    }
                    else if(e.getY() >= 490 && e.getY() <= 530 && metalCount > 0) {
                        metalCount--;
                    }
                } else if (e.getX() >= 990 && e.getX() <= 1050) {
                    if(e.getY() >= 230 && e.getY() <= 270 && concreteCount < player.getConcrete()){
                        concreteCount++;
                    }
                    else if(e.getY() >= 490 && e.getY() <= 530 && concreteCount > 0) {
                        concreteCount--;
                    }
                }

                //build
                if(e.getX() >= 400 && e.getX() <= 800 && e.getY() >= 600 && e.getY() <= 700){
                    house.addBrick(brickCount);
                    house.addWood(woodCount);
                    house.addMetal(metalCount);
                    house.addConcrete(concreteCount);
                    player.removeMaterial("brick", brickCount);
                    player.removeMaterial("wood", woodCount);
                    player.removeMaterial("metal", metalCount);
                    player.removeMaterial("concrete", concreteCount);
                    builtAHouse = true;
                    screen = 5;
                    drawing.repaint();
                }

                if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){ //Click on the exit button in the build screen
                    //exit
                    screen = lastScreen;
                }

                drawing.repaint(); // Redraw the arrows after the counts have been updated
            }
        }

        /**
         * Method called whenever the mouse is moved.
         * @param e the event to be processed
         */
        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            //Changing the colour of the home button on hover
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
            if(screen == 6){ //Death screen
                //Changing the colour of the labels in the death screen on hover
                if(e.getX() >= 300 && e.getX() <= 900 && e.getY() >= 350 && e.getY() <= 450){ //Click on restart level
                    color1 = Color.black;
                }
                else if(e.getX() >= 300 && e.getX() <= 900 && e.getY() >= 470 && e.getY() <= 570){ //Click on restart level
                    color2 = Color.black;
                }
                else {
                    color1 = Color.white;
                    color2 = Color.white;
                }
            }
            drawing.repaint();
        }
    }

    /**
     * Class to represent the drawings of each screen in the final level.
     * Parts created by Pouya Karimi: Background and material graphics, nighttime damage taking mechanics, day and night timing logic, screens 0, 2, 6
     * Parts created by Sailesh Badri: Player and house graphics, picking up and building mechanics, screens 1, 3, 4, 5, 7
     */
    class Drawing extends JComponent{
        /**
         * Method to pain each screen of the code.
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g){
            if(moveUp) player.moveUp();
            if(moveDown) player.moveDown();
            if(moveLeft) player.moveLeft();
            if(moveRight) player.moveRight();
            //static drawings

            //background
            g.setColor(new Color(105,168,79));
            g.fillRect(0,0,getWidth(), getHeight());
            //roads
            g.setColor(new Color(50,50,50));
            g.fillRect(100,100,100,670);
            g.fillRect(900,100,100,300);
            g.fillRect(750,600,100,200);
            g.fillRect(315,200,100,250);

            g.fillRect(0,200,1200,100);
            g.fillRect(0,700,800,100);
            g.fillRect(850,650,400,100);
            //boxes stats
            g.setColor(new Color(241, 194, 51));
            g.fillRect(1040, 110, 150, 175);
            g.fillRect(1040, 300, 150, 175);

            g.setFont(new Font("Arial", Font.BOLD, 16)); // Set font to Arial bold with size 24
            g.setColor(new Color(0, 0, 0));
            g.drawString("Backpack", 1085, 334);
            g.drawString("House", 1085, 144);

            //House Stats
            g.drawString("Wood:", 1053, 175);
            g.drawString(house.getNumOfWood()+"", 1111, 175);
            g.drawString("Brick:", 1053, 195);
            g.drawString(house.getNumOfBrick()+"", 1105, 195);
            g.drawString("Metal:", 1053, 215);
            g.drawString(house.getNumOfMetal()+"", 1103, 215);
            g.drawString("Concrete:", 1053, 235);
            g.drawString(house.getNumOfConcrete()+"", 1136, 235);
            g.drawString("Durability:", 1053, 265);
            g.drawString(house.getDurability()+"", 1135, 265);

            // Bag Stats
            g.drawString("Wood:", 1053, 365);
            g.drawString(player.getWood()+"", 1111, 365);
            g.drawString("Brick:", 1053, 385);
            g.drawString(player.getBrick()+"", 1105, 385);
            g.drawString("Metal:", 1053, 405);
            g.drawString(player.getMetal()+"", 1103, 405);
            g.drawString("Concrete:", 1053, 425);
            g.drawString(player.getConcrete()+"", 1136, 425);
            g.drawString("Weight:", 1053, 455);
            g.drawString(player.getBagWeight()+"", 1115, 455);

            //Drawing the house if it has been built
            if(builtAHouse){
                try {
                    house.draw(g, 250, 250);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //materials
            for(Material m : materials){
                try {
                    m.draw(g, 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //Drawing the player
            try {
                player.draw(g, direction, playerImage, 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //timing logic
            if(screen != 6 && screen != 7){ //Not building or death screen
                long curTime = System.currentTimeMillis();

                if(justTurnedDay){
                    screen = 2;
                    materialGeneration();
                    justTurnedDay = false;
                    timeTicker = System.currentTimeMillis();
                    displayTime = (int) MAX_DAYTIME;
                }

                if(dayTime){ //When it is daytime, checking if it should be night yet
                    if(displayTime == 0){
                        startTime = System.currentTimeMillis();
                        dayTime = false;
                    }
                    repaint();
                }
                else{ //Nightime
                    if(builtAHouse) screen = 1;
                    else screen = 6;
                    repaint();
                }
            }

            if(screen == 0){//start screen
                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);
                //Loading font
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                //Starting message
                g.setColor(new Color(0,0,0));
                g.drawString("Welcome to the final level! The goal is to have shelter heading into", (getWidth() - g.getFontMetrics().stringWidth("Welcome to the final level! The goal is to have shelter heading into")) / 2, 25);
                g.drawString("ever night. Look around for materials and pick them up to build your", (getWidth() - g.getFontMetrics().stringWidth("ever night. Look around for materials and pick them up to build your")) / 2, 55);
                g.drawString("house. Quick! Before night time comes!", (getWidth() - g.getFontMetrics().stringWidth("house. Quick! Before night time comes!")) / 2, 85);

            }
            else if(screen == 1){ //nighttime
                if(insideHouse){
                    //Taking damage each night
                    int damage = 15 + 10*numOfNights;
                    house.removeDurability(damage);
                    //Losing materials from your house after each night
                    int concreteLost = damage/20;
                    if(concreteLost <= house.getNumOfConcrete()) {
                        damage -= 20*concreteLost;
                        house.removeConcrete(concreteLost);
                    }
                    int metalLost = damage/15;
                    if(metalLost <= house.getNumOfMetal()) {
                        damage -= 15*metalLost;
                        house.removeMetal(metalLost);
                    }
                    int brickLost = damage/10;
                    if(brickLost <= house.getNumOfBrick()) {
                        damage -= 10*brickLost;
                        house.removeBrick(brickLost);
                    }
                    int woodLost = damage/5;
                    if(woodLost <= house.getNumOfWood()){
                        house.removeWood(woodLost);
                    }
                    if(house.getDurability() < 0){
                        screen = 6;
                    }
                    else{
                        justTurnedDay = true;
                        dayTime = true;
                        startTime = System.currentTimeMillis();
                        dimness = 100;
                        screen = 2;
                    }
                    numOfNights++;
                }
                else{ //It is nighttime but the player has not yet returned to their house
                    g.setColor(new Color(0,0,0, 100));
                    g.fillRect(0,0,getWidth(), getHeight());

                    g.setColor(new Color(163, 235, 240));
                    g.fillRect(0, 0, 1200, 100);
                    //Loading font
                    try {
                        Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                        g.setFont(font.deriveFont(Font.BOLD, 20f));
                    } catch (Exception e) {
                    }
                    //Telling the player to go back to their house
                    g.setColor(new Color(0,0,0));
                    g.drawString("We're heading into the night, time to return to your shelter that you", (getWidth() - g.getFontMetrics().stringWidth("We're heading into the night, time to return to your shelter that you")) / 2, 25);
                    g.drawString("(hopefully) built! Let's see if your habitat can survive the storm", (getWidth() - g.getFontMetrics().stringWidth("(hopefully) built! Let's see if your habitat can survive the storm")) / 2, 55);
                    g.drawString("that has been brewing. Good Luck!", (getWidth() - g.getFontMetrics().stringWidth("that has been brewing. Good Luck!")) / 2, 85);
                }
            }
            else if(screen == 2){ //wakeup
                if(dimness < 255){ //Animation for waking up
                    g.setColor(new Color(0,0,0, dimness));
                    g.fillRect(0,0,getWidth(), getHeight());
                    dimness++;

                }
                //Instructing the user to continue building their house
                else{
                    g.setColor(new Color(163, 235, 240));
                    g.fillRect(0, 0, 1200, 100);

                    try {
                        Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                        g.setFont(font.deriveFont(Font.BOLD, 20f));
                    } catch (Exception e) {
                    }
                    g.setColor(new Color(0,0,0));
                    g.drawString("Wakey wakey! Congratulations, your shelter survived the night,", (getWidth() - g.getFontMetrics().stringWidth("Wakey wakey! Congratulations, your shelter survived the night,")) / 2, 30);
                    g.drawString("but next night’s damage will be even worse. Get building!", (getWidth() - g.getFontMetrics().stringWidth("but next night’s damage will be even worse. Get building!")) / 2, 60);
                }
            }
            else if(screen == 3){ //pickup
                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                if(builtAHouse){
                    g.drawString("You have successfully picked up a piece of " + player.getBag().get(player.getBag().size()-1).getType() + ". Return", (getWidth() - g.getFontMetrics().stringWidth("You have successfully picked up a piece of " + player.getBag().get(player.getBag().size()-1).getType() + ". Return")) / 2, 30);
                    g.drawString("to your shelter area to build something with it.", (getWidth() - g.getFontMetrics().stringWidth("to your shelter area to build something with it.")) / 2, 60);
                }
                else{
                    g.drawString("You have successfully picked up a piece of " + player.getBag().get(player.getBag().size()-1).getType() + ". Pick", (getWidth() - g.getFontMetrics().stringWidth("You have successfully picked up a piece of " + player.getBag().get(player.getBag().size()-1).getType() + ". Pick")) / 2, 30);
                    g.drawString("anywhere on the map to build your shelter!", (getWidth() - g.getFontMetrics().stringWidth("anywhere on the map to build your shelter!")) / 2, 60);
                }
            }
            else if(screen == 4){ //backpack full
                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                g.drawString("Oops! Looks like you don’t have any more space to carry", (getWidth() - g.getFontMetrics().stringWidth("Oops! Looks like you don’t have any more space to carry")) / 2, 30);
                g.drawString("materials. Use the ones that you have picked up first!", (getWidth() - g.getFontMetrics().stringWidth("materials. Use the ones that you have picked up first!")) / 2, 60);
            }
            else if(screen == 5){ //building
                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                g.setColor(Color.black);
                g.drawString("Nice! You have used the materials in your backpack to", (getWidth() - g.getFontMetrics().stringWidth("Nice! You have used the materials in your backpack to")) / 2, 30);
                g.drawString("upgrade your shelter. The durability of your home just went up!", (getWidth() - g.getFontMetrics().stringWidth("upgrade your shelter. The durability of your home just went up!")) / 2, 60);
            }
            else if(screen == 6){ //didn't survive
                g.setColor(new Color(0,0,0,100));
                g.fillRect(0,0,getWidth(), getHeight());
                g.setColor(new Color(0,0,0));
                g.fillOval(getWidth()/2 - radius/2, getHeight()/2 - radius/2, radius, radius);
                if(radius < 3400){
                    radius+=15;
                    repaint();
                    secondStartTime = System.currentTimeMillis();
                }
                else{
                    while(!rightName){
                        String name = JOptionPane.showInputDialog(frame, "Enter your username. It may not include a space.");
                        if(name == null){
                            break;
                        }
                        rightName = processUsername(name);
                        if(rightName){
                            try{
                                Leaderboard.readData();
                                FileWriter temp = new FileWriter("leaderboard.txt", true);
                                PrintWriter writer = new PrintWriter(temp);
                                writer.println(name + " " + numOfNights);
                                writer.close();
                            }
                            catch (IOException e){}
                            repaint();
                        }
                    }
                    if(rightName){
                        g.setColor(Color.black);
                        g.fillRect(0,0,getWidth(), getHeight());
                        try {
                            Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                            g.setFont(font.deriveFont(Font.BOLD, 40f));
                        } catch (Exception e) {
                        }
                        g.setColor(Color.white);
                        g.drawString("Your shelter was not durable enough", (getWidth() - g.getFontMetrics().stringWidth("Your shelter was not durable enough")) / 2, 150);
                        g.drawString("to survive the night. You survived", (getWidth() - g.getFontMetrics().stringWidth("to survive the night. This means")) / 2, 200);
                        g.drawString(numOfNights + " nights in the game. Restart", (getWidth() - g.getFontMetrics().stringWidth(numOfNights + " nights in the game. Restart")) / 2, 250);
                        g.drawString("or go to the menu to play again.", (getWidth() - g.getFontMetrics().stringWidth("or go to the menu to play again.")) / 2, 300);
                        g.setColor(new Color(60, 120, 220));
                        g.fillRect(300,350,getWidth()/2, 100);
                        g.fillRect(300,470,getWidth()/2, 100);
                        g.setColor(color1);
                        g.drawString("Restart Level", (getWidth() - g.getFontMetrics().stringWidth("Restart Level")) / 2, 415);
                        g.setColor(color2);
                        g.drawString("Main Menu", (getWidth() - g.getFontMetrics().stringWidth("Main Menu")) / 2, 535);
                    }
                }
            }
            else if(screen == 7){//building screen
                //background
                g.setColor(new Color(0,0,0, 240));
                g.fillRect(0,0,getWidth(), getHeight());

                //squares
                g.setColor(new Color(163, 235, 240));
                g.fillRoundRect(80, 250, 200, 200, 50, 50);
                g.fillRoundRect(360, 250, 200, 200, 50, 50);
                g.fillRoundRect(640, 250, 200, 200, 50, 50);
                g.fillRoundRect(920, 250, 200, 200, 50, 50);

                //arrows

                //wood
                int[] xTri = { 150, 210, 180 };
                int[] yTriUp = { 240, 240, 200 };
                int[] yTriDown = { 460, 460, 500 };
                g.fillPolygon(xTri, yTriUp, 3);
                g.fillPolygon(xTri, yTriDown, 3);

                //brick
                int[] xTri2 = { 430, 490, 460 };
                g.fillPolygon(xTri2, yTriUp, 3);
                g.fillPolygon(xTri2, yTriDown, 3);

                //metal
                int[] xTri3 = { 710, 770, 740 };
                g.fillPolygon(xTri3, yTriUp, 3);
                g.fillPolygon(xTri3, yTriDown, 3);

                //concrete
                int[] xTri4 = { 990, 1050, 1020 };
                g.fillPolygon(xTri4, yTriUp, 3);
                g.fillPolygon(xTri4, yTriDown, 3);

                //heading
                g.setFont(new Font("Arial", Font.BOLD, 80));
                g.setColor(Color.white);
                g.drawString("Building Screen", (getWidth() - g.getFontMetrics().stringWidth("Building Screen")) / 2, 100);

                //build button
                g.setColor(new Color(60, 120, 220));
                g.fillRoundRect(400,600,400, 100,100,50);
                g.setColor(Color.WHITE);
                g.drawString("Build", (getWidth() - g.getFontMetrics().stringWidth("Build")) / 2, 680);

                //item names
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.setColor(Color.BLACK);
                g.drawString("Wood", 180-(g.getFontMetrics().stringWidth("Wood")/ 2), 335);
                g.drawString("Brick", 460-(g.getFontMetrics().stringWidth("Brick")/ 2), 335);
                g.drawString("Metal", 740-(g.getFontMetrics().stringWidth("Metal")/ 2), 335);
                g.drawString("Concrete", 1020-(g.getFontMetrics().stringWidth("Concrete")/ 2), 335);

                g.drawString(woodCount+"", 180-(g.getFontMetrics().stringWidth(woodCount+"")/ 2), 410);
                g.drawString(brickCount+"", 460-(g.getFontMetrics().stringWidth(brickCount+"")/ 2), 410);
                g.drawString(metalCount+"", 740-(g.getFontMetrics().stringWidth(metalCount+"")/ 2), 410);
                g.drawString(concreteCount+"", 1020-(g.getFontMetrics().stringWidth(concreteCount+"")/ 2), 410);
                try{
                    Image redX = ImageIO.read(Material.class.getResource("/Assets/redX.png"));
                    g.drawImage(redX, 10, 20, 60, 60, null);
                }
                catch (IOException d){}
            }

            if(screen != 6 && screen != 7) { //If not in the death screen or the building screen, the counter should go down
                g.drawImage(homeBtn, 10,20,60,60,null);
                //drawing seconds
                g.setColor(new Color(241, 194, 51));
                g.fillRect(getWidth()-80, getHeight()-50, 80, 50);
                g.setFont(new Font("Arial", Font.BOLD, 26));
                int min = displayTime/60;
                int sec = displayTime - 60*min;
                String time = "";
                if(sec < 10){
                    time = min + ":0" + sec;
                }
                else{
                    time = min + ":" + sec;
                }
                g.setColor(Color.black);
                g.drawString(time, 1130,755);
                if(System.currentTimeMillis() - timeTicker >= 1000 && displayTime != 0){
                    displayTime--;
                    timeTicker = System.currentTimeMillis();
                    repaint();
                }
                if(displayTime == 0){
                    g.setColor(new Color(0,0,0, 100));
                    g.fillRect(getWidth()-80, getHeight()-50, 80, 50);
                }
            }
        }
    }

    /**
     * Choosing a random material to be drawn.
     * Coded by Sailesh Badri.
     * @return
     */
    private String randomMaterial(){
        int x = (int)(Math.random()*10)+1;
        if(x<=4)return "wood";
        else if(x<=7) return "brick";
        else if(x<=9) return "metal";
        else return "concrete";
    }

    /**
     * Generating a random material in the game.
     * Coded by Sailesh
     */
    private void materialGeneration(){
        int num = (int)(Math.random()*4)+2;
        for(int i = 0; i < num; i++){
            int x = (int)(Math.random()*950);
            int y = (int)(Math.random()*550)+100;

            while(x >= 450 && x <= 750 && y >= 250 && y <= 550){
                x = (int)(Math.random()*950);
                y = (int)(Math.random()*550)+100;
            }

            materials.add(new Material(x,y,randomMaterial()));
        }
    }
    private boolean processUsername(String name){
        boolean pass = true;
        for(int i = 0; i<name.length(); i++){
            if(name.charAt(i) == ' ') pass = false;
        }
        if(name.length() == 0) pass = false;
        if(!pass){
            JOptionPane.showMessageDialog(frame, "Invalid username. Try again.");
        }
        return pass;
    }
}
