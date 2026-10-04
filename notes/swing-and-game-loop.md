# Swing, Timers, and the Game Loop

## What is Swing?

**Swing** is part of Java's standard library for creating desktop windows and
interfaces.

The game uses:

- `JFrame` for the application window.
- `JPanel` as the surface where the game is drawn.
- `Timer` to update the game repeatedly.

## Creating the window

```java
JFrame window = new JFrame("Space Game");
window.add(new Game());
window.pack();
window.setVisible(true);
```

`JFrame` is the window. The `Game` panel is placed inside it.

## The timer and game loop

```java
private final Timer timer = new Timer(16, this::tick);
```

The timer calls `tick()` approximately every 16 milliseconds, or about 60
times per second.

```java
private void tick(ActionEvent ignored) {
    if (!gameOver) update();
    repaint();
}
```

Each tick:

1. Updates movement, bullets, aliens, coins, and collisions.
2. Requests the screen to be drawn again.

This repeated process is called the **game loop**.

## Keyboard actions

The game maps keys to actions:

```java
getInputMap(WHEN_IN_FOCUSED_WINDOW)
    .put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "shoot-down");
```

When the space key is pressed, Swing runs the matching action and updates the
`Input` object.

## Why use `repaint()`?

`repaint()` asks Swing to call `paintComponent()` again. The game does not draw
once and stop; it redraws many times as positions change.

