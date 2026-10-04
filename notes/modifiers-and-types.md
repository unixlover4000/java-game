# Modifiers and Common Java Types

## Access modifiers

Access modifiers control who can use a class member.

```java
public void update() { }
private int score;
protected double x;
```

- `public` means other classes can use it.
- `private` means only the current class can use it.
- `protected` allows the class and its child classes to use it.

The game keeps most internal data `private` so other classes cannot change it
accidentally.

## `static`

`static` means something belongs to the class rather than to one object:

```java
private static final int WIDTH = 720;
```

There is one `WIDTH` constant shared by the game class. It does not belong to a
particular spaceship or saucer.

The `main` method is also static because Java needs to call it before creating a
`Main` object.

## `final`

`final` prevents a variable from being assigned again:

```java
private final Input input = new Input();
```

The `input` reference will always point to the same `Input` object.

`final` classes, such as `Spaceship`, cannot be inherited from. This keeps the
design intentional and simple.

## Common types in the game

| Type | What it stores |
| --- | --- |
| `int` | Whole numbers such as lives and score |
| `double` | Decimal numbers such as positions and speed |
| `boolean` | `true` or `false`, such as `gameOver` |
| `String` | Text such as `"Space Game"` |
| `Color` | A drawing color |
| `Rectangle` | Position and size used for collisions |

Choosing an appropriate type helps Java catch mistakes and makes the code
easier to understand.
