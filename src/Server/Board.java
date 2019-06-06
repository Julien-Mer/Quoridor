package Server;

import Game.Square;

public class Board {

	Square[][] grid;
	
	public Board() {
		
	}

	public boolean checkBarriers() {
		return false;
	}

	public boolean globalCheckBarriers() {
		return false;
	}

	public Square[][] getGrid() {
		return this.grid;
	}

}