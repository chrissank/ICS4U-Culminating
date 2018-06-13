package shooter.entities;

import javafx.geometry.Rectangle2D;

public class Wall {

    int x, y, width, height;
    public Wall(int x1, int y1, int width, int height) {
        this.x = x1;
        this.y = y1;
        this.width = width;
        this.height = height;
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
    
    public Rectangle2D getBounds() {
        Rectangle2D r = new Rectangle2D(x, y, width, height);
        return r;
    }
}
