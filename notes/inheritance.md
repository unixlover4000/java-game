# Inheritance and Polymorphism

## What is inheritance?

Inheritance lets one class reuse another class's data and methods.

```java
public final class Spaceship extends Entity
```

This means a spaceship **is an** entity. It automatically gets the common
entity features:

```java
public Rectangle bounds()
public boolean intersects(Entity other)
public Position center()
```

`Coin`, `Bullet`, and `FlyingSaucer` also extend `Entity`.

## The parent class

`Entity` contains behavior shared by all game objects:

```java
public abstract class Entity {
    protected double x;
    protected double y;

    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }
}
```

Without inheritance, every object would need to duplicate collision code.

## Abstract methods

`Entity` declares:

```java
public abstract void draw(Graphics2D graphics);
```

This says every child class must provide its own drawing code.

For example:

```java
@Override
public void draw(Graphics2D g) {
    // Draw a spaceship
}
```

The spaceship and saucer look different, so each class implements `draw()` in
its own way.

## `@Override`

`@Override` tells Java that a method is replacing a method from the parent
class. Java can then warn us if we accidentally use the wrong method name or
parameters.

