/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Sailesh Badri
 * @version 09-06-2023
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Material {
    private int x; // x-coordinate of material position
    private int y; // y-coordinate of material position
    //Width and height of the material
    private int width;
    private int height;
    private String type;
    private int strength;
    private int weight;

    /**
     * {@link Material} Constructor
     * @param x X-coordinate of material
     * @param y Y-coordinate of material
     * @param s Type of material
     */
    public Material(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.type = s;
        //Setting the strength and weight of the material based on its type
        switch(s){
            case "wood":
                this.strength = 5;
                this.weight = 1;
                break;
            case "brick":
                this.strength = 10;
                this.weight = 3;
                break;
            case "metal":
                this.strength = 15;
                this.weight = 5;
                break;
            case "concrete":
                this.strength = 20;
                this.weight = 7;
        }
    }

    /**
     * @return X-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * @return Type of material
     */
    public String getType(){
        return type;
    }

    /**
     * @return Strength of material
     */
    public int getStrength(){
        return strength;
    }

    /**
     * Drawing a material on a given canvas
     * @param g Canvas to draw on
     * @param x Which screen the material is being drawn in (0 indicating the maze)
     * @throws IOException
     */
    public void draw(Graphics g, int x) throws IOException {
        if(x==0) { //Drawing in the maze (material are scaled down)
            if (type.equals("wood")) {
                Image wood = ImageIO.read(new File("Assets/wood.png"));
                g.drawImage(wood, this.x, this.y, 70, 70, null);
            } else if (type.equals("brick")) {
                Image brick = ImageIO.read(new File("Assets/brick.png"));
                g.drawImage(brick, this.x, this.y, 50, 30, null);
            } else if (type.equals("concrete")) {
                Image concrete = ImageIO.read(new File("Assets/cement.png"));
                g.drawImage(concrete, this.x, this.y, 60, 30, null);
            } else {
                Image metal = ImageIO.read(new File("Assets/metal.png"));
                g.drawImage(metal, this.x, this.y, 50, 50, null);
            }
        }
        else{ //Drawing in the final level (regular sized materials)
            if (type.equals("wood")) {
                Image wood = ImageIO.read(new File("Assets/wood.png"));
                width = 100;
                height = 100;
                g.drawImage(wood, this.x, this.y, width, height, null);
            } else if (type.equals("brick")) {
                Image brick = ImageIO.read(new File("Assets/brick.png"));
                width = 100;
                height = 100;
                g.drawImage(brick, this.x, this.y, width, height, null);
            } else if (type.equals("concrete")) {
                Image concrete = ImageIO.read(new File("Assets/cement.png"));
                width = 100;
                height = 100;
                g.drawImage(concrete, this.x, this.y, width, height, null);
            } else {
                Image metal = ImageIO.read(new File("Assets/metal.png"));
                width = 100;
                height = 100;
                g.drawImage(metal, this.x, this.y, width, height, null);
            }
        }
    }

    /**
     * @return Width of the image of the material
     */
    public int getWidth(){return width;}
    /**
     * @return Height of the image of the material
     */
    public int getHeight() {return height;}
    /**
     * @return Weight of the material
     */
    public int getWeight(){return weight;}

}
