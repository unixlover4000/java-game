import java.awt.Color;
import java.awt.Graphics2D;

public final class Bullet extends Entity {
    private static final int SIZE = 6;
    private static final double SPEED = 8;

    public Bullet(Spaceship spaceship) {
        super(spaceship.center().x() - SIZE / 2.0, spaceship.center().y() - 18, SIZE);
    }

    public void update() {
        y -= SPEED;
    }

    public boolean isOffscreen(int topBar) {
        return y + SIZE < topBar;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 236, 93));
        g.fillRoundRect((int) x, (int) y, SIZE, 14, 4, 4);
    }
}
