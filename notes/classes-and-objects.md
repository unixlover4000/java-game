# Classes and Objects

## What is a class?

A **class** is a blueprint for an object. It describes:

- Data the object stores.
- Actions the object can perform.

For example, `Spaceship` is a class:

```java
public final class Spaceship extends Entity {
    // spaceship data and methods go here
}
```

## What is an object?

An **object** is a real instance created from a class.

The game creates one spaceship object:

```java
private final Spaceship spaceship = new Spaceship(WIDTH / 2.0, HEIGHT - 80);
```

`new Spaceship(...)` creates the object. The object stores its own position and
can move or draw itself.

## Why use classes in this game?

Each important game thing has its own class:

| Class | Responsibility |
| --- | --- |
| `Game` | Runs the game and coordinates objects |
| `Spaceship` | Moves and draws the player |
| `FlyingSaucer` | Chases and draws an alien |
| `Bullet` | Moves and draws a shot |
| `Coin` | Stores and draws a collectible coin |
| `Explosion` | Displays a temporary explosion |
| `Input` | Stores keyboard state |

This keeps `Game` from having to know every detail of every object.

## Object-oriented idea

The game asks objects to do their own work:

```java
spaceship.update(input, WIDTH, HEIGHT, TOP_BAR);
saucer.chase(spaceship, saucers, WIDTH, HEIGHT, TOP_BAR);
bullet.update();
```

This is easier to understand than keeping all spaceship, saucer, and bullet
logic in one giant method.

