package Client.Controllers;

import Client.*;
import Client.Views.NewGame;

public class NewGameController {
	
	/**
	 * The client used by the Game
	 */
	private Client client;
	
	/**
	 * The NewGame view
	 */
	private NewGame view;

	/**
	 * Constructor of NewGameController
	 * @param client the client used by the game
	 */
	public NewGameController(Client client) {
		this.client = client;
		this.view = new NewGame();
	}
	
	/**
	 *  Method which return the current view
	 * @return the current view
	 */
	public NewGame getView() {
		return this.view;
	}

}
