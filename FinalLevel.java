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
    boolean dayTime = true, justTurnedDay = true;
    private Player player;
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();

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


    }
    class KeyHandler extends KeyAdapter{
        public void keyTyped(KeyEvent e){
            if((e.getKeyChar()+"").toLowerCase().equals("p")  && screen == 0){
                for(int i = 0; i < materials.size(); i++){
                    if(player.pick(materials.get(i))){
                        materials.remove(i);
                        break;
                    }
                }
                drawing.repaint();
            }

            if((e.getKeyChar()+"").toLowerCase().equals("b") && screen == 1){

            }
            System.out.println(e.getKeyChar());
        }

        public void keyPressed(KeyEvent e) {
            if (screen == 0) {
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
                        if (playerY < 750 ) {
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
            if(justTurnedDay){
                startTime = System.currentTimeMillis();
                justTurnedDay = false;
                materialGeneration();
            }
            g.setColor(new Color(105,168,79));
            g.fillRect(0,0,getWidth(), getHeight());
            g.setColor(new Color(241, 194, 51));
            g.fillRect(1040, 110, 150, 200);
            g.fillRect(1040, 320, 150, 100);

            //player
            int playerX = player.getX();
            int playerY = player.getY();

            g.setColor(Color.BLUE);
            g.fillRect(playerX, playerY, 20, 20);

            long curTime = System.currentTimeMillis();
            if(dayTime){
                if(curTime - startTime >= 120000){
                    startTime = System.currentTimeMillis();
                    dayTime = !dayTime;
                    justTurnedDay = !justTurnedDay;
                }
                repaint();
            }
            else{
                screen = 1;
                repaint();
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

                for(Material m : materials){
                    g.setColor(Color.RED);
                    try {
                        m.draw(g, 1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else if(screen == 1){
                g.setColor(new Color(0,0,0, 100));
                g.fillRect(0,0,getWidth(), getHeight());
            }
            g.drawImage(homeBtn, 10,20,60,60,null);


        }
    }

    public String randomMaterial(){
        int x = (int)(Math.random()*10)+1;
        if(x<=4)return "wood";
        else if(x<=7) return "brick";
        else if(x<=9) return "metal";
        else return "cement";
    }

    public void materialGeneration(){
        int x = (int)(Math.random()*5)+1;
        for(int i = 0; i < x; i++){
            materials.add(new Material((int)(Math.random()*1001),(int)(Math.random()*701)+100,randomMaterial()));
        }
    }
}