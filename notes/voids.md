# Understanding `void` in Java

## What does `void` mean?

`void` means that a method **does not return a value**.

A method can either:

1. Perform an action and return nothing.
2. Calculate something and return a result.

## A method that returns a value

```java
public int getLives() {
    return lives;
}
```

This method returns an `int`, so another part of the program can use its result:

```java
int remainingLives = getLives();
```

## A method that uses `void`

```java
public void reset() {
    score = 0;
    lives = 3;
}
```

`reset()` changes the game state, but there is no useful value to send back. It
simply performs an action.

Call it like this:

```java
reset();
```

Do not assign its result to a variable:

```java
int result = reset(); // Incorrect: reset() returns nothing
```

## Why does this game use `void` so often?

The game is interactive, so many methods **change something or draw something**
instead of calculating and returning a value.

### Starting the program

```java
public static void main(String[] args)
```

The program starts here. Java does not need a result from `main`, so it is
declared as `void`.

### Moving the spaceship

```java
public void update(Input input, int width, int height, int topBar)
```

This method changes the spaceship's position:

```java
x += SPEED;
y -= SPEED;
```

It does not need to return the new position because the spaceship object already
stores that position internally.

### Drawing objects

```java
public void draw(Graphics2D g)
```

This method draws directly onto the screen:

```java
g.fillPolygon(ship);
```

The drawing is the result. There is no value that needs to be returned, so the
method uses `void`.

### Changing keyboard input

```java
public void setShooting(boolean shooting)
```

This changes the `shooting` field:

```java
this.shooting = shooting;
```

It does not need to return anything.

### Updating explosions

```java
public void update()
```

This increases the explosion's age:

```java
age++;
```

The object itself has been changed, so returning a value is unnecessary.

## Methods in the game that return values

The game also has many non-`void` methods.

### `boolean intersects(Entity other)`

Returns either `true` or `false`:

```java
if (bullet.intersects(saucer)) {
    // The bullet hit the saucer
}
```

### `Rectangle bounds()`

Returns a rectangle describing an object's position and size:

```java
return new Rectangle((int) x, (int) y, size, size);
```

### `Position center()`

Returns a `Position` object:

```java
return new Position(x + size / 2.0, y + size / 2.0);
```

### `boolean isFinished()`

Tells the game whether an explosion has finished:

```java
explosions.removeIf(Explosion::isFinished);
```

## A simple comparison

Imagine a vending machine:

```java
public void dispenseDrink() {
    // Gives you a drink
}
```

It performs an action and returns nothing.

```java
public int getPrice() {
    return 2;
}
```

It calculates something and returns a value.

The game follows the same idea:

| Method | Purpose | Return type |
| --- | --- | --- |
| `reset()` | Reset the game | `void` |
| `draw()` | Draw an object | `void` |
| `update()` | Change an object's state | `void` |
| `intersects()` | Check for a collision | `boolean` |
| `bounds()` | Get the collision area | `Rectangle` |
| `center()` | Get an object's position | `Position` |
| `isFinished()` | Check explosion status | `boolean` |

There are many `void` methods because the game is full of operations: moving,
drawing, resetting, firing, and updating.

`void` does not mean that a method is useless. It means that the method's
purpose is to **do something**, rather than give a value back.
