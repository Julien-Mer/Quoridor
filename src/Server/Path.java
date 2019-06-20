package Server;

import java.util.ArrayList;
import java.util.HashMap;

import Game.Square;

public class Path {

	
	/**
	 * Get the shortest path to win
	 * @param player the player concerned
	 * @param board the board of the game
	 * @return the shortest path in an arraylist of Square
	 */
	public static ArrayList<Square> getShortestPath(Player player, Board board) {
		paths = new HashMap<Square, ArrayList<Square>>();
		forbidden = new ArrayList<Square>();
		ArrayList<Square> path = new ArrayList<Square>();
		path.add(player.getPosition());
		ArrayList<Thread> threads = new ArrayList<Thread>();
		paths.put(player.getPosition(), new ArrayList<Square>());

		for(Square possibility : board.getMovementsPossible(player.getPosition())) {
			Thread t = (new Thread( new Runnable() {
		        public void run()  {
		        	ArrayList<Square> result = getShortestPathRec(possibility, board, board.positionToWin(player), (ArrayList<Square>)path.clone());	
			        if(result.size() > 0 && result.size() < paths.get(player.getPosition()).size() || result.size() > 0 && paths.get(player.getPosition()) == null) { // C'est une solution
		        		paths.put(player.getPosition(), result);
		        	}
		        }
			}));
			threads.add(t);
		    t.start();
		}
		boolean threadsStopped = true;
		for(Thread thread : threads) 
			if(thread.isAlive())
				threadsStopped = false;
		while(!threadsStopped) {
			for(Thread thread : threads) 
				if(thread.isAlive())
					threadsStopped = false;
			try { Thread.sleep(20); } catch (InterruptedException e) { } // On pause celui là pour économiser des ressources
		}
		System.out.println("STOP");
		return paths.get(player.getPosition()); // On a rien trouvé
	}
	
	public static HashMap<Square, ArrayList<Square>> paths;
	public static ArrayList<Square> forbidden;
	
	
	public static boolean hasForbiddenSquare(ArrayList<Square> path) {
		boolean res = false;
		for(Square f : forbidden) {
			if(path.contains(f))
				res = true;
		}
		return res;
	}
	
	/**
	 * Get the shortest path to win
	 * @param square the square concerned
	 * @param board the board of the game
	 * @param posToWin the position to win the game
	 * @return the shortest path in an arraylist of Square
	 */
	public static ArrayList<Square> getShortestPathRec(Square current, Board board, int posToWin, ArrayList<Square> path) {
		if(current.getY() == posToWin) { // Il a trouvé un chemin !
			return path;
		}
		path.add(current);
		paths.put(current, new ArrayList<Square>());
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		if(board.getMovementsPossible(current).size() == 0) {
			System.out.println("Nothing");
			forbidden.add(current);
			return new ArrayList<Square>(); // On a rien trouvé
		}
		if(hasForbiddenSquare(path))
			return new ArrayList<Square>(); // Ce path est donc inutile
		
		for(Square possibility : board.getMovementsPossible(current)) {
			if(!path.contains(possibility)) { // Si on ne vient pas de cette case
				Thread t = (new Thread( new Runnable() {
			        public void run()  {
			        	ArrayList<Square> result = getShortestPathRec(possibility, board, posToWin, (ArrayList<Square>)path.clone());
			        	if(result.size() > 0 && result.size() <= paths.get(current).size()) { // C'est une solution
			        		paths.put(current, result);
			        	}
			        }  
			    }));
				threads.add(t);
			    t.start();
			}
		}
		boolean threadsStopped = true;
		for(Thread thread : threads) 
			if(thread.isAlive())
				threadsStopped = false;
		while(!threadsStopped) {
			for(Thread thread : threads) 
				if(thread.isAlive())
					threadsStopped = false;
			try { Thread.sleep(3); } catch (InterruptedException e) { } // On pause celui là pour économiser des ressources
		}
		if(paths.get(current).size() > 0)
			return paths.get(current); // On retourne le chemin le plus court !
		else
			return new ArrayList<Square>(); // On a rien trouvé
	}
	
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