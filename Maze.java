import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    Drawing draw = new Drawing();
    JFrame frame = new JFrame("Habitat Hero");
    private int screen = 0;
    private Player player;
    private ArrayList<Material> materials = new ArrayList<>();

    private char[][] maze; // Maze structure
    private int mazeWidth;
    private int mazeHeight;
    private int cellSize;


    public Maze() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(draw);
        draw.addMouseListener(new MouseHandler());
        frame.addKeyListener(new KeyHandler());
        frame.setResizable(true);
        frame.setVisible(true);

        mazeWidth = 24; // Adjust the width as needed
        mazeHeight = 14; // Adjust the height as needed

        maze = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        cellSize = 50;
        System.out.println(cellSize);

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
                player = new Player(60, 350);
                materials.add(new Material(1060,415));
                materials.add(new Material(65,620));
                materials.add(new Material(740,160));
            }
            if((e.getKeyChar() == 'p' || e.getKeyChar() == 'P') && screen == 1){
                for(int i = 0; i < materials.size(); i++){
                    if(player.pick(materials.get(i))){
                        materials.remove(i);
                        break;
                    }
                }
                draw.repaint();

            }
            System.out.println(e.getKeyChar());
        }

        public void keyPressed(KeyEvent e) {
            if (screen == 1) {
                String keyCode = (e.getKeyChar()+"").toLowerCase();
                int playerX = player.getX();
                int playerY = player.getY();

                switch (keyCode) {
                    case "w":
                        if (playerY > 50 && maze[(playerY-107) / cellSize][(playerX / cellSize)] != '#') {
                            player.moveUp();
                        }
                        break;
                    case "s":
                        if (playerY < 750 && maze[(playerY-127) / cellSize][(playerX / cellSize)] != '#') {
                            player.moveDown();
                        }
                        break;
                    case "a":
                        if (playerX > 50 && maze[(playerY-100) / cellSize][(playerX-7) / cellSize] != '#') {
                            player.moveLeft();
                        }
                        break;

                    case "d":
                        if (playerX < 1150 && maze[((playerY-100) / cellSize)][(playerX+27) / cellSize] != '#') {
                            player.moveRight();
                        }
                        break;
                }

                System.out.println("Player y: " + playerY + " Cell y: " + (playerY / cellSize) + "\nPlayer x: " + playerX + " Cell x: " + (playerX / cellSize));
                draw.repaint();
            }

        }

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
                g.drawString("Test", (getWidth() - g.getFontMetrics().stringWidth("Test")) / 2, 200);
                g.fillRoundRect(100, 300, 1000, 400, 50, 50);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 30f));
                } catch (Exception e) {
                }

                g.setColor(new Color(255, 229, 153));
                g.setColor(new Color(0,0,0));
                g.drawString("Hello! Now that you have learned all the", (getWidth() - g.getFontMetrics().stringWidth("Hello! Now that you have learned all the")) / 2, 370);
                g.drawString("controls, let's put them to the test. You will", (getWidth() - g.getFontMetrics().stringWidth("controls, let's put them to the test. You will")) / 2, 410);
                g.drawString("have to complete the maze’s challenges using", (getWidth() - g.getFontMetrics().stringWidth("have to complete the maze’s challenges using")) / 2, 450);
                g.drawString("your knowledge to move on to the game!", (getWidth() - g.getFontMetrics().stringWidth("your knowledge to move on to the game!")) / 2, 490);
                g.drawString("Press <enter> to move on", (getWidth() - g.getFontMetrics().stringWidth("Press <enter> to move on")) / 2, 620);
            }
            else if(screen == 1){

                // Draw the maze
                for (int i = 0; i < mazeHeight; i++) {
                    for (int j = 0; j < mazeWidth; j++) {
                        int x = j * cellSize;
                        int y = (i * cellSize)+100;

                        if (maze[i][j] == '#') {
                            g.setColor(Color.BLACK);
                            g.fillRect(x, y, cellSize, cellSize);
                        } else {
                            g.setColor(Color.WHITE);
                            g.fillRect(x, y, cellSize, cellSize);
                        }
                    }
                }

                int playerSize = 20;
                int playerX = player.getX();
                int playerY = player.getY();

                g.setColor(Color.BLUE);
                g.fillRect(playerX, playerY, playerSize, playerSize);


                for(Material m : materials){
                    g.setColor(Color.RED);
                    g.fillRect(m.getX(), m.getY(), playerSize, playerSize);
                }

                g.setColor(new Color(163, 235, 240));
                g.fillRect(0, 0, 1200, 100);

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                    g.setFont(font.deriveFont(Font.BOLD, 20f));
                } catch (Exception e) {
                }

                g.setColor(new Color(255, 229, 153));
                g.setColor(new Color(0,0,0));
                g.drawString("In order to leave the maze, you must first build a house in the middle square block.", (getWidth() - g.getFontMetrics().stringWidth("In order to leave the maze, you must first build a house in the middle square block.")) / 2, 25);
                g.drawString(" Find and pick up materials and return to the middle square to build.", (getWidth() - g.getFontMetrics().stringWidth("Find and pick up materials and return to the middle square to build.")) / 2, 55);
                g.drawString(" After you have built, the exit will open and you may leave!", (getWidth() - g.getFontMetrics().stringWidth("After you have built, the exit will open and you may leave!")) / 2, 85);
            }
            else if(screen == 2){

            }
        }
    }
}
