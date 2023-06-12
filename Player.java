 /**
 * <h1>ICS4U ISP - Habitat Hero</h1>
 * <h2>Course Info:</h2>
 * ICS4U0.2 with Ms. Krasteva.
 * @author Sailesh Badri
 * @version 09-06-2023
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

 /**
  * Class to represent a player in our game.
  */
 class Player {
    /** X coordinate of the player */
    private int x; // x-coordinate of player position
    /** Y coordinate of the player */
    private int y; // y-coordinate of player position
    /** Speed of the player */
    private int speed;
    /** ArrayList of all the materials in the bag of the player */
    private ArrayList<Material> bag;
    /** The maximum weight the player can hold in their bag */
    final int MAX_WEIGHT = 10; //Max weight of their bag
    /** The current weight of the bag */
    private int bagWeight = 0;
    /** Width of the bag. */
    private int width;
    /** Height of the bag. */
    private int height;

    /**
     * {@link Player} Constructor
     * @param x X-coordinate of person
     * @param y Y-coordinate of person
     */
    public Player(int x, int y, int s) {
        this.x = x;
        this.y = y;
        speed = s;
        bag = new ArrayList<>();
    }

     /**
      * Returning the speed of the player.
      * @return Speed of player
      */

    public int getSpeed(){
        return speed;
    }
    /**
     * Return the X-coordinate of the person
     * @return X-coordinate of person
     */
    public int getX() {
        return x;
    }

    /**
     * Return the Y-coordinate of the person
     * @return Y-coordinate of person
     */
    public int getY() {
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    /**
     * Return the weight of the player's bag
     * @return Weight of their bag
     */
    public int getBagWeight(){return bagWeight;}

    /**
     * Move the character up
     */
    public void moveUp() {
        y-=speed;

    }

    /**
     * Move the character down
     */
    public void moveDown() {
        y+=speed;
    }

    /**
     * Move the character left
     */
    public void moveLeft() {
        x-=speed;
    }

    /**
     * Move the character right
     */
    public void moveRight() {
        x+=speed;
    }

    /**
     * Return the player's bag.
     * @return Player's bag
     */
    public ArrayList<Material> getBag(){
        return bag;
    }

    /**
     * Pick up a material
     * @param m Material being picked up
     * @return Whether the material was picked up
     */
    public boolean pick(Material m, int range){
        if(withinRange(m, range) && !isFull(m.getWeight())){
            bag.add(m);
            bagWeight += m.getWeight();
            return true;
        }
        return false;
    }

    /**
     * Is the player withing range of a material
     * @param m Material being checked for
     * @param range The radius the player should be within
     * @return True if the player is within range of a material, false otherwise
     */
    public boolean withinRange(Material m, int range){
        if(Math.abs((x+width/2) - (m.getX()+(m.getWidth()/2))) <= range && Math.abs((y+height/2) - (m.getY()+(m.getHeight()/2))) <= range){
            return true;
        }
        return false;
    }

    /**
     * Is the player withing range of a building
     * @param b Building being checked for
     * @return True if the player is within range of a material, false otherwise
     */
    public boolean withinRange(Building b){
        if(Math.abs((x+45) - (b.getX()+(b.getWidth()/2))) <= 100 && Math.abs((y+50) - (b.getY()+(b.getHeight()/2))) <= 100){
            return true;
        }
        return false;
    }

    /**
     * Check if the bag has space for something with weight X
     * @param x Weight of material being picked up
     * @return True if there is space in the bag, false otherwise
     */
    public boolean isFull(int x){
        if(bagWeight+x > MAX_WEIGHT) return true;
        return false;
    }

    /**
     * Return Number of wood in the player's backpack
     * @return Number of wood in the player's backpack
     */
    public int getWood(){
        int x = 0;
        for(Material m : bag){
            if(m.getType().equals("wood")){
                x++;
            }
        }
        return x;
    }
    /**
     * Return Number of brick in the player's backpack
     * @return Number of brick in the player's backpack
     */
    public int getBrick(){
        int x = 0;
        for(Material m : bag){
            if(m.getType().equals("brick")){
                x++;
            }
        }
        return x;
    }
    /**
     * Return Number of metal in the player's backpack
     * @return Number of metal in the player's backpack
     */
    public int getMetal(){
        int x = 0;
        for(Material m : bag){
            if(m.getType().equals("metal")){
                x++;
            }
        }
        return x;
    }
    /**
     * Return Number of concrete in the player's backpack
     * @return Number of concrete in the player's backpack
     */
    public int getConcrete(){
        int x = 0;
        for(Material m : bag){
            if(m.getType().equals("concrete")){
                x++;
            }
        }
        return x;
    }

     /**
      * Checking if the user has any material, return true if they do.
      * @return Whether the player has any material in their bag.
      */
    public boolean hasMaterials(){
        if(getWood() > 0 || getBrick() > 0 || getMetal() > 0 || getConcrete() > 0){
            return true;
        }
        return false;
    }
    /**
     * Remove n number of material m from the player's bag.
     * @param material Material being removed
     * @param n Number of times it is removed
     */
    public void removeMaterial(String material, int n){
        int i = 0;
        int numRemoved = 0;
        //Removing n material m from player's bag
        while(i < bag.size()){
            if(numRemoved == n) break;
            if(bag.get(i).getType().equals(material) && numRemoved < n){
                numRemoved++;
                bag.remove(i);
            }
            else{
                i++;
            }
        }
        //Updating the weight of the bag
        if(material.equals("wood")){
            bagWeight-= n;
        }
        else if(material.equals("brick")){
            bagWeight -= 3*n;
        }
        else if(material.equals("metal")){
            bagWeight -= 5*n;
        }
        else if(material.equals("concrete")){
            bagWeight -= 7*n;
        }
    }

    /**
     * The method to draw the player in different screens
     * @param g
     * @param direction
     * @param player
     * @throws IOException
     */
    public void draw(Graphics g, String direction, boolean player, int screen) throws IOException {
        if(screen == 0){
            if(direction.equals("back")){
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_back1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_back2.png"));

                if(player)  g.drawImage(player1, this.x-10, this.y-10, 40, 50, null);
                else  g.drawImage(player2, this.x-10, this.y-10, 40, 50, null);
                width = 40;
                height = 50;
            }
            else if(direction.equals("front")){
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_front_1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_front_2.png"));

                if(player)  g.drawImage(player1, this.x-10, this.y-10, 40, 50, null);
                else  g.drawImage(player2, this.x-10, this.y-10, 40, 50, null);
                width = 40;
                height = 50;
            }
            else if(direction.equals("right")){
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_right1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_right2.png"));

                if(player)  g.drawImage(player1, this.x-20, this.y-10, 60, 50, null);
                else  g.drawImage(player2, this.x-20, this.y-10, 60, 50, null);
                width = 60;
                height = 50;

            }
            else{
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_left1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_left2.png"));

                if(player)  g.drawImage(player1, this.x-25, this.y-10, 60, 50, null);
                else  g.drawImage(player2, this.x-25, this.y-10, 60, 50, null);
                width = 60;
                height = 50;
            }
        }
        else{
            if(direction.equals("back")){
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_back1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_back2.png"));

                if(player)  g.drawImage(player1, this.x, this.y, 70, 90, null);
                else  g.drawImage(player2, this.x, this.y, 70, 90, null);
                width = 70;
                height = 90;
            }
            else if(direction.equals("front")){
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_front_1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_front_2.png"));

                if(player)  g.drawImage(player1, this.x, this.y, 70, 90, null);
                else  g.drawImage(player2, this.x, this.y, 70, 90, null);
                width = 70;
                height = 90;
            }
            else if(direction.equals("right")){
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_right1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_right2.png"));

                if(player)  g.drawImage(player1, this.x-10, this.y, 100, 90, null);
                else  g.drawImage(player2, this.x-10, this.y, 100, 90, null);
                width = 100;
                height = 90;
            }
            else{
                Image player1 = ImageIO.read(Player.class.getResource("/Assets/player_left1.png"));
                Image player2 = ImageIO.read(Player.class.getResource("/Assets/player_left2.png"));

                if(player)  g.drawImage(player1, this.x-10, this.y, 100, 90, null);
                else  g.drawImage(player2, this.x-10, this.y, 100, 90, null);
                width = 100;
                height = 90;
            }
        }
    }
}
