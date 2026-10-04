# Records, Enums, and `switch`

## Records

A **record** is a compact class for storing data.

```java
public record Position(double x, double y) {
}
```

Java automatically provides a constructor and access methods:

```java
Position position = new Position(100, 200);
double x = position.x();
double y = position.y();
```

`Position` does not need complex behavior, so a record is a good fit.

## Enums

An **enum** defines a small list of named choices:

```java
public enum Direction {
    UP, DOWN, LEFT, RIGHT
}
```

This is safer and clearer than passing strings such as `"up"` or `"left"`.

## `switch`

`Input` uses `switch` to choose which direction changed:

```java
switch (direction) {
    case UP -> up = pressed;
    case DOWN -> down = pressed;
    case LEFT -> left = pressed;
    case RIGHT -> right = pressed;
}
```

The arrow syntax is a modern Java form of `switch`. Only the matching case is
run.

