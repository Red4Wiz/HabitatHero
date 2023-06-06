import java.awt.*;

public class Building {
    private int x; // x-coordinate of material position
    private int y; // y-coordinate of material position
    private int width; // y-coordinate of material position
    private int height; // y-coordinate of material position
    private int numOfBrick, numOfWood, numOfMetal, numOfConcrete;
    private int durability, screen;
    public Building(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.screen = s;
        this.numOfBrick = 0;
        this.numOfWood = 0;
        this.numOfMetal = 0;
        this.numOfConcrete = 0;
    }
    public void addBrick(int n){
        this.numOfBrick += n;
        durability += 15*n;
    }
    public void addMetal(int n){
        this.numOfMetal += n;
        durability += 20*n;
    }
    public void addWood(int n){
        this.numOfWood += n;
        durability += 10*n;
    }
    public void addConcrete(int n){
        this.numOfConcrete += n;
        durability += 25*n;
    }

    public int getNumOfBrick() {
        return numOfBrick;
    }

    public int getNumOfConcrete() {
        return numOfConcrete;
    }

    public int getNumOfMetal() {
        return numOfMetal;
    }

    public int getNumOfWood() {
        return numOfWood;
    }

    public int getDurability() {
        return durability;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g){
        if(screen == 1){

        }
        if(screen == 2){
            g.setColor(new Color(255, 0, 0));
            int[] xTri = { x, x+100, x+200 };
            int[] yTri = { y+70, y, y+70 };
            g.fillPolygon(xTri, yTri, 3);

            g.setColor(Color.WHITE);
            g.fillRect(x,y+70, 200, 110);

            g.setColor(Color.YELLOW);
            g.fillRect(x+20,y+90, 25, 20);
            g.fillRect(x+155,y+90, 25, 20);
            
        }
    }



}
