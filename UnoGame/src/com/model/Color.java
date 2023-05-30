package com.model;

public enum Color {
// Each card should have a color (red, blue, green, or yellow) 
	Red, Blue, Green, Yellow;

	private static final Color[] colors = Color.values();

	public static Color getColor(int i) {
		return Color.colors[i];
	}
}
