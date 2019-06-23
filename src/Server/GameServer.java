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
	 * The difficulty level of AutoPlayers
	 */
	private int difficulty;
	
	/**
	 * If the learning mode is enabled
	 */
	private boolean pedagoMode;
	
	/**
	 * The turns passed since the start of the game
	 */
	private int turns;
	
	/**
	 * Constructor of Server
	 * @param nbPlayers the number of players
	 */
	public GameServer(int nbPlayers, int nbAutoPlayers, int difficulty) {
		System.out.println("Création d'un serveur difficulté " + difficulty + " (" + nbPlayers  + " joueurs, " + nbAutoPlayers + " ordinateurs)");
		this.nbPlayers = nbPlayers;
		this.nbAutoPlayers = nbAutoPlayers;
		this.timeLine = new LinkedList<Player>();
		this.turns = 0;
		if(difficulty ==0) {
			this.pedagoMode = true;
			difficulty = 1;
		}
		this.difficulty = difficulty;
	}
	
	/**
	 * Return the boolean if the learning mode is enabled
	 * @return the learning mode
	 */
	public boolean getPedagoMode() {
		return this.pedagoMode;
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
			AutoPlayer autoPlayer = new AutoPlayer(AutoPlayer.getName(i), getColor(i), null, Board.getFirstPosition(this.board, nbPlayers,  getColor(i)), this, this.difficulty);
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
	 * Check if everybody is disconnected
	 * @return if the game is empty or not
	 */
	public boolean isEmptyGame() {
		int disconnected = 0;
		for(Player p : this.timeLine)
			if(p.getListener() == null || p.getListener() != null && p.getListener().getSocket().isClosed())
				disconnected++;
		return disconnected == (this.timeLine.size());
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
		if(this.pedagoMode) {
			String message = "Eh bien, ce n'est pas trop tôt !\n"
					+ "Essayez d'affronter d'autres intelligences artificielles plus corriaces en cliquant sur Nouvelle partie, dans l'accueil !\n";
			ServerCommunication.sendDataAll(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket(message), this.timeLine);
		 }
		ServerCommunication.sendDataAll(BasicCommunication.END_GAME_PREFIX, this.getPreviousPlayer(), this.timeLine);
		 ServerListener.servers.remove(this);
		 try { Thread.sleep(2000); } catch (InterruptedException e1) { } // On attend que les joueurs soient bien notifiés de la fin
		 for(Player p : this.timeLine)
			try {
				if(p.getListener() != null) // Si ce n'est pas un ordinateur
					p.getListener().getSocket().close(); // On détruit le listener de tous les joueurs
			} catch (IOException e) { }
	}

	/**
	 * Get the rules of the game
	 * @return the rules of the game
	 */
	public String rules() {
		String res = "/***** Déroulement du jeu *****/\n" +
				"Le but de ce jeu est d'arrivée sur la ligne de départ de son adversaire en se déplaçant tour à tour. De plus, il est possible de placer des barrières pour gêner son adversaire.\n" +
				"Le jeu s'arrête quand un des joueurs est arrivé sur la ligne d'en face, qui est donc le vainqueur.\n" +
				"/***** Placer les barrières *****/\n" +
				"Vous pouvez placer une barrière n'importe où sur le plateau de jeu, de plus celle-ci sera donc de taille 2.\n" +
				"Par contre, il est interdit de bloquer totalement son adversaire avec les barrières. En effet, il y aura une vérification à chaque pose de barrières pour vérifier cette règle.\n" + 
				"-----> Bon jeu sur Quoridor ! (*-*) <-----\n";
		return res;
	}

	/**
	 * Refresh infos for the players
	 */
	public void refreshInfos() {
		try {
			if(this.timeLine.get(0).getListener() != null && this.timeLine.get(0).getListener().getSocket().isClosed())
				this.nextTurnPlayer(); // Si ce n'est pas un ordinateur et qu'il est déconnecté
			
			for(Player p : this.timeLine)
				if(p.getListener() != null && !p.getListener().getSocket().isClosed()) // Si ce n'est pas un ordinateur et qu'il est connecté
					ServerCommunication.sendData(BasicCommunication.PLAYER_PREFIX, p, p.getListener()); // On lui envoie son objet
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
		System.out.println(player.getName() + " a rejoint un serveur (" + this.nbPlayers  + " joueur(s))");
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
	 
	 /**
	 * Get the player who has played before
	 * @return the player who has played before
	 */
	 public Player getPreviousPlayer() {
		 return this.timeLine.get(timeLine.size()-1);
	 }
	 
	 /**
	  * Change the turn of the timeLine
	  */
	 public void nextTurnPlayer() {
		 this.turns++;
		 this.timeLine.add(this.timeLine.remove(0)); // On met le joueur courant à la fin de la timeLine
		 refreshInfos();
		 if(!this.getBoard().hasWinned(this.getPreviousPlayer())) {
			 if(this.getTurnPlayer().getNumberBarriersLeft() == 0 && this.board.getMovementsPossible(this.getTurnPlayer().getPosition(), true).isEmpty()) {
				 try {
					ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Vous ne pouvez pas jouer donc vous passez votre tour."), this.getTurnPlayer().getListener());
				} catch (IOException e) { }
				nextTurnPlayer();
			 } else if(this.getTurnPlayer().getListener() == null) // C'est un ordinateur
				 this.getTurnPlayer().play();
			 if(this.pedagoMode) {
				 String message = null;
				 if(turns < this.getNbPlayers()) { // Premier tour
					 message = "<html><u>Bienvenue dans Quoridor !</u><html>\n"
					 		+ "Le but du jeu est d'arriver sur la ligne en face de vous !\n"
					 		+ "Pour ce faire, il vous suffit de vous déplacer en cliquant sur les cases grises.\n"
					 		+ "Vous ne pouvez effectuer une action que pendant votre tour de jeu, vérifiez le message qui s'affiche au dessus de la grille.";
				 } else if(turns < 2*this.getNbPlayers()) {
					 message = "Maintenant que vous savez vous déplacer, avez-vous remarqué ces traits noirs ?\n"
						 	+ "Il s'agit de barrières, vous pouvez aussi en poser, cependant elles ne doivent pas bloquer entièrement l'adversaire.\n"
						 	+ "Essayez de poser une barrière devant un joueur, vous pouvez voir ses coordonnées sur un des côtés de la grille.\n"
						 	+ "Tout d'abord, cliquez sur le bouton: Placer une barrière, en bas à droite. Ensuite vous pouvez entrer les coordonnées du joueur ainsi que la direction de la barrière.";
				 } else if(turns < 3*this.getNbPlayers()) {
					 message = "Il va falloir user d'intelligence pour arriver le premier au bout de la grille\n"
							+ "Evitez de vous piéger vous même et réutilisez les barrières de l'adversaire contre lui.\n";
				 }
				 if(message != null) {
					 try {
						ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket(message), this.getTurnPlayer().getListener());
					 } catch (IOException e) { }
				 }
			 }
		 } else {
			 this.endOfGame();
		 }
	 }
	 
	 /**
	  * Get the board used by the server
	  * @return the board of the server
	  */
	 public Board getBoard() {
		 return this.board;
	 }
	 
	 /**
	  * Get the difficulty of the bots
	  * @return the difficulty level of the bots
	  */
	 public int getDifficulty() {
		 return this.difficulty;
	 }
}