package Server;

import java.awt.Color;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map.Entry;

import Client.*;
import Game.Square;

public class AutoPlayer extends Player implements Serializable  {

	/**
	 * The difficulty of the autoplayer 
	 */
	private int difficulty;
	
	/**
	 * The turn passed since the start of the game
	 */
	private int turn;
	
	/**
	 * Constructor of AutoPlayer
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param socket the socket used
	 * @param position the position of the player
	 * @param barriers the number of barriers of the player
	 * @param board the board used by the game
	 */
	public AutoPlayer(String name, Color color, Socket socket, Square position, GameServer server, int difficulty) {
		super(name, color, null, position, server);
		this.difficulty = difficulty;
		this.turn = 0;
	}

	/**
	 * The AutoPlayer plays
	 */
	public void play() {
		try { Thread.sleep(500); } catch (InterruptedException e) {} // On laisse un petit délai pour que le joueur ait le temps de voir
		this.turn++;
		System.out.println("Jeu de l'IA: " + this.getName() + " niveau: " + this.difficulty);
		ArrayList<Square> path = Path.getShortestPath(this, this.getServer().getBoard(), true);
		Player target = Path.getBestOtherPlayer(this.getServer().getTimeLine(), (Player)this, this.getServer().getBoard());
		ArrayList<Square> targetPath = Path.getShortestPath(target, this.getServer().getBoard(), true);
				
		if(difficulty == 1) { // Si elle est en mode facile
			Square[] barriers = Path.getRandomBarriers(target, this.getServer().getBoard(), this.getServer().getTimeLine());
			if(((int)(Math.random() * 2) == 1 || path.size() == 0) && this.getNumberBarriersLeft() > 0 && barriers[0] != null) { // Soit elle joue ou place une barrière aléatoirement
				this.addBarrier(barriers[0], barriers[1]);
			} else if(path.size() > 1) { // Si on peut bouger
				this.movePlayer(path.get(1)); // On move le player sur un voisin aléatoire
			} else { // On ne peut rien faire donc on passe son tour
				this.getServer().nextTurnPlayer();
			}
		} else if(difficulty == 2) {
			Square[] barriers = Path.getRandomBarriers(target, this.getServer().getBoard(), this.getServer().getTimeLine());
			if((targetPath.size() < path.size() || path.size() == 0) && this.getNumberBarriersLeft() > 0 && barriers[0] != null) { // Soit elle joue ou place une barrière sur l'autre joueur est plus proche ou qu'elle ne peut pas jouer
				this.addBarrier(barriers[0], barriers[1]);
			} else if(path.size() > 1) { // Si on peut bouger
				this.movePlayer(path.get(1)); // On move le player sur un voisin aléatoire
			} else { // On ne peut rien faire donc on passe son tour
				this.getServer().nextTurnPlayer();
			}
		} else if(difficulty == 3) {
			Entry<Integer, Square[]> bestBarriers = Path.getBestBarriers(this, target, this.getServer().getBoard(), this.getServer().getTimeLine(), true);
			System.out.println("best: " + bestBarriers.getKey());
			if(((targetPath.size() < path.size() || bestBarriers.getKey() > 2)|| path.size() == 0) && this.getNumberBarriersLeft() > 0 && bestBarriers.getKey() > 0) { // Soit elle joue ou place une barrière sur l'autre joueur est plus proche ou qu'elle ne peut pas jouer
				this.addBarrier(bestBarriers.getValue()[0], bestBarriers.getValue()[1]);
			} else if(path.size() > 1) { // Si on peut bouger
				this.movePlayer(path.get(1)); // On move le player sur un voisin aléatoire
			} else { // On ne peut rien faire donc on passe son tour
				this.getServer().nextTurnPlayer();
			}
		}
	}
	
	/**
	 * Get the name of the bot
	 * @param the id of the bot
	 * @return the name of the bot
	 */
	public static String getName(int i) {
		String res = "";
		switch(i) {
			case 0:
				res = "Pierre";
				break;
			case 1:
				res = "Baptiste";
				break;
			case 2:
				res = "Julien";
				break;
		}
		return res;
	}

}