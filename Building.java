public class Building {
    private int x; // x-coordinate of material position
    private int y; // y-coordinate of material position
    private int width; // y-coordinate of material position
    private int height; // y-coordinate of material position
    private int numOfBrick, numOfWood, numOfMetal, numOfConcrete;
    private int durability;
    public Building(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(){

    }



}
