import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.Font.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SplashScreen{
    Drawing draw = new Drawing();
    JFrame frame = new JFrame("Habitat Hero");
    private int rect1 = 0;
    private int rect2 = 0;

    public SplashScreen(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.add(draw);
        draw.addMouseListener(new MouseHandler());
        frame.setResizable(false);
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

            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 70f));
            }
            catch(Exception e){}

            g.setColor(new Color(0, 0,0));
            g.drawString("A",50,630);
            g.drawString("PRODUCTION",490,630);

            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 50f));
            }
            catch(Exception e){}

            g.setColor(new Color(0, 0,0));
            //g.drawString("A");

            // Load the image file from the file directory
            try {
                Image image = ImageIO.read(new File("Assets/logo.png"));
                int width = 450;
                int height = 300;

                BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(image, 0, 0, width, height, null);

                // Dispose the Graphics2D object
                g2d.dispose();

                // Draw the resized image at the specified coordinates
                g.drawImage(resizedImage, 90, 450, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            g.setColor(new Color(241, 194, 50));
            g.fillRect(300, 250, rect1, 30);
            g.fillRect(400, 300, rect2, 30);
            if(rect1 < 600){
                rect1+=6;
                rect2+=4;
                repaint();
            }else{
                new MainMenu();
                frame.dispose();
            }
        }
    }
}
