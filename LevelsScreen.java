import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelsScreen {
    JFrame frame = new JFrame("Levels");
    Drawing drawing = new Drawing();
    MouseHandler mouseListener = new MouseHandler();
    Color label1 = Color.black, label2 = Color.black, label3 = Color.black;
    Image homeBtn = null;
    File whiteBtn = new File("Assets/homeButtonW.png"), blackBtn = new File("Assets/homeButton.png");;
    public LevelsScreen(){
        try {
            homeBtn = ImageIO.read(blackBtn);
        } catch (IOException d){}
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }
    class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=310){ //click on levels
                new Lesson();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=360 && y<=510){ //click on play
                new Maze();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=560 && y<=710){ //click on exit
                new Lesson();
                frame.dispose();
            }
            if(e.getX() >= 10 && e.getX() <= 70 && e.getY()>=20 && e.getY() <= 80){
                new MainMenu();
                frame.dispose();
            }
            System.out.println(x+ ", " + y);
        }
        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=310){ //hover on levels
                label1 = Color.blue;
            }
            else if(x>=300 && x<=900 && y>=360 && y<=510){ //hover on play
                label2 = Color.blue;
            }
            else if(x>=300 && x<=900 && y>=560 && y<=710){ //hover on exit
                label3 = Color.blue;
            }
            else {
                label1 = Color.black;
                label2 = Color.black;
                label3 = Color.black;
            }
            try {
                if(x >= 10 && x <= 70 && y>=20 && y <= 80){
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
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            }
            catch(Exception e){}
            g.setColor(new Color(224, 102, 102));
            g.fillRect(0,0, getWidth(), getHeight());
            g.setColor(new Color(255, 229, 153));
            g.fillRoundRect(getWidth()/2 - 300, 160, 600, 150, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 360, 600, 150, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 560, 600, 150, 40, 40);
            g.setColor(Color.white);
            g.drawString("Levels", getWidth()/2 - (g.getFontMetrics().stringWidth("Levels")/2),110);
            g.setColor(label1);
            g.drawString("Learn", getWidth()/2 - (g.getFontMetrics().stringWidth("Learn")/2), 270);
            g.setColor(label2);
            g.drawString("Maze", getWidth()/2 - (g.getFontMetrics().stringWidth("Maze")/2), 470);
            g.setColor(label3);
            g.drawString("Game", getWidth()/2 - (g.getFontMetrics().stringWidth("Game")/2), 670);
            g.drawImage(homeBtn, 10,20,60,60,null);
        }
    }
}
