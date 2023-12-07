# Bouncy Asteroids Game

## Description
Bouncy Asteroids is a game developed in Java. The player's objective is to dodge and destroy asteroids while navigating through different levels of difficulty. The game also features enemy ships and special props that add to the complexity and fun of the game.

## Features
- Multiple levels with increasing difficulty
- Enemy ships that fire at the player
- Special props that provide power-ups
- High score tracking

## Installation
1. Clone the repository: `git clone <repository-url>`
2. Open the project in your preferred IDE.
3. Run the `ApplicationStart.java` file to start the game.

## Usage
After starting the game, you can:
- Press 'N' to start a new game.
- Press 'A' to see the controls.
- Press 'H' to see the high scores.
- Press 'X' to exit the game.

## Controls
- `UP Arrow`: Accelerate
- `LEFT Arrow`: Rotate Left
- `RIGHT Arrow`: Rotate Right
- `Space Bar`: Fire

## Code Structure
The game is structured into several classes, each responsible for a specific aspect of the game:

- `Player`: Handles player-related properties and actions such as movement, firing bullets, and collision detection.
- `EnemyShip`: Handles enemy ship properties and actions.
- `Asteroids`: Handles asteroid properties and actions.
- `Bullet`: Handles bullet properties and actions.
- `Level`: Handles level properties and actions.
- `Swarm`: Handles the swarm of asteroids and enemy ships in each level.
- `BouncyAsteroidsGame`: The main game class that ties everything together.

## Project Structure
- `src/si/model`: Contains the main game logic classes.
- `src/si/display`: Contains classes for game display and user interaction.
- `README.md`: This file.

## Dependencies
- JavaFX: For the game's graphical user interface.
- JUnit: For running unit tests (if any).

## Testing
To run tests, right-click on the test class or method in IntelliJ IDEA and select 'Run'.

## Appraisal

In this section, I will evaluate how well my project meets the provided specifications.

- **Design and Cohesion**: The game is structured into several classes, each responsible for a specific aspect of the game. This design ensures that each class has a single responsibility, making the code easier to understand and maintain.

- **Input**: The game handles user input effectively through the `PlayerListener` class. This class listens for key presses and releases, and updates the game state accordingly.

- **Display**: The game's display is managed by the `GameScreen` class. This class is responsible for drawing the game state onto the screen.

- **Menu**: The game's menu is implemented in the `MenuScreen` class. This class displays the menu and handles user interactions with the menu.

- **Asteroids**: The `Asteroids` class handles the properties and actions of the asteroids in the game. The movement and collision detection of the asteroids are implemented effectively in this class.

- **Difficulty**: The difficulty of the game increases as the player progresses through the levels. This is implemented in the `Level` class, which initializes each level with a different number of asteroids and enemy ships.

- **Movement of Player**: The player's movement is handled by the `Player` class. This class updates the player's position based on user input.

- **Movement of Asteroids**: The movement of the asteroids is handled by the `Asteroids` class. This class updates the position of each asteroid in each frame of the game.

- **Movement of Aliens**: The movement of the aliens is handled by the `EnemyShip` class. This class updates the position of each alien in each frame of the game.

Please note that this appraisal is based on the current state of the project. Future improvements may include adding more levels, implementing power-ups, and improving the user interface.

## Contributors
- Chen Ziang

## Contact
For any queries or help related to the project, please reach out at `960015420@qq.com`.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.