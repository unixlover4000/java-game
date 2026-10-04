# Graphics and Collision Detection

## Drawing with `Graphics2D`

`Graphics2D` is Java's drawing tool. The game uses it to draw shapes:

```java
g.fillOval((int) x, (int) y, SIZE, SIZE);
g.fillPolygon(ship);
g.fillRoundRect((int) x, (int) y, SIZE, 14, 4, 4);
```

Different shapes create coins, spaceships, bullets, saucers, and explosions.

## `paintComponent`

Swing calls this method when the panel needs to be drawn:

```java
@Override
protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    // Draw the game here
}
```

Calling `super.paintComponent(graphics)` clears the old frame before the new
frame is drawn.

## Collision rectangles

Every `Entity` can create a `Rectangle` around itself:

```java
public Rectangle bounds() {
    return new Rectangle((int) x, (int) y, size, size);
}
```

Two rectangles can be tested for overlap:

```java
public boolean intersects(Entity other) {
    return bounds().intersects(other.bounds());
}
```

The game uses this for:

- Bullets hitting flying saucers.
- The spaceship collecting coins.
- Flying saucers hitting the spaceship.

## Why use collision detection?

The game needs to know when objects touch. Rectangles are simple, fast, and
good enough for this beginner-friendly game, even though the drawn shapes are
not all rectangles.

