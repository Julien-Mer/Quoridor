package Client;

import java.awt.Color;
import java.io.*;
import Game.*;
import Model.*;
import Server.Player;

public class HumanPlayer extends Player implements Serializable {

	/**
	 * The grid of the game
	 */
	private Square[][] grid;

	/**
	 * Creates a player
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param socket the socket of the player
	 * @param position the position of the player
	 * @param barriers the maximum of barriers for the player
	 */
	public HumanPlayer(String name, Color color, DataListener listener, Square position) {
		super(name, color, listener, position);
	}

	/**
	 * Add a barrier on a square
	 * @param square the square to place the barrier
	 */
	public void addBarrier(Square square) {
		
	}
	
	/**
	 * Updates the board of the game 
	 * @param grid the grid of the game 
	 */
	public void updateBoard(Square[][] grid) {
		System.out.println("Mise à jour du tableau");
	}

	/**
	 * Try to move the player
	 * @param x : the horizontal position
	 * @param y : the vertical position 
	 * @throws IOException Exception if the socket is unavailable
	 */
	public void movePlayer(int x, int y) throws IOException {
		BasicCommunication.sendData(BasicCommunication.MOVE_PREFIX, ClientCommunication.getMovePacket(x, y), this.getListener());
	}
	
	/**
	 * Displays the grid
	 **/
	public void displayGrid() {

	}

}
