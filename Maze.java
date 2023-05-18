import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Maze {
    MazeDrawing draw = new MazeDrawing();

    public Maze() {
        JFrame frame = new JFrame("Habitat Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(draw);
        draw.addMouseListener(new MouseHandler());
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

    public static void main(String[] args) {
        new Maze();
    }

    class MazeDrawing extends JComponent {

    }
}
