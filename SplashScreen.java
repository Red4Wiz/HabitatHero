import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.Font.*;

public class SplashScreen{
    public SplashScreen(){
        JFrame frame = new JFrame("Java Practice!");
        JLabel label = new JLabel("Press Enter To Continue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
        frame.pack();
        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, SplashScreen.class.getResourceAsStream("Assets/ZenDots-Regular.ttf"));
            label.setFont(font.deriveFont(Font.BOLD, 12f));
        }
        catch(Exception e){}


    }
    public static void main(String[]args){
        new SplashScreen();
    }
}