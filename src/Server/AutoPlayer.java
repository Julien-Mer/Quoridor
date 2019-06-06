package Server;

import java.awt.Color;
import java.net.Socket;

import Client.*;
import Game.Square;

public class AutoPlayer extends Player {
	
	Board board;

	/**
	 * 
	 * @param board
	 */
	public AutoPlayer(String nom, Color color, Socket socket, Square position, int barriers, Board board) {
		super(nom, color, socket, position, barriers);
		this.board = board;
	}

	public void play() {
		
	}

	/**
	 * 
	 * @param square
	 */
	public void placeBarrier(Square square) {

	}

	/**
	 * 
	 * @param square
	 */
	public void movePlayer(Square square) {

	}

}