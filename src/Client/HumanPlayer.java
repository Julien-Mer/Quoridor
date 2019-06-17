package Client;

import java.awt.Color;
import java.io.*;
import Client.Controllers.*;
import Game.*;
import Model.ServerListener;
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
	public HumanPlayer(String name, Color color, ServerListener listener, Square position) {
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
	 * Try to set the position of the player
	 * @param x : the horizontal position
	 * @param y : the vertical position 
	 */
	public void setPosition(int x, int y) {

	}
	
	/**
	 * Displays the grid
	 **/
	public void displayGrid() {

	}

}
