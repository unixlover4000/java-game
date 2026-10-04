# Methods, Parameters, and Return Values

## What is a method?

A **method** is a named block of code that performs one job.

```java
public void reset() {
    score = 0;
    lives = 3;
}
```

Calling `reset()` runs that code.

## Parameters

**Parameters** are values given to a method:

```java
public void update(Input input, int width, int height, int topBar)
```

This method receives keyboard input and screen dimensions so it can move the
spaceship while keeping it inside the game window.

## Return values

Some methods send a value back to the caller:

```java
public boolean intersects(Entity other) {
    return bounds().intersects(other.bounds());
}
```

The caller can use the returned answer:

```java
if (bullet.intersects(saucer)) {
    // The bullet hit the saucer
}
```

Methods that do not return values use `void`. See the [`voids`](voids) note for
a detailed explanation.

## Why split code into methods?

Small methods are easier to read, test, and change. For example, `Game` has
separate methods for:

- `reset()` - starting a new game.
- `update()` - changing the game state.
- `paintComponent()` - drawing the game.
- `drawHud()` - drawing the score and lives.
- `bindMovement()` - connecting a key to movement.

