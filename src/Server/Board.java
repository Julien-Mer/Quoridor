package Server;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

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
		this.grid = new Square[SIZE*2-1][SIZE*2-1];
		for(int y = 0; y < SIZE*2-1; y++)
			for(int x = 0; x < SIZE*2-1; x++) {
				this.grid[x][y] = new Square(x, y);
				this.grid[x][y].setColor(Color.WHITE);
			}
	}

	/**
	 * Get the position to win the game for a player
	 * @param player the player concerned
	 * @return the y position to win the game
	 */
	public int positionToWin(Player player) {
		int res = 0;
		if(player.getColor().equals(ColorSquare.BLUE) || player.getColor().equals(ColorSquare.RED)) // Car pour green et red c'est 0
			res = this.grid[0].length-1;
		return res;
	}
	
	/**
	 * Boolean if the player has winned or not
	 * @param player the player concerned
	 * @return boolean if he has winned
	 */
	public boolean hasWinned(Player player) {
		return player.getPosition().getY() == positionToWin(player);
	}
	
	/**
	 * Check if a player can move
	 * @param position the initial position
	 * @param position the new position
	 * @return boolean if a player can move
	 */
	public boolean canMove(Square current, Square expected) {
		return getMovementsPossible(current, true).contains(expected);
	}
	
	/**
	 * Get all the movements possible
	 * @param current the current square
	 * @param players if it has to take care about players
	 * @return all the possibilities
	 */
	public ArrayList<Square> getMovementsPossible(Square current, Boolean players) {
		ArrayList<Square> possibilities = new ArrayList<Square>();
		possibilities.addAll(localPossibilities(current, players, 1, 0));
		possibilities.addAll(localPossibilities(current, players, -1, 0));
		possibilities.addAll(localPossibilities(current, players, 0, 1));
		possibilities.addAll(localPossibilities(current, players, 0, -1));
		return possibilities;
	}
	
	/**
	 * Get the local possibilities
	 * @param current the current square
	 * @param players if it has to take care about players
	 * @param incX the x incrementation
	 * @param incY the y incrementation
	 * @return an arrayList of the possibilities
	 */
	public ArrayList<Square> localPossibilities(Square current, Boolean players, int incX, int incY) {
		ArrayList<Square> localPossibilities = new ArrayList<Square>();
		int x = current.getX();
		int y = current.getY();
		
		if(players) { // Si on prend en compte les joueurs
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
		} else { // Sans prendre en compte les joueurs
			// [0, 0]
			if(checkPos(x+1*incX, y+1*incY) && this.grid[x+1*incX][y+1*incY].getColor().equals(ColorSquare.FREE))// [1, 0] pas de case de barrière
				if(checkPos(x+2*incX, y+2*incY)) // [2, 0] case jouable
					localPossibilities.add(this.grid[x+2*incX][y+2*incY]); // case voisine [2,0]
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
	public boolean checkBarriers(Square barrier1, Square barrier2, LinkedList<Player> players) {
		boolean res = false;
		Color color1 = barrier1.getColor();
		Color color2 = barrier2.getColor();
		if(!color1.equals(ColorSquare.BARRIER) || !color2.equals(ColorSquare.BARRIER)) // Si ce ne sont pas déjà 2 barrières
			if(Math.abs(barrier1.getX() - barrier2.getX()) == 2 && barrier1.getY() == barrier2.getY() && barrier1.getY() % 2 != 0 || Math.abs(barrier1.getY() - barrier2.getY()) == 2  && barrier1.getX() == barrier2.getX() && barrier1.getX() % 2 != 0) {
				res = true;
				barrier1.setColor(ColorSquare.BARRIER);
				barrier2.setColor(ColorSquare.BARRIER);
				for(Player p : players) 
					if(Path.getShortestPathSize(p.getPosition(), this, this.positionToWin(p), false) == -1)
						res = false;
				barrier1.setColor(color1);
				barrier2.setColor(color2);
			}
		return res;
	}
	
	/**
	 * Get the first position of the player
	 * @param board the board of the player
	 * @param nbrPlayers the number of players
	 * @param numberPlayer the number of the player
	 * @return the position of the player
	 */
	public static Square getFirstPosition(Board board, int nbrPlayers, Color color) {
		int space = 0;
		if(nbrPlayers > 2)
			space = 2;
		Square position = null;
			if(color.equals(ColorSquare.BLUE))
				position = board.getGrid()[(int)(board.getGrid().length/2) + space][0];
			else if(color.equals(ColorSquare.GREEN))
				if(nbrPlayers % 2 != 0) // Si c'est impair et ce sera donc le seul
					position = board.getGrid()[(int)(board.getGrid().length/2)][board.getGrid()[0].length-1];
				else 
					position = board.getGrid()[(int)(board.getGrid().length/2) + space][board.getGrid()[0].length-1];
			else if(color.equals(ColorSquare.RED))
				position = board.getGrid()[(int)(board.getGrid().length/2) - space][0];
			else if(color.equals(ColorSquare.YELLOW))
				position = board.getGrid()[(int)(board.getGrid().length/2) - space][board.getGrid()[0].length-1];
		return position;
	}

	/**
	 * Gets the grid
	 * @return the grid of the Board
	 */
	public Square[][] getGrid() {
		return this.grid;
	}

}