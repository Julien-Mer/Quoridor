package Game;

import java.awt.Color;

public class Square {

	private int x;
	private int y;
	private Color color;

	/**
	 * Constructor of Square
	 * @param x horizontal position of the square
	 * @param y vertical position of the square
	 */
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Changes the color of the square
	 * @param color the color of the square
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Gets the color of the Square
	 * @return the color of the square
	 * */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Gets the X position of the square
	 * @return the x position
	 * */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gets the Y position of the square
	 * @return the y position
	 * */
	public int getY() {
		return this.y;
	}

}
