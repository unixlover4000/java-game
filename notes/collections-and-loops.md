# Lists, Loops, and Collections

## What is a collection?

A collection stores multiple values. The game uses `List` for groups of
objects:

```java
private final List<Coin> coins = new ArrayList<>();
private final List<FlyingSaucer> saucers = new ArrayList<>();
private final List<Bullet> bullets = new ArrayList<>();
```

The lists can grow and shrink while the game is running.

## Adding objects

```java
coins.add(new Coin(randomPosition()));
saucers.add(new FlyingSaucer(spawnPosition()));
```

## Enhanced `for` loops

This loop visits every saucer:

```java
for (FlyingSaucer saucer : saucers) {
    saucer.chase(spaceship, saucers, WIDTH, HEIGHT, TOP_BAR);
}
```

It means “for each saucer in the saucers list.”

## Removing objects

Collected coins are removed with a method reference:

```java
coins.removeIf(Coin::isCollected);
```

This means “remove every coin for which `isCollected()` returns `true`.”

Bullets and saucers use an `Iterator` because objects are removed while the
lists are being checked:

```java
Iterator<Bullet> bulletIterator = bullets.iterator();
while (bulletIterator.hasNext()) {
    Bullet bullet = bulletIterator.next();
    bulletIterator.remove();
}
```

## Why use lists?

The number of bullets and aliens changes during play. A fixed number of
variables would be awkward, while a list makes spawning and removing objects
natural.

