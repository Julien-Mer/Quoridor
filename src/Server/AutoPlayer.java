package Server;

import java.awt.Color;
import java.net.Socket;

import Client.*;
import Game.Square;

public class AutoPlayer extends Player {
	
	/**
	 * The board used by the game
	 */
	Board board;

	/**
	 * Constructor of AutoPlayer
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param socket the socket used
	 * @param position the position of the player
	 * @param barriers the number of barriers of the player
	 * @param board the board used by the game
	 */
	public AutoPlayer(String name, Color color, Square position, int barriers, Board board) {
		super(name, color, null, position, barriers);
		this.board = board;
	}

	/**
	 * The AutoPlayer plays
	 */
	public void play() {
		
	}

	/**
	 * Place a barrier
	 * @param square the square where the barrier is placed
	 * @param orientation the orientation of the barrier
	 */
	public void placeBarrier(Square square, int orientation) {

	}

	/**
	 * Move the player
	 * @param square the new position of the player
	 */
	public void movePlayer(Square square) {

	}

}