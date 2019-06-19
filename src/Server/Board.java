package Server;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import Game.*;

public class Board implements Serializable {

	/**
	 * The size of the grid
	 */
	public final static int SIZE = 9;
	
	/**
	 * Number of barriers for a player
	 */
	public final static int BARRIERS = 10;
	
	/**
	 * The grid of the game
	 */
	private Square[][] grid;
	
	/**
	 * Constructor of Board
	 */
	public Board() {
		this.grid = new Square[SIZE*2][SIZE*2];
		for(int y = 0; y < SIZE*2; y++)
			for(int x = 0; x < SIZE*2; x++) {
				this.grid[x][y] = new Square(x, y);
				this.grid[x][y].setColor(Color.WHITE);
			}
		this.getGrid()[0][2].setColor(ColorSquare.RED);
		this.getGrid()[2][0].setColor(ColorSquare.GREEN);
		this.getGrid()[0][3].setColor(ColorSquare.BARRIER);
		System.out.println("---------POSSSIBLE:-----------");
		for(Square s: this.getMovementsPossible(this.grid[0][0]))
			s.setColor(ColorSquare.YELLOW);
	}

	/**
	 * Check if a player can move
	 * @param position the initial position
	 * @param position the new position
	 * @return boolean if a player can move
	 */
	public boolean canMove(Square current, Square expected) {
		return getMovementsPossible(current).contains(expected);
	}
	
	/**
	 * Get all the movements possible
	 * @param current the current square
	 * @return all the possibilities
	 */
	public ArrayList<Square> getMovementsPossible(Square current) {
		ArrayList<Square> possibilities = new ArrayList<Square>();
		possibilities.addAll(localPossibilites(current, 1, 0));
		possibilities.addAll(localPossibilites(current, -1, 0));
		possibilities.addAll(localPossibilites(current, 0, 1));
		possibilities.addAll(localPossibilites(current, 0, -1));
		return possibilities;
	}
	
	public ArrayList<Square> localPossibilites(Square current, int incX, int incY) {
		ArrayList<Square> localPossibilities = new ArrayList<Square>();
		int x = current.getX();
		int y = current.getY();
		
		// [0, 0]
		if(checkPos(x+1*incX, y+1*incY) && this.grid[x+1*incX][y+1*incY].getColor().equals(ColorSquare.FREE))// [1, 0] pas de case de barrière
			if(checkPos(x+2*incX, y+2*incY) && this.grid[x+2*incX][y+2*incY].getColor().equals(ColorSquare.FREE)) // [2, 0] case jouable vide
				localPossibilities.add(this.grid[x+2*incX][y+2*incY]); // Aucun joueur, case voisine [2,0]
			else if(checkPos(x+3*incX, y+3*incY) && checkPos(x+4*incX, y+4*incY) && this.grid[x+3*incX][y+3*incY].getColor().equals(ColorSquare.FREE) && this.grid[x+4*incX][y+4*incY].getColor().equals(ColorSquare.FREE) ) // [2, 0] avec un joueur et [3,0] et [4,0] libres	
				localPossibilities.add(this.grid[x+4*incX][y+4*incY]); // Case après un joueur sans barrière ou joueur [4, 0]
			else { // [2,0] avec un joueur et [3,0] avec barrière ou [4,0] avec joueur
				if(checkPos(x+2*incX+incY, y+2*incY+incX) && this.grid[x+2*incX+incY][y+2*incY+incX].getColor().equals(ColorSquare.FREE)) { // Pas de barrière en [2,1]
					if(checkPos(x+2*incX+incY, y+2*incY+2*incX) && this.grid[x+2*incX+2*incY][y+2*incY+2*incX].getColor().equals(ColorSquare.FREE)) // Case [2,2] libre
						localPossibilities.add(this.grid[x+2*incX+2*incY][y+2*incY+2*incX]); // Case [2,2] car joueur ou barriere en [3,0] ou [4,0]
				}
				if(checkPos(x+2*incX-incY, y+2*incY-incX) && this.grid[x+2*incX-incY][y+2*incY-incX].getColor().equals(ColorSquare.FREE)) { // Pas de barrière en [2,-1]
					if(checkPos(x+2*incX-2*incY, y+2*incY-2*incX) && this.grid[x+2*incX-2*incY][y+2*incY-2*incX].getColor().equals(ColorSquare.FREE)) // Case [2,-2] libre
						localPossibilities.add(this.grid[x+2*incX-2*incY][y+2*incY-2*incX]); // Case [2,-2] car joueur ou barriere en [3,0] ou [4,0]
				}
			
			}
		return localPossibilities;
	}
	
	/**
	 * Check if the positions are in the grid
	 * @param x the x position
	 * @param y the y position
	 * @return a boolean if the positions are in the grid
	 */
	public boolean checkPos(int x, int y) {
		return x >= 0 && x < this.grid.length && y >= 0 && y < this.grid[0].length;
	}
	
	/**
	 * Check if the barriers can be placed
	 * @return boolean if the barriers can be placed
	 */
	public boolean checkBarriers(Square barrier1, Square barrier2) {
		return false;
	}
	
	public static Square getFirstPosition(Board board, int nbrPlayers, int numberPlayer) {
		int coeff = 1;
		if(nbrPlayers == 4)
			coeff = 3;
		else if(nbrPlayers == 2)
			coeff = 2;
		Square position = null;
			if(numberPlayer == 0)
					position = board.getGrid()[(int)(board.getGrid().length/coeff)-1][0];
			else if(numberPlayer == 1)
					position = board.getGrid()[(int)(board.getGrid().length/coeff)-1][board.getGrid()[0].length-1];
			else if(numberPlayer == 2)
				if(nbrPlayers % 2 != 0) // Si c'est impair et ce sera donc le seul
					position = board.getGrid()[(int)(board.getGrid().length/2)-1][0];
				else 
					position = board.getGrid()[(int)(board.getGrid().length/coeff)*2-1][0];
			else if(numberPlayer == 3)
				position = board.getGrid()[(int)(board.getGrid().length/coeff)*2-1][board.getGrid()[0].length-1];
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