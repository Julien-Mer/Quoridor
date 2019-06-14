package Server;

import java.awt.Color;
import java.io.Serializable;
import java.net.Socket;

import Client.*;
import Game.Square;

public class AutoPlayer extends Player implements Serializable  {
	
	/**
	 * The board used by the game
	 */
	transient Board board;

	/**
	 * Constructor of AutoPlayer
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param socket the socket used
	 * @param position the position of the player
	 * @param barriers the number of barriers of the player
	 * @param board the board used by the game
	 */
	public AutoPlayer(String name, Color color, Socket socket, Square position, Board board) {
		super(name, color, null, position);
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
	
	/**
	 * Get the name of the bot
	 * @param the id of the bot
	 * @return the name of the bot
	 */
	public static String getName(int i) {
		String res = "";
		switch(i) {
			case 0:
				res = "Pierre";
				break;
			case 1:
				res = "Baptiste";
				break;
			case 2:
				res = "Julien";
				break;
		}
		return res;
	}

}