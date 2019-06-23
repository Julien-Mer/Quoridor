package Server;

import java.awt.Color;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import Game.ColorSquare;
import Game.Square;

public class Path {

	/**
	 * Get the best barriers to place, to add the most length to the path of the player
	 * @param player the player who wants to place barriers
	 * @param target the player targeted
	 * @param board the board of the game
	 * @param players the players of the game
	 * @param playersObstacle if the players are like barriers or not
	 * @return the best barriers to place
	 */
	public static Entry<Integer, Square[]> getBestBarriers(Player player, Player target, Board board, LinkedList<Player> players, boolean playersObstacle) {
		Square[] barriers = new Square[2];
		int lengthAdded = -1;
		int x = 0, y = 0;
		while(x < board.getGrid().length) {
			y = 0;
			while(y < board.getGrid()[0].length) {
				if(board.checkPos(x, y-2) && (!board.getGrid()[x][y].getColor().equals(ColorSquare.BARRIER) && !board.getGrid()[x][y-2].getColor().equals(ColorSquare.BARRIER))) {
					int localLengthAdded = getAddedLength(board.getGrid()[x][y], board.getGrid()[x][y-2], board, players, target, playersObstacle);
					if(localLengthAdded > lengthAdded && getAddedLength(board.getGrid()[x][y], board.getGrid()[x][y-2], board, players, player, playersObstacle) < localLengthAdded) {
						barriers[0] = board.getGrid()[x][y];
						barriers[1] = board.getGrid()[x][y-2];
						lengthAdded = localLengthAdded;
					}
				}
				if(board.checkPos(x, y+2) && (!board.getGrid()[x][y].getColor().equals(ColorSquare.BARRIER) && !board.getGrid()[x][y+2].getColor().equals(ColorSquare.BARRIER))) {
					int localLengthAdded = getAddedLength(board.getGrid()[x][y], board.getGrid()[x][y+2], board, players, target, playersObstacle);
					if(localLengthAdded > lengthAdded && getAddedLength(board.getGrid()[x][y], board.getGrid()[x][y+2], board, players, player, playersObstacle) < localLengthAdded) {
						barriers[0] = board.getGrid()[x][y];
						barriers[1] = board.getGrid()[x][y+2];
						lengthAdded = localLengthAdded;
					}
				}
				if(board.checkPos(x-2, y) && (!board.getGrid()[x][y].getColor().equals(ColorSquare.BARRIER) && !board.getGrid()[x-2][y].getColor().equals(ColorSquare.BARRIER))) {
					int localLengthAdded = getAddedLength(board.getGrid()[x][y], board.getGrid()[x-2][y], board, players, target, playersObstacle);
					if(localLengthAdded > lengthAdded && getAddedLength(board.getGrid()[x][y], board.getGrid()[x-2][y], board, players, player, playersObstacle) < localLengthAdded) {
						barriers[0] = board.getGrid()[x][y];
						barriers[1] = board.getGrid()[x-2][y];
						lengthAdded = localLengthAdded;
					}
				}
				if(board.checkPos(x+2, y) && (!board.getGrid()[x][y].getColor().equals(ColorSquare.BARRIER) && !board.getGrid()[x+2][y].getColor().equals(ColorSquare.BARRIER))) {
					int localLengthAdded = getAddedLength(board.getGrid()[x][y], board.getGrid()[x+2][y], board, players, target, playersObstacle);
					if(localLengthAdded > lengthAdded && getAddedLength(board.getGrid()[x][y], board.getGrid()[x+2][y], board, players, player, playersObstacle) < localLengthAdded) {
						barriers[0] = board.getGrid()[x][y];
						barriers[1] = board.getGrid()[x+2][y];
						lengthAdded = localLengthAdded;
					}
				} 
				y++;
			}
			x++;
		}
		return new AbstractMap.SimpleEntry<Integer, Square[]>(lengthAdded, barriers);
	}
	
	/**
	 * Get the length added to the path of the player to win, by adding barriers
	 * @param barrier1 the first barrier
	 * @param barrier2 the second barrier
	 * @param board the board of the game
	 * @param players the players of the game
	 * @param player the player concerned
	 * @param playersObstacle if the player are considered like barriers or not
	 * @return the length added to the path
	 */
	public static int getAddedLength(Square barrier1, Square barrier2, Board board, LinkedList<Player> players, Player player, boolean playersObstacle) {
		int res = -1;
		if(board.checkBarriers(barrier1, barrier2, players)) {
			int beforeLength = Path.getShortestPathSize(player.getPosition(), board, board.positionToWin(player), playersObstacle);
			Color color1 = barrier1.getColor();
			Color color2 = barrier2.getColor();
			barrier1.setColor(ColorSquare.BARRIER);
			barrier2.setColor(ColorSquare.BARRIER);
			res = Path.getShortestPathSize(player.getPosition(), board, board.positionToWin(player), playersObstacle) - beforeLength;
			barrier1.setColor(color1);
			barrier2.setColor(color2);
		}
		return res;
	}
	
	/**
	 * Get the random barriers close to the player
	 * @param player the player concerned
	 * @param board the board of the game
	 * @param players the players of the game
	 * @return the random barriers
	 */
	public static Square[] getRandomBarriers(Player player, Board board, LinkedList<Player> players) {
		Square[] barriers = new Square[2];
		barriers = getLocalRandomBarriers(player, board, players, -1, -1);
		if(barriers[0] == null) 
			barriers = getLocalRandomBarriers(player, board, players, 1, 1);
		return barriers;
	}
	
	/**
	 * Get the local random barriers close to the player
	 * @param player the player concerned
	 * @param board the board of the game
	 * @param players the timeLine of the game
	 * @param incX the incrementation of x
	 * @param incY the incrementation of y
	 * @return the random barriers
	 */
	public static Square[] getLocalRandomBarriers(Player player, Board board, LinkedList<Player> players, int incX, int incY) {
		Square[] barriers = new Square[2];
		int x = player.getPosition().getX(), y = player.getPosition().getY();
		
		while(board.checkPos(x, y) && barriers[0] == null) {
			y = player.getPosition().getY();
			while(board.checkPos(x, y) && barriers[0] == null) {
				if(board.checkPos(x, y-2) && board.checkBarriers(board.getGrid()[x][y], board.getGrid()[x][y-2], players)) {
					barriers[0] = board.getGrid()[x][y];
					barriers[1] = board.getGrid()[x][y-2];
				} else if(board.checkPos(x, y+2) && board.checkBarriers(board.getGrid()[x][y], board.getGrid()[x][y+2], players)) {
					barriers[0] = board.getGrid()[x][y];
					barriers[1] = board.getGrid()[x][y+2];
				} else if(board.checkPos(x-2, y) && board.checkBarriers(board.getGrid()[x][y], board.getGrid()[x-2][y], players)) {
					barriers[0] = board.getGrid()[x][y];
					barriers[1] = board.getGrid()[x-2][y];
				} else if(board.checkPos(x+2, y) && board.checkBarriers(board.getGrid()[x][y], board.getGrid()[x+2][y], players)) {
					barriers[0] = board.getGrid()[x][y];
					barriers[1] = board.getGrid()[x+2][y];
				} 
				y += incY;
			}
			x += incX;
		}
		return barriers;
	}
	
	/**
	 * Get the best player in the timeLine excepting a given player
	 * @param players the timeLine of player
	 * @param current the player to exclude
	 * @param board the board of the game
	 * @return the best player excepting the given player
	 */
	public static Player getBestOtherPlayer(LinkedList<Player> players, Player current, Board board) {
		Player bestPlayer = null;
		int min = board.getGrid().length * board.getGrid()[0].length; // On prend une très grande taille relative à la grille
		for(Player p : players) {
			if(p != current) { // Si ce n'est pas le joueur que l'on ne veut pas
				int dist = getShortestPathSize(p.getPosition(), board, board.positionToWin(p), true);
				if(dist < min) {
					min = dist;
					bestPlayer = p;
				}
			}
		}
		return bestPlayer;
	}
	
	/**
	 * Get the shortest path size with a BFS
	 * @param square the first square
	 * @param board the board of the game
	 * @param posToWin the y position to win the game
	 * @param players if the players are considered as walls or not
	 * @return the shortest path size
	 */
	public static int getShortestPathSize(Square square, Board board, int posToWin, boolean players) {
		int res = 0;
		boolean found = false;
		ArrayList<Square> visited = new ArrayList<Square>(); // Les cellules déjà visitées à ne pas réinclure
		visited.add(square); // On ajoute la cellule courante
	
		ArrayList<Square> list = new ArrayList<Square>(); 
		list.addAll(board.getMovementsPossible(square, players)); // On ajoute tous les mouvements possibles
		while(!list.isEmpty() && !found) { // Tant qu'il n'y a plus de mouvements possibles et que le chemin le plus court n'est pas trouvé
			res++; // On incrémente la taille du chemin
			for(Square s : list)
				if(s.getY() == posToWin) // Un des mouvements possibles est la solution
					found = true;
			if(!found) { // Sinon on ajoute les autres mouvements possibles et on mets ceux qu'on avait dans la liste des visités
				ArrayList<Square> newList = new ArrayList<Square>();
				for(Square element : list)
					for(Square possibility : board.getMovementsPossible(element, players))
						if(!visited.contains(possibility))
							newList.add(possibility);
				visited.addAll(list);
				list = newList;
			}
		}
		if(!found) // Renvoie -1 si aucun chemin n'est trouvé
			res = -1;
		return res;
	}
	
	/**
	 * Get the shortest path to win
	 * @param player the player concerned
	 * @param board the board of the game
	 * @param players boolean if the algorithm takes care of players or not
	 * @return the shortest path in an arraylist of Square
	 */
	public static ArrayList<Square> getShortestPath(Player player, Board board, boolean players) {
		int shortestLength = getShortestPathSize(player.getPosition(), board, board.positionToWin(player), players);
		return getShortestPath(player.getPosition(), new ArrayList<Square>(), board, board.positionToWin(player), shortestLength,players);
	}
	
	/**
	 * Get the shortest path to win
	 * @param square the square concerned
	 * @param path the path used before this square
	 * @param board the board of the game
	 * @param posToWin the y position to win the game
	 * @param players boolean if the algorithm takes care of players or not
	 * @param shortestLength the shortest length of the path
	 * @return the shortest path in an arraylist of Square
	 */
	public static ArrayList<Square> getShortestPath(Square square, ArrayList<Square> path, Board board, int posToWin, int shortestLength, boolean players) {
		ArrayList<Square> newPath = (ArrayList<Square>)path.clone();
		newPath.add(square);
		if(square.getY() == posToWin && newPath.size() == shortestLength+1) // Si le chemin trouvé fait bien la taille du plus court chemin
			return newPath;
		
		ArrayList<Square> res = new ArrayList<Square>();
		if(newPath.size() > shortestLength) // On arrête l'algorithme si l'on dépasse le plus court chemin
			return res;
		
		ArrayList<Square> possibilities = new ArrayList<Square>();
		possibilities.addAll(board.getMovementsPossible(square, players)); // On ajoute nos mouvements possibles
		for(Square possibility : possibilities)
			if(!newPath.contains(possibility)) { // Si le mouvement possible n'est pas déjà dans le chemin
				ArrayList<Square> temp = getShortestPath(possibility, newPath, board, posToWin, shortestLength, players); // Récursivité sur cette fonction
				if(temp.size() > 0) // Si un chemin a bien été trouvé
					res = temp;
			}	
		return res;
	}

}