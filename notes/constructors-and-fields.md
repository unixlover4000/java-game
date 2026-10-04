# Constructors and Fields

## Fields

A **field** is a variable belonging to an object or class.

`Entity` stores an object's position:

```java
protected double x;
protected double y;
```

Every spaceship, bullet, coin, and saucer has its own `x` and `y` values.

The game also has fields for its state:

```java
private int score;
private int lives;
private boolean gameOver;
```

## Constructors

A **constructor** runs when an object is created. It usually gives the object
its starting data.

```java
public Spaceship(double x, double y) {
    super(x, y, SIZE);
}
```

This constructor receives a starting position and passes it to the parent
`Entity` constructor.

Objects are created with `new`:

```java
new Coin(randomPosition());
new FlyingSaucer(spawnPosition());
```

## `this`

`this` means “the current object.”

```java
public void setShooting(boolean shooting) {
    this.shooting = shooting;
}
```

The parameter and field have the same name. `this.shooting` means the field,
while `shooting` by itself means the parameter.

## Why constructors and fields are useful

They allow every object to remember its own state. A saucer remembers its
position, an explosion remembers its age, and a coin remembers whether it was
collected.

