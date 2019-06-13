package Client.Controllers;

import Client.*;
import Client.Views.Home;

public class HomeController {

	/**
	 * The client of game
	 */
	private Client client;
	
	/**
	 * The Home view
	 */
	private Home view;
	
	/**
	 * Constructor of HomeController
	 * @param client The client used by the game 
	 */
	public HomeController(Client client) {
		this.client = client;
		this.view = new Home();
	}

}
