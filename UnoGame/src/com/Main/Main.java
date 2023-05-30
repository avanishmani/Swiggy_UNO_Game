package com.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Controller.Game;

import com.model.Card;
import com.model.CardPattern;
import com.model.Player;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of players from the user.
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
if(numPlayers>10||numPlayers<2) {
	System.out.println("Number of Player should be in between the range of 2 to 10");
	System.out.println("Please re-Run the Game and enter the Valid player number ");
	Main.main(args);
}
        // Create a list of players.
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            players.add(new Player(Integer.toString(i), name));
        }

        // Create a new game with the list of players.
        Game game = new Game(players);

        // Start the game.
        game.startGame();

        // Play turns until a winner is declared.
        while (game.getWinner() == null) {
            Player currentPlayer = game.getCurrentPlayer();
            List<Card> hand = currentPlayer.getHand();
            System.out.println(currentPlayer.getName() + "'s turn. Your hand:");
          for (int i = 0; i < hand.size(); i++) {
          Card card = hand.get(i);
          if(card.getNumber()!=null)
          System.out.println(i + ": " + card.getColor() + " " + card.getNumber());
          else if(card.getCardPattern().values().length!=0 && card.getCardPattern().values()[0]==CardPattern.Wild4)
          	System.out.println(i + ": " +  " " + card.getCardPattern());	
          else
          	System.out.println(i + ": " +card.getColor() + " " + card.getCardPattern());
      }
//            System.out.println("Current player: " + currentPlayer.getName());
//            System.out.println("Current card: " + game.getCurrentCard().toString());
//            System.out.println("Your hand: " + currentPlayer.getHand().toString());

            System.out.print("Enter card index to play or 'draw' to draw a card: ");
            String input = scanner.nextLine();
            if (input.equals("draw")) {
                currentPlayer.drawCardFromDeck(game.getDeck());
            } else {
                int cardIndex = Integer.parseInt(input);
                if (cardIndex < 0 || cardIndex >= currentPlayer.getHand().size()) {
                    System.out.println("Invalid card index!");
                }
                Card card = currentPlayer.getHand().get(cardIndex);
                if (!game.playTurn(Integer.parseInt(currentPlayer.getIdentifier()),cardIndex)) {
                    System.out.println("Invalid play!");
                }
            }

            game.nextTurn();
        }

        // Retrieve the winner at the end of the game.
        Player winner = game.getWinner();
        if (winner != null) {
            System.out.println(winner.getName() + " wins!");
        } else {
            System.out.println("No winner.");
        }

        scanner.close();
    }
}

