package Server;

import java.io.Serializable;

import Game.Square;

public class Board implements Serializable {

	/**
	 * The grid of the game
	 */
	Square[][] grid;
	
	/**
	 * Constructor of Board
	 */
	public Board() {
		
	}

	/**
	 * Check if the barriers can be placed
	 * @return boolean if the barriers can be placed
	 */
	public boolean checkBarriers(Square barrier, int orientation) {
		return false;
	}

	/**
	 * Check if the barriers can be placed in a given alignment
	 * @param barrier the square where the barrier is placed
	 * @param orientation the orientation of the barrier
	 * @param incX the incremention of horizontal position
	 * @param incY the incremention of the vertical position
	 * @return if the barriers can be placed
	 */
	public boolean globalCheckBarriers(Square barrier, int orientation, int incX, int incY) {
		return false;
	}

	/**
	 * Gets the grid
	 * @return the grid of the Board
	 */
	public Square[][] getGrid() {
		return this.grid;
	}

}