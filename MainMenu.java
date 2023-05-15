import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {
    private Drawing drawing = new Drawing();
    JFrame frame = new JFrame("Main Menu");
    public MainMenu(){
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawing);
        drawing.addMouseListener(new MouseHandler());
        frame.setVisible(true);
    }
    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
        }
    }
    class Drawing extends JComponent {
        public void paint(Graphics g){

        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
