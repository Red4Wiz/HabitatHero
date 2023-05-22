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
    private Player player;

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
        frame.setResizable(false);
        frame.setVisible(true);

        mazeWidth = 24; // Adjust the width as needed
        mazeHeight = 16; // Adjust the height as needed

        maze = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', '#', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        cellSize = Math.min(1200 / mazeWidth, 800 / mazeHeight); // Calculate the cell size based on screen dimensions
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
            }
            System.out.println(e);
        }

        public void keyPressed(KeyEvent e) {
            if (screen == 1) {
                int keyCode = e.getKeyCode();
                int playerX = player.getX();
                int playerY = player.getY();

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        if (playerY > 50 && maze[(playerY - 3) / cellSize][(playerX / cellSize)] != '#') {
                            player.moveUp();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (playerY < 750 && maze[(playerY + 23) / cellSize][(playerX / cellSize)] != '#') {
                            player.moveDown();
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (playerX > 50 && maze[playerY / cellSize][(playerX-3) / cellSize] != '#') {
                            player.moveLeft();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (playerX < 1150 && maze[(playerY / cellSize)][(playerX+23) / cellSize] != '#') {
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
                g.drawString("Test", 430, 200);
                g.fillRoundRect(150, 300, 900, 400, 50, 50);
                g.setColor(new Color(227, 100, 100));
                g.drawString("Click Enter!", (getWidth() - g.getFontMetrics().stringWidth("Click Enter!")) / 2, 520);

            }
            else if(screen == 1){
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, 1200, 800);

                // Draw the maze
                for (int i = 0; i < mazeHeight; i++) {
                    for (int j = 0; j < mazeWidth; j++) {
                        int x = j * cellSize;
                        int y = i * cellSize;

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
            }
        }


    }
}
