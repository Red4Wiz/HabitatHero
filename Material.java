import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Material {
    private int x; // x-coordinate of material position
    private int y; // y-coordinate of material position
    private String type;
    private int strength;

    public Material(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.type = s;
        switch(s){
            case "wood":
                this.strength = 10;
            case "brick":
                this.strength = 15;
            case "cement":
                this.strength = 20;
            case "metal":
                this.strength = 25;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public String getType(){
        return type;
    }

    public int getStrength(){
        return strength;
    }

    public void draw(Graphics g) throws IOException {
        if(type.equals("wood")){
            Image wood = ImageIO.read(new File("Assets/wood.png"));
            g.drawImage(wood, this.x, this.y, 30,30, null);
        }
        else if(type.equals("brick")){
            Image brick = ImageIO.read(new File("Assets/brick.png"));
            g.drawImage(brick, this.x, this.y, 30,30, null);
        }
        else if(type.equals("cement")){
            Image cement = ImageIO.read(new File("Assets/cement.png"));
            g.drawImage(cement, this.x, this.y, 30,30, null);
        }
        else{
            Image metal = ImageIO.read(new File("Assets/metal.png"));
            g.drawImage(metal, this.x, this.y, 60,40, null);
        }
    }

}
