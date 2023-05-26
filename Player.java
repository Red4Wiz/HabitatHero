import java.util.ArrayList;

class Player {
    private int x; // x-coordinate of player position
    private int y; // y-coordinate of player position
    private int speed;
    private ArrayList<Material> bag;

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
    public boolean pick(Material m){
        if(Math.abs(x - (m.getX()+(m.getWidth()/2))) <= 50 && Math.abs(y - (m.getY()+(m.getHeight()/2))) <= 50){
            bag.add(m);
            System.out.println(bag);
            return true;
        }
        return false;
    }
}
