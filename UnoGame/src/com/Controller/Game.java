package com.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Card;
import com.model.CardPattern;
import com.model.Color;
import com.model.Deck;

import com.model.Player;

public class Game {
	// Define a class representing the game itself. The game class should manage the
	// flow of the game and handle player turns.
	private List<Player> players;
	private Deck deck;
	private int currentPlayerIndex;
	private int direction;
	private Card currentCard;
	private Player winner;

	public Game(List<Player> players) {
		this.players = players;
		this.deck = new Deck();
		this.currentPlayerIndex = 0;
		this.direction = 1;
		this.currentCard = null;
		this.winner = null;
	}
// Start the game by initializing the deck, distributing cards to players, and determining the first player.

	public void startGame() {
		// Shuffle the deck and distribute initial cards to players.
		deck.shuffle();
		for (Player player : players) {
			List<Card> initialCards = new ArrayList<>();
			for (int i = 0; i < 7; i++)
				initialCards.add(deck.drawCard());
			player.receiveInitialCardsAtStarting(initialCards);
		}

		// Determine the first player and the first card.
		Card firstCard = null;
		while (firstCard == null || firstCard.isSpecialActionCard() || firstCard.isSpecialWildActionCards()) {
			firstCard = deck.drawCard();
		}

		// Set the current card and start the game.
		currentCard = firstCard;
		System.out.println("CurrentCard that going to start the game :-" + currentCard.getCardPattern() + "  :-   "
				+ currentCard.getColor());
	}

	// Handle the order of turns, taking into account skips and reverses

	public void nextTurn() {
		// Move to the next player based on the direction of play.
		int nextPlayerIndex = (currentPlayerIndex + players.size() + direction) % players.size();

		// Check if the next player has any valid cards to play.
		boolean nextPlayerHasValidCardsToPlay = false;
		while (!nextPlayerHasValidCardsToPlay) {
			nextPlayerHasValidCardsToPlay = true;

			// If the current card is a skip card or a draw two card,
			// apply its effect on the next player.
			if (currentCard.getCardPattern() == CardPattern.Skip) {
				nextPlayerIndex += players.size() + direction % players.size();
			} else if (currentCard.getCardPattern() == CardPattern.Draw2) {
				nextPlayerIndex += players.size() + direction % players.size();
				for (int i = 0; i < 2; i++)
					players.get(nextPlayerIndex).drawCardFromDeck(deck);
			}

			// Check if the next player has any valid cards to play.
			nextPlayerHasValidCardsToPlay = players.get(nextPlayerIndex).hasValidCardToPlay(currentCard);

			// If not, draw a card from the deck and check again.
			if (!nextPlayerHasValidCardsToPlay) {
				players.get(nextPlayerIndex).drawCardFromDeck(deck);
				nextPlayerHasValidCardsToPlay = players.get(nextPlayerIndex).hasValidCardToPlay(currentCard);
			}
		}

		// Set the current player index to the index of the next player.
		currentPlayerIndex = nextPlayerIndex;
	}

	// Validate and apply card plays, including checking if a card is a valid play
	// based on the current card in play.
	public boolean playTurn(int playerIndex, Integer cardIndex) {
		// Check if it's the current player's turn.
		if (playerIndex != currentPlayerIndex)
			return false;
		// If no card index is provided,
		// it means that the player cannot play any valid cards and must draw a card
		// from the deck.
		if (cardIndex == null) {
			players.get(playerIndex).drawCardFromDeck(deck);
			return true;
		}

		// Check if the played card is a valid card based on the current card in play.
		Card playedCard = players.get(playerIndex).playCardWithTheirHand(cardIndex);
		if (playedCard.getColor() != currentCard.getColor()&& playedCard.getNumber() != currentCard.getNumber() && !playedCard.isSpecialActionCard()
				&& !playedCard.isSpecialWildActionCards()) {
			// If not, return the card to the player's hand and return false.
			players.get(playerIndex).getHand().add(playedCard);
			return false;
		}

		// Set the current card to the played card and apply any special actions.
		currentCard = playedCard;
		if (currentCard.getCardPattern() == CardPattern.Reverse) {
			direction *= -1;
		} else if (currentCard.isSpecialActionCard()&&currentCard.getCardPattern().values()[0]==CardPattern.Wild) {
			Scanner scanner = new Scanner(System.in);
	        System.out.print("Choose a color (Red, Blue, Green, Yellow): ");
	        String color = scanner.nextLine();
	        currentCard.setColor(Color.valueOf(color));
		}else if (currentCard.isSpecialActionCard()&&currentCard.getCardPattern().values()[0]==CardPattern.Draw2) {
			 int nextPlayerIndex = (currentPlayerIndex + players.size() + direction) % players.size();
		        for (int i = 0; i < 2; i++) players.get(nextPlayerIndex).drawCardFromDeck(deck);
		}else if (currentCard.isSpecialActionCard()&&currentCard.getCardPattern().values()[0]==CardPattern.Skip) {
	        int nextPlayerIndex = (currentPlayerIndex + players.size() + direction) % players.size();
	        currentPlayerIndex = nextPlayerIndex;
	    }

		// Check for win conditions.such as a player running out of cards.
		// Check for win conditions.
		if (players.get(playerIndex).getHand().isEmpty()) {
			winner = players.get(playerIndex);
			return true;
		}

		return true;
	}

	// Declare a winner at the end of the game.
	public Player getWinner() {
		return winner;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Card getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	private Player getPlayerById(String id) {
		for (Player player : players) {
			if (player.getIdentifier().equals(id))
				return player;
		}
		return null;
	}
}
