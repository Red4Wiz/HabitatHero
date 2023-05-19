import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Lesson {
    JFrame frame = new JFrame("Lessons");
    Drawing drawing = new Drawing();
    int choice = 0;
    public Lesson(){
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyHandler());
        frame.add(drawing);
        frame.setVisible(true);
    }
    class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            System.out.println(e.getKeyChar());
            if(e.getKeyChar() == 'h'){
                new MainMenu();
                frame.dispose();
            }
            drawing.repaint();
        }
    }
    class Drawing extends JComponent {
        public void paint(Graphics g){
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
                g.fillRoundRect(125,170, 950,2*getHeight()/3, 50,50);
                try{
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                }
                catch(Exception e){}
                g.setColor(Color.black);
                g.drawString("Hello! Welcome to the tutorials of Habitat", 180, 250);
                g.drawString("Hero. In this section you will learn all the", 180, 300);
                g.drawString("necessary controls and information for you", 180, 350);
                g.drawString("to succeed in this game. Use the left", 180, 400);
                g.drawString("and right arrow keys to move between", 180, 450);
                g.drawString("instructions and once you are satisfied", 180, 500);
                g.drawString("move on to the testing portion to test out", 180, 550);
                g.drawString("your skills! Click the right arrow key to move!", 180, 600);
            }
        }
    }

    public static void main(String[] args) {
        new Lesson();
    }
}

