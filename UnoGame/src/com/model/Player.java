package com.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
//Define a class representing a player in the game. Each player should have a unique identifier, a name, and a hand of cards.
	private String identifier;
	private String name;
	private List<Card> hand;

	// Receive a set of initial cards at the start of the game.
	public void receiveInitialCardsAtStarting(List<Card> cards) {
		hand.addAll(cards);
	}

	// Draw a card from the deck.
	public void drawCardFromDeck(Deck deck) {
		Card card = deck.drawCard();
		if (card != null)
			hand.add(card);
	}

	// Play a card from their hand.
	public Card playCardWithTheirHand(int card) {
		return hand.remove(card);
	}
	// Receive and apply the effects of special cards (e.g., skip, reverse).

	// Check if the player has any valid cards to play.
	public boolean hasValidCardToPlay(Card currentCard) {
		for (Card card : hand) {
			if (card.getColor() == currentCard.getColor() || card.getNumber() == currentCard.getNumber()
					|| card.isSpecialActionCard() || card.isSpecialWildActionCards()) {
				return true;
			}
		}
		return false;
	}

	// Settingup the constructor
	public Player(String identifier, String name) {
		this.identifier = identifier;
		this.name = name;
		hand = new ArrayList<>();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
}
