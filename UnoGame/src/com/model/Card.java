package com.model;

public class Card {
	// Define a class representing a single card in the UNO deck. Each card should
	// have a color (red, blue, green, or yellow) and a value.

	private CardPattern cardPattern;
	private Color color;
	private Integer number;

	public Card(CardPattern cardPattern, Color color, Integer number) {

		this.cardPattern = cardPattern;
		this.color = color;
		this.number = number;
	}

	// Determine if the card is a special action card (e.g., skip, reverse, draw
	// two).

	public boolean isSpecialActionCard() {
		/*
		 * These cards have special actions associated with them and can affect
		 * gameplay. - Skip Card: When played, the next player in turn is "skipped," and
		 * their turn is skipped. - Reverse Card: When played, the direction of play is
		 * reversed, so the order of turns is reversed. - Draw Two Card: When played,
		 * the next player must draw two cards from the deck and skip their turn. - Wild
		 * Card: This card allows the player to choose the color that continuesthe game.
		 * It does not have a specific number and can be played on any card.
		 */

		return cardPattern == cardPattern.Skip || cardPattern == cardPattern.Reverse || cardPattern == cardPattern.Draw2
				|| cardPattern == cardPattern.Wild;
	}

	// Special Wild Action Cards
	public boolean isSpecialWildActionCards() {
		/*
		 * - These cards introduce additional actions and choices to the game. - Wild
		 * Draw Four Card: Similar to the Wild Card, the player gets to choose the
		 * color. In addition, the next player must draw four cards from the deck and
		 * skip their turn. However, this card can only be played if the player does not
		 * have a card of the current color.
		 */
		return cardPattern == cardPattern.Wild4;
	}

//Get and set the cardPattern of the card.
	public CardPattern getCardPattern() {
		return cardPattern;
	}

	public void setCardPattern(CardPattern cardPattern) {
		this.cardPattern = cardPattern;
	}
//Get and set the color and value of the card.

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
