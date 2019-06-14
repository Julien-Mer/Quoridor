package Server;

import java.awt.Color;
import java.io.Serializable;
import java.net.Socket;

import Game.Square;

public class Player implements Serializable {

	/**
	 * The color of the player
	 */
	private Color color;
	
	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * The socket used by the player
	 */
	private transient Socket socket; // Transient pour ne pas transmettre les informations aux autres joueurs
	
	/**
	 * The barriers placed by the player
	 */
	private Square[] barriers;
	
	/**
	 * The position of the player
	 */
	private Square position;

	/**
	 * Creates a player
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param socket the socket of the player
	 * @param position the position of the player
	 * @param barriers the maximum of barriers for the player
	 */
	public Player(String name, Color color, Socket socket, Square position) {
		this.name = name;
		this.color = color;
		this.socket = socket;
		this.position = position;
		this.barriers = new Square[10];
	}
	
	/**
	 * Get the color of the player
	 * @param i the number of the player
	 * @return the color of the player
	 */
	public static Color getColor(int i) {
		Color res = null;
		switch(i) {
			case 0:
				res = Color.BLUE;
				break;
			case 1:
				res = Color.GREEN;
				break;
			case 2:
				res = Color.RED;
				break;
			case 3:
				res = Color.YELLOW;
				break;
		}
		return res;
	}
	
	/**
	 * Get the socket used by the player
	 * @return the socket used by the player
	 */
	public Socket getSocket() {
		return this.socket;
	}

	/**
	 * Get the name of the player
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the color of the player
	 * @return the color of the player
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Set the new position of the player
	 * @param square the new position of the player
	 */
	public void setPosition(Square square) {
		this.position = square;
	}

	/**
	 * Get the position of the player
	 * @return the position of the player
	 */
	public Square getPosition() {
		return this.position;
	}

	/**
	 * Add a barrier on a square
	 * @param square the square to place the barrier
	 */
	public void addBarrier(Square square) {
		
	}

}