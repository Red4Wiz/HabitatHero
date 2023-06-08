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
import java.util.ArrayList;

class Player {
    private int x; // x-coordinate of player position
    private int y; // y-coordinate of player position
    private int speed;
    private ArrayList<Material> bag;
    final int MAX_WEIGHT = 10; //Max weight of their bag
    private int bagWeight = 0;
    boolean playerBack = false, playerFront = false, playerRight = false, playerLeft = false;

    /**
     * {@link Player} Constructor
     * @param x X-coordinate of person
     * @param y Y-coordinate of person
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 7;
        bag = new ArrayList<>();
    }

    /**
     * @return X-coordinate of person
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y-coordinate of person
     */
    public int getY() {
        return y;
    }

    /**
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
    public boolean pick(Material m){
        if(withinRange(m) && !isFull(m.getWeight())){
            bag.add(m);
            bagWeight += m.getWeight();
            return true;
        }
        return false;
    }

    /**
     * Is the player withing range of a material
     * @param m Material being checked for
     * @return True if the player is within range of a material, false otherwise
     */
    public boolean withinRange(Material m){
        if(Math.abs(x - (m.getX()+(m.getWidth()/2))) <= 50 && Math.abs(y - (m.getY()+(m.getHeight()/2))) <= 50){
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
    
    public void draw(Graphics g, String direction, boolean player) throws IOException {
        if(direction.equals("back")){
            Image player1 = ImageIO.read(new File("Assets/player_back1.png"));
            Image player2 = ImageIO.read(new File("Assets/player_back2.png"));

            if(player)  g.drawImage(player1, this.x, this.y, 70, 70, null);
            else  g.drawImage(player2, this.x, this.y, 70, 70, null);
        }
        else if(direction.equals("front")){
            Image player1 = ImageIO.read(new File("Assets/player_front_1.png"));
            Image player2 = ImageIO.read(new File("Assets/player_front_2.png"));

            if(player)  g.drawImage(player1, this.x, this.y, 70, 70, null);
            else  g.drawImage(player2, this.x, this.y, 70, 70, null);
        }
        else if(direction.equals("right")){
            Image player1 = ImageIO.read(new File("Assets/player_right1.png"));
            Image player2 = ImageIO.read(new File("Assets/player_right2.png"));

            if(player)  g.drawImage(player1, this.x, this.y, 70, 70, null);
            else  g.drawImage(player2, this.x, this.y, 70, 70, null);

        }
        else{
            Image player1 = ImageIO.read(new File("Assets/player_left1.png"));
            Image player2 = ImageIO.read(new File("Assets/player_left2.png"));

            if(player)  g.drawImage(player1, this.x, this.y, 70, 70, null);
            else  g.drawImage(player2, this.x, this.y, 70, 70, null);

        }
    }
}
