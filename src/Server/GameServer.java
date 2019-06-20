package Server;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.LinkedList;

import Client.*;
import Client.Controllers.Listener;
import Game.ColorSquare;
import Game.Square;
import Model.BasicCommunication;
import Model.DataListener;
import Model.ServerCommunication;

public class GameServer implements Serializable {
	
	/**
	 * The board used by the game
	 */
	private Board board;
	
	/**
	 * The TimeLine of players
	 */
	private LinkedList<Player> timeLine;
	
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
		this.timeLine = new LinkedList<Player>();
	}
	
	/**
	 * Return the number places available on the server
	 * @return the number of places
	 */
	public int getPlaces() {
		return this.nbPlayers - this.timeLine.size();
	}
	
	/**
	 * Initializes the server
	 */
	public void initialize() {
		this.board = new Board();
		for(int i = 0; i < nbAutoPlayers; i++) {
			AutoPlayer autoPlayer = new AutoPlayer(AutoPlayer.getName(i), getColor(i), null, Board.getFirstPosition(this.board, nbPlayers,  getColor(i)), this);
			this.timeLine.add(autoPlayer);
		}
	}

	/**
	 * Get the timeLine of the server
	 * @return the timeLine of the server
	 */
	public LinkedList<Player> getTimeLine() {
		return this.timeLine;
	}
	
	/**
	 * Starts the game
	 */
	public void start() {
		System.out.println("Début d'une partie (" + this.nbPlayers  + " joueurs)");
		ServerCommunication.sendDataAll(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("La partie a commencé"), this.timeLine);
		if(this.getTurnPlayer().getListener() == null) // Le premier joueur est un ordinateur
			((AutoPlayer)this.getTurnPlayer()).play();
	}

	/**
	 * Ends the game
	 */
	public void endOfGame() {
		ServerCommunication.sendDataAll(BasicCommunication.END_GAME_PREFIX, this.getTurnPlayer(), this.timeLine);
		 ServerListener.servers.remove(this);
		 for(Player p : this.timeLine)
			try {
				if(p.getListener() != null) { // Si ce n'est pas un ordinateur
					p.getListener().getSocket().close();
					p.getListener().getThread().stop();
					//TODO DECONNEXION
				}
			} catch (IOException e) { } // On détruit le listener de tous les joueurs
	}

	/**
	 * Get the rules of the game
	 * @return the rules of the game
	 */
	public String rules() {
		return "Les règles sont les règles.";
	}

	/**
	 * Creates a server from a save
	 * @param object the save of the server
	 */
	public GameServer(Object object) {
		GameServer server = ((GameServer)object);
		this.board = server.board;
		this.nbAutoPlayers = server.nbAutoPlayers;
		this.timeLine = server.timeLine;
		this.nbPlayers = server.nbPlayers;
	}

	/**
	 * Send a turn message to the player who has to play and sends the timeLine
	 * @throws IOException If the listener is unavailable
	 */
	public void sendTurnInfos() throws IOException {
		if(this.getPlaces() == 0) { // Si le serveur est lancé
			ServerCommunication.sendDataAll(BasicCommunication.TURN_PREFIX, this.timeLine, this.timeLine); // On leur donne la liste des joueurs
		}
	}
	
	/**
	 * Send the player object to the player
	 * @throws IOException If the listener is unavailable
	 */
	public void sendPlayerInfos() throws IOException {
		for(Player p : this.timeLine)
			if(p.getListener() != null) // Si ce n'est pas un ordinateur
				ServerCommunication.sendData(BasicCommunication.PLAYER_PREFIX, p, p.getListener()); // On lui envoie son objet
	}
	
	/**
	 * Refresh infos for the players
	 */
	public void refreshInfos() {
		try {
			sendPlayerInfos();
			ServerCommunication.sendDataAll(BasicCommunication.BOARD_PREFIX, this.board.getGrid(), this.timeLine); // On leur donne la carte
			sendTurnInfos();
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
				res = ColorSquare.BLUE;
				break;
			case 1:
				res = ColorSquare.GREEN;
				break;
			case 2:
				res = ColorSquare.RED;
				break;
			case 3:
				res = ColorSquare.YELLOW;
				break;
		}
		return res;
	}
	
	/**
	 * Add a new player to the game
	 * @param player the new player
	 * @param socket the socket of the player
	 * @param server the server of the player
	 */
	public Player newPlayer(String name, DataListener listener, GameServer server) {
		Player player = new HumanPlayer(name, getColor(timeLine.size()), listener, Board.getFirstPosition(this.board, this.nbPlayers, getColor(timeLine.size())), server);
		timeLine.add(player);
		refreshInfos();
		System.out.println(player.getName() + " a rejoint un serveur (" + this.nbPlayers  + " joueurs)");
		if(timeLine.size() == this.nbPlayers) 
			this.start();
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
	
	/**
	 * Get the player who has to play
	 * @return the player who has to play
	 */
	 public Player getTurnPlayer() {
		 return this.timeLine.get(0);
	 }
	 
	 public void nextTurnPlayer() {
		 if(!this.getBoard().hasWinned(this.getTurnPlayer())) {
			 this.timeLine.add(this.timeLine.remove(0)); // On met le joueur courant à la fin de la timeLine
			 refreshInfos();
			 if(this.getTurnPlayer().getNumberBarriersLeft() == 0 && this.board.getMovementsPossible(this.getTurnPlayer().getPosition()).isEmpty()) {
				 try {
					ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Vous ne pouvez pas jouer donc vous passez votre tour."), this.getTurnPlayer().getListener());
				} catch (IOException e) { }
				nextTurnPlayer();
			 } else if(this.getTurnPlayer().getListener() == null) // C'est un ordinateur
				 this.getTurnPlayer().play();
		 } else {
			 this.endOfGame();
		 }
		 System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
	 }
	 
	 /**
	  * Get the board used by the server
	  * @return the board of the server
	  */
	 public Board getBoard() {
		 return this.board;
	 }
}