import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Lesson {
    JFrame frame = new JFrame("Lessons");
    Drawing drawing = new Drawing();
    int choice = 0;
    MouseHandler mouseListener = new MouseHandler();
    Image homeBtn = null;
    File whiteBtn = new File("Assets/homeButtonW.png"), blackBtn = new File("Assets/homeButton.png");;
    public Lesson(){
        try {
            homeBtn = ImageIO.read(blackBtn);
        } catch (IOException d){}
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyHandler());
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }
    class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == 39){
                if(choice != 5) choice++;
                else{
                    new Maze();
                    frame.dispose();
                }
            }
            if(e.getKeyCode() == 37){
                if(choice != 0) choice--;
            }
            drawing.repaint();
        }
    }
    class MouseHandler extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80 && choice != 5){
                new MainMenu();
                frame.dispose();
            }
        }
        public void mouseMoved(MouseEvent e){
            try {
                if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80 && choice != 5){
                    System.out.println("here");
                    homeBtn = ImageIO.read(whiteBtn);
                }
                else{
                    homeBtn = ImageIO.read(blackBtn);
                }
            } catch (IOException d){}
            drawing.repaint();
        }
    }
    class Drawing extends JComponent {
        public void paint(Graphics g){
            if(choice != 0 && choice != 5){
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

                g.setColor(new Color(165, 196, 71));
                g.fillOval(-850,570,1600,300);
                g.fillOval(700,570,1600,300);
                g.setColor(new Color(174, 207, 102));
                g.fillRect(0,630,getWidth(), getHeight()-630);
            }
            if(choice == 0){
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 90f));
                }
                catch(Exception e){}
                g.setColor(new Color(255, 229, 153));
                g.fillRect(0,0, getWidth(), getHeight());
                g.setColor(new Color(224, 102, 102));
                g.drawString("Welcome", 350, 130);
                g.fillRoundRect(125,170, 950,2*getHeight()/3-60, 50,50);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
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
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                g.setColor(new Color(67, 67, 67));
                g.drawString("Movement", (getWidth() - g.getFontMetrics().stringWidth("Movement")) / 2, 120);

                g.setColor(new Color(224, 102, 102));
                g.fillRoundRect(3*getWidth()/4 - 350, getHeight()/2 - 200, 600,380, 50, 50);
                g.setColor(Color.black);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                g.drawString("Movement can be achieved", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("Movement can be achieved"))/2, 240);
                g.drawString("by clicking on the WASD keys", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("by clicking on the WASD keys"))/2, 290);
                g.drawString("to control your character", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("to control your character:"))/2, 340);
                g.drawString("W - Up", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("W - Up:"))/2, 390);
                g.drawString("S - Down", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("S - Down:"))/2, 440);
                g.drawString("A - Left", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("A - Left:"))/2, 490);
                g.drawString("D - Right", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("D - Right:"))/2, 540);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/Roboto-Bold.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 40f));
                }
                catch(Exception e){}
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
            else if(choice == 2){
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

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
            else if(choice == 3){
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                g.setColor(new Color(67, 67, 67));
                g.drawString("Backpack", (getWidth() - g.getFontMetrics().stringWidth("Backpack")) / 2, 120);

                g.setColor(new Color(224, 102, 102));
                g.fillRoundRect(3*getWidth()/4 - 350, getHeight()/2 - 150, 600,330, 50, 50);
                g.setColor(Color.black);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                g.drawString("The bag can only hold a set", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("The bag can only hold a set"))/2, 290);
                g.drawString("a set number of materials.", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("a set number of materials."))/2, 340);
                g.drawString("You have to prioritize the", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("You have to prioritize the"))/2, 390);
                g.drawString("materials you want to keep,", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("materials you want to keep,"))/2, 440);
                g.drawString("or throw away. You will be", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("or throw away. You will be."))/2, 490);
                g.drawString("notified when your bag is full.", 3*getWidth()/4 - 50 - (g.getFontMetrics().stringWidth("notified when your bag is full.."))/2, 540);

            }
            else if(choice == 4){
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                }
                catch(Exception e){}

                g.setColor(new Color(67, 67, 67));
                g.drawString("Materials", (getWidth() - g.getFontMetrics().stringWidth("Materials")) / 2, 120);
                g.setColor(new Color(224, 102, 102));
                g.fillRoundRect(100, 170, getWidth()-200,330, 50, 50);
                g.setColor(Color.black);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                g.drawString("There are 4 materials, each with different", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("There are 4 materials, each with different"))/2, 220);
                g.drawString("strength which will boost your house.", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("strength which will boost your house."))/2, 270);
                g.drawString("The better the strength of the material,", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("The better the strength of the material,"))/2, 320);
                g.drawString("the less you can carry in your backpack.", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("the less you can carry in your backpack."))/2, 370);
                g.drawString("The order from least strength to most is:", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("The order from least strength to most is:"))/2, 420);
                g.drawString("Wood - Brick - Metal - Concrete", (100+(getWidth()-200)/2) - (g.getFontMetrics().stringWidth("Wood - Brick - Metal - Concrete"))/2, 470);
            }
            else if(choice == 5){
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 90f));
                }
                catch(Exception e){}
                g.setColor(new Color(255, 229, 153));
                g.fillRect(0,0, getWidth(), getHeight());
                g.setColor(new Color(224, 102, 102));
                g.drawString("Congrats!", (getWidth() - g.getFontMetrics().stringWidth("Congrats!")) / 2, 130);
                g.fillRoundRect(125,170, 950,2*getHeight()/3-80, 50,50);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
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
            if(choice != 5){
                g.drawImage(homeBtn, 10,20,60,60,null);
            }
        }
    }
}

