/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 * ICS4U0.2 with Ms. Krasteva.
 * @author Sailesh Badri & Pouya Karimi
 * @version 09-06-2023
 * Pouya Karimi added timing into the program, Sailesh Badri did all the other graphics and animations for the splash screen
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.Font.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Class that creates the splash screen for the game
 */
public class SplashScreen {

    /** Drawing object used to display graphics */
    private Drawing draw = new Drawing();
    /** Frame for the splash screen */
    private JFrame frame = new JFrame("Habitat Hero");
    /** The first underline rectangble below the game title*/
    private int rect1 = 0;
    /** The second underline rectangble below the game title */
    private int rect2 = 0;
    /** The rotating angle for the logo */
    private double rotationAngle = 0.0;
    /** Checks if the animation should still run */
    private boolean run = true;
    /** The start time of the program */
    private long start = 0;

    /**
     * {@link SplashScreen} Constructor
     */
    public SplashScreen() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(draw);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Drawing class used to draw the graphics
     */
    class Drawing extends JComponent {
        /**
         * The method paint that draws out the graphics
         * @param g  the <code>Graphics</code> context in which to paint
         */
        public void paint(Graphics g) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 100f));
            } catch (Exception e) {
            }

            /** Background static drawings */
            g.setColor(new Color(255, 229, 153));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(new Color(227, 100, 100));
            g.drawString("Habitat Hero", (getWidth() - g.getFontMetrics().stringWidth("Habitat Hero")) / 2, 200);


            int[] xTri = { 171, 214, 256 };
            int[] yTri = { 125, 61, 125 };
            g.fillPolygon(xTri, yTri, 3);

            int[] xTri2 = { 692, 736, 780 };
            int[] yTri2 = { 125, 60, 125 };
            g.fillPolygon(xTri2, yTri2, 3);

            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 70f));
            } catch (Exception e) {
            }

            g.setColor(new Color(0, 0, 0));
            g.drawString("A", 50, 630);
            g.drawString("PRODUCTION", 490, 630);

            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
                g.setFont(font.deriveFont(Font.BOLD, 50f));
            } catch (Exception e) {
            }

            g.setColor(new Color(0, 0, 0));

            //logo
            try {
                InputStream inputStream = SplashScreen.class.getResourceAsStream("/Assets/logo.png");
                Image image = ImageIO.read(inputStream);
                int width = 450;
                int height = 300;

                BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(image, 0, 0, width, height, null);

                // Rotate the image
                AffineTransform rotation = AffineTransform.getTranslateInstance(90 + width / 2, 450 + height / 2);
                rotation.rotate(Math.toRadians(rotationAngle));
                rotation.translate(-width / 2, -height / 2);
                ((Graphics2D) g).drawImage(resizedImage, rotation, null);
                g2d.dispose();

            } catch (IOException e) {
                e.printStackTrace();
            }


            /** Underline drawings */
            g.setColor(new Color(241, 194, 50));
            g.fillRect(300, 250, rect1, 30);
            g.fillRect(400, 300, rect2, 30);

            /** Checks if the animation is complete */
            if (run) {
                if(rect1 < 600){
                    rect1 += 6;
                    rect2 += 4;
                    rotationAngle += 3.6; // Increase the rotation angle
                    start = System.currentTimeMillis();
                }
                else if(System.currentTimeMillis() - start >= 1000){
                    run = false;
                }
                repaint();
            } else {
                new MainMenu();
                frame.dispose();
            }
        }
    }
}
