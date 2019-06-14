package Server;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.LinkedList;

import Client.*;
import Game.Square;
import Model.ServerCommunication;

public class Server implements Serializable {

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
	public Server(int nbPlayers, int nbAutoPlayers) {
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
			AutoPlayer autoPlayer = new AutoPlayer(AutoPlayer.getName(i), Player.getColor(i), null, Board.getFirstPosition(this.board, nbPlayers, i), this.board);
			timeLine.add(autoPlayer);
		}
	}

	/**
	 * Starts the game
	 */
	public void start() {
		System.out.println("STAAART");
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
	 * Refresh infos for the players
	 */
	public void refreshInfos() {
		try {
			ServerCommunication.sendDataAll(ServerCommunication.getBoardPacket(), this.timeLine); // On lui dit qu'on va lui envoyer des informations sur le tour
			ServerCommunication.sendObjectAll(this.board.getGrid(), this.timeLine); // On lui donne la carte
			ServerCommunication.sendDataAll(ServerCommunication.getTurnPacket(), this.timeLine); // On lui dit qu'on va lui envoyer des informations sur le tour
			ServerCommunication.sendObjectAll(this.timeLine.get(0), this.timeLine); // On lui dit qui doit jouer
		} catch (Exception e) {
			 e.printStackTrace(); 
		}
		
	}

	
	/**
	 * Add a new player to the game
	 * @param player the new player
	 * @param socket the socket of the player
	 */
	public Player newPlayer(String name, Socket socket) {
		Player player = new Player(name, Player.getColor(timeLine.size()), socket, Board.getFirstPosition(this.board, this.nbPlayers, timeLine.size()));
		try {
			ServerCommunication.sendData(ServerCommunication.getNewPlayerPacket(), player.getSocket()); // On lui dit qu'on va lui envoyer son objet joueur
			ServerCommunication.sendObject(player, player.getSocket()); // On lui envoie son objet
			timeLine.add(player);
			refreshInfos();
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