import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public final class Game extends JPanel {
    private static final int WIDTH = 720;
    private static final int HEIGHT = 500;
    private static final int TOP_BAR = 48;
    private static final int COIN_COUNT = 6;
    private static final int STARTING_SAUCERS = 5;
    private static final int MAX_SAUCERS = 12;

    private final Random random = new Random();
    private final Input input = new Input();
    private final List<Coin> coins = new ArrayList<>();
    private final List<FlyingSaucer> saucers = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Explosion> explosions = new ArrayList<>();
    private final Spaceship spaceship = new Spaceship(WIDTH / 2.0, HEIGHT - 80);
    private final Timer timer = new Timer(16, this::tick);

    private int score;
    private int lives;
    private int elapsedFrames;
    private int invulnerabilityFrames;
    private boolean gameOver;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        configureInput();
        reset();
        timer.start();
    }

    private void configureInput() {
        bindMovement(KeyEvent.VK_W, "up", Input.Direction.UP);
        bindMovement(KeyEvent.VK_UP, "up", Input.Direction.UP);
        bindMovement(KeyEvent.VK_S, "down", Input.Direction.DOWN);
        bindMovement(KeyEvent.VK_DOWN, "down", Input.Direction.DOWN);
        bindMovement(KeyEvent.VK_A, "left", Input.Direction.LEFT);
        bindMovement(KeyEvent.VK_LEFT, "left", Input.Direction.LEFT);
        bindMovement(KeyEvent.VK_D, "right", Input.Direction.RIGHT);
        bindMovement(KeyEvent.VK_RIGHT, "right", Input.Direction.RIGHT);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "shoot-down");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "shoot-up");
        getActionMap().put("shoot-down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                input.setShooting(true);
            }
        });
        getActionMap().put("shoot-up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                input.setShooting(false);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "restart");
        getActionMap().put("restart", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (gameOver) reset();
            }
        });
    }

    private void bindMovement(int key, String name, Input.Direction direction) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0, false), name + "-down");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0, true), name + "-up");
        getActionMap().put(name + "-down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                input.set(direction, true);
            }
        });
        getActionMap().put(name + "-up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                input.set(direction, false);
            }
        });
    }

    private void reset() {
        score = 0;
        lives = 3;
        gameOver = false;
        coins.clear();
        saucers.clear();
        bullets.clear();
        explosions.clear();
        elapsedFrames = 0;
        invulnerabilityFrames = 0;
        spaceship.reset(WIDTH / 2.0, HEIGHT - 80);
        for (int i = 0; i < COIN_COUNT; i++) coins.add(new Coin(randomPosition()));
        for (int i = 0; i < STARTING_SAUCERS; i++) saucers.add(new FlyingSaucer(spawnPosition()));
    }

    private Position randomPosition() {
        return new Position(30 + random.nextInt(WIDTH - 60), TOP_BAR + 30 + random.nextInt(HEIGHT - TOP_BAR - 60));
    }

    private Position spawnPosition() {
        return new Position(30 + random.nextInt(WIDTH - 60), TOP_BAR + 15);
    }

    private void tick(ActionEvent ignored) {
        if (!gameOver) update();
        explosions.removeIf(Explosion::isFinished);
        explosions.forEach(Explosion::update);
        repaint();
    }

    private void update() {
        elapsedFrames++;
        if (invulnerabilityFrames > 0) invulnerabilityFrames--;
        spaceship.update(input, WIDTH, HEIGHT, TOP_BAR);
        if (input.shooting() && elapsedFrames % 8 == 0) bullets.add(new Bullet(spaceship));
        bullets.forEach(bullet -> bullet.update());
        bullets.removeIf(bullet -> bullet.isOffscreen(TOP_BAR));
        for (FlyingSaucer saucer : saucers) saucer.chase(spaceship, saucers, WIDTH, HEIGHT, TOP_BAR);
        if (elapsedFrames % 180 == 0 && saucers.size() < MAX_SAUCERS) saucers.add(new FlyingSaucer(spawnPosition()));

        for (Coin coin : coins) {
            if (!coin.isCollected() && coin.intersects(spaceship)) {
                coin.collect();
                score++;
            }
        }
        coins.removeIf(Coin::isCollected);
        while (coins.size() < COIN_COUNT) {
            coins.add(new Coin(randomPosition()));
        }

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<FlyingSaucer> saucerIterator = saucers.iterator();
            while (saucerIterator.hasNext()) {
                FlyingSaucer saucer = saucerIterator.next();
                if (bullet.intersects(saucer)) {
                    saucerIterator.remove();
                    explosions.add(new Explosion(saucer.center()));
                    score += 5;
                    bulletIterator.remove();
                    break;
                }
            }
        }

        if (invulnerabilityFrames == 0) {
            for (FlyingSaucer saucer : saucers) {
                if (saucer.intersects(spaceship)) {
                    explosions.add(new Explosion(spaceship.center()));
                lives--;
                    invulnerabilityFrames = 60;
                    spaceship.reset(WIDTH / 2.0, HEIGHT - 80);
                    if (lives <= 0) gameOver = true;
                    break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(new GradientPaint(0, 0, new Color(18, 30, 68), 0, HEIGHT, new Color(5, 9, 24)));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        drawBackground(g);
        drawHud(g);
        coins.forEach(coin -> coin.draw(g));
        bullets.forEach(bullet -> bullet.draw(g));
        saucers.forEach(saucer -> saucer.draw(g));
        spaceship.draw(g);
        explosions.forEach(explosion -> explosion.draw(g));
        if (gameOver) drawGameOver(g);
        g.dispose();
    }

    private void drawBackground(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, 150));
        for (int i = 0; i < 55; i++) g.fillRect((i * 83 + 21) % WIDTH, (i * 47 + 63) % HEIGHT, 2, 2);
    }

    private void drawHud(Graphics2D g) {
        g.setColor(new Color(5, 9, 24, 220));
        g.fillRect(0, 0, WIDTH, TOP_BAR);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        g.drawString("SPACE GAME", 16, 30);
        g.setFont(new Font("SansSerif", Font.PLAIN, 16));
        g.drawString("Coins: " + score, 310, 30);
        g.drawString("Lives: " + lives + "  |  SPACE: FIRE", 525, 30);
    }

    private void drawGameOver(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 175));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 38));
        String title = "GAME OVER";
        g.drawString(title, (WIDTH - g.getFontMetrics().stringWidth(title)) / 2, 230);
        g.setFont(new Font("SansSerif", Font.PLAIN, 18));
        String message = "Coins: " + score + "  |  Press R to restart";
        g.drawString(message, (WIDTH - g.getFontMetrics().stringWidth(message)) / 2, 270);
    }
}
