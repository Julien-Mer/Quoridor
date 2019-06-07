package Client;

import java.awt.Color;
import java.io.BufferedWriter;

public class Client {

	/**
	 * Buffered Writer to send dada to the server
	 */
	private BufferedWriter out;
	
	/**
	 * Boolean if the client needs to display the game on a GUI
	 */
	private boolean terminal;

	/**
	 * Creates a player for the game 
	 * @param color the color of the player 
	 */
	public void createPlayer(Color color) {

	}

	/**
	 * Sends the data to the server
	 * @param data the data to send
	 */
	public void sendData(String data) {

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

	}
	
	/**
	 * Get the HumanPlayer used by the client
	 * @return The HumanPlayer 
	 * */
	public HumanPlayer getPlayer() {
		return null;
	}

}
