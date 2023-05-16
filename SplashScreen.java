import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.Font.*;

public class SplashScreen{
    Drawing draw = new Drawing();
    public SplashScreen(){
        JFrame frame = new JFrame("Java Practice!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.add(draw);
        draw.addMouseListener(new MouseHandler());
        //frame.setResizable(false);
        frame.setVisible(true);
    }

    class MouseHandler extends MouseAdapter {
        public void mouseClicked (MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            System.out.println(x + ", " + y);
        }
    }

    public static void main(String[]args){
        new SplashScreen();
    }
    class Drawing extends JComponent{
        public void paint (Graphics g) {
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 100f));
            }
            catch(Exception e){}

            g.setColor(new Color(255, 229, 153));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(new Color(227, 100,100));
            g.drawString("Habitat Hero",150,200);

            int[] xTri = {157,200, 242};
            int[] yTri = {125, 61, 125};
            g.fillPolygon(xTri,yTri,3);

            int[] xTri2 = {677,721, 765};
            int[] yTri2 = {125, 60, 125};
            g.fillPolygon(xTri2,yTri2,3);


            g.setColor(new Color(241, 194, 50));
            g.fillRect(300, 250, 600, 30);
            g.fillRect(400, 300, 400, 30);

            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 70f));
            }
            catch(Exception e){}

            g.setColor(new Color(0, 0,0));
            g.drawString("A",50,630);
            g.drawString("PRODUCTION",490,630);
        }
    }

}