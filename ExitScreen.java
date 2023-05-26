import javax.swing.*;
import java.awt.*;

public class ExitScreen {
    JFrame frame = new JFrame("Exit Screen");
    Drawing drawing = new Drawing();
    long start = 0;
    public ExitScreen(){
        start = System.currentTimeMillis();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawing);
        frame.setVisible(true);
    }
    class Drawing extends  JComponent{
        public void paint(Graphics g){
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 90f));
            }
            catch(Exception e){}
            g.setColor(new Color(224, 102, 102));
            g.fillRect(0,0,getWidth(), getHeight());
            g.setColor(new Color(255, 229, 153));
            g.drawString("Thanks For", getWidth()/2 - g.getFontMetrics().stringWidth("Thanks For")/2, 150);
            g.drawString("Playing", getWidth()/2 - g.getFontMetrics().stringWidth("Playing")/2, 250);
            try{
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 50f));
            }
            catch(Exception e){}
            g.setColor(Color.white);
            g.drawString("Game Made By:", getWidth()/2, 450);
            g.drawString("Pouya Karimi &", getWidth()/2, 520);
            g.drawString("Sailesh V. Badri", getWidth()/2, 590);
//            if(System.currentTimeMillis() - start >= 7000){
//                frame.dispose();
//            }
//            else {
//                repaint();
//            }
        }
    }
}
