package Client;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.JFrame;

import Client.*;
import Client.Controllers.*;
import Client.Controllers.Helpers.Functions;
import Client.Views.*;
import Game.*;
import Model.*;
import Server.Player;

public class Client extends DataListener {
	
	/**
	 * Allow the controllers to use the client
	 */
	public static Client client;
	
	/**
	 * The current view showed
	 */
	public static JFrame view;
	
	/**
	 * All the controllers used by the client
	 */
	private GameController game;
	
	/**
	 * Adresse IP du serveur
	 */
	private final static String IP = "127.0.0.1";
	
	/**
	 * Port du serveur
	 */
	private final static int PORT = 1111;
	
	/**
	 * Boolean if the client needs to display the game on a GUI
	 */
	private boolean terminal;

	/**
	 * Sends the data to the server
	 * @param data the data to send
	 */
	public void sendData(String data) {

	}

	public void newGame(int players, int bots, String name, int difficulty) {
		try {
			ClientCommunication.sendData(BasicCommunication.NEW_GAME_PREFIX, ClientCommunication.getNewGamePacket(bots, players, name, difficulty), this);
		} catch (IOException e) {
			System.out.println("Impossible de créer la partie");
		}
	}
	
	/**
	 * Send a save of the game to the server
	 * @param object Serialization of the game
	 */
	public void save(Object object) {

	}

	/**
	 * Constructor of the client
	 * @throws Exception if the server is unavailable
	 */
	public Client() throws Exception {
		super(ClientCommunication.initialize(IP, PORT));
		new HomeController();
		this.client = this;
		DataListener listener = this;
		new Thread( new Runnable() {
	        public void run()  {
	        	listener.listenSocket();
	        	
	        }
	    } ).start();
		new Thread( new Runnable() {
	        public void run()  {
	        	analyzeData(listener);
	        }
	    } ).start();
		this.terminal = true;
	}
	
	/**
	 * Analyzes the data and send a command to the client
	 * @param data the date to analyze
	 * @param client the client to send command
	 */
	public static void analyzeData(DataListener listener) {
		while(listener.getSocket() != null) { // Tant que le joueur est connecté
			Entry<Character, Object> entry = listener.getFirstDataReceived();
			if(entry != null) {
				Player player = null;
				try {
					switch(entry.getKey()) { // On s'intéresse au préfixe
						case BasicCommunication.PLAYER_PREFIX: // On reçoit notre joueur
							player = (HumanPlayer) entry.getValue();
							player.setListener(((DataListener)listener));
							if(client.getPlayer() == null)
								if(!client.terminal) {
									JFrame oldView = client.view;
									client.game = new GameController();
									Functions.changeView(oldView, client.game.getView());
								} else
									((HumanPlayer)player).showInfos();
							client.setPlayer(player);
							break;
						case BasicCommunication.BOARD_PREFIX: // On reçoit le tableau à jour
							Square[][] grid = (Square[][]) entry.getValue();
							if(client.terminal)
								((HumanPlayer)client.getPlayer()).showGrid(grid);
							else 
								client.game.updateGame(grid);
							break;
						case BasicCommunication.TURN_PREFIX: // On reçoit la timeLine
							LinkedList<Player> players = (LinkedList<Player>) entry.getValue();
							if(players.get(0).getColor().equals(client.getPlayer().getColor())) {
								if(client.terminal)
									new Thread( new Runnable() {
								        public void run()  {
								        	((HumanPlayer)client.getPlayer()).play();
								        }
								    } ).start();
								else 
									new Message("C'est à votre tour de jouer");
							} else 
								if(client.terminal) 
									System.out.println("C'est au joueur: " + players.get(0).getName() + " de jouer");
								else 
									client.game.updatePlayers(players);
							break;
						case BasicCommunication.END_GAME_PREFIX:
							player = (Player) entry.getValue();
							if(client.terminal)
								System.out.println("Le joueur: " + player.getName() + " a gagné la partie !");
							else
								new Message("Le joueur: " + player.getName() + " a gagné la partie !");
							break;
						case BasicCommunication.MESSAGE_PREFIX:
							if(client.terminal)
								System.out.println((String)entry.getValue());
							else
								new Message((String)entry.getValue());
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					System.out.println("Une erreur est survenue lors de l'analyse: " + ex.toString());
				}
			} else {
				try {
					Thread.sleep(50); // Economie d'energie
				} catch (InterruptedException e) { } 
			}
			
		}
	}
	
}
