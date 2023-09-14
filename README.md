# Arkanoid Game in Java

## Overview

This is a Java-based implementation of the classic Arkanoid game. The game features a paddle controlled by the player and a ball that bounces off the paddle and bricks. The goal is to break all the bricks without letting the ball touch the ground.

## Features

- **Paddle Movement**: Use keyboard arrows to control the paddle's movement.
- **Ball Physics**: Realistic ball physics for bouncing off the paddle and bricks.
- **Brick Levels**: Different levels of bricks that require a hit to break.
- **Score Tracking**: Keep track of your score as you break bricks and complete levels.
- **Game States**: Start, Pause, and End screens for better game control.
- 
## Compilation and Execution

This project comes with a `build.xml` file for easy compilation and execution.

To compile the code, use the following Ant command:
```bash
ant compile
```

To run the compiled code, use:
```bash
ant run
```

You can specify the sequence of levels
to play by passing arguments to the ant run command like this:
```bash
ant -Dargs="1 3 2 1 9 1 bla 3 3" run
```
The numbers and strings represent the order of levels that will be loaded during the game.
 
## Controls

- **Left Arrow**: Move paddle left
- **Right Arrow**: Move paddle right
- **Space**: Exit the game after loss/win
- **P**: Pause the game
