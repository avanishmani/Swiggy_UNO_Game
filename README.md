# Swiggy_UNO_Game
Design a class structure for a UNO game that allows multiple players to participate. The game should support the standard rules of UNO, including drawing cards, playing cards, skipping turns, reversing the direction of play, and declaring a winner.
# Uno Game

This is a simple implementation of the classic card game Uno in Java.

## How to Play

To play the game, run the `Main` class. The game will prompt you to enter the number of players and their names. Then it will start the game and continue until a winner is declared.

On each turn, the current player's hand and the top card on the stockpile will be displayed. The current player can then choose to play a card from their hand or draw a card from the deck.

## Code Structure

The code is organized into several classes:

- `Main`: This class contains the `main` method that runs the game.
- `Game`: This class represents a game of Uno. It manages the deck, players, and turns.
- `Player`: This class represents a player in the game. It keeps track of the player's hand and allows them to draw and play cards.
- `Card`: This class represents a card in the game. It has a color and a value.
- `CardPattern`: This enum represents the different values that a card can have.

## Contributing

Contributions to this project are welcome! If you have an idea for an improvement or new feature, feel free to submit a pull request or open an issue.

## Tech Stack

- **Language**: Java17

## Duration

This project was completed in 1 Day.

## Author

This project was created by [Er. Avanish Mani Tripathi](https://github.com/avanishmani).
