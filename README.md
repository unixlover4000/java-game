# Space Game

A small Java 2D game built with Swing and the standard JDK.

## How to play

- Move with **WASD** or the **arrow keys**.
- Collect the yellow coins to increase your score.
- Press **Space** to shoot the flying saucers.
- More flying saucers spawn over time and chase you without grouping together.
- You have three lives.
- An explosion appears when a saucer hits your spaceship.
- Press **R** after game over to restart.

## Run with Gradle

```bash
./gradlew run
```

On Windows, use `gradlew.bat run`.

To create a distributable application image:

```bash
./gradlew installDist
build/install/space-game/bin/space-game
```
