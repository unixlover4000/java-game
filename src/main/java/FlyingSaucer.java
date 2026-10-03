import java.awt.Color;
import java.awt.Graphics2D;

public final class FlyingSaucer extends Entity {
    private static final int SIZE = 30;
    private static final double SPEED = 1.25;

    public FlyingSaucer(Position position) {
        super(position.x(), position.y(), SIZE);
    }

    public void chase(Spaceship target, java.util.List<FlyingSaucer> saucers, int width, int height, int topBar) {
        double targetX = target.bounds().getCenterX();
        double targetY = target.bounds().getCenterY();
        double centerX = x + SIZE / 2.0;
        double centerY = y + SIZE / 2.0;
        double distance = Math.hypot(targetX - centerX, targetY - centerY);
        if (distance > 0) {
            x += (targetX - centerX) / distance * SPEED;
            y += (targetY - centerY) / distance * SPEED;
        }
        for (FlyingSaucer other : saucers) {
            if (other == this) continue;
            double dx = centerX - other.x - SIZE / 2.0;
            double dy = centerY - other.y - SIZE / 2.0;
            double separation = Math.hypot(dx, dy);
            if (separation > 0 && separation < 58) {
                x += dx / separation * 1.6;
                y += dy / separation * 1.6;
            }
        }
        x = Math.max(8, Math.min(width - SIZE - 8, x));
        y = Math.max(topBar + 8, Math.min(height - SIZE - 10, y));
    }

    @Override
    public void draw(Graphics2D g) {
        int left = (int) x;
        int top = (int) y;
        g.setColor(new Color(222, 76, 104));
        g.fillOval(left, top + 9, SIZE, SIZE / 2);
        g.setColor(new Color(255, 155, 169));
        g.fillOval(left + 8, top, SIZE - 16, SIZE - 10);
        g.setColor(new Color(35, 20, 50));
        g.fillOval(left + 11, top + 5, 8, 5);
    }
}
