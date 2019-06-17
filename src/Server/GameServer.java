package Server;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.LinkedList;

import Client.*;
import Game.Square;
import Model.BasicCommunication;
import Model.ServerCommunication;
import Model.ServerListener;

public class GameServer implements Serializable {

	/**
	
	/**
	 * The board used by the game
	 */
	private Board board;
	
	/**
	 * The TimeLine of players
	 */
	private LinkedList<Player> timeLine;
	
	/**
	 * The number of places available
	 */
	private int nbPlaces;
	
	/**
	 * The number of players
	 */
	private int nbPlayers;
	
	/**
	 * The number of AutoPlayers
	 */
	private int nbAutoPlayers;
	
	/**
	 * Constructor of Server
	 * @param nbPlayers the number of players
	 */
	public GameServer(int nbPlayers, int nbAutoPlayers) {
		System.out.println("Création d'un serveur (" + nbPlayers  + " joueurs, " + nbAutoPlayers + " ordinateurs)");
		this.nbPlayers = nbPlayers;
		this.nbAutoPlayers = nbAutoPlayers;
		this.nbPlaces = this.nbPlayers - this.nbAutoPlayers - 1;
		this.timeLine = new LinkedList<Player>();
	}
	
	/**
	 * Return the number places available on the server
	 * @return the number of places
	 */
	public int getPlaces() {
		return this.nbPlaces;
	}
	
	/**
	 * Initializes the server
	 */
	public void initialize() {
		this.board = new Board();
		for(int i = 0; i < nbAutoPlayers; i++) {
			AutoPlayer autoPlayer = new AutoPlayer(AutoPlayer.getName(i), getColor(i), null, Board.getFirstPosition(this.board, nbPlayers, i), this.board);
			timeLine.add(autoPlayer);
		}
	}

	/**
	 * Starts the game
	 */
	public void start() {
		System.out.println("Début d'une partie (" + this.nbPlayers  + " joueurs)");
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
	public GameServer(Object object) {

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
	 * Refresh infos for the players
	 */
	public void refreshInfos() {
		try {
			ServerCommunication.sendDataAll(BasicCommunication.BOARD_PREFIX, this.board.getGrid(), this.timeLine); // On lui donne la carte
			ServerCommunication.sendDataAll(BasicCommunication.TURN_PREFIX, this.timeLine.get(0), this.timeLine); // On lui dit qui doit jouer
		} catch (Exception e) {
			 e.printStackTrace(); 
		}
		
	}
	
	/**
	 * Get the color of the player
	 * @param i the number of the player
	 * @return the color of the player
	 */
	public static Color getColor(int i) {
		Color res = null;
		switch(i) {
			case 0:
				res = Color.BLUE;
				break;
			case 1:
				res = Color.GREEN;
				break;
			case 2:
				res = Color.RED;
				break;
			case 3:
				res = Color.YELLOW;
				break;
		}
		return res;
	}

	
	/**
	 * Add a new player to the game
	 * @param player the new player
	 * @param socket the socket of the player
	 */
	public Player newPlayer(String name, ServerListener listener) {
		Player player = new HumanPlayer(name, getColor(timeLine.size()), listener, Board.getFirstPosition(this.board, this.nbPlayers, timeLine.size()));
		try {
			ServerCommunication.sendData(BasicCommunication.NEW_PLAYER_PREFIX, player, player.getListener()); // On lui envoie son objet
			timeLine.add(player);
			refreshInfos();
			System.out.println(player.getName() + " a rejoint un serveur (" + this.nbPlayers  + " joueurs)");
			if(timeLine.size() == this.nbPlayers) 
				this.start();
		} catch (IOException e) {
			System.out.println("Impossible d'ajouter le joueur à la partie");
		}
		this.nbPlaces = this.nbPlayers - this.timeLine.size();
		return player;
	}

	/**
	 * Get the number of players
	 * @return the number of players
	 */
	public int getNbPlayers() {
		return this.nbPlayers;
	}
	
	/**
	 * Get the number of players
	 * @return the number of players
	 */
	public int getNbAutoJoueurs() {
		return this.nbAutoPlayers;
	}

}