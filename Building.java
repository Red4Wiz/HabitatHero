/**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 *ICS4U0.2 with Ms. Krasteva.
 * @author Sailesh Badri & Pouya Karimi
 * @version 09-06-2023
 * A class to represent a house/building drawn in the game.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Building {
    private int x; // x-coordinate of material position
    private int y; // y-coordinate of material position
    private int width; // y-coordinate of material position
    private int height; // y-coordinate of material position
    private int numOfBrick, numOfWood, numOfMetal, numOfConcrete;
    private int durability;

    /**
     * {@link Building} Constructor
     * @param x X coordinate of the building
     * @param y Y coordinate of the building
     */
    public Building(int x, int y) {
        this.x = x;
        this.y = y;
        this.numOfBrick = 0;
        this.numOfWood = 0;
        this.numOfMetal = 0;
        this.numOfConcrete = 0;
    }

    /**
     * Remove n amount of durability from the house
     * @param n Amount of damage being taken
     */
    public void removeDurability(int n){
        this.durability -= n;
    }

    /**
     * Adding n number of bricks to a house.
     * @param n Number of bricks being added to the house
     */
    public void addBrick(int n){
        this.numOfBrick += n;
        durability += 10*n;
    }
    /**
     * Adding n number of metal to a house.
     * @param n Number of metal being added to the house
     */
    public void addMetal(int n){
        this.numOfMetal += n;
        durability += 15*n;
    }
    /**
     * Adding n number of wood to a house.
     * @param n Number of wood being added to the house
     */
    public void addWood(int n){
        this.numOfWood += n;
        durability += 5*n;
    }
    /**
     * Adding n number of concrete to a house.
     * @param n Number of concrete being added to the house
     */
    public void addConcrete(int n){
        this.numOfConcrete += n;
        durability += 20*n;
    }
    /**
     * Removing n number of bricks to a house.
     * @param n Number of bricks being removed to the house
     */
    public void removeBrick(int n){
        this.numOfBrick -= n;
    }
    /**
     * Removing n number of wood to a house.
     * @param n Number of wood being removed to the house
     */
    public void removeWood(int n){
        this.numOfWood -= n;
    }
    /**
     * Removing n number of metal to a house.
     * @param n Number of metal being removed to the house
     */
    public void removeMetal(int n){
        this.numOfMetal -= n;
    }
    /**
     * Removing n number of concrete to a house.
     * @param n Number of concrete being removed to the house
     */
    public void removeConcrete(int n){
        this.numOfConcrete -= n;
    }
    /**
     * @return Number of bricks in the house.
     */
    public int getNumOfBrick() {
        return numOfBrick;
    }
    /**
     * @return Number of concrete in the house.
     */
    public int getNumOfConcrete() {
        return numOfConcrete;
    }
    /**
     * @return Number of metal in the house.
     */
    public int getNumOfMetal() {
        return numOfMetal;
    }
    /**
     * @return Number of wood in the house.
     */
    public int getNumOfWood() {
        return numOfWood;
    }
    /**
     * @return Amount of durability in the house
     */
    public int getDurability() {
        return durability;
    }
    /**
     * @return X-coordinate of the house
     */
    public int getX() {
        return x;
    }
    /**
     * @return Y-coordinate of the house
     */
    public int getY() {
        return y;
    }

    /**
     * Drawing the house in a give graphic.
     * @param g Graphic in which the house is drawn in.
     */
    public void draw(Graphics g, int width, int height) throws IOException {
        Image house = ImageIO.read(new File("Assets/house.png"));
        g.drawImage(house, this.x, this.y, width, height, null);
    }
}
