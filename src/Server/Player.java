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
	private Socket socket;
	
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
	public Player(String name, Color color, Socket socket, Square position, int barriers) {
		this.name = name;
		this.color = color;
		this.socket = socket;
		this.position = position;
		this.barriers = new Square[barriers*2];
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