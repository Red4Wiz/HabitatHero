import java.util.ArrayList;

class Player {
    private int x; // x-coordinate of player position
    private int y; // y-coordinate of player position
    private int speed;
    private ArrayList<Material> bag;
    final int MAX_WEIGHT = 25;
    private int bagWeight = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 7;
        bag = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getBagWeight(){return bagWeight;}

    public void moveUp() {
        y-=speed;
    }

    public void moveDown() {
        y+=speed;
    }

    public void moveLeft() {
        x-=speed;
    }

    public void moveRight() {
        x+=speed;
    }
    public ArrayList<Material> getBag(){
        return bag;
    }
    public boolean pick(Material m){
        if(Math.abs(x - (m.getX()+(m.getWidth()/2))) <= 50 && Math.abs(y - (m.getY()+(m.getHeight()/2))) <= 50){
            if(!isFull(m.getWeight())) {
                bag.add(m);
                System.out.println(bag);
                bagWeight += m.getWeight();
                return true;
            }
            else{
                System.out.println("too much weight the bag can't carry!");
                return false;
            }
        }
        return false;
    }
    public boolean isFull(int x){
        if(bagWeight+x > MAX_WEIGHT) return true;
        return false;
    }
}
