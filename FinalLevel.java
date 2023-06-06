import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FinalLevel {
    JFrame frame = new JFrame("Final Level");
    Drawing drawing = new Drawing();
    int screen = 7;
    int radius = 0;
    Image homeBtn = null;
    MouseHandler mouseHandler = new MouseHandler();
    File whiteBtn = new File("Assets/homeButtonW.png"), blackBtn = new File("Assets/homeButton.png");
    boolean dayTime = true, justTurnedDay = false;
    private Player player;
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    final long MAX_DAYTIME = 7500;
    Color color1 = Color.white, color2 = Color.white;
    long secondStartTime;

    private int woodCount = 0;
    private int brickCount = 0;
    private int metalCount = 0;
    private int cementCount = 0;

    long startTime;
    public FinalLevel(){
        try {
            homeBtn = ImageIO.read(blackBtn);
        } catch (IOException d){}
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(mouseHandler);
        frame.addMouseMotionListener(mouseHandler);
        frame.addKeyListener(new KeyHandler());
        frame.add(drawing);
        frame.setVisible(true);

        player = new Player(600, 450);

        //start of program
        startTime = System.currentTimeMillis();
        materialGeneration();
    }
    class KeyHandler extends KeyAdapter{
        public void keyTyped(KeyEvent e){
            if((e.getKeyChar()+"").toLowerCase().equals("p")){
                if(screen != 1) {
                    for (int i = 0; i < materials.size(); i++) {
                        if (player.pick(materials.get(i))) {
                            materials.remove(i);
                            screen = 3;
                            break;
                        }
                        else if(player.withinRange(materials.get(i)) && player.isFull(materials.get(i).getWeight())){
                            screen = 4;
                            break;
                        }
                    }
                }
                drawing.repaint();
            }

            if((e.getKeyChar()+"").toLowerCase().equals("b")){
                if(screen != 1){
                    screen = 7;
                }
            }

            //test code, real code would use this when the person moves into their house
            if((e.getKeyChar()+"").toLowerCase().equals("x")){
                if(!dayTime){
                    justTurnedDay = true;
                    dayTime = true;
                    startTime = System.currentTimeMillis();
                }
            }

            drawing.repaint();
            System.out.println(e.getKeyChar());
        }

        public void keyPressed(KeyEvent e) {
            if (screen != 6) {
                String keyCode = (e.getKeyChar()+"").toLowerCase();
                int playerX = player.getX();
                int playerY = player.getY();

                switch (keyCode) {
                    case "w":
                        if (playerY > 107) {
                            player.moveUp();
                        }
                        break;
                    case "s":
                        if (playerY < 750) {
                            player.moveDown();
                        }
                        break;
                    case "a":
                        if (playerX > 50) {
                            player.moveLeft();
                        }
                        break;

                    case "d":
                        if (playerX < 1150) {
                            player.moveRight();
                        }
                        break;
                }
                drawing.repaint();
                System.out.println(keyCode);
            }
        }
    }

    class MouseHandler extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            System.out.println(e.getX() + ", " + e.getY());
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80 && screen != 6){
                new MainMenu();
                frame.dispose();
            }
            if(screen == 6){
                if(e.getX() >= 300 && e.getX() <= 900 && e.getY() >= 350 && e.getY() <= 450){ //Click on restart level
                    System.out.println("here");
                    new FinalLevel();
                    frame.dispose();
                }
                else if(e.getX() >= 300 && e.getX() <= 900 && e.getY() >= 470 && e.getY() <= 570){ //Click on restart level
                    new MainMenu();
                    frame.dispose();
                }
            }
            if(screen == 7){
                if (e.getX() >= 150 && e.getX() <= 210) {
                    if(e.getY() >= 200 && e.getY() <= 240){
                        woodCount++;
                    }
                    else if(e.getY() >= 460 && e.getY() <= 500) {
                        woodCount--;
                    }
                    System.out.println("Wood count: " + woodCount);
                } else if (e.getX() >= 430 && e.getX() <= 490) {
                    if(e.getY() >= 200 && e.getY() <= 240){
                        brickCount++;
                    }
                    else if(e.getY() >= 460 && e.getY() <= 500) {
                        brickCount--;
                    }
                    System.out.println("Brick count: " + brickCount);
                } else if (e.getX() >= 710 && e.getX() <= 770) {
                    if(e.getY() >= 200 && e.getY() <= 240){
                        metalCount++;
                    }
                    else if(e.getY() >= 460 && e.getY() <= 500) {
                        metalCount--;
                    }
                    System.out.println("Metal count: " + metalCount);
                } else if (e.getX() >= 990 && e.getX() <= 1050) {
                    if(e.getY() >= 200 && e.getY() <= 240){
                        cementCount++;
                    }
                    else if(e.getY() >= 460 && e.getY() <= 500) {
                        cementCount--;
                    }
                    System.out.println("Cement count: " + cementCount);
                }
                drawing.repaint(); // Redraw the arrows after the counts have been updated
            }
        }
        public void mouseMoved(MouseEvent e){
            try {
                if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){
                    homeBtn = ImageIO.read(whiteBtn);
                }
                else{
                    homeBtn = ImageIO.read(blackBtn);
                }
            } catch (IOException d){}
            if(screen == 6){
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

    class Drawing extends JComponent{
        public void paint(Graphics g){
            //static drawings

            //background
            g.setColor(new Color(105,168,79));
            g.fillRect(0,0,getWidth(), getHeight());
            //boxes stats
            g.setColor(new Color(241, 194, 51));
            g.fillRect(1040, 110, 150, 200);
            g.fillRect(1040, 320, 150, 175);

            g.setFont(new Font("Arial", Font.BOLD, 16)); // Set font to Arial bold with size 24
            g.setColor(new Color(0, 0, 0));
            g.drawString("Backpack", 1085, 354);

            // Bag Stats
            g.drawString("Wood:", 1053, 385);
            g.drawString(getCntItem("wood")+"", 1111, 385);
            g.drawString("Brick:", 1053, 405);
            g.drawString(getCntItem("brick")+"", 1105, 405);
            g.drawString("Metal:", 1053, 425);
            g.drawString(getCntItem("metal")+"", 1103, 425);
            g.drawString("Concrete:", 1053, 445);
            g.drawString(getCntItem("concrete")+"", 1136, 445);
            g.drawString("Weight:", 1053, 475);
            g.drawString(player.getBagWeight()+"", 1115, 475);

            //materials
            for(Material m : materials){
                try {
                    m.draw(g, 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //player
            int playerX = player.getX();
            int playerY = player.getY();
            g.setColor(Color.BLUE);
            g.fillRect(playerX, playerY, 20, 20);

            //timing logic
            if(screen != 6 && screen != 7){
                //timing logic
                long curTime = System.currentTimeMillis();

                if(justTurnedDay){
                    screen = 2;
                    materialGeneration();
                    justTurnedDay = false;
                }

                if(dayTime){
                    if(curTime - startTime >= MAX_DAYTIME){
                        startTime = System.currentTimeMillis();
                        dayTime = false;
                    }
                    repaint();
                }
                else{
                    screen = 1;
                    repaint();
                }

                //System.out.println(curTime-startTime);
            }

            if(screen == 0){
                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                g.drawString("Welcome to the final level! The goal is to have shelter heading into", (getWidth() - g.getFontMetrics().stringWidth("Welcome to the final level! The goal is to have shelter heading into")) / 2, 25);
                g.drawString("ever night. Look around for materials and pick them up to build your", (getWidth() - g.getFontMetrics().stringWidth("ever night. Look around for materials and pick them up to build your")) / 2, 55);
                g.drawString("house. Quick! Before night time comes!", (getWidth() - g.getFontMetrics().stringWidth("house. Quick! Before night time comes!")) / 2, 85);

            }
            else if(screen == 1){ //nighttime
                g.setColor(new Color(0,0,0, 100));
                g.fillRect(0,0,getWidth(), getHeight());

                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                g.drawString("We're heading into the night, time to return to your shelter that you", (getWidth() - g.getFontMetrics().stringWidth("We're heading into the night, time to return to your shelter that you")) / 2, 25);
                g.drawString("(hopefully) built! Let's see if your habitat can survive the storm", (getWidth() - g.getFontMetrics().stringWidth("(hopefully) built! Let's see if your habitat can survive the storm")) / 2, 55);
                g.drawString("that has been brewing. Good Luck!", (getWidth() - g.getFontMetrics().stringWidth("that has been brewing. Good Luck!")) / 2, 85);
            }
            else if(screen == 2){ //wakeup
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
            else if(screen == 3){ //pickup
                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }
                g.setColor(new Color(0,0,0));
                g.drawString("You have successfully picked up a piece of " + player.getBag().get(player.getBag().size()-1).getType() + ". Return", (getWidth() - g.getFontMetrics().stringWidth("You have successfully picked up a piece of " + player.getBag().get(player.getBag().size()-1).getType() + ". Return")) / 2, 30);
                g.drawString("to your shelter area to build something with it.", (getWidth() - g.getFontMetrics().stringWidth("to your shelter area to build something with it.")) / 2, 60);
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
                    g.setColor(Color.black);
                    g.fillRect(0,0,getWidth(), getHeight());
                    try {
                        Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                        g.setFont(font.deriveFont(Font.BOLD, 40f));
                    } catch (Exception e) {
                    }
                    g.setColor(Color.white);
                    g.drawString("Your shelter was not durable enough", (getWidth() - g.getFontMetrics().stringWidth("Your shelter was not durable enough")) / 2, 150);
                    g.drawString("to survive the night. This means", (getWidth() - g.getFontMetrics().stringWidth("to survive the night. This means")) / 2, 200);
                    g.drawString("that you have lost the game. Restart", (getWidth() - g.getFontMetrics().stringWidth("that you have lost the game. Restart")) / 2, 250);
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

                //cement
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
                g.drawString("Cement", 1020-(g.getFontMetrics().stringWidth("Cement")/ 2), 335);

                g.drawString(woodCount+"", 180-(g.getFontMetrics().stringWidth(woodCount+"")/ 2), 410);
                g.drawString(brickCount+"", 460-(g.getFontMetrics().stringWidth(brickCount+"")/ 2), 410);
                g.drawString(metalCount+"", 740-(g.getFontMetrics().stringWidth(metalCount+"")/ 2), 410);
                g.drawString(cementCount+"", 1020-(g.getFontMetrics().stringWidth(cementCount+"")/ 2), 410);


            }

            if(screen != 6) g.drawImage(homeBtn, 10,20,60,60,null);
        }
    }
    private String randomMaterial(){
        int x = (int)(Math.random()*10)+1;
        if(x<=4)return "wood";
        else if(x<=7) return "brick";
        else if(x<=9) return "metal";
        else return "concrete";
    }

    private void materialGeneration(){
        int x = (int)(Math.random()*4)+1;
        for(int i = 0; i < x; i++){
            materials.add(new Material((int)(Math.random()*1001),(int)(Math.random()*701)+100,randomMaterial()));
        }
    }

    private int getCntItem(String s){
        int x = 0;
        for(Material m : player.getBag()){
            if(m.getType().equals(s))x++;
        }
        return x;
    }

}
