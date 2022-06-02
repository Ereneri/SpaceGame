package Main;

import java.util.ArrayList;

public class Explosion {
    public int x;
    public int y;
    public int exploded = 0;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        this.exploded = 0;
    }

    //adds one to the counter
    public void tick() {
        exploded++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
