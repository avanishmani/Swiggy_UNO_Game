package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	/*
	 * Define a class representing the deck of cards used in the game. The deck
	 * should consist of a standard UNO deck with colored cards (numbered 0-9) and
	 * special action cards (skip, reverse, draw two).
	 */
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		// Everytime we create an object of Class Deck it will initialized new Set of
		// Cards.
		initializeDeck();
	}

//Initialize the deck with the standard set of cards
	private void initializeDeck() {
		for (Color color : Color.values()) {
			for (int number = 0; number < 10; number++) {
				cards.add(new Card(CardPattern.NumberedCards, color, number));
				if (number != 0) {
					cards.add(new Card(CardPattern.NumberedCards, color, number));
				}
			}
			for (int i = 0; i < 2; i++) {
				cards.add(new Card(CardPattern.Skip, color, null));
				cards.add(new Card(CardPattern.Reverse, color, null));
				cards.add(new Card(CardPattern.Draw2, color, null));
				cards.add(new Card(CardPattern.Wild, null, null));
			}
		}
		for (int i = 0; i < 4; i++) {

			cards.add(new Card(CardPattern.Wild4, null, null));
		}
	}

//Shuffle the cards.
	public void shuffle() {
		Collections.shuffle(cards);
	}

//Draw a card from the deck.
	public Card drawCard() {
		if (cards.isEmpty())
			return null;
		return cards.remove(cards.size() - 1);
	}
}
