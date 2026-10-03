import java.awt.Color;
import java.awt.Graphics2D;

public final class Coin extends Entity {
    private static final int SIZE = 18;
    private boolean collected;

    public Coin(Position position) {
        super(position.x(), position.y(), SIZE);
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    @Override
    public void draw(Graphics2D g) {
        if (collected) return;
        g.setColor(new Color(255, 211, 63));
        g.fillOval((int) x, (int) y, SIZE, SIZE);
        g.setColor(new Color(255, 246, 170));
        g.drawOval((int) x + 3, (int) y + 3, SIZE - 6, SIZE - 6);
    }
}
