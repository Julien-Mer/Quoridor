package Game;

import java.awt.Color;

public class Square {

	private int x;
	private int y;
	private Color color;
	private int attribute;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}