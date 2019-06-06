package Server;

import java.awt.Color;
import java.net.Socket;

import Game.Square;

public class Player {

	private Color color;
	private String nom;
	private Socket socket;
	private Square[] barriers;
	private Square position;

	/**
	 * 
	 * @param nom
	 * @param color
	 * @param socket
	 * @param position
	 * @param barriers
	 */
	public Player(String nom, Color color, Socket socket, Square position, int barriers) {
		this.nom = nom;
		this.color = color;
		this.socket = socket;
		this.position = position;
		this.barriers = new Square[barriers];
	}

	/**
	 * 
	 * @param square
	 */
	public void setPosition(Square square) {
		this.position = square;
	}

	public Square getPosition() {
		return this.position;
	}

	/**
	 * 
	 * @param square
	 */
	public void addBarrier(Square square) {

	}

}