package Server;

import java.awt.Color;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import Client.*;
import Game.Square;

public class AutoPlayer extends Player implements Serializable  {

	/**
	 * Constructor of AutoPlayer
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param socket the socket used
	 * @param position the position of the player
	 * @param barriers the number of barriers of the player
	 * @param board the board used by the game
	 */
	public AutoPlayer(String name, Color color, Socket socket, Square position, GameServer server) {
		super(name, color, null, position, server);
	}

	/**
	 * The AutoPlayer plays
	 */
	public void play() {
		System.out.println("Jeu de l'IA: " + this.getName());
		System.out.println("-------------------------------");
		ArrayList<Square> path = Path.getShortestPath(this, this.getServer().getBoard());
		for(Square s : path)
			System.out.print("[" + s.getX() + "," + s.getY() + "] ");
		System.out.println("OK");
		this.addBarrier(this.getServer().getBoard().getGrid()[this.getPosition().getX() + 1][this.getPosition().getY() + 1], this.getServer().getBoard().getGrid()[this.getPosition().getX() + 1][this.getPosition().getY() + 2]);
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