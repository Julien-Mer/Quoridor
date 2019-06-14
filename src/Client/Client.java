package Client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import Game.Square;
import Model.ClientCommunication;

public class Client {
	
	/**
	 * Permet aux controllers de s'adresser au client
	 */
	public static Client client;

	/**
	 * The socket used for the communication
	 */
	private Socket socket;

	/**
	 * The player used by the client
	 */
	private HumanPlayer player;
	
	/**
	 * Boolean if the client needs to display the game on a GUI
	 */
	private boolean terminal;

	/**
	 * Creates a player for the game 
	 * @param name the name of the plater
	 * @param color the color of the player
	 * @param position the position of the player 
	 */
	public void createPlayer(String name, Color color, Square position) {
		this.player = new HumanPlayer(color, name, this);
		player.setPosition(position.getX(), position.getY());
	}

	/**
	 * Sends the data to the server
	 * @param data the data to send
	 */
	public void sendData(String data) {

	}

	public void newGame(int players, int bots, String name) {
		try {
			ClientCommunication.sendData(ClientCommunication.getNewGamePacket(bots, players, name), this.socket);
			ClientCommunication.listenData(this); // Lancement de l'écoute
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
	 */
	public Client() {
		try {
			this.socket = ClientCommunication.initialize("127.0.0.1", 1111);
			this.client = client;
			newGame(4, 2, "Test");
		} catch(Exception ex) {
			System.out.println("Impossible de se connecter au serveur");
		}
	}
	
	/**
	 * Get the HumanPlayer used by the client
	 * @return The HumanPlayer 
	 * */
	public HumanPlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * Get the socket
	 * @return the socket used for the communication
	 */
	public Socket getSocket() {
		return this.socket;
	}
	
}
