package Server;

import java.util.ArrayList;

import Game.Square;

public class Path {

	/**
	 * The best path
	 */
	private ArrayList<Square> bestPath;
	
	/**
	 * The path currently tested
	 */
	private ArrayList<Square> newPath;
	
	/**
	 * The best number of moves
	 */
	private int best;

	/**
	 * Get the best path to move
	 * @param ori the origin of the move
	 * @param dest the destination of the move
	 */
	public ArrayList<Square> getPath(Square ori, Square dest) {
		return null;
	}

	/**
	 * Recursive method to get the best path to move
	 * @param ori the origin of the move
	 * @param dest the destination of the move
	 */
	public void getPathRec(Square ori, Square dest) {
		
	}

}