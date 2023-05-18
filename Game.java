import javax.swing.*;

public class Game {
    public static JFrame frame = new JFrame("Habitat Hero");
    public static void main(String[] args) {
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new MainMenu();
        frame.setVisible(true);
    }
}
