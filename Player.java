class Player {
    private int x; // x-coordinate of player position
    private int y; // y-coordinate of player position

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp() {
        y-=3;
    }

    public void moveDown() {
        y+=3;
    }

    public void moveLeft() {
        x-=3;
    }

    public void moveRight() {
        x+=3;
    }
}
