import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Maze {
    Drawing draw = new Drawing();
    JFrame frame = new JFrame("Habitat Hero");
    private int screen = 0;


    public Maze() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(draw);
        draw.addMouseListener(new MouseHandler());
        frame.addKeyListener(new KeyHandler());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            System.out.println(x + ", " + y);
        }
    }

    class KeyHandler extends KeyAdapter{
        public void keyTyped(KeyEvent e){
            if(e.getKeyChar() == '\n' && screen == 0) {
                screen = 1;
                draw.repaint();
            }
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Maze();
    }

    class Drawing extends JComponent {
        public void paint(Graphics g) {
            if (screen == 0) {
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 100f));
                } catch (Exception e) {
                }


                g.setColor(new Color(227, 100, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(new Color(255, 229, 153));
                g.drawString("Test", 430, 200);
                g.fillRoundRect(150, 300, 900, 400, 50, 50);

            }
            else if(screen == 1){

            }
        }


    }
}
