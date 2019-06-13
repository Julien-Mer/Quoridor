package Server;

import java.io.Serializable;
import java.net.Socket;
import java.util.LinkedList;

import Client.*;

public class Server implements Serializable {

	/**
	 * The board used by the game
	 */
	private Board board;
	
	/**
	 * Maximum number of barriers
	 */
	private int NB_BARRIERS;
	
	/**
	 * The TimeLine of players
	 */
	private LinkedList<Player> timeLine;
	
	/**
	 * The number of players
	 */
	private int nbJoueurs;

	/**
	 * Constructor of Server
	 * @param nbPlayers the number of players
	 */
	public Server(int nbPlayers) {

	}

	/**
	 * Starts the game
	 */
	public void start() {

	}

	/**
	 * Ends the game
	 */
	public void endOfGame() {

	}

	/**
	 * Get the rules of the game
	 * @return the rules of the game
	 */
	public String rules() {
		return null;
	}

	/**
	 * Creates a server from a save
	 * @param object the save of the server
	 */
	public Server(Object object) {

	}

	/**
	 * Send a save of Server to the player
	 * @param client the client to send the save
	 */
	public void sendSave(Socket client) {

	}

	/**
	 * Moves the player
	 * @param player the player concerned
	 */
	public void movePlayer(Player player) {

	}

	/**
	 * Add a player to the game
	 * @param socket the socket of the player
	 */
	public void addPlayer(Socket socket) {

	}

	/**
	 * Get the number of players
	 * @return the number of players
	 */
	public int getNbJoueurs() {
		return this.nbJoueurs;
	}

}