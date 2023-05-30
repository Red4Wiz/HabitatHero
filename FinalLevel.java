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
    int screen = 0;
    Image homeBtn = null;
    MouseHandler mouseHandler = new MouseHandler();
    File whiteBtn = new File("Assets/homeButtonW.png"), blackBtn = new File("Assets/homeButton.png");
    boolean dayTime = true, justTurnedDay = false;
    private Player player;
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    final long MAX_DAYTIME = 7500;

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
                            drawing.repaint();
                        }
                        break;
                    case "s":
                        if (playerY < 750) {
                            player.moveDown();
                            drawing.repaint();
                        }
                        break;
                    case "a":
                        if (playerX > 50) {
                            player.moveLeft();
                            drawing.repaint();
                        }
                        break;

                    case "d":
                        if (playerX < 1150) {
                            player.moveRight();
                            drawing.repaint();
                        }
                        break;
                }
                
                System.out.println(keyCode);
            }
        }
    }

    class MouseHandler extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            System.out.println(e.getX() + ", " + e.getY());
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){
                new MainMenu();
                frame.dispose();
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

            // Add code
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
                g.setColor(new Color(0,0,0));
                g.drawString("Nice! You have used the materials in your backpack to", (getWidth() - g.getFontMetrics().stringWidth("Nice! You have used the materials in your backpack to")) / 2, 30);
                g.drawString("upgrade your shelter. The durability of your home just went up!", (getWidth() - g.getFontMetrics().stringWidth("upgrade your shelter. The durability of your home just went up!")) / 2, 60);
            }


            g.drawImage(homeBtn, 10,20,60,60,null);
        }
    }
    public String randomMaterial(){
        int x = (int)(Math.random()*10)+1;
        if(x<=4)return "wood";
        else if(x<=7) return "brick";
        else if(x<=9) return "metal";
        else return "concrete";
    }

    public void materialGeneration(){
        int x = (int)(Math.random()*4)+1;
        for(int i = 0; i < x; i++){
            materials.add(new Material((int)(Math.random()*1001),(int)(Math.random()*701)+100,randomMaterial()));
        }
    }

    public int getCntItem(String s){
        int x = 0;
        for(Material m : player.getBag()){
            if(m.getType().equals(s))x++;
        }
        return x;
    }
}
