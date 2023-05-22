import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainMenu{
    JFrame frame = new JFrame("Main Menu");
    Drawing drawing = new Drawing();
    MouseHandler mouseListener = new MouseHandler();
    Color label1 = Color.white, label2 = Color.white, label3 = Color.white;
    public MainMenu(){
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.addMouseListener(mouseListener);
        drawing.addMouseMotionListener(mouseListener);
        frame.add(drawing);
        frame.setVisible(true);
    }
    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=310){ //click on levels

            }
            else if(x>=300 && x<=900 && y>=360 && y<=510){ //click on play
                new Lesson();
                frame.dispose();
            }
            else if(x>=300 && x<=900 && y>=560 && y<=710){ //click on exit
                frame.dispose();
            }
            System.out.println(x+ ", " + y);
        }
        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(x>=300 && x<=900 && y>=160 && y<=310){ //hover on levels
                label1 = Color.black;
            }
            else if(x>=300 && x<=900 && y>=360 && y<=510){ //hover on play
                label2 = Color.black;
            }
            else if(x>=300 && x<=900 && y>=560 && y<=710){ //hover on exit
                label3 = Color.black;
            }
            else {
                label1 = Color.white;
                label2 = Color.white;
                label3 = Color.white;
            }
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
            g.setColor(new Color(255, 229, 153));
            g.fillRect(0,0, getWidth(), getHeight());
            g.setColor(new Color(224, 102, 102));
            g.fillRoundRect(getWidth()/2 - 300, 160, 600, 150, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 360, 600, 150, 40, 40);
            g.fillRoundRect(getWidth()/2 - 300, 560, 600, 150, 40, 40);
            g.setColor(Color.black);
            g.drawString("Main Menu", 290,110);
            g.setColor(label1);
            g.drawString("Levels", 410, 270);
            g.setColor(label2);
            g.drawString("Play", 470, 470);
            g.setColor(label3);
            g.drawString("Exit", 470, 670);

        }
    }
}
