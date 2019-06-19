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
						case BasicCommunication.NEW_PLAYER_PREFIX: // On va créér un nouveau joueur
							if(client.getPlayer() == null) { // On vérifie que le client n'a pas déjà un joueur
								player = (HumanPlayer) entry.getValue();
								System.out.println("On a reçu le joueur: " + player.getName());
								player.setListener(((DataListener)listener));
								client.setPlayer(player);
								if(!client.terminal) {
									JFrame oldView = client.view;
									client.game = new GameController();
									Functions.changeView(oldView, client.game.getView());
								}
							}
							break;
						case BasicCommunication.BOARD_PREFIX:
							System.out.println("BOARD");
							Square[][] grid = (Square[][]) entry.getValue();
							if(client.terminal)
								showGrid(grid);
							else 
								client.game.updateGame(grid);
							break;
						case BasicCommunication.TURN_PREFIX:
							LinkedList<Player> players = (LinkedList<Player>) entry.getValue();
							if(client.terminal) {
								System.out.println("C'est au joueur: " + players.get(0).getName() + " de jouer");
							} else 
								client.game.updatePlayers(players);
							((HumanPlayer)client.getPlayer()).movePlayer(5, 10);
							break;
						case BasicCommunication.MESSAGE_PREFIX:
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
	
	/**
	 * Displays the grid
	 * grid the grid to display
	 */
	 public static void showGrid(Square[][] grid) {
		 String res = "";
		 System.out.println("start");
		 for(int x = 0; x <= grid[0].length+1; x++) 
			 res += "_";
		 res += "\n";
		 for(int y = 0; y < grid.length; y++) {
			 res += "|";
				for(int x = 0; x < grid[0].length; x++) {
					if(grid[x][y].getColor().equals(ColorSquare.BARRIER))
						res += "X";
					else if(grid[x][y].getColor().equals(ColorSquare.YELLOW))
						res += "Y";
					else if(grid[x][y].getColor().equals(ColorSquare.BLUE))
						res += "B";
					else if(grid[x][y].getColor().equals(ColorSquare.GREEN))
						res += "G";
					else if(grid[x][y].getColor().equals(ColorSquare.RED))
						res += "R";
					else
						res += " ";
				}
		 	res += "|\n";
	 	}
		 for(int x = 0; x <= grid[0].length+1; x++) 
			 res += "_";
		 res += "\n";
		System.out.println(res);
	 }
	
}
