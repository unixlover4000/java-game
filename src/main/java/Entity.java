import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity {
    protected double x;
    protected double y;
    private final int size;

    protected Entity(double x, double y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }

    public boolean intersects(Entity other) {
        return bounds().intersects(other.bounds());
    }

    public Position center() {
        return new Position(x + size / 2.0, y + size / 2.0);
    }

    public abstract void draw(Graphics2D graphics);
}
