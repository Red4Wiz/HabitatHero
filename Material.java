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

/**
 * Class to represent a material in our game (wood, brick, metal, or concrete)
 */
public class Material {
    /** X-coordinate of material*/
    private int x;
    /** Y-coordinate of material*/
    private int y;
    /** Width of the material.*/
    private int width;
    /** Height of the material.*/
    private int height;
    /** Type of the material.*/
    private String type;
    /** Strength of the material.*/
    private int strength;
    /** Weight of the material.*/
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
     * Return X-coordinate
     * @return X-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Return Y-coordinate
     * @return Y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Return the type of material
     * @return Type of material
     */
    public String getType(){
        return type;
    }

    /**
     * Return the strength of material
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
                Image wood = ImageIO.read(Material.class.getResource("/Assets/wood.png"));
                width = 70;
                height = 70;
                g.drawImage(wood, this.x, this.y, 70, 70, null);
            } else if (type.equals("brick")) {
                Image brick = ImageIO.read(Material.class.getResource("/Assets/brick.png"));
                g.drawImage(brick, this.x, this.y, 50, 30, null);
            } else if (type.equals("concrete")) {
                Image concrete = ImageIO.read(Material.class.getResource("/Assets/cement.png"));
                g.drawImage(concrete, this.x, this.y, 60, 30, null);
            } else {
                Image metal = ImageIO.read(Material.class.getResource("/Assets/metal.png"));
                width = 50;
                height = 50;
                g.drawImage(metal, this.x, this.y, 50, 50, null);
            }
        }
        else{ //Drawing in the final level (regular sized materials)
            if (type.equals("wood")) {
                Image wood = ImageIO.read(Material.class.getResource("/Assets/wood.png"));
                width = 100;
                height = 100;
                g.drawImage(wood, this.x, this.y, width, height, null);
            } else if (type.equals("brick")) {
                Image brick = ImageIO.read(Material.class.getResource("/Assets/brick.png"));
                width = 100;
                height = 100;
                g.drawImage(brick, this.x, this.y, width, height, null);
            } else if (type.equals("concrete")) {
                Image concrete = ImageIO.read(Material.class.getResource("/Assets/cement.png"));
                width = 100;
                height = 100;
                g.drawImage(concrete, this.x, this.y, width, height, null);
            } else {
                Image metal = ImageIO.read(Material.class.getResource("/Assets/metal.png"));
                width = 100;
                height = 100;
                g.drawImage(metal, this.x, this.y, width, height, null);
            }
        }
    }

    /**
     * Return the width of the image of the material
     * @return Width of the image of the material
     */
    public int getWidth(){return width;}
    /**
     * Return the height of the image of the material
     * @return Height of the image of the material
     */
    public int getHeight() {return height;}
    /**
     * Return the weight of the material.
     * @return Weight of the material
     */
    public int getWeight(){return weight;}

}
