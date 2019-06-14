package Server;

import java.io.Serializable;

import Game.Square;

public class Board implements Serializable {

	/**
	 * The grid of the game
	 */
	private Square[][] grid;
	
	/**
	 * Constructor of Board
	 */
	public Board() {
		this.grid = new Square[10][10];
		for(int y = 0; y < 10; y++)
			for(int x = 0; x < 10; x++) 
				this.grid[x][y] = new Square(x, y);
	}

	/**
	 * Check if the barriers can be placed
	 * @return boolean if the barriers can be placed
	 */
	public boolean checkBarriers(Square barrier, int orientation) {
		return false;
	}
	
	public static Square getFirstPosition(Board board, int nbrPlayers, int numberPlayer) {
		Square position = null;
			if(numberPlayer == 0)
				if(numberPlayer % 2 != 0 && numberPlayer == 0) // Si c'est impair et ce sera donc le seul
					position = board.getGrid()[(int)(board.getGrid().length/nbrPlayers*2)-1][1];
				else 
					position = board.getGrid()[(int)(board.getGrid().length/nbrPlayers-1)][1];
			else if(numberPlayer == 1)
					position = board.getGrid()[(int)(board.getGrid().length/nbrPlayers-1)][board.getGrid()[0].length-2];
			else if(numberPlayer == 2)
					position = board.getGrid()[(int)(board.getGrid().length/nbrPlayers*2-1)][1];
			else if(numberPlayer == 3)
				position = board.getGrid()[(int)(board.getGrid().length/nbrPlayers*2-1)][board.getGrid()[0].length-2];
		return position;
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