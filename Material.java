import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Material {
    private int x; // x-coordinate of material position
    private int y; // y-coordinate of material position
    private int width;
    private int height;
    private String type;
    private int strength;
    private int weight;

    public Material(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.type = s;
        switch(s){
            case "wood":
                this.strength = 10;
                this.weight = 1;
                break;
            case "brick":
                this.strength = 15;
                this.weight = 3;
                break;
            case "metal":
                this.strength = 20;
                this.weight = 5;
                break;
            case "concrete":
                this.strength = 25;
                this.weight = 7;
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

    public void draw(Graphics g,int x) throws IOException {
        if(x==0) {
            if (type.equals("wood")) {
                Image wood = ImageIO.read(new File("Assets/wood.png"));
                g.drawImage(wood, this.x, this.y, 30, 30, null);
            } else if (type.equals("brick")) {
                Image brick = ImageIO.read(new File("Assets/brick.png"));
                g.drawImage(brick, this.x, this.y, 50, 30, null);
            } else if (type.equals("concrete")) {
                Image concrete = ImageIO.read(new File("Assets/cement.png"));
                g.drawImage(concrete, this.x, this.y, 60, 30, null);
            } else {
                Image metal = ImageIO.read(new File("Assets/metal.png"));
                g.drawImage(metal, this.x, this.y, 60, 40, null);
            }
        }
        else{
            if (type.equals("wood")) {
                Image wood = ImageIO.read(new File("Assets/wood.png"));
                width = 70;
                height = 50;
                g.drawImage(wood, this.x, this.y, width, height, null);
            } else if (type.equals("brick")) {
                Image brick = ImageIO.read(new File("Assets/brick.png"));
                width = 80;
                height = 50;
                g.drawImage(brick, this.x, this.y, width, height, null);
            } else if (type.equals("concrete")) {
                Image concrete = ImageIO.read(new File("Assets/cement.png"));
                width = 80;
                height = 50;
                g.drawImage(concrete, this.x, this.y, width, height, null);
            } else {
                Image metal = ImageIO.read(new File("Assets/metal.png"));
                width = 100;
                height = 60;
                g.drawImage(metal, this.x, this.y, width, height, null);
            }
        }
    }

    public int getWidth(){return width;}
    public int getHeight() {return height;}


}
