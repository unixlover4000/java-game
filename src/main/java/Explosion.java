import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class Explosion {
    private final Position center;
    private int age;

    public Explosion(Position center) {
        this.center = center;
    }

    public void update() {
        age++;
    }

    public boolean isFinished() {
        return age >= 24;
    }

    public void draw(Graphics2D g) {
        int radius = 8 + age * 2;
        int alpha = Math.max(0, 220 - age * 9);
        g.setColor(new Color(255, 160, 45, alpha));
        g.fillOval((int) center.x() - radius, (int) center.y() - radius, radius * 2, radius * 2);
        g.setColor(new Color(255, 238, 120, alpha));
        g.setStroke(new BasicStroke(3));
        g.drawOval((int) center.x() - radius, (int) center.y() - radius, radius * 2, radius * 2);
    }
}
