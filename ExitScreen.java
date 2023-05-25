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
            if(System.currentTimeMillis() - start >= 10000){
                frame.dispose();
            }
            else {
                repaint();
            }
        }
    }
}
