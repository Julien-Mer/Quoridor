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
import Model.File;
import Server.GameServer;
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
	private final static String IP = "projet-milleetunsourires.com";
	//private final static String IP = "127.0.0.1";
	
	/**
	 * Port du serveur
	 */
	private final static int PORT = 1111;
	
	/**
	 * Boolean if the game is finished
	 */
	public static boolean finished;
	
	/**
	 * Boolean if the client needs to display the game on a GUI
	 */
	private boolean terminal;

	/**
	 * Creates a game
	 * @param players the number of players
	 * @param bots the number of autoplayers
	 * @param name the name of the player
	 * @param difficulty the difficulty of the bots
	 */
	public void newGame(int players, int bots, String name, int difficulty) {
		try {
			ClientCommunication.sendData(BasicCommunication.NEW_GAME_PREFIX, ClientCommunication.getNewGamePacket(bots, players, name, difficulty), this);
		} catch (IOException e) {
			client.showMessage("Impossible de créer la partie", true);
		}
	}
	
	public void newPedagoGame() {
		newGame(3, 2, "Vous", 0);
	}
	
	/**
	 * Send a save of the game to the server
	 * @param object Serialization of the game
	 * @throws IOException if the socket is unavailable
	 */
	public void sendSave(Object object) throws IOException {
		ClientCommunication.sendData(BasicCommunication.SAVE_PREFIX, File.readObject(File.SAVE_FILE), this);
	}
	
	/**
	 * Saves the game in a file and closes the game
	 * @param server the server of the game
	 */
	public void saveGame(GameServer server) {
		File.writeObject(File.SAVE_FILE, server);
		if(view != null)
			view.dispose();
		try {
			this.getSocket().close();
		} catch (IOException e) { }
	}

	/**
	 * Constructor of the client
	 * @throws Exception if the server is unavailable
	 */
	public Client() throws Exception {
		super(ClientCommunication.initialize(IP, PORT));
		Functions.getTheme();
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
	 * Set the terminal boolean
	 * @param terminal the terminal boolean
	 */
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	
	/**
	 * Show a message to the client
	 * @param message the message to show
	 */
	public void showMessage(String message, boolean popup) {
		if(this.terminal) 
			System.out.println(message);
		else if(popup)
			new Message(message);
		else
			((Game)this.game.getView().getComponent()).setStateLbl(message);
	}
	
	/**
	 * Analyzes the data and send a command to the client
	 * @param data the date to analyze
	 * @param client the client to send command
	 */
	public static void analyzeData(DataListener listener) {
		while(listener.getSocket() != null && !listener.getSocket().isClosed()) { // Tant que le joueur est connecté
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
									client.game = new GameController(player.getServer().getNbPlayers() == player.getServer().getNbAutoJoueurs()+1);
									Functions.changeView(oldView, client.game.getView());
								} else {
									try { client.view.dispose(); } catch(Exception ex) { } // On ferme la page de lancement
									((HumanPlayer)player).showInfos();
								}
							client.setPlayer(player);
							
							if(player.getServer().getTimeLine().get(0).getColor().equals(client.getPlayer().getColor()) && player.getServer().getPlaces() == 0) {
								if(!player.getServer().getBoard().hasWinned(player.getServer().getPreviousPlayer())) {
									for(Square possibility : player.getServer().getBoard().getMovementsPossible(player.getPosition(), true))
										possibility.setColor(ColorSquare.POSSIBILITY);
									client.showMessage("C'est à votre tour de jouer", false);
									if(client.terminal) 
										new Thread( new Runnable() {
											public void run()  {
												((HumanPlayer)client.getPlayer()).play();
											}
										} ).start();
								}
							} else 
								if(player.getServer().getPlaces() == 0) {
									client.showMessage("C'est au joueur: " + player.getServer().getTimeLine().get(0).getName() + " de jouer", false);
								} else {
									client.showMessage("En attente de " + player.getServer().getPlaces() + " joueur(s).", false);
								}
							if(client.terminal)
								((HumanPlayer)client.getPlayer()).showGrid(player.getServer().getBoard().getGrid());
							else
								client.game.updateGame(player.getServer().getBoard().getGrid(), player.getServer().getTimeLine());
							break;
						case BasicCommunication.END_GAME_PREFIX:
							player = (Player) entry.getValue();
							if(!Client.client.terminal)
								client.showMessage("Le joueur " + player.getName() + " a gagné la partie !", false);
							client.showMessage("Le joueur " + player.getName() + " a gagné la partie !", true);
							finished = true;
							break;
						case BasicCommunication.MESSAGE_PREFIX:
							client.showMessage((String)entry.getValue(), true);
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
