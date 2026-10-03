import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public final class Spaceship extends Entity {
    private static final int SIZE = 30;
    private static final double SPEED = 4.5;

    public Spaceship(double x, double y) {
        super(x, y, SIZE);
    }

    public void update(Input input, int width, int height, int topBar) {
        if (input.up()) y -= SPEED;
        if (input.down()) y += SPEED;
        if (input.left()) x -= SPEED;
        if (input.right()) x += SPEED;
        x = Math.max(8, Math.min(width - SIZE - 8, x));
        y = Math.max(topBar + 8, Math.min(height - SIZE - 10, y));
    }

    public void reset(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        int left = (int) x;
        int top = (int) y;
        Polygon ship = new Polygon(
                new int[]{left + SIZE / 2, left + SIZE, left + SIZE - 7, left + 7},
                new int[]{top, top + SIZE, top + SIZE - 7, top + SIZE}, 4);
        g.setColor(new Color(82, 211, 255));
        g.fillPolygon(ship);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        g.drawPolygon(ship);
        g.setColor(new Color(255, 164, 65));
        g.fillOval(left + 11, top + 19, 8, 8);
    }
}
