package Client.Controllers;

import Client.*;
import Client.Views.Game;

public class GameController {
	
	/**
	 * The client of the game
	 */
	private Client client;
	
	/**
	 * The Game view
	 */
	private Game view;

	/**
	 * The constructor of GameController
	 * @param client the client used by the game
	 */
	public GameController(Client client) {
		this.client = client;
		//this.view = new Game();
	}
	
	/**
	 * Saves the game
	 */
	public void saveGame() {

	}

}
